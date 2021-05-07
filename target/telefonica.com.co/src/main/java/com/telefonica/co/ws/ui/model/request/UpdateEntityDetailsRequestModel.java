package com.telefonica.co.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateEntityDetailsRequestModel {
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter1NotNull}")
	private String parametro1;
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter2NotNull}")
	private String parametro2;

	
	/**
	 * @TO-DO Reemplazar getters y setters
	 */
	public String getParametro1() {
		return parametro1;
	}
	public void setParametro1(String parametro1) {
		this.parametro1 = parametro1;
	}
	public String getParametro2() {
		return parametro2;
	}
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}
	
}
