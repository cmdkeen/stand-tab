package uk.ac.stand.testing;

import uk.ac.stand.enums.Required;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class DataSetup {

	public DataSetup(int numRounds, int numSpeakersPerTeam, int numTeams, int numTeamsPerSide) {
		
		Settings settings = Settings.getInstance();
		
		settings.setValue(Required.ROUNDS, numRounds);
		settings.setValue(Required.SPEAKERS_PER_TEAM, numSpeakersPerTeam);
		settings.setValue(Required.NUMBER_OF_TEAMS, numTeams);
		settings.setValue(Required.TEAMS_PER_SIDE, numTeamsPerSide);
		
		Competition.getInstance().setup();
		
	}
	
	public static void addTeamsSpeakers() {
		for(int i = 0; i < (Integer) Required.NUMBER_OF_TEAMS.getValue(); i++) {
			ITeam t = new Team();
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("TeamName"),"Team_" + i);
			t.setFlagValue(Team.getFlagsStatic().getFlagFromString("Institution"), "Inst_" + ((i % 4) + 1));
			
			Competition.getInstance().addTeam(t);
			
			for(int j = 0; j < (Integer) Required.SPEAKERS_PER_TEAM.getValue(); j++) {
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