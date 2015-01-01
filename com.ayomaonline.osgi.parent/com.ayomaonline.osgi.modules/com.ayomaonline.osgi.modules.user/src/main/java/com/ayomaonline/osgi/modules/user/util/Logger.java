package com.ayomaonline.osgi.modules.user.util;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Logger {
	private final BundleContext context;
	private ServiceTracker tracker;

	public static final int ERROR = 1;
	public static final int WARNING = 2;
	public static final int INFO = 3;
	public static final int DEBUG = 4;
	

	public Logger(BundleContext context) {
		this.context = context;
		this.tracker = init(context);
	}

	private ServiceTracker init(BundleContext context) {
		ServiceTracker tracker = null;
		try {
			tracker = new ServiceTracker(context, org.osgi.service.log.LogService.class.getName(), null);
			tracker.open();
		} catch (NoClassDefFoundError error) {
		}
		return tracker;
	}

	public synchronized void close() {
		if (tracker != null) {
			tracker.close();
		}
	}

	public synchronized void log(int level, String msg) {
		boolean logged = false;
		if (tracker == null) {
			tracker = init(context);
		}
		if (tracker != null) {
			org.osgi.service.log.LogService logger = (org.osgi.service.log.LogService) tracker.getService();
			if (logger != null) {
				logger.log(level, msg);
				logged = true;
			}
		}
		if (!logged) {
			System.out.println("[" + level + "] " + msg);
		}
	}
}