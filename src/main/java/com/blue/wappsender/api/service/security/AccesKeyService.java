package com.blue.wappsender.api.service.security;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.blue.wappsender.api.model.user.acces.AccessKey;

/**
 * Servicio encargado de las tareas de authenticacion
 * @author jmendoza
 *
 */
@Component
public class AccesKeyService {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccesKeyService.class);

	/**
	 * Genera una calve de acceso para el usuario.
	 * @return
	 */
	public AccessKey generateAccesKey() {
		AccessKey key = new AccessKey(UUID.randomUUID().toString());	
		LOG.info("Se construye la acces-key:[{}]",key.getValue());
		return key;
	}
	
}
