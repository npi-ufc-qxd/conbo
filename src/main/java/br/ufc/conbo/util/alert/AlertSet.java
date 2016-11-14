package br.ufc.conbo.util.alert;

import java.util.ArrayList;

public class AlertSet extends ArrayList<AlertMessage> {

	private static final long serialVersionUID = 1L;

	public AlertSet() { super(); }
	
	public AlertSet withShortInfo(String text) {
		add(AlertMessage.createAsShortInfo(text));
		return this;
	}
	
	public AlertSet withShortSuccess(String text) {
		add(AlertMessage.createAsShortSuccess(text));
		return this;
	}
	
	public AlertSet withShortWarning(String text) {
		add(AlertMessage.createAsShortWarning(text));
		return this;
	}
	
	public AlertSet withShortError(String text) {
		add(AlertMessage.createAsShortError(text));
		return this;
	}
	
	public AlertSet withLongInfo(String text) {
		add(AlertMessage.createAsLongInfo(text));
		return this;
	}
	
	public AlertSet withLongSuccess(String text) {
		add(AlertMessage.createAsLongSuccess(text));
		return this;
	}
	
	public AlertSet withLongWarning(String text) {
		add(AlertMessage.createAsLongWarning(text));
		return this;
	}
	
	public AlertSet withLongError(String text) {
		add(AlertMessage.createAsLongError(text));
		return this;
	}
	
}
