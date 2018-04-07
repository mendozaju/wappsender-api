package com.blue.wappsender.api.controller.register;

import java.io.IOException;
import java.util.Iterator;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin
@RequestMapping(path = "/api")
public class RegistrationController {
	
	private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
	
	private final String ENDPOINT = "https://eu2.chat-api.com/instance1401/message?token=al43m3nih2jpfhi9";
	private final String MESSAGE_TEMPLATE = "*Hola %s!* Ya estas registrado para recibir alertas sobre los siguientes temas de interes: ";
	
	@RequestMapping(method = RequestMethod.POST, path = "/registration")
	public ResponseEntity<String> sendRegistrationMessage(@Valid @RequestBody RegisterRequest registerRequest) throws IOException {
		log.info("Se atiende el pedido de envio de mensaje de registracion para el telefono:[{}]",registerRequest.getPhone());		

		ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(requestFactory);	
		
		RegisterBody body = new RegisterBody();
		body.setPhone(registerRequest.getPhone());
		body.setBody(this.getFormattedMessages(registerRequest));
		
		HttpEntity<RegisterBody> request = new HttpEntity<>(body);
		
		ResponseEntity<String> respone = restTemplate.postForEntity(ENDPOINT, request, String.class);	
		return ResponseEntity.ok(respone.getBody());		
	}
	
	
	private String getFormattedMessages(RegisterRequest registerRequest) {
		StringBuilder builder = new StringBuilder(String.format(MESSAGE_TEMPLATE, registerRequest.getFirstName()));
		Iterator<String> it = registerRequest.getInterests().iterator();
		while (it.hasNext()) {
			builder.append("_*" + it.next() + "_");
			if(it.hasNext()) {
				builder.append(", ");
			}
		}
		log.debug("Mensaje a entregar:[{}]",builder.toString());		
		return builder.toString();
	}


	private ClientHttpRequestFactory getClientHttpRequestFactory() {
	    int timeout = 5000;
	    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
	      = new HttpComponentsClientHttpRequestFactory();
	    clientHttpRequestFactory.setConnectTimeout(timeout);
	    return clientHttpRequestFactory;
	}

}