package uk.ac.stand.antlr;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.MultFlag;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.interfaces.ISpeaker;

public class FakeRules extends Rules {

	public FakeRules(File location) {
		super(location);
	}

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

	@Override
	public boolean loadData() {
		Settings settings = Settings.getInstance();
		
		try {
			settings.setFlagValue("numRounds", 4);
			settings.setFlagValue("speakersPerTeam", 2);
			settings.setFlagValue("numTeams", 12);
			settings.setFlagValue("teamsPerSide", 2);
		} catch (StoreException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		return true;
	}

	@Override
	public Map<String, DrawFunction> loadDrawRules() {
		Map<String, DrawFunction> l = new HashMap<String, DrawFunction>();
		
		for(String s : FakeDraw.draws) l.put(s, new FakeDraw(s));
		
		return l;
	}
	
}
