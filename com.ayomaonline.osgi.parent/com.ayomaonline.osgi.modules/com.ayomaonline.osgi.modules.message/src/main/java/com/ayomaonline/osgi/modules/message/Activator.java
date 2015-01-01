package com.ayomaonline.osgi.modules.message;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ayomaonline.osgi.main.api.Module;
import com.ayomaonline.osgi.modules.message.util.Logger;

public class Activator implements BundleActivator {
	private static Logger LOGGER = null;

	public void start(BundleContext context) throws Exception {
		LOGGER = new Logger(context);

		LOGGER.log(Logger.INFO, "===============================");
		LOGGER.log(Logger.INFO, "     STARTING USER MODULE");
		LOGGER.log(Logger.INFO, "===============================");

		context.registerService(Module.class.getName(), new MessageModule(), null);
	}

	public void stop(BundleContext context) throws Exception {
		LOGGER.log(Logger.INFO, "===============================");
		LOGGER.log(Logger.INFO, "     STOPPING USER MODULE");
		LOGGER.log(Logger.INFO, "===============================");

		LOGGER.close();
	}

	public static Logger getApplicationLogger() {
		return LOGGER;
	}
}
