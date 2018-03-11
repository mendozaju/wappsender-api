package com.blue.wappsender.api.controller.campaing.response;

import java.util.ArrayList;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.model.campaing.Campaign;

/**
 * Representa una coleccion de campañas
 * @author jmendoza
 *
 */
public class CampaignsResponse extends BaseResponse{
	
	private ArrayList<Campaign> campaigns;
	private int quantity;
	
	public CampaignsResponse() {
		this.campaigns = new ArrayList<>();
	}
	

	public ArrayList<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(ArrayList<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	

}
