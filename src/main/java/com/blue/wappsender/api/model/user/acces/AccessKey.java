package com.blue.wappsender.api.model.user.acces;

import java.time.LocalDateTime;

/**
 * Objecto que representa la calve de acceso de un usuario a la api
 * 
 * @author jmendoza
 *
 */
public class AccessKey {

	private String value;
	private LocalDateTime expiration;

	public AccessKey(String value) {
		this.value = value;
		this.expiration = ExpirationManager.expiration();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

}
