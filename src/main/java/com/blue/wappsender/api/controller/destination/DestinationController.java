package com.blue.wappsender.api.controller.destination;

import java.util.ArrayList;
import java.util.Iterator;

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
import com.blue.wappsender.api.controller.destination.request.UpdateDestinationRequest;
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
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(path = "/{campaingId}/destinations", method = RequestMethod.PUT)
	public ResponseEntity<BaseResponse> addDestinations(@Valid @RequestBody AddDestinationRequest request,
			@PathVariable(required = true) String campaingId) {

		log.info("Se atiende el pedido de adicion de destinos para la campaña:[{}]", campaingId);
		this.destinationPersistenceService.addDestinationToCampaing(Integer.parseInt(campaingId),
				request.getDestinations());

		return ResponseEntity.ok(new BaseResponse("OK", "Se agrean correctamente los destinos a la campaña"));
	}

	/**
	 * Obtiene los detinos para un id de capaña
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(path = "/{campaingId}/destinations", method = RequestMethod.GET)
	public ResponseEntity<DestinationsResponse> getDestinations(@PathVariable(required = true) String campaingId) {
		log.info("Se atiende el pedido de obtencio de itinerairos para la campaña:[{}]", campaingId);
		ArrayList<Destination> results = this.destinationPersistenceService.getDestinationsOfCampaign(campaingId);

		return DestinationResponseBuilder.build(results, campaingId);

	}

	/**
	 * Actualiza los destinos de una campaña
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/{campaignId}/destinations", method = RequestMethod.PATCH)
	public ResponseEntity<BaseResponse> updateDestination(@RequestBody UpdateDestinationRequest request, @PathVariable(required=true) String campaignId ) {
		log.info("Se atiende pediod de actualizacion de destinos. Se actualizaran:[{}] destinos",
				request.getDestinations().size());

		Iterator<Destination> it = request.getDestinations().iterator();
		Integer quantity = 0;
		while (it.hasNext()) {
			Destination aDestination = it.next();
			log.info("Actualizando el destino con id [{}]", aDestination.getId());
			Integer updated = this.destinationPersistenceService.updateDestinations(Integer.parseInt(campaignId),
					aDestination);
			quantity = quantity + updated;
		}
		return ResponseEntity.ok(new BaseResponse("OK", String.format("Se actualizarion %d registros", quantity)));
	}
	
	/**
	 * Sete los detinos de una campaña. Si la campaña ya posee destinos se
	 * sobreescibiran quedando solo estos.
	 * 
	 * @return
	 */
	@RequestMapping(path = "/{campaignId}/destinations", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> setDestinations(@RequestBody AddDestinationRequest request, @PathVariable String campaignId ) {

		int deleted = this.destinationPersistenceService.deleteAllDestinations(Integer.parseInt(campaignId));
		log.info("Se eliminaron [{}] destinos", deleted);
		int inserted = this.destinationPersistenceService.addDestinationToCampaing(Integer.parseInt(campaignId),
				request.getDestinations());
		log.info("Se insertaron [{}] registros", inserted);
		return ResponseEntity.ok(new BaseResponse("OK",String.format("Se insertarion %s registros", inserted)));
	}
	
	
	/**
	 * Elimina todos los destinos de una campaña
	 * 
	 * @return
	 */
	@RequestMapping(path = "/{campaignId}/destinations", method = RequestMethod.DELETE)
	public ResponseEntity<BaseResponse> deleteAllDestination(@PathVariable(required = true) String campaignId) {
		log.info("Se atiende el pedido de eliminacion de destinos de la campaña:[{}]", campaignId);

		int deleted = this.destinationPersistenceService.deleteAllDestinations(Integer.parseInt(campaignId));
		log.info("Se eliminarion [{}] detinos", deleted);

		return ResponseEntity.ok(new BaseResponse("OK",String.format("Se elimniarion %d registros", deleted)));

	}

}
