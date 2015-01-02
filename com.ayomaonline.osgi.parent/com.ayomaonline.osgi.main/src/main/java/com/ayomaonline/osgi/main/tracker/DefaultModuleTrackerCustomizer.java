package com.ayomaonline.osgi.main.tracker;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.ops4j.pax.web.extender.whiteboard.ResourceMapping;
import org.ops4j.pax.web.extender.whiteboard.runtime.DefaultResourceMapping;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.ayomaonline.osgi.main.Activator;
import com.ayomaonline.osgi.main.api.Module;
import com.ayomaonline.osgi.main.registrar.MenuItemRegistrar;
import com.ayomaonline.osgi.main.util.Logger;

public class DefaultModuleTrackerCustomizer implements ServiceTrackerCustomizer {
	private final BundleContext context;
	private final Map<String, List<ServiceRegistration>> httpServiceRegistrations = new HashMap<String, List<ServiceRegistration>>();

	public DefaultModuleTrackerCustomizer(BundleContext context) {
		this.context = context;
	}

	public Object addingService(ServiceReference reference) {
		Module service = (Module) context.getService(reference);
		Activator.getApplicationLogger().log(Logger.INFO, "ADDING MODULE    : " + service);

		new ModuleAdder(service).start();
		return service;
	}

	public void modifiedService(ServiceReference reference, Object service) {
		Activator.getApplicationLogger().log(Logger.INFO, "MODIFYING MODULE : " + service);
		removedService(reference, service);
		addingService(reference);
	}

	public void removedService(ServiceReference reference, Object service) {
		Activator.getApplicationLogger().log(Logger.INFO, "REMOVING MODULE  : " + service);

		new ModuleRemover((Module) service).start();
	}

	public class ModuleAdder extends Thread {
		private final Module service;

		public ModuleAdder(Module service) {
			this.service = service;
		}

		public void run() {
			MenuItemRegistrar.addMenuItem(service.getModuleId(), service.getMenuItems());
			
			List<ServiceRegistration> registrations = new ArrayList<ServiceRegistration>();
			Map<String, String> resourceBindings = service.getResourceBindings();
			for (String path : resourceBindings.keySet()) {
				String adjustedPath = "/" + service.getModuleId() + "/" + path;
				Activator.getApplicationLogger().log(LogService.LOG_DEBUG, "Registering : " + adjustedPath + " / " + resourceBindings.get(path));

				DefaultResourceMapping resourceMapping = new DefaultResourceMapping();
				resourceMapping.setAlias(adjustedPath);
				resourceMapping.setPath(adjustedPath);

				ServiceRegistration registration = context.registerService(ResourceMapping.class.getName(), resourceMapping, null);
				registrations.add(registration);
			}

			Map<String, Servlet> servletBindings = service.getServletBindings();
			for (String path : servletBindings.keySet()) {
				String adjustedPath = "/" + service.getModuleId() + "/" + path;
				Activator.getApplicationLogger().log(LogService.LOG_DEBUG, "Registering : " + adjustedPath + " / " + servletBindings.get(path));

				Dictionary<String, String> props = new Hashtable<String, String>();
				props.put("alias", adjustedPath);

				ServiceRegistration registration = context.registerService(Servlet.class.getName(), servletBindings.get(path), props);
				registrations.add(registration);
			}

			httpServiceRegistrations.put(service.getClass().getName(), registrations);
		}
	}

	public class ModuleRemover extends Thread {
		private final Module service;

		public ModuleRemover(Module service) {
			this.service = service;
		}

		public void run() {
			MenuItemRegistrar.removeMenuItem(service.getModuleId());
			
			for (ServiceRegistration registration : httpServiceRegistrations.get(service.getClass().getName())) {
				Activator.getApplicationLogger().log(Logger.DEBUG, "UnRegistering : " + registration);
				registration.unregister();
			}
		}
	}
}