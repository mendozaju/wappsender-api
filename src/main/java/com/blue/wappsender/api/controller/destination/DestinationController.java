package com.blue.wappsender.api.controller.destination;

import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.controller.destination.request.AddDestinationRequest;
import com.blue.wappsender.api.controller.destination.response.DestinationResponseBuilder;
import com.blue.wappsender.api.controller.destination.response.DestinationsResponse;
import com.blue.wappsender.api.model.campaing.Destination;
import com.blue.wappsender.api.service.persistence.DestinationsPersistenceService;

@Controller
@CrossOrigin
@RequestMapping(path = "/api/campaing")
public class DestinationController {
	
	private static final Logger log = LoggerFactory.getLogger(DestinationController.class);
	
	@Autowired
	private DestinationsPersistenceService destinationPersistenceService;

	/**
	 * Agrega destinos de envio a una campaña
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(path="/{campaingId}/destinations", method= RequestMethod.PATCH)
	public ResponseEntity<BaseResponse> addDestinations(@Valid @RequestBody AddDestinationRequest request, @PathVariable(required=true) String campaingId) {
		
		log.info("Se atiende el pedido de adicion de destinos para la campaña:[{}]",campaingId);		
		this.destinationPersistenceService.addDestinationToCampaing(campaingId, request.getDestinations());

		BaseResponse response = new BaseResponse();
		response.setCode("OK");
		response.setDescription("Se agrean correctamente los destinos a la campaña");
			
		return ResponseEntity.ok(new BaseResponse());
	}
	
	/**
	 * Obtiene los detinos para un id de capaña
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(path="/{campaingId}/destinations", method=RequestMethod.GET)
	public ResponseEntity<DestinationsResponse> getDestinations(@PathVariable(required=true) String campaingId){
		log.info("Se atiende el pedido de obtencio de itinerairos para la campaña:[{}]", campaingId);
		ArrayList<Destination> result = this.destinationPersistenceService.getDestinationsOfCampaign(campaingId);
		
		return DestinationResponseBuilder.build(result, campaingId);
		
	}
}
