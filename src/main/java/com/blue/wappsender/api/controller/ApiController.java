package com.blue.wappsender.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
	
	@RequestMapping(method=RequestMethod.GET, path="/api")
	@ResponseBody
	public String getStatus() {
		return "OK";
	}

}
