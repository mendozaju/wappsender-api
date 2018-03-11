package com.blue.wappsender.api.model.campaing;

import com.blue.wappsender.api.controller.campaing.request.CampaignCreateRequest;

public class CampaignModelBuilder {
	
	public static Campaign build(CampaignCreateRequest request) {		
		Campaign model = new Campaign();
		model.setActivationDate(request.getActivationDate());
		model.setDescription(request.getDescription());
		model.setText(request.getText());
		return model;
	}

}
