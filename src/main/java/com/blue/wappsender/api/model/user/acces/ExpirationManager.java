package com.blue.wappsender.api.model.user.acces;

import java.time.LocalDateTime;

/***
 * Clase responsable de descidir el tiempo de expiracion de una clave de acceso
 * @author jmendoza
 *
 */
public class ExpirationManager {
	
	private static final int SESSION_TIME = 8;

	/**
	 * Retorna el timepo de expiracion de una clave de acceso
	 * @return
	 */
	public static LocalDateTime expiration() {
		return LocalDateTime.now().plusHours(SESSION_TIME);
	}

}
