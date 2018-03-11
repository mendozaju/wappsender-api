package com.blue.wappsender.api.controller.destination.response;

import java.util.ArrayList;

/**
 * Clase que representa la respuesta de destinos
 * @author jmendoza
 *
 */
public class DestinationsResponse {

	private ArrayList<String> destinations;
	private int quantity;
	private int campaingId;

	public DestinationsResponse() {
		this.destinations = new ArrayList<String>();
	}

	public ArrayList<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<String> destinations) {
		this.destinations = destinations;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCampaingId() {
		return campaingId;
	}

	public void setCampaingId(int campaingId) {
		this.campaingId = campaingId;
	}

	
	
}
