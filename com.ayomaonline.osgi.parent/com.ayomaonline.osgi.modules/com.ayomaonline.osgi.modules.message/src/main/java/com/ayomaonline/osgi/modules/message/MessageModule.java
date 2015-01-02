package com.ayomaonline.osgi.modules.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import com.ayomaonline.osgi.main.api.MenuItem;
import com.ayomaonline.osgi.main.api.Module;
import com.ayomaonline.osgi.modules.message.servlet.Create;
import com.ayomaonline.osgi.modules.message.servlet.Inbox;
import com.ayomaonline.osgi.modules.message.servlet.Outbox;
import com.ayomaonline.osgi.modules.message.servlet.Sent;

public class MessageModule implements Module {
	public String getModuleId() {
		return "message";
	}

	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		menuItemList.add(new MenuItem("message.create", "create"));
		menuItemList.add(new MenuItem("message.inbox", "inbox"));
		menuItemList.add(new MenuItem("message.outbox", "outbox"));
		menuItemList.add(new MenuItem("message.sent", "sent"));

		return menuItemList;
	}

	public Map<String, Servlet> getServletBindings() {
		Map<String, Servlet> bindings = new HashMap<String, Servlet>();

		bindings.put("create", new Create());
		bindings.put("inbox", new Inbox());
		bindings.put("outbox", new Outbox());
		bindings.put("sent", new Sent());

		return bindings;
	}

	public Map<String, String> getResourceBindings() {
		Map<String, String> bindings = new HashMap<String, String>();

		return bindings;
	}
}
