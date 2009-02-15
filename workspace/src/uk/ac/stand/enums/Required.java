package uk.ac.stand.enums;

import uk.ac.stand.impl.Settings;

public enum Required {
	NUMBER_OF_TEAMS ("Number of teams"), 
	SPEAKERS_PER_TEAM ("Speakers per team"), 
	ROUNDS ("Number of rounds"), 
	TEAMS_PER_SIDE ("Teams per side");
	
	private final String text;
	
	Required(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public Object getValue() {
		return Settings.getInstance().getValue(this);
	}
	
	public void setValue(Object val) {
		Settings.getInstance().setValue(this, val);
	}
	
	
}
