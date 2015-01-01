package com.ayomaonline.osgi.main;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ayomaonline.osgi.main.tracker.ModuleTracker;
import com.ayomaonline.osgi.main.util.Logger;

public class Activator implements BundleActivator {

	private static Logger LOGGER = null;
	private static ModuleTracker MODULE_TRACKER = null;

	public void start(final BundleContext context) throws Exception {
		LOGGER = new Logger(context);

		LOGGER.log(Logger.INFO, "==============================");
		LOGGER.log(Logger.INFO, "  STARTING AYOMAONLINE BASE   ");
		LOGGER.log(Logger.INFO, "==============================");

		new Thread() {
			@Override
			public void run() {
				MODULE_TRACKER = new ModuleTracker(context);
			}
		}.start();

	}

	public void stop(BundleContext context) throws Exception {
		LOGGER.log(Logger.INFO, "==============================");
		LOGGER.log(Logger.INFO, "  STOPPING AYOMAONLINE BASE   ");
		LOGGER.log(Logger.INFO, "==============================");

		MODULE_TRACKER.close();
		LOGGER.close();
	}

	public static Logger getApplicationLogger() {
		return LOGGER;
	}
}