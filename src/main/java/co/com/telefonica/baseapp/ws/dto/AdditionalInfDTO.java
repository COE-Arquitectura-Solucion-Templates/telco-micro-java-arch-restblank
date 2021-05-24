package co.com.telefonica.baseapp.ws.dto;

import java.io.Serializable;
/**
 * Clase encargada de almacenar en un objeto serializable de transporte los
 * datos del header adicionales, que se envian, filtros, paginación entre otros
 * 
 * @version 1.0.0
 * @author COEArquitectura@telefonica.com
 * @since 19/05/2021
 */
public class AdditionalInfDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//:TODO, en caso de necesitar elementos adicionales en el header, deben ir en este DTO.
	private String parameter;

	/**
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter the parameter to set
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

}
