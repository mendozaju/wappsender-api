package com.blue.wappsender.api.service.persistence;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.blue.wappsender.api.model.user.User;
import com.blue.wappsender.api.model.user.acces.AccessKey;
import com.blue.wappsender.api.service.persistence.common.Tables;

/**
 * Clase responsabe de manejar la persistencia de las sessiones de los usuarios
 * @author jmendoza
 *
 */
@Component
public class SessionPersistenceService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SessionPersistenceService.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SessionPersistenceService(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Actualiza la clave de acceso para un usuario
	 * @param user
	 * @param accesKey
	 */
	public int updateAccesKey(User user, AccessKey accesKey) {	
		LOG.debug("Se actualiza el usurio :[{}] con la accesKey:[{}]",user.getUsername(), accesKey.getValue());
		String query = String.format("UPDATE %s SET session_id = ?, expiration = ?  where username = ?",Tables.SESSIONS);
		Object[] parameters = {accesKey.getValue(),accesKey.getExpiration(), user.getUsername()};
		int quatntity = this.jdbcTemplate.update(query,parameters);
		LOG.info("Se actualizaron:[{}] registros", quatntity);
		return quatntity;
		
	}
	
	/**
	 * Inserta la clave de acceso de un usuario
	 * @return
	 */
	public int insertAccesKey(User user, AccessKey accesKey) {
		LOG.debug("Se actualiza el usurio :[{}] con la accesKey:[{}]",user.getUsername(), accesKey.getValue());
		String query = String.format("INSERT INTO %s (username, session_id, expiration) VALUES (?,?,?)",Tables.SESSIONS);
		Object[] parameters = {user.getUsername(),accesKey.getValue(),accesKey.getExpiration()};
		int quatntity = this.jdbcTemplate.update(query,parameters);
		LOG.info("Se insertaron:[{}] registros", quatntity);
		return quatntity;		
	
	}
	
	/**
	 * Elimina el accessKey de un usuario
	 * @param user
	 * @return
	 */
	public int deleteAcessKey(User user) {
		LOG.debug("Se elimina la access key para el usurio :[{}]",user.getUsername());
		String query = String.format("DELETE FROM %s WHERE username = ?",Tables.SESSIONS);
		Object[] parameters = {user.getUsername()};
		int quatntity = this.jdbcTemplate.update(query,parameters);
		LOG.info("Se Eliminaron:[{}] registros", quatntity);
		return quatntity;	
	}
	
}
