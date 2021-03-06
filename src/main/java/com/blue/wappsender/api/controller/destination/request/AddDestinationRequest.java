package com.blue.wappsender.api.controller.destination.request;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.blue.wappsender.api.model.campaing.Destination;

/**
 * Calse que representa el request para agregar destinos a una campaña
 * @author jmendoza
 *
 */
public class AddDestinationRequest {
	@NotNull
	ArrayList<Destination> destinations;

	public ArrayList<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(ArrayList<Destination> destinations) {
		this.destinations = destinations;
	}
}
