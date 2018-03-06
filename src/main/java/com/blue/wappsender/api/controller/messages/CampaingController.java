package com.blue.wappsender.api.controller.messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CampaingController {
	
	
	@RequestMapping(method = RequestMethod.POST, path="/campaing")
	@ResponseBody
	public CampaingResponse saveCampaign(){
		CampaingResponse result = new CampaingResponse();
		result.setStatus("OK");
		return result;
	}
	

}
