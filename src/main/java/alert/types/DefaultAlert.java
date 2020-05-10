package main.java.alert.types;

public class DefaultAlert extends AbstractAlert {

	private String title;
	private String message;
	
	public DefaultAlert (String title, String message) {
		alertType = AlertType.DEFAULT;
		this.title = title;
		this.message = message;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getMessage() {
		return message;
	}
}
