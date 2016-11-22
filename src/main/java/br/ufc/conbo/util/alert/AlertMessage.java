package br.ufc.conbo.util.alert;

public class AlertMessage {
		
	private static final int LONG_DELAY = 5000; // 3.5 seconds
	private static final int SHORT_DELAY = 3000; // 2 seconds
	
	private String text;
	private AlertType type;
	private int delay;
	
	private AlertMessage(String text, AlertType type, int delay) {
		this.text = text;
		this.type = type;
		this.delay = delay;
	}
	
	public String getText() {
		return text;
	}

	public AlertType getType() {
		return type;
	}

	public int getDelay() {
		return delay;
	}

	public static AlertMessage createAsShortInfo(String text) {
		return new AlertMessage(text, AlertType.INFO, SHORT_DELAY);
	}
	
	public static AlertMessage createAsShortSuccess(String text) {
		return new AlertMessage(text, AlertType.SUCCESS, SHORT_DELAY);
	}
	
	public static AlertMessage createAsShortWarning(String text) {
		return new AlertMessage(text, AlertType.WARNING, SHORT_DELAY);
	}
	
	public static AlertMessage createAsShortError(String text) {
		return new AlertMessage(text, AlertType.ERROR, SHORT_DELAY);
	}
	
	public static AlertMessage createAsLongInfo(String text) {
		return new AlertMessage(text, AlertType.INFO, LONG_DELAY);
	}
	
	public static AlertMessage createAsLongSuccess(String text) {
		return new AlertMessage(text, AlertType.SUCCESS, LONG_DELAY);
	}
	
	public static AlertMessage createAsLongWarning(String text) {
		return new AlertMessage(text, AlertType.WARNING, LONG_DELAY);
	}
	
	public static AlertMessage createAsLongError(String text) {
		return new AlertMessage(text, AlertType.ERROR, LONG_DELAY);
	}
	
}
