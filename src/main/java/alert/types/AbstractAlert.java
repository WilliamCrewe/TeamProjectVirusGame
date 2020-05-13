package main.java.alert.types;

public abstract class AbstractAlert {

	protected AlertType alertType;
	
	public AlertType getEventType() {
		return alertType;
	}
	
	public String getMessage() {
		return null;
		
	}
}
