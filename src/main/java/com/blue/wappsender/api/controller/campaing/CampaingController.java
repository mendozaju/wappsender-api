package com.blue.wappsender.api.controller.campaing;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.wappsender.api.controller.campaing.request.CampaignCreateRequest;
import com.blue.wappsender.api.controller.campaing.response.CampaignResponseBuilder;
import com.blue.wappsender.api.controller.campaing.response.CampaignsResponse;
import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.model.campaing.Campaign;
import com.blue.wappsender.api.model.campaing.CampaignModelBuilder;
import com.blue.wappsender.api.service.persistence.CampaingPersistenceService;

@Controller
@CrossOrigin
@RequestMapping(path="/api")
public class CampaingController {
	
	private static final Logger log = LoggerFactory.getLogger(CampaingController.class);
	
	@Autowired
	private CampaingPersistenceService campaingPresistence;
	
	/**
	 * Atiende el pedido de creacion de una campaña
	 * @param campaing
	 * @return
	 */
	
	@RequestMapping(method = RequestMethod.POST, path="/campaing")
	public ResponseEntity<BaseResponse> saveCampaign(@Valid @RequestBody CampaignCreateRequest request){
		
		//TODO: agregar un mapeo para los errores de validacion salgan bien formateados
		//TODO: mapear los errores SQL
		
		Campaign campaign = CampaignModelBuilder.build(request);	
		int result = this.campaingPresistence.saveCampaing(campaign);		
		return CampaignResponseBuilder.buildCreateCampaign(result);
		
	}
	
	/**
	 * Retorna las campañas que se encuentran guardadas
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value= {"/campaing","/campaing/{campaingId}"})
	public ResponseEntity<CampaignsResponse> getCampaigns(@PathVariable(required=false) String campaingId, @RequestParam(required=false,name="user_id") String userId) {		
		
		ArrayList<Campaign> result = new ArrayList<Campaign>();		
		if(userId != null) {
			result = this.campaingPresistence.getAllCampaignsToUser(userId);
		}else {
			if(campaingId != null) {
				result = this.campaingPresistence.getCampaigns(campaingId);
			}else {
				result = this.campaingPresistence.getAllCampaigns();
			}			
		}							
		return CampaignResponseBuilder.buildGetCampaings(result);		
	}	
	

}
