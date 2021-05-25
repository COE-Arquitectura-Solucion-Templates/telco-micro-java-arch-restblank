package ${groupId}.ws.ui.model.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorMessage {
	private Date timestamp;
	private String message;
	
	public ErrorMessage() {
		
	}	
	
	public ErrorMessage( Date timestamp,String message) {
		this.timestamp = timestamp;
		this.message = message;
	}
	
	

}
