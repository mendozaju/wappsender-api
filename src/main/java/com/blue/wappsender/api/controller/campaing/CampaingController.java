package com.blue.wappsender.api.controller.campaing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.wappsender.api.controller.campaing.request.CampaignCreateRequest;
import com.blue.wappsender.api.controller.campaing.response.CampaignResponseBuilder;
import com.blue.wappsender.api.controller.campaing.response.CampaignsResponse;
import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.model.campaing.Campaign;
import com.blue.wappsender.api.model.campaing.CampaignModelBuilder;
import com.blue.wappsender.api.model.campaing.Destination;
import com.blue.wappsender.api.service.persistence.CampaingPersistenceService;
import com.blue.wappsender.api.service.persistence.DestinationsPersistenceService;

@Controller
@CrossOrigin
@RequestMapping(path = "/api")
public class CampaingController {

	private static final Logger log = LoggerFactory.getLogger(CampaingController.class);

	@Autowired
	private CampaingPersistenceService campaingPresistence;

	@Autowired
	private DestinationsPersistenceService destinationPersistence;

	/**
	 * Atiende el pedido de creacion de una campaña
	 * 
	 * @param campaing
	 * @return
	 */

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/campaign")
	public ResponseEntity<BaseResponse> saveCampaign(@Valid @RequestBody CampaignCreateRequest request) {

		// TODO: agregar un mapeo para los errores de validacion salgan bien formateados
		// TODO: mapear los errores SQL

		Campaign campaign = CampaignModelBuilder.build(request);
		int campaignId = this.campaingPresistence.saveCampaing(campaign);
		
		if(!request.getDestinations().isEmpty()) {
			log.info("La campaña pose destinos, se procede a agregarlos");
			this.destinationPersistence.addDestinationToCampaing(campaignId, request.getDestinations());
		}		
		return CampaignResponseBuilder.buildCreateCampaign(campaignId);
	}

	/**
	 * Retorna las campañas que se encuentran guardadas
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = { "/campaign", "/campaign/{campaingId}" })
	public ResponseEntity<CampaignsResponse> getCampaigns(@PathVariable(required = false) String campaingId,
			@RequestParam(required = false, name = "options") String options) {

		ArrayList<String> splitedOptions = new ArrayList<String>();
		if(options != null) {
			splitedOptions = new ArrayList<String>(Arrays.asList(options.toLowerCase().split(",")));
		}
		
		ArrayList<Campaign> campaings = new ArrayList<Campaign>();
		ArrayList<Destination> destinations = new ArrayList<Destination>();

		// TODO: Hoy en dia se hace uso de un USR_ID generico, esto despues tiene que
		// salir de la autenticacion:
		String mockUserId = "1";

		if (splitedOptions.contains(Options.DESTINATIONS.description())) {
			if (campaingId != null) {
				log.info("Se atiende el pedido de obtencion de la campaña:[{}] con destinos", campaingId);
				campaings = this.campaingPresistence.getCampaigns(campaingId, mockUserId);
				destinations = this.destinationPersistence.getDestinationsOfCampaign(campaingId);
				return CampaignResponseBuilder.buildGetCampaingsWhitDestination(campaings, destinations);
			} else {
				log.info("Se atiene el pedido de obtencion de todas las campañas con destinos");
				campaings = this.campaingPresistence.getAllCampaigns(mockUserId);
				ArrayList<String> campaignsIds = (ArrayList<String>) campaings.stream()
						.map(campaing -> campaing.getId().toString()).collect(Collectors.toList());
				destinations = this.destinationPersistence.getDestinationsOfCampaigns(campaignsIds);
				return CampaignResponseBuilder.buildGetCampaingsWhitDestination(campaings, destinations);
			}
		} else {
			if (campaingId != null) {
				log.info("Se atiende el pedido de obtencion de la campaña:[{}] sin destinos", campaingId);
				campaings = this.campaingPresistence.getCampaigns(campaingId, mockUserId);
				return CampaignResponseBuilder.buildGetCampaings(campaings);
			} else {
				log.info("Se atiende el pedido de obtencion de todas las campañas sin destinos");
				campaings = this.campaingPresistence.getAllCampaigns(mockUserId);
				return CampaignResponseBuilder.buildGetCampaings(campaings);
			}
		}
	}
}
