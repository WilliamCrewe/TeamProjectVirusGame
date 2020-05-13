package main.java.alert.types;

/**
 * Alert to be used when the user is exhausted and so cannot perform the action they want to
 * @author Daniel
 *
 */
public class ExhaustionAlert extends AbstractAlert {

	private static final String MESSAGE = "You are too tired to do that, you must go home and sleep";
	
	public ExhaustionAlert() {
		alertType = AlertType.EXHAUSTION;
	}
	
	public String getMessage() {
		return MESSAGE;
	}
}
