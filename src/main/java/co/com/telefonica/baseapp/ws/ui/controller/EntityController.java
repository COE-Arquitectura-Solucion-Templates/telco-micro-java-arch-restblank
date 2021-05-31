package co.com.telefonica.baseapp.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.telefonica.baseapp.ws.dto.HeaderInDTO;
import co.com.telefonica.baseapp.ws.service.LogServiceDelegate;
import co.com.telefonica.baseapp.ws.ui.model.request.EntityDetailsRequestModel;
import co.com.telefonica.baseapp.ws.ui.model.response.RestEntity;
import co.com.telefonica.baseapp.ws.util.UtilHeader;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de mapear las operaciones verbos del servicio REST.
 * 
 * @autor: COE-Arquitectura-Telefonica
 * @date: 19-10-2020
 * @version 1.0
 */
@RestController
@RequestMapping("operacion")
@Slf4j
@Service

public class EntityController {

	// Colleccion para almacenar en cache los elementos ingresados.
	Map<String, RestEntity> collectionMap;
	// Declara un encargado de generar los logs
	@Autowired
	LogServiceDelegate logServiceDelegate;

	@Value("${error.headers}")
	private int ERROR_HEADER;

	HeaderInDTO user;

	UtilHeader utilHeader;

	/**
	 * @method Este metodo es encargado de mapear la operacion GET
	 *         /operacion/{parametro1}
	 **/
	@GetMapping(path = "telefonica/hispam/${variable}/GET/{parameter}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> getParameter(@RequestHeader HttpHeaders headers, @PathVariable String parameter) {
		log.info("Se esta ejecutando la aplicación");

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);

		} catch (Exception exception) {
			log.error("{$applicationId}", ERROR_HEADER, exception);

			return ResponseEntity.badRequest().headers(new UtilHeader().buildHeaderOut(headers)).build();
		}

		if (collectionMap == null)
			collectionMap = new HashMap<>();

		if (collectionMap.containsKey(parameter)) {

			return ResponseEntity.ok().headers(new UtilHeader().buildHeaderOut(headers))
					.body(collectionMap.get(parameter));

		} else {

			log.error("{$applicationId}", "Parametro : " + parameter + " NOT FOUND");

			return ResponseEntity.notFound().headers(new UtilHeader().buildHeaderOut(headers)).build();

		}

	}

	/**
	 * @method Este metodo es encargado de mapear la operacion GET
	 *         /operacion/{parametro1}
	 **/
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getParametros(@RequestHeader HttpHeaders headers,
			@RequestParam(value = "page", defaultValue = "-1") int page,
			@RequestParam(value = "limit", defaultValue = "-1") int limit,
			@RequestParam(value = "sort", required = false) String sort) {

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);
		} catch (Exception exception) {
			log.error("{$applicationId}",
					"Bad Request: Los parametros del Header no Cumplen con la especificación Técnica", exception);
			return "get parametros called was called with fail in header";
		}

		if (page == -1 || limit == -1) {
			return "get parametros called was called";
		} else {
			return "get parametros called was called page=" + page + " and limit=" + limit + " and sort=" + sort;
		}
	}

	/**
	 * @method Este metodo es encargado de mapear la operacion GET /operacion/all
	 **/
	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String getEntity(@RequestHeader HttpHeaders headers) {

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);
		} catch (Exception exception) {
			log.error("{$applicationId}",
					"Bad Request: Los parametros del Header no Cumplen con la especificación Técnica", exception);
			return "get parametros called was called with fail in header";
		}

		return "get entity called was called";
	}

	/**
	 * @method Este metodo es encargado de mapear la operacion POST
	 *         /operacion/{parametro1}
	 **/
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> createEntity(@RequestHeader HttpHeaders headers,
			@Valid @RequestBody EntityDetailsRequestModel entityDetails) {

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);
		} catch (Exception exception) {
			log.error("{$applicationId}",
					"Bad Request: Los parametros del Header no Cumplen con la especificación Técnica", exception);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

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

	/**
	 * @method Este metodo es encargado de mapear la operacion PUT
	 *         /operacion/{parametro1}
	 **/
	@PutMapping(path = "/{entityId}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> updateEntity(@RequestHeader HttpHeaders headers, @PathVariable String entityId,
			@Valid @RequestBody EntityDetailsRequestModel entityDetails) {

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);
		} catch (Exception exception) {
			log.error("{$applicationId}", ERROR_HEADER, exception);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		RestEntity returnValue = null;
		if (collectionMap.containsKey(entityId)) {
			returnValue = collectionMap.get(entityId);

			if (entityDetails.getParameter1() != null)
				returnValue.setParametro1(entityDetails.getParameter1());
			if (entityDetails.getParameter2() != null)
				returnValue.setParametro2(entityDetails.getParameter2());

			collectionMap.put(entityId, returnValue);

			return new ResponseEntity<>(returnValue, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * @method Este metodo es encargado de mapear la operacion DELETE
	 *         /operacion/{parametro1}
	 **/
	@DeleteMapping(path = "/{entityId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<RestEntity> deleteParameter(@RequestHeader HttpHeaders headers,
			@PathVariable String entityId) {

		try {
			// Se valida y procesa el header.
			new UtilHeader().processHeader(headers);
		} catch (Exception exception) {
			log.error("{$applicationId}", ERROR_HEADER, exception);
			return ResponseEntity.badRequest().headers(new UtilHeader().buildHeaderOut(headers)).build();
		}

		if (collectionMap.containsKey(entityId)) {
			return new ResponseEntity<>(collectionMap.remove(entityId), HttpStatus.OK);
		} else {
			return ResponseEntity.notFound().headers(new UtilHeader().buildHeaderOut(headers)).build();
		}

	}

}
