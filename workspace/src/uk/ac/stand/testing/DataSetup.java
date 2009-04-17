package uk.ac.stand.testing;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.interfaces.ITeam;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.impl.Team;

public class DataSetup {
	
	public static void addTeamsSpeakers() throws Exception {
		Settings settings = Settings.getInstance();
		for(int i = 0; i < (Integer) settings.getFlagValue("numTeams"); i++) {
			ITeam t = new Team();
			t.setFlagValue("TeamName","Team_" + i);
			t.setFlagValue("Institution", "Inst_" + ((i % 4) + 1));
			
			Competition.getInstance().addTeam(t);
			
			for(int j = 0; j < (Integer) settings.getFlagValue("speakersPerTeam"); j++) {
				ISpeaker s = new Speaker(t);
				
				Competition.getInstance().addSpeaker(s);
				
				s.setFlagValue(Speaker.getFlagsStatic().getFlagFromString("SpeakerName"), "Speaker_" + j);
			}
		}
		
	}
	
}
