package com.ayomaonline.osgi.modules.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import com.ayomaonline.osgi.main.api.MenuItem;
import com.ayomaonline.osgi.main.api.Module;
import com.ayomaonline.osgi.modules.user.servlet.Add;
import com.ayomaonline.osgi.modules.user.servlet.Manage;

public class UserModule implements Module {
	public String getModuleId() {
		return "user";
	}
	
	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		menuItemList.add(new MenuItem("user.add", "add"));
		menuItemList.add(new MenuItem("user.manage", "manage"));

		return menuItemList;
	}

	public Map<String, Servlet> getServletBindings() {
		Map<String, Servlet> bindings = new HashMap<String, Servlet>();

		bindings.put("add", new Add());
		bindings.put("manage", new Manage());

		return bindings;
	}

	public Map<String, String> getResourceBindings() {
		Map<String, String> bindings = new HashMap<String, String>();

		return bindings;
	}
}
