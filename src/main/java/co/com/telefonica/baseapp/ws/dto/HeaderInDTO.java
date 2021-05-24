package co.com.telefonica.baseapp.ws.dto;

import java.io.Serializable;

import org.springframework.http.HttpHeaders;

/**
 * Clase encargada de almacenar en un objeto serializable de transporte los
 * datos del header.
 * 
 * @version 1.0.0
 * @author COEArquitectura@telefonica.com
 * @since 19/05/2021
 */
public class HeaderInDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authorization;
	private String system;
	private String operation;
	private String execId;
	private String timestamp;
	private String msgType;

	/**
	 * Constructor que recibe un objeto headers y lo transforma a DTO.
	 * 
	 * @param headers, información que viene del HTTP headers
	 */
	public HeaderInDTO(HttpHeaders headers) {
		super();
		this.authorization = headers.getFirst("authorization");
		this.system = headers.getFirst("system");
		this.operation = headers.getFirst("operation");
		this.execId = headers.getFirst("execId");
		this.timestamp = headers.getFirst("timestamp");
		this.msgType = headers.getFirst("msgType");
	}
	
	/**
	 * constructor que recibe los elementos de los headers y lo transforma a DTO.
	 * 
	 * @param authorization
	 * @param system
	 * @param operation
	 * @param execId
	 * @param timestamp
	 * @param msgType
	 */
	public HeaderInDTO(String authorization, String system, String operation, String execId, String timestamp,
			String msgType) {
		super();
		this.authorization = authorization;
		this.system = system;
		this.operation = operation;
		this.execId = execId;
		this.timestamp = timestamp;
		this.msgType = msgType;
	}

	/**
	 * @return the authorization
	 */
	public String getAuthorization() {
		return authorization;
	}

	/**
	 * @param authorization the authorization to set
	 */
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	/**
	 * @return the system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(String system) {
		this.system = system;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @return the execId
	 */
	public String getExecId() {
		return execId;
	}

	/**
	 * @param execId the execId to set
	 */
	public void setExecId(String execId) {
		this.execId = execId;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public HeaderInDTO() {

	}

	@Override
	public String toString() {
		return "HeaderInDTO [authorization=" + authorization + ", system=" + system + ", operation=" + operation
				+ ", execId=" + execId + ", timestamp=" + timestamp + ", msgType=" + msgType + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
