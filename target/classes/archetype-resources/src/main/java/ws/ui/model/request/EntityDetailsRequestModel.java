package ${groupId}.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EntityDetailsRequestModel {
	
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter1NotNull}")
	private String parameter1;
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter2NotNull}")
	private String parameter2;
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@Email
	@NotNull(message="${entity.parameter3NotNull}")
	private String parameter3;
	
	/**
	 * @TO-DO Reemplazar parameter por el nombre de la variable , esto afecta los gets y sets
	 */
	@NotNull(message="${entity.parameter4NotNull}")
	@Size(min=8,max=16, message="${entity.parameter4Size}")
	private String parameter4;
	
	/**
	 * @TO-DO Reemplazar getters y setters
	 */
	public String getParameter1() {
		return parameter1;
	}

	public void setParameter1(String parameter1) {
		this.parameter1 = parameter1;
	}

	public String getParameter2() {
		return parameter2;
	}

	public void setParameter2(String parameter2) {
		this.parameter2 = parameter2;
	}

	public String getParameter3() {
		return parameter3;
	}

	public void setParameter3(String parameter3) {
		this.parameter3 = parameter3;
	}

	public String getParameter4() {
		return parameter4;
	}

	public void setParameter4(String parameter4) {
		this.parameter4 = parameter4;
	}

}
