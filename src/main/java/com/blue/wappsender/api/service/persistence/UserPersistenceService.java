package com.blue.wappsender.api.service.persistence;

import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.blue.wappsender.api.model.campaing.Campaign;
import com.blue.wappsender.api.model.user.User;

/**
 * Clase encargada de manejar la persistencia y consulta de los usuarios
 * 
 * @author jmendoza
 *
 */
@Repository
public class UserPersistenceService {

	private static final Logger LOG = LoggerFactory.getLogger(UserPersistenceService.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public UserPersistenceService(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 
	 * @return
	 */
	public Optional<User> find(String username) {
		// TODO: Las pasworsd deberian estar encriptadas, tanto para la creacion como
		// para la consulta desencriptarlas

		
		LOG.info("Buscando el usuario :[{}]", username);

		String query = "SELECT * FROM users WHERE username = ?";
		Object[] parameters = { username };
		LOG.info("Query de obtencion de usuarios:[{}] - parametros:[{}]", query, parameters);
		List<User> user = this.jdbcTemplate.query(query, parameters, new BeanPropertyRowMapper<User>(User.class));
		
		Optional<User> result = Optional.empty();
		if(user.iterator().hasNext()) {
			result = Optional.of(user.iterator().next());
			LOG.info("Se retorna el usuario:[{}]",result.get().getUsername());
		}else {
			LOG.info("No se encontraron usuarios con ese username");
		}		
		return result;
	}

}
