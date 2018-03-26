package com.blue.wappsender.api.controller.destination.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.blue.wappsender.api.model.campaing.Destination;

public class UpdateDestinationRequest {
	
	@NotNull
	private List<Destination> destinations;
	
	public List<Destination> getDestinations() {
		return destinations;
	}
	
	public void setDestinations(ArrayList<Destination> destinations) {
		this.destinations = destinations;
	}
}
