package com.blue.wappsender.api.controller.campaing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/api")
public class CampaingController {
	
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path="/campaing")
	@ResponseBody
	public CampaingResponse saveCampaign(){
		CampaingResponse result = new CampaingResponse();
		result.setStatus("OK");
		return result;
	}
	

}
