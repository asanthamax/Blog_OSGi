package com.ayomaonline.osgi.modules.user;

import java.util.ArrayList;
import java.util.List;

import com.ayomaonline.osgi.main.api.MenuItem;
import com.ayomaonline.osgi.main.api.Module;

public class UserModule implements Module {

	public List<MenuItem> getMenuItems() {
		List<MenuItem> menuItemList = new ArrayList<MenuItem>();

		menuItemList.add(new MenuItem("user.add", "/user/add"));
		menuItemList.add(new MenuItem("user.manage", "/user/manage"));

		return menuItemList;
	}

}
