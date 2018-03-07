package com.blue.wappsender.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blue.wappsender.api.controller.healt.HealtResponse;

@Controller
public class ApiController {
	
	@RequestMapping(method=RequestMethod.GET, path="/api")
	@ResponseBody
	public String getStatus() {
		return "OK";
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/", produces="application/json; charset=utf-8")
	@ResponseBody
	public HealtResponse healtCheck() {
		HealtResponse response = new HealtResponse();
		response.setStatus("OK");
		response.setVersion("0.0.1-SNAPSHOT");
		response.setDescription("API para el envio de mensajes");
		return response;
	}

}
