package com.blue.wappsender.api.service.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.blue.wappsender.api.model.campaing.Campaign;
import com.blue.wappsender.core.formats.DateFormatter;

/**
 * Servicio encargado de persistir y consultar las campañas
 * 
 * @author jmendoza
 */

@Repository
public class CampaingPersistenceService {
	private static final Logger log = LoggerFactory.getLogger(CampaingPersistenceService.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CampaingPersistenceService(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Guarda una campaña
	 * 
	 * @param campaing
	 * @return Retorna el id de la campaña insertada
	 */
	public int saveCampaing(Campaign campaing) {
 		String query = "INSERT INTO campaings (text, description, activation_date, user_id, status) VALUES (?,?,?,?, ?)";
		log.info("Se ejecuta la query:[{}] con los valores: [{}] - [{}] - [{}]", query, campaing.getText(),
				campaing.getDescription(), campaing.getActivationDate());
		
		//TODO: Ver que puede tirar un Runtime, agregar manejador general.
		//TODO: Hay que colocar el userId que corresponde. Cuando se defina la autenticacion
		/*
		int result = this.jdbcTemplate.update(query, campaing.getText(), campaing.getDescription(),
				campaing.getActivationDate(),1, CampaingStatus.PENDING.toString());
		*/
		KeyHolder holder = new GeneratedKeyHolder();
		Object[] parameters = {campaing.getText(), campaing.getDescription(),campaing.getActivationDate(),1,CampaingStatus.PENDING.toString()};
		int result = this.jdbcTemplate.update(this.updateAndReturn(query, parameters),holder);
		int campaignId = holder.getKey().intValue();
		
		log.info("Se actualizaron [{}] registros - id generado:[{}]",result, campaignId);
		return campaignId;

	}

	/**
	 * Retorna todas las campañas para un id de campaña determinado
	 * @param userId
	 * @return
	 */
	public ArrayList<Campaign> getCampaigns(String campaignId, String userId){
		
		String query = "SELECT * FROM campaings WHERE id = ? AND user_id = ?";		
		Object[] parameters = {campaignId, userId};
		log.info("Se ejecuta la query:[{}] con los valores: [{}] - [{}] - [{}]", query, parameters);
		
		List<Campaign> result = this.jdbcTemplate.query(query, parameters, new BeanPropertyRowMapper<Campaign>(Campaign.class));
		return (ArrayList<Campaign>) result;
		
	}	
	
	/**
	 * Retorna todas las campañas
	 * @return
	 */
	public ArrayList<Campaign> getAllCampaigns(){
		
		String query = "SELECT * FROM campaings";		
		log.info("Se ejecuta la query:[{}]", query);
		
		List<Campaign> result = this.jdbcTemplate.query(query, new BeanPropertyRowMapper<Campaign>(Campaign.class));
		return (ArrayList<Campaign>) result;
		
	}
	
	/***
	 * Retorna todas las campañas para un usuario particular.
	 * @param userId
	 * @return
	 */
	public ArrayList<Campaign> getAllCampaigns(String userId){
		
		String query = "SELECT * FROM campaings WHERE user_id = ?";	
		Object[] parameters = {userId};
		log.info("Se ejecuta la query:[{}] con los parametros:[{}]", query,parameters);
		
		List<Campaign> result = this.jdbcTemplate.query(query,parameters, new BeanPropertyRowMapper<Campaign>(Campaign.class));
		return (ArrayList<Campaign>) result;
		
	}
	
	
	private PreparedStatementCreator updateAndReturn(String query, Object[] parameters) {
		return new PreparedStatementCreator() {
			/*
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getName());
				ps.setString(2, user.getAddress());
				ps.setString(3, user.getEmail());
				return ps;
			}
			*/

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				
				for (int i = 1; i <= parameters.length; i++) {
					Object aParameter = parameters[i-1];
					
					if(aParameter.getClass().equals(LocalDateTime.class)) {
						ps.setString(i, ((LocalDateTime) aParameter).format( DateTimeFormatter.ofPattern(DateFormatter.internalPattern())));
					}else {
						ps.setString(i, aParameter.toString());
					}
					
				}
				
				return ps;
			}
		};
	}
	
	
	

}
