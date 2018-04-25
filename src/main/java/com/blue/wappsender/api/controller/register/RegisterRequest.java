package com.blue.wappsender.api.controller.register;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

public class RegisterRequest {	

	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String phone;
	
	@NotNull
	private ArrayList<String> interests;

	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public ArrayList<String> getInterests() {
		return interests;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setInterests(ArrayList<String> interests) {
		this.interests = interests;
	}
}
