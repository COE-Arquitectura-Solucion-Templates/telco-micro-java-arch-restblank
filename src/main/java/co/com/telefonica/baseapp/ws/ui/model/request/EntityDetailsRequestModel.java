package co.com.telefonica.baseapp.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EntityDetailsRequestModel {
	
	
	/**
	 * :TODO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter1NotNull}")
	private String parameter1;
	
	/**
	 * :TODO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter2NotNull}")
	private String parameter2;
	
	/**
	 * :TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@Email
	@NotNull(message="${entity.parameter3NotNull}")
	private String parameter3;
	
	/**
	 * :TODO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter4NotNull}")
	@Size(min=8,max=16, message="${entity.parameter4Size}")
	private String parameter4;
	
	
}
