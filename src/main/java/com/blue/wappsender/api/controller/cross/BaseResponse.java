package com.blue.wappsender.api.controller.cross;

/**
 * Calse base de respuesta
 * @author jmendoza
 *
 */
public class BaseResponse {
	protected String code;
	protected String description;
	
	public BaseResponse() {};
	
	public BaseResponse(String code, String description) {
		this.code = code;
		this.description = description;
	};
	
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
	
}
