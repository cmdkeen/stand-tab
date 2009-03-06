package uk.ac.stand.antlr;

import java.util.LinkedList;
import java.util.List;

import uk.ac.stand.enums.Required;
import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.MultFlag;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;

public class Rules {

	public Flags createTeamFlags() {
		
		List<Flag> tflag = new LinkedList<Flag>();
		tflag.add(new Flag("TeamName"));
		tflag.add(new Flag("Institution"));
		
		for(int i = 0; i < (Integer) Required.SPEAKERS_PER_TEAM.getValue(); i++) tflag.add(new MultFlag("Speaker",i));
		
		for(int i = 1; i <= (Integer) Required.ROUNDS.getValue(); i++) tflag.add(new MultFlag("Result",i));
		
		Flags teamFlags = new Flags(tflag.toArray(new Flag[tflag.size()]), Team.getBuiltInStatic(), null);
		
		Team.setFlags(teamFlags);
		
		return teamFlags;
	}
	
	public Flags createSpeakerFlags() {
		List<Flag> sflag = new LinkedList<Flag>();
		
		sflag.add(new Flag("SpeakerName"));
		
		for(int i = 1; i <= (Integer) Required.ROUNDS.getValue(); i++) sflag.add(new MultFlag("Result",i));
		
		Flags speakerFlags = new Flags(sflag.toArray(new Flag[sflag.size()]), Speaker.getBuiltInStatic(), null);
		
		Speaker.setFlags(speakerFlags);
		
		return speakerFlags;
	}
	
}
