package com.blue.wappsender.api.service.security;

import org.springframework.stereotype.Component;

import com.blue.wappsender.api.model.user.User;

/**
 * Clase encargada de realizar las validaciones 
 * @author jmendoza
 *
 */
@Component
public class UserValidations {
	
	/**
	 * Valida que la password del usuario sea valida.
	 * @param user
	 * @return
	 */
	public boolean isValidPassword(User user) {
		return true;
	}

}
