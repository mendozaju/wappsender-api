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
	private String phone;	
	private String firstName;
	private String lastName;
	private Integer id;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String number) {
		this.phone = number;
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

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
