package com.ayomaonline.osgi.main.api;

import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

public interface Module {
	public String getModuleId();

	public List<MenuItem> getMenuItems();

	public Map<String, Servlet> getServletBindings();

	public Map<String, String> getResourceBindings();
}
