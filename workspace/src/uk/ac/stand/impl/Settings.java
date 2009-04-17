package uk.ac.stand.impl;

public class Settings extends FlagUser {

	private static final long serialVersionUID = 160320091L;
	private static Flags flags = null;
	
	private static Settings instance = null;
	
	//Singleton factory
	
	private Settings() {
		super();
	}
	
	public static Settings getInstance() {
		if(instance==null) instance = new Settings();
		return instance;
	}
	
	//Flag user required methods

	public Flag[] getBuiltInFunctions() {
		return null;
	}

	public Flags getFlags() {
		if(flags==null) flags = Competition.getInstance().getSettingsFlags(); //Get around serialisation
		return flags;
	}

	public Object runBuiltInFunction(Flag name, Object... args) {
		//There are no built in functions
		return null;
	}

	public Object runInterpretedFunction(Flag flag, Object... args) throws Exception {
		return flags.getFunction(flag).run(this, args);
	}
	
	public static void setFlags(Flags flags) {
		Settings.flags = flags;
	}
	
	public static Flags getFlagsStatic() {
		if(flags==null) flags = Competition.getInstance().getSettingsFlags(); //Get around serialisation
		return flags;
	}
	
	/**
	 * Checks if all the specified fields have been given values yet
	 * 
	 * @return whether the fields have values
	 */
	public boolean setupComplete() {
		if(flags==null) return false;
		for(Flag f : flags.getFields())
			try {
				if(getFlagValue(f)==null) return false;
			} catch (Exception e) {
				return false;
			}
		
		return true;
	}

	public boolean isValid(String name, Object value) {
		//Call to the current rules file to check if value is a valid assignment
		return Competition.getInstance().getSettingsRules().validateSetting(name, value);
	}

}
