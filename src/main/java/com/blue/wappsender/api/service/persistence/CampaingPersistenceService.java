package com.blue.wappsender.api.service.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.blue.wappsender.api.model.campaing.Campaign;

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
	 * @return
	 */
	public int saveCampaing(Campaign campaing) {
 		String query = "INSERT INTO campaings (text, description, activation_date, user_id) VALUES (?,?,?,?)";
		log.info("Se ejecuta la query:[{}] con los valores: [{}] - [{}] - [{}]", query, campaing.getText(),
				campaing.getDescription(), campaing.getActivationDate());
		
		//TODO: Ver que puede tirar un Runtime, agregar manejador general.
		//TODO: Hay que colocar el userId que corresponde. Cuando se defina la autenticacion
		int result = this.jdbcTemplate.update(query, campaing.getText(), campaing.getDescription(),
				campaing.getActivationDate(),1);
		
		log.info("Se actualizaron [{}] registros",result);
		return result;

	}

	/**
	 * Retorna todas las campañas para un id de campaña determinado
	 * @param userId
	 * @return
	 */
	public ArrayList<Campaign> getCampaigns(String campaignId, String userId){
		
		String query = "SELECT * FROM campaings WHERE id = ? AND user_id = ?";		
		Object[] parameters = {campaignId, userId};
		log.info("Se ejecuta la query:[{}] con los valores: [{}] - [{}] - [{}]", query, campaignId);
		
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

}
