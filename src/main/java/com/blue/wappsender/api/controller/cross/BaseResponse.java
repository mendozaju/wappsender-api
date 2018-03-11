package com.blue.wappsender.api.controller.cross;

/**
 * Calse base de respuesta
 * @author jmendoza
 *
 */
public class BaseResponse {
	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
