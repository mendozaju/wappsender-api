package com.blue.wappsender.api.controller.loggin.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Clase que representa el request para loguear al usuario
 * 
 * @author jmendoza
 *
 */
public class LogginRequest {
	
	@NotEmpty
	private String user;
	@NotEmpty
	private String password;

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
