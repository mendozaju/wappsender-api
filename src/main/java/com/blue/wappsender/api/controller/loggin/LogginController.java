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
import com.blue.wappsender.api.controller.utils.HeaderManager;
import com.blue.wappsender.api.model.user.AccessKey;
import com.blue.wappsender.api.model.user.User;
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
	 */
	@RequestMapping(path= PathsController.LOGGIN, method = RequestMethod.POST)
	public  ResponseEntity<BaseResponse> loggin(@Valid @RequestBody LogginRequest request){
		LOG.info("Se atiende el pedido de loggin para el usuario:[{}]",request.getUser());
		
		Optional<User> user = this.userService.find(request.getUser());		
		if(user.isPresent()) {
			User aUser = user.get();
			if(this.userValidationService.isValidPassword(aUser)) {
				AccessKey accesKey = this.authenticationService.generateAccesKey();
				this.sessionService.updateAccesKey(aUser, accesKey);
				return new ResponseEntity<BaseResponse>(new BaseResponse(),HeaderManager.getAuthentcationHeader(accesKey), HttpStatus.ACCEPTED);
			}else {
				return ResponseEntity.badRequest().body(new BaseResponse());
			}
		}else {
			//TODO: Lanzar una exception particular de usuario no valido
			LOG.info("No se pudo indentificar al usuario");
			return ResponseEntity.badRequest().body(new BaseResponse());
		}		
	}

}
