package com.ayomaonline.osgi.modules.message;

import java.util.ArrayList;
import java.util.List;

import com.ayomaonline.osgi.main.api.MenuItem;
import com.ayomaonline.osgi.main.api.Module;

public class MessageModule implements Module {

	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		menuItemList.add(new MenuItem("message.create", "/message/create"));
		menuItemList.add(new MenuItem("message.inbox", "/message/inbox"));
		menuItemList.add(new MenuItem("message.outbox", "/message/outbox"));
		menuItemList.add(new MenuItem("message.sent", "/message/sent"));

		return menuItemList;
	}
}
