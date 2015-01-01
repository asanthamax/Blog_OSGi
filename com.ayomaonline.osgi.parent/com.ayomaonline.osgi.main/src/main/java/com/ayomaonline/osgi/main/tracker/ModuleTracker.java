package com.ayomaonline.osgi.main.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import com.ayomaonline.osgi.main.api.Module;

public class ModuleTracker {
	private ServiceTracker tracker;

	public ModuleTracker(BundleContext context) {
		this.tracker = init(context);
	}

	private ServiceTracker init(BundleContext context) {
		ServiceTracker tracker = null;
		try {
			tracker = new ServiceTracker(context, Module.class.getName(), new DefaultModuleTrackerCustomizer(context));
			tracker.open();
		} catch (NoClassDefFoundError error) {
			error.printStackTrace();
		}
		return tracker;
	}

	public synchronized void close() {
		if (tracker != null) {
			tracker.close();
		}
	}
}
