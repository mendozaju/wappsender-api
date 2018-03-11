package com.blue.wappsender.api.controller.destination.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

/**
 * Calse que representa el request para agregar destinos a una campaña
 * @author jmendoza
 *
 */
public class AddDestinationRequest {

	@NotNull
	ArrayList<String> destinations;

	public ArrayList<String> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<String> destinations) {
		this.destinations = destinations;
	}	
}
