package br.ufc.conbo.util.alert;

public enum AlertType {
	INFO("INFO"), SUCCESS("SUCESS"), ERROR("ERROR"), WARNING("WARNING");
	
	private String description;
	
	private AlertType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
