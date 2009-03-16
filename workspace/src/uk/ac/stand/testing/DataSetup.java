package uk.ac.stand.testing;

import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.interfaces.ITeam;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.impl.exceptions.StoreException;

public class DataSetup {
	
	//TODO replace with user input generated

	public DataSetup(int numRounds, int numSpeakersPerTeam, int numTeams, int numTeamsPerSide, boolean setup) throws StoreException {
		
		Settings settings = Settings.getInstance();
		
		settings.setFlagValue("numRounds", numRounds);
		settings.setFlagValue("speakersPerTeam", numSpeakersPerTeam);
		settings.setFlagValue("numTeams", numTeams);
		settings.setFlagValue("teamsPerSide", numTeamsPerSide);
		
		if(setup) Competition.getInstance().setup();
	}
	
	public static void addTeamsSpeakers() throws Exception {
		Settings settings = Settings.getInstance();
		for(int i = 0; i < (Integer) settings.getFlagValue("numTeams"); i++) {
			ITeam t = new Team();
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("TeamName"),"Team_" + i);
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("Institution"), "Inst_" + ((i % 4) + 1));
			
			Competition.getInstance().addTeam(t);
			
			for(int j = 0; j < (Integer) settings.getFlagValue("speakersPerTeam"); j++) {
				ISpeaker s = new Speaker(t);
				
				Competition.getInstance().addSpeaker(s);
				
				s.setFlagValue(Speaker.getFlagsStatic().getFlagFromString("SpeakerName"), "Speaker_" + j);
			}
		}
		
		//Competition.getInstance().addDraw(1, genDraw(1));
	}
	/*
	public static Draw genDraw(int round) {
		Draw d = new Draw(round);
		
		LinkedList<ITeam> teams = new LinkedList<ITeam>(Competition.getInstance().getTeams());
		
		Collections.shuffle(teams);
		
		d.addTeams(teams.toArray(new ITeam[teams.size()]));
		
		for(int i = 0; i < d.getRooms().size(); i++) d.getRooms().get(i).setRoomName("Room: " + i); 
		
		return d;
	}
	*/
	
}
