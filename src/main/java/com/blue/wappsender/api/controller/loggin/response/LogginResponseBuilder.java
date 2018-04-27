package com.blue.wappsender.api.controller.loggin.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.controller.utils.HeaderManager;
import com.blue.wappsender.api.model.user.acces.AccessKey;

/**
 * Clase responsable de constriuir la respuesta del controlador de loggin
 * 
 * @author jmendoza
 *
 */
public class LogginResponseBuilder {

	/**
	 * Retorna una respuesta para un password invalido.
	 * 
	 * @return
	 */
	public static ResponseEntity<BaseResponse> invalidCredential() {
		BaseResponse response = new BaseResponse("INVALID_CREDENTIAL", "Usuario o contraseña invalida");
		return new ResponseEntity<BaseResponse>(response, HttpStatus.FORBIDDEN);
	}

	/**
	 * Retorna una respuesta ok para el loggeo de usario
	 * 
	 * @return
	 */
	public static ResponseEntity<BaseResponse> loggedOk(AccessKey accesKey) {
		BaseResponse response = new BaseResponse("LOGIN_OK", "Se loguea el usuario exitosamente");
		return new ResponseEntity<BaseResponse>(response, HeaderManager.getAuthentcationHeader(accesKey),
				HttpStatus.ACCEPTED);
	}
}
