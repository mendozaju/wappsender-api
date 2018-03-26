package com.blue.wappsender.api.controller.destination.response;

import java.util.ArrayList;

import com.blue.wappsender.api.model.campaing.Destination;


/**
 * Clase que representa la respuesta de destinos
 * @author jmendoza
 *
 */
public class DestinationsResponse {

	private ArrayList<Destination> destinations;
	private int quantity;
	private int campaingId;

	public DestinationsResponse() {
		this.destinations = new ArrayList<Destination>();
	}

	public ArrayList<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<Destination> destinations) {
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
