package uk.ac.stand.antlr;

import java.io.File;
import java.util.Map;

import uk.ac.stand.impl.Flags;

public abstract class Rules {

	protected File location;
	
	public Rules(File location) {
		this.location = location;
	}
	
	public Flags createSettingsFlags() {
		return null;
	}

	public Flags createTeamFlags() {
		return null;
	}
	
	public Flags createSpeakerFlags() {
		return null;
	}
	
	public abstract boolean loadData();
	
	public abstract Map<String, DrawFunction> loadDrawRules();
	
}
