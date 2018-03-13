package com.blue.wappsender.api.controller.campaing;

/**
 * Opciones para el controller de campañas
 * @author jmendoza
 *
 */
enum Options {
	
	DESTINATIONS("destinations");
	
	private String description;	
	
	private Options(String description) {
		this.description = description;
	}
	
	String description() {
		return this.description;
	}
}
