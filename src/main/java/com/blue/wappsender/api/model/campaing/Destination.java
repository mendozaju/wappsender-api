package com.blue.wappsender.api.model.campaing;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Clase que representa un destino
 * @author jmendoza
 *
 */
public class Destination {	
	
	@JsonIgnore
	private Integer campaignId;
	private String number;	
	private Integer id;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
