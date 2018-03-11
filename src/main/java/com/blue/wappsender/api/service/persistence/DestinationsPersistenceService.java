package com.blue.wappsender.api.service.persistence;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.blue.wappsender.api.model.campaing.Destination;

/**
 * Clase encargada de manejar la peristencia de los detinos de una campaña
 * 
 * @author jmendoza
 *
 */
@Repository
public class DestinationsPersistenceService {

	private static final Logger log = LoggerFactory.getLogger(DestinationsPersistenceService.class);

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public DestinationsPersistenceService(@Qualifier(value = "dataSource") DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Agrega destinos a una campaña
	 * @param campaingId
	 * @param destinations
	 * @return
	 */
	public int addDestinationToCampaing(String campaingId, ArrayList<String> destinations) {

		StringBuilder queryBuilder = new StringBuilder(
				"INSERT INTO campaing_destinations (number, campaing_id) VALUES ");
		ArrayList<String> parameters = new ArrayList<String>();

		Iterator<String> iterator = destinations.iterator();
		
		while(iterator.hasNext()) {
			
			String destination = iterator.next();
			queryBuilder.append("(?,?)");
			parameters.add(destination);
			parameters.add(campaingId);
			if (parameters.size()/2 < destinations.size()) {
				queryBuilder.append(",");
			}			
		}		

		log.info("Se ejecuta la query:[{}] con los valores: [{}]", queryBuilder.toString(), parameters);
		int result = this.jdbcTemplate.update(queryBuilder.toString(),parameters.toArray());
		log.info("Se insertaron:[{}] destinos", result);
		return result;
	}
	
	/**
	 * Retorna todos los detinos para una campaña
	 * @param campaignId
	 * @return
	 */
	public ArrayList<Destination> getDestinationsOfCampaign(String campaignId){
		String query = "SELECT * from campaing_destinations where campaing_id = ?";
		Object[] params = {campaignId};		
		
		List<Destination> result = this.jdbcTemplate.query(query,params,new BeanPropertyRowMapper<Destination>(Destination.class));
		return (ArrayList<Destination>) result;		
	}

}
