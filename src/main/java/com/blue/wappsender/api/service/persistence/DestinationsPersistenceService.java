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
	
	//TODO: Se podria ver de pasar todas las sentencias a un builder de query

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
	public int addDestinationToCampaing(Integer campaingId, ArrayList<Destination> destinations) {

		StringBuilder queryBuilder = new StringBuilder(
				"INSERT INTO campaing_destinations (number, campaign_id) VALUES ");
		ArrayList<String> parameters = new ArrayList<String>();

		Iterator<Destination> iterator = destinations.iterator();
		
		while(iterator.hasNext()) {
			
			Destination destination = iterator.next();
			queryBuilder.append("(?,?)");
			parameters.add(destination.getNumber());
			parameters.add(campaingId.toString());
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
		String query = "SELECT * from campaing_destinations where campaign_id = ?";
		Object[] params = {campaignId};		
		
		List<Destination> result = this.jdbcTemplate.query(query,params,new BeanPropertyRowMapper<Destination>(Destination.class));
		return (ArrayList<Destination>) result;		
	}
	
	/**
	 * Retorna los destinos para un array de id de campañas
	 * @return
	 */
	public ArrayList<Destination> getDestinationsOfCampaigns(ArrayList<String> campaignsIds){
		StringBuilder query = new StringBuilder("SELECT * from campaing_destinations where campaign_id IN ");		
		
		ArrayList<String> params = new ArrayList<String>();	
		query.append("(");
		
		Iterator<String> campaignsIT = campaignsIds.iterator();
		while(campaignsIT.hasNext()) {
			
			query.append("?");
			String id = campaignsIT.next();
			params.add(id);
			
			if(campaignsIT.hasNext()) {
				query.append(",");
			}			
		}
		query.append(")");	
		
		List<Destination> result = this.jdbcTemplate.query(query.toString(),params.toArray(),new BeanPropertyRowMapper<Destination>(Destination.class));
		return (ArrayList<Destination>) result;		
	}

	/**
	 * Actualiza los destinos correspondientes a una campaña
	 * @param campaignId
	 * @param destinations
	 */
	public int updateDestinations(Integer campaignId, Destination destinations) {
		String query = "UPDATE campaing_destinations SET number = ? WHERE id = ? AND campaign_id = ?";
		Object[] params = {destinations.getNumber(), destinations.getId() , campaignId};
		log.info("Se ejecuta la query :[{}] con los valores:[{}]", query, params);		
		int result = this.jdbcTemplate.update(query,params);		
		log.info("Se actualizaron [{}] registros",result);
		return result;	
	}
	
	/**
	 * Elimina todos los destinatiarios de una campaña
	 * @returns
	 */
	public int deleteAllDestinations(Integer campaignId) {		
		String query = "DELETE from campaing_destinations WHERE campaign_id = ?";
		Object[] params = {campaignId};
		log.info("Se ejecuta la query :[{}] con los valores:[{}]", query, params);
		int result = this.jdbcTemplate.update(query,params);
		log.info("Se actualizaron [{}] registros",result);
		return result;			
	}

}
