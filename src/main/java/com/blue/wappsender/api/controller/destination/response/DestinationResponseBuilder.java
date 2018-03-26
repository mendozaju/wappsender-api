package com.blue.wappsender.api.controller.destination.response;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.http.ResponseEntity;

import com.blue.wappsender.api.model.campaing.Destination;

/**
 * Clase encargada de construir las respuestas de destinos
 * @author jmendoza
 *
 */
public class DestinationResponseBuilder {

	public static ResponseEntity<DestinationsResponse> build(ArrayList<Destination> destinations, String campaingId) {
		DestinationsResponse response = new DestinationsResponse();
		
		Iterator<Destination> it = destinations.iterator();
		while(it.hasNext()) {
			Destination number = it.next();
			response.getDestinations().add(number);
		}
		
		response.setQuantity(destinations.size());
		response.setCampaingId(Integer.parseInt(campaingId));
		
		return ResponseEntity.ok(response);
	}
	
}
