package com.ayomaonline.osgi.main.api;

public class MenuItem {
	private String key;
	private String path;

	public MenuItem(String key, String path) {
		super();
		this.key = key;
		this.path = path;
	}

	public MenuItem(String key) {
		super();
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
