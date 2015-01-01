package com.ayomaonline.osgi.main.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.ayomaonline.osgi.main.Activator;
import com.ayomaonline.osgi.main.api.Module;
import com.ayomaonline.osgi.main.registrar.MenuItemRegistrar;
import com.ayomaonline.osgi.main.util.Logger;

public class DefaultModuleTrackerCustomizer implements ServiceTrackerCustomizer {
	private final BundleContext context;

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
			MenuItemRegistrar.addMenuItem(service.getClass().getName(), service.getMenuItems());
		}
	}

	public class ModuleRemover extends Thread {
		private final Module service;

		public ModuleRemover(Module service) {
			this.service = service;
		}

		public void run() {
			MenuItemRegistrar.removeMenuItem(service.getClass().getName());
		}
	}
}