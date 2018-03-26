package com.blue.wappsender.api.controller.campaing.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.model.campaing.Campaign;
import com.blue.wappsender.api.model.campaing.Destination;

import antlr.StringUtils;

public class CampaignResponseBuilder {
	
	/**
	 * Crea una respuesta para la creacion de la campaña
	 * @param createdCampaigns
	 * @return
	 */
	public static ResponseEntity<BaseResponse> buildCreateCampaign(int createdCampaigns) {
		String code = "";
		String description = "";
		if(createdCampaigns > 0) {
			code = "OK";
			description = "Se creo la campaña correctamente";
		}else {
			code = "NOT_CREATED";
			description = "No se pudo crear la campaña";
		}		
		return ResponseEntity.ok(new BaseResponse(code, description));
	}
	
	/**
	 * Crea una repuesta para la obtencion de todas las campañas
	 * @param result
	 * @return
	 */
	public static ResponseEntity<CampaignsResponse> buildGetCampaings( ArrayList<Campaign> result ) {
		CampaignsResponse response = new CampaignsResponse();		
		result.stream().forEach(campaign -> {
			response.getCampaigns().add(campaign);			
		});		
		response.setQuantity(response.getCampaigns().size());		
		response.setCode("OK");
		response.setDescription("Se obtienen correctamente las campañas");		
		return ResponseEntity.ok(response);		
	}

	/**
	 * Crea la respuesta para una campaña con sus destinos
	 * @param campaings
	 * @param destinations
	 * @return
	 */
	public static ResponseEntity<CampaignsResponse> buildGetCampaingsWhitDestination(ArrayList<Campaign> campaings,
			ArrayList<Destination> destinations) {
		
		CampaignsResponse response = new CampaignsResponse();
		
		ArrayList<Campaign> completeCampaigns = new ArrayList<Campaign>();
		
		Iterator<Campaign> campaignsIT = campaings.iterator();
		while(campaignsIT.hasNext()) {
			Campaign aCampaign = campaignsIT.next();
			ArrayList<Destination> aDestinations = (ArrayList<Destination>) destinations.stream()
					.filter(destination -> destination.getCampaignId().equals(aCampaign.getId())).collect(Collectors.toList());
			aCampaign.setDestinations(aDestinations);
			completeCampaigns.add(aCampaign);
		}
		
		response.setCampaigns(completeCampaigns);
		response.setQuantity(completeCampaigns.size());
		response.setCode("OK");
		response.setDescription("Se obtienen las campañas con los destinos");
		
		return ResponseEntity.ok(response);		
	}

}
