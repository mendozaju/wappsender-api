package com.blue.wappsender.api.model.campaing;

/**
 * Clase que representa un destino
 * @author jmendoza
 *
 */
public class Destination {
	
	private String number;	
	private Integer campaignId;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}


}
