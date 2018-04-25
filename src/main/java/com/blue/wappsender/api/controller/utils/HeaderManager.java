package com.blue.wappsender.api.controller.utils;

import org.springframework.util.LinkedMultiValueMap;

import com.blue.wappsender.api.model.user.AccessKey;

/**
 * Clase responsabel de manejar las cokies
 * @author jmendoza
 *
 */
public class HeaderManager {
	
	public static final String SET_COOKIE_HEADER = "Set-Cookie";
	public static final String AUTH_HEADER_NAME = "whapi-auth";
	
	/**
	 * Retorna los headers necesarios para la authenticacion
	 * @param accesKey
	 * @return
	 */
	public static LinkedMultiValueMap<String, String> getAuthentcationHeader(AccessKey accesKey) {
		LinkedMultiValueMap<String,String> headers = new LinkedMultiValueMap<>();		
		headers.add(SET_COOKIE_HEADER, AUTH_HEADER_NAME + "=" + accesKey.getValue());		
		return headers;
	}

}
