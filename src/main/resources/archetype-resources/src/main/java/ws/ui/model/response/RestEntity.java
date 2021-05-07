package ${groupId}.ws.ui.model.response;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entidad que maneja los parametros a exposicion en REST.
 * @autor: COE-Arquitectura-Telefonica
 * @date: 19-10-2020
 * @version 1.0
 */
@Entity
public class RestEntity {
	private String parametro1;
	private String parametro2;
	private String parametro3;
	private String parametro4;
	@Id
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
	public String getParametro3() {
		return parametro3;
	}
	public void setParametro3(String parametro3) {
		this.parametro3 = parametro3;
	}
	public String getParametro4() {
		return parametro4;
	}
	public void setParametro4(String parametro4) {
		this.parametro4 = parametro4;
	}
	
	
	
}
