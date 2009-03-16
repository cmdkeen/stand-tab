package uk.ac.stand.antlr;

import java.util.LinkedList;
import java.util.List;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.MultFlag;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.interfaces.ISpeaker;

public class Rules {
	
	public Flags createSettingsFlags() {
		List<Flag> tflag = new LinkedList<Flag>();
			tflag.add(new Flag("numTeams", Integer.class));
			tflag.add(new Flag("speakersPerTeam", Integer.class));
			tflag.add(new Flag("numRounds", Integer.class));
			tflag.add(new Flag("teamsPerSide", Integer.class));
			
		Flags sf = new Flags(tflag.toArray(new Flag[tflag.size()]), null, null);
		
		return sf;
	}

	public Flags createTeamFlags() {
		
		List<Flag> tflag = new LinkedList<Flag>();
			tflag.add(new Flag("TeamName", String.class));
			tflag.add(new Flag("Institution", String.class));
			
		try {
			for(int i = 0; i < (Integer) Settings.getInstance().getFlagValue("speakersPerTeam"); i++) tflag.add(new MultFlag("Speaker",i, ISpeaker.class));
			for(int i = 1; i <= (Integer) Settings.getInstance().getFlagValue("numRounds"); i++) tflag.add(new MultFlag("Result",i, Integer.class));
		} catch (Exception e) {
			return null;
		}
		
		Flags teamFlags = new Flags(tflag.toArray(new Flag[tflag.size()]), Team.getBuiltInStatic(), null);
		
		Team.setFlags(teamFlags);
		
		return teamFlags;
	}
	
	public Flags createSpeakerFlags() {
		List<Flag> sflag = new LinkedList<Flag>();
		
		sflag.add(new Flag("SpeakerName", String.class));
		
		try {
			for(int i = 1; i <= (Integer) Settings.getInstance().getFlagValue("numRounds"); i++) sflag.add(new MultFlag("Result",i, Integer.class));
		} catch (Exception e) {
			return null;
		}
		
		Flags speakerFlags = new Flags(sflag.toArray(new Flag[sflag.size()]), Speaker.getBuiltInStatic(), null);
		
		Speaker.setFlags(speakerFlags);
		
		return speakerFlags;
	}
	
}
