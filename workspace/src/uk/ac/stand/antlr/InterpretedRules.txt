package uk.ac.stand.antlr;

import java.io.File;
import java.util.Map;

public class InterpretedRules extends Rules {

	public InterpretedRules(File location) {
		super(location);
	}

	@Override
	public boolean loadData() {
		if(!location.exists()) return false;
		
		
		/*
		settings.setFlagValue("numRounds", numRounds);
		settings.setFlagValue("speakersPerTeam", numSpeakersPerTeam);
		settings.setFlagValue("numTeams", numTeams);
		settings.setFlagValue("teamsPerSide", numTeamsPerSide);
		*/

		return true;
		
	}

	@Override
	public Map<String, DrawFunction> loadDrawRules() {
		if(!location.exists()) return null;
		
		
		return null;
	}

}
