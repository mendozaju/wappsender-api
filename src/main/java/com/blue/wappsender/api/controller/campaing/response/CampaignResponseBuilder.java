package com.blue.wappsender.api.controller.campaing.response;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.model.campaing.Campaign;

public class CampaignResponseBuilder {
	
	/**
	 * Crea una respuesta para la creacion de la campaña
	 * @param createdCampaigns
	 * @return
	 */
	public static ResponseEntity<BaseResponse> buildCreateCampaign(int createdCampaigns) {
		BaseResponse response = new BaseResponse();
		if(createdCampaigns > 0) {
			response.setCode("OK");
			response.setDescription("Se creo la campaña correctamente");
		}else {
			response.setCode("NOT_CREATED");
			response.setDescription("No se pudo crear la campaña");
		}
		return ResponseEntity.ok(response);
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
		
		new ResponseEntity<CampaignsResponse>(HttpStatus.ACCEPTED);
		return ResponseEntity.ok(response);		
	}

}
