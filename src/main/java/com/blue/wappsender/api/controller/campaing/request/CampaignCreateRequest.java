package com.blue.wappsender.api.controller.campaing.request;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;

import com.blue.wappsender.api.model.campaing.serializer.ActivationDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Request para pedido de creacion de campaña
 * 
 * @author jmendoza
 *
 */
public class CampaignCreateRequest {

	private int id;

	@NotEmpty
	private String text;

	@NotEmpty
	private String description;

	@JsonDeserialize(using = ActivationDateDeserializer.class)
	private LocalDateTime activationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(LocalDateTime activationDate) {
		this.activationDate = activationDate;
	}

}
