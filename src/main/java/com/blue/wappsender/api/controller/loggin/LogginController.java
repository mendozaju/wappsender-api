package com.blue.wappsender.api.controller.loggin;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.blue.wappsender.api.controller.cross.BaseResponse;
import com.blue.wappsender.api.controller.cross.PathsController;
import com.blue.wappsender.api.controller.loggin.request.LogginRequest;
import com.blue.wappsender.api.controller.loggin.response.LogginResponseBuilder;
import com.blue.wappsender.api.controller.utils.HeaderManager;
import com.blue.wappsender.api.model.user.User;
import com.blue.wappsender.api.model.user.acces.AccessKey;
import com.blue.wappsender.api.service.persistence.SessionPersistenceService;
import com.blue.wappsender.api.service.persistence.UserPersistenceService;
import com.blue.wappsender.api.service.security.AccesKeyService;
import com.blue.wappsender.api.service.security.UserValidations;

/**
 * Controller encargado de loguear al usuario
 * @author jmendoza
 *
 */
@Controller
public class LogginController {
		
	private static final Logger LOG = LoggerFactory.getLogger(LogginController.class);
	
	@Autowired
	private UserPersistenceService userService;
	@Autowired
	private UserValidations userValidationService;
	@Autowired
	private AccesKeyService authenticationService;
	@Autowired
	private SessionPersistenceService sessionService;
	
	
	
	/**
	 * Controller encargado de loguear al usuario. Al loguearse se guarda la sesion en BBDD
	 * La respuesta implatara una cookie y entregara el token de autenticacion.
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(path= PathsController.LOGGIN, method = RequestMethod.POST)
	public  ResponseEntity<BaseResponse> loggin(@Valid @RequestBody LogginRequest request) throws Exception{
		LOG.info("Se atiende el pedido de loggin para el usuario:[{}]",request.getUser());
		
		Optional<User> user = this.userService.find(request.getUser());		
		if(user.isPresent()) {
			return this.validateUser(request, user);
		}else {
			return LogginResponseBuilder.invalidCredential();
		}		
	}


	/**
	 * Valida las credenciales de usuario
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	private ResponseEntity<BaseResponse> validateUser(LogginRequest request, Optional<User> user) throws Exception {
		User aUser = user.get();
		if(this.userValidationService.isValidPassword(aUser, request.getPassword())) {
			AccessKey accesKey = this.authenticationService.generateAccesKey();
			int updatedQuantity = this.sessionService.updateAccesKey(aUser, accesKey);
			if(updatedQuantity == 0) {
				LOG.info("No existe registro para el usuario. Se inserta la accesskey");
				int insertQuantity = this.sessionService.insertAccesKey(aUser, accesKey);
				if(insertQuantity != 1) {
					//TODO: Ver que lanzamos en este caso.
					throw new Exception("Error con la autenticacion");
				}
			}				
			return LogginResponseBuilder.loggedOk(accesKey);
		}else {
			LOG.info("Password invalido");
			return LogginResponseBuilder.invalidCredential();				
		}
	}
}
