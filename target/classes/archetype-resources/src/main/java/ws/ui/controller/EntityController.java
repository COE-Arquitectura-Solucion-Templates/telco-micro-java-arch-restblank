package ${groupId}.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import ${groupId}.ws.service.LogServiceDelegate;
import ${groupId}.ws.ui.model.request.EntityDetailsRequestModel;
import ${groupId}.ws.ui.model.request.LogDetailsRequestModel;
import ${groupId}.ws.ui.model.request.UpdateEntityDetailsRequestModel;
import ${groupId}.ws.ui.model.response.LogRestResponseModel;
import ${groupId}.ws.ui.model.response.RestEntity;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Clase encargada de mapear las operaciones verbos del servicio REST.
 * 
 * @autor: COE-Arquitectura-Telefonica
 * @date: 19-10-2020
 * @version 1.0
 */
@RestController
@RequestMapping("operacion") // http://localhost:8080/{operacion}
public class EntityController {
	//Colleccion para almacenar en cache los elementos ingresados.
	Map<String, RestEntity> collectionMap;
	//Declara un encargado de generar los logs
	@Autowired
	LogServiceDelegate logServiceDelegate;
	
	/** @method
	 * Este metodo es encargado de mapear la operacion GET /operacion/{parametro1}
	 **/
	@GetMapping(path = "/{parameter}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> getParameter(@PathVariable String parameter) {

		LogDetailsRequestModel logRequest = new LogDetailsRequestModel();
		ResponseEntity<LogRestResponseModel> logResponse = null;

		if (collectionMap == null)
			collectionMap = new HashMap<>();

		if (collectionMap.containsKey(parameter)) {
			return new ResponseEntity<RestEntity>(collectionMap.get(parameter), HttpStatus.OK);
		} else {
			logRequest.setNombreAplicacion("{$applicationId}");
			logRequest.setMensaje("Parametro : " + parameter + " NOT FOUND");
			logResponse = logServiceDelegate.writeLog("exception", logRequest);
			System.out.println(logResponse.toString());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	/** @method
	 * Este metodo es encargado de mapear la operacion GET /operacion/{parametro1}
	 **/
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getParametros(@RequestParam(value = "page", defaultValue = "-1") int page,
			@RequestParam(value = "limit", defaultValue = "-1") int limit,
			@RequestParam(value = "sort", required = false) String sort) {
		if (page == -1 || limit == -1) {
			return "get parametros called was called";
		} else {
			return "get parametros called was called page=" + page + " and limit=" + limit + " and sort=" + sort;
		}
	}
	/** @method
	 * Este metodo es encargado de mapear la operacion GET /operacion/all
	 **/
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getEntity() {
		return "get entity called was called";
	}
	/** @method
	 * Este metodo es encargado de mapear la operacion POST /operacion/{parametro1}
	 **/
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> createEntity(@Valid @RequestBody EntityDetailsRequestModel entityDetails) {
		RestEntity returnValue = new RestEntity();
		UUID value = UUID.randomUUID();
		returnValue.setParametro1(entityDetails.getParameter1());
		returnValue.setParametro2(entityDetails.getParameter2());
		returnValue.setParametro3(entityDetails.getParameter3());
		returnValue.setParametro4(value.toString());

		if (collectionMap == null)
			collectionMap = new HashMap<>();
		collectionMap.put(value.toString(), returnValue);

		return new ResponseEntity<>(returnValue, HttpStatus.OK);

	}
	/** @method
	 * Este metodo es encargado de mapear la operacion PUT /operacion/{parametro1}
	 **/
	@PutMapping(path = "/{parametro1}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> updateEntity(@PathVariable String entityId,
			@Valid @RequestBody UpdateEntityDetailsRequestModel entityDetails) {
		RestEntity returnValue = null;
		if (collectionMap.containsKey(entityId)) {
			returnValue = collectionMap.get(entityId);

			if (entityDetails.getParametro1() != null)
				returnValue.setParametro1(entityDetails.getParametro1());
			if (entityDetails.getParametro2() != null)
				returnValue.setParametro2(entityDetails.getParametro2());

			collectionMap.put(entityId, returnValue);

			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	/** @method
	 * Este metodo es encargado de mapear la operacion DELETE /operacion/{parametro1}
	 **/
	@DeleteMapping(path = "/{parametro}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> deleteParameter(@PathVariable String entityId) {
		if (collectionMap.containsKey(entityId)) {
			return new ResponseEntity<>(collectionMap.remove(entityId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
