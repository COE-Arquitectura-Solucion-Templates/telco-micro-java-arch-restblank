package co.com.telefonica.baseapp.ws.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;

import co.com.telefonica.baseapp.ws.dto.HeaderInDTO;
import lombok.extern.slf4j.Slf4j;
/**
 * Clase encargada de validar los Header de un servicio REST.
 * 
 * @version 1.0.0
 * @author COEArquitectura@telefonica.com
 * @since 19/05/2021
 */
@Slf4j
public class UtilHeader {

	private static final int TAM_SOURCE_FIELD = 40;
	private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
	private static final String REGEX_FORMAT = "[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}";

	public static HeaderInDTO headerIn = null;

	public  HeaderInDTO processHeader(HttpHeaders headers) throws Exception{
		headerIn = new HeaderInDTO(headers);
		if (validateHeader(headerIn)) {
			return headerIn;
		}else {
			throw new Exception("Bad Request");
		}
	}
	/**
	 * Este metodo es el encargadon de validar los header
	 * @param headerIn, objeto header a validar
	 * @return boolean, verdadero, si el header no cumple con las especificaciones
	 */
	public  boolean validateHeader(HeaderInDTO headerIn) {

		if (headerIn == null) {
			return true;
		}

		return !(!validateField(headerIn.getAuthorization()) || !validateField(headerIn.getSystem())
				|| !validateField(headerIn.getOperation()) || !validateRegExExecId(headerIn.getExecId())
				|| (!validateTimeStamp(headerIn.getTimestamp())) || !validateField(headerIn.getMsgType()));
		
		
	}
	/**
	 * Este método es el encargado de validar si cumple con un minimo esperado en tamaño y que no sea null
	 * @param source, elemento del header
	 * @return boolean, que indica true, si no cumple con las validaciones false en caso contrario
	 */
	public static boolean validateField(String source) {
		return !(source == null || source.isEmpty()|| source.length() > TAM_SOURCE_FIELD);
	}
	/**
	 * Metodo encargado de validar que el formato de fecha es el espérado
	 * @param strDate, parametro fecha
	 * @return boolean, que indica true, si no cumple con las validaciones false en caso contrario
	 */
	public static boolean validateTimeStamp(String strDate) {
		if (validateField(strDate)) {
			/*
			 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
			 */
			DateFormat sdfrmt = new SimpleDateFormat(DATE_FORMAT);
			sdfrmt.setLenient(false);
			/*
			 * Create Date object parse the string into date
			 */
			try {
				sdfrmt.parse(strDate);

			}
			/* Date format is invalid */
			catch (ParseException e) {
				log.error("Error en formato de fecha: "+e.getMessage());
				return false;
			}

		} else {
			/* Return true if date format is valid */
			return true;

		}
		return true;

	}
	/**
	 * metodo encargado de validar que se cumple con el formato esperado de ExecId
	 * @param source, parametro donde se contempla el valor de ExecId
	 * @return boolean, que indica true, si no cumple con las validaciones false en caso contrario
	 */
	public static boolean validateRegExExecId(String source) {

		return !(Pattern.matches(REGEX_FORMAT, source))
				|| !(validateField(source));
	}
	/**
	 * 
	 * @param headers
	 * @return
	 */
	public HttpHeaders buildHeaderOut(HttpHeaders headers) {
		HttpHeaders responseHeaders = new HttpHeaders();		
		
		headers.forEach((key, values) -> {
		    for (String value : values) {
		    	if(key.equals("msgType"))
		    		responseHeaders.add("msgType", "RESPONSE");
		    		
		    	responseHeaders.add(key, value);
		    }
		  });
	    
		return responseHeaders;
	}

}
