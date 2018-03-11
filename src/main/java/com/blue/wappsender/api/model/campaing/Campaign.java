package com.blue.wappsender.api.model.campaing;
/**
 * Clase que representa una campaña
 * @author jmendoza
 *
 */

import java.time.LocalDateTime;

import com.blue.wappsender.api.model.campaing.serializer.ActivationDateDeserializer;
import com.blue.wappsender.api.model.campaing.serializer.ActivationDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Campaign{

	private int id;
	
	private int userId;
	
	private String text;	
	
	private String description;
	
	@JsonSerialize(using=ActivationDateSerializer.class)
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
