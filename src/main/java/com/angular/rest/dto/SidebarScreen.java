package com.angular.rest.dto;

import org.apache.commons.lang3.StringUtils;

public enum SidebarScreen {

	ACCOUNT_PROFILE("ACOUNT PROFILE"), ADMIN("ADMIN"), ACCOUNT_SETTING("ACCOUNT SETTING");
	
	private String name;
	
	private SidebarScreen(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static SidebarScreen fromValue(String value) {
		for (SidebarScreen screen : SidebarScreen.values()) {
			if (StringUtils.equalsAnyIgnoreCase(value, screen.name)) {
				return screen;
			}
		}
		return null;
	}
	
}
