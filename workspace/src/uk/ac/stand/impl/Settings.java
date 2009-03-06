package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

import uk.ac.stand.enums.Required;

/* TODO lots of stuff in list below
 * Add speaker points range
 * 
 * 
 */

public class Settings implements Serializable {

	private static final long serialVersionUID = 50320091L;
	
	//Singleton factory
	private static Settings instance = null;
	
	public static Settings getInstance() {
		if(instance==null) instance = new Settings();
		return instance;
	}
	
	private Settings() {
		settings = new EnumMap<Required, Object>(Required.class);
	}

	private String rulesFileLocation = null;
	private String rules = null;
	
	private static Map<Required, Object> settings = null;
	
	public void setValue(Required r, Object value) {
		settings.put(r, value);
	}
	
	public Object getValue(Required r) {
		return settings.get(r);
	}

	/**
	 * @return whether all the required information has been loaded or entered
	 */
	public boolean setupComplete() {
		for(Required r : settings.keySet()) if (settings.get(r) == null) return false;
		
		return true;
	}

	public String getRulesFileLocation() {
		return rulesFileLocation;
	}

	public void setRulesFileLocation(String rulesFileLocation) {
		this.rulesFileLocation = rulesFileLocation;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}
	
}
