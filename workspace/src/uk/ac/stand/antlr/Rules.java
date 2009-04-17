package uk.ac.stand.antlr;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenRewriteStream;

import uk.ac.stand.scalafiles.Stab;
import uk.ac.stand.scalafiles.Stab.*;
import uk.ac.stand.antlr.gen.stabLexer;
import uk.ac.stand.antlr.gen.stabParser;
import uk.ac.stand.impl.Flag;
import uk.ac.stand.impl.Flags;
import uk.ac.stand.impl.MultFlag;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.Speaker;
import uk.ac.stand.impl.Team;
import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.interfaces.ISpeaker;

public class Rules {

	private Map<Flag, Object> defaults = new HashMap<Flag, Object>();
	private Map<String, Map<Flag, Object>> interp = new HashMap<String, Map<Flag, Object>>();
	
	public Rules(File location) throws IOException, RecognitionException {
		
		ANTLRFileStream fs = new ANTLRFileStream(location.getAbsolutePath());
		stabLexer lex = new stabLexer(fs);
		TokenRewriteStream tokens = new TokenRewriteStream(lex);
		stabParser grammar = new stabParser(tokens);
		
		Program program = grammar.program();
		
		Stab.runInitial(program);
		
		//Store the output immediately as may change with another interpretation 
		interp.clear();
		interp.put("c", Stab.getSettings());
		interp.put("t", Stab.getTeamFields());
		interp.put("s", Stab.getSpeakerFields());
	}
	
	public Flags createSettingsFlags() {
		Map<Flag, Object> out = interp.get("c");
		
		List<Flag> tflag = new LinkedList<Flag>();
		//Application required flags
			tflag.add(new Flag("numTeams", Integer.class));
			tflag.add(new Flag("speakersPerTeam", Integer.class));
			tflag.add(new Flag("numRounds", Integer.class));
			tflag.add(new Flag("teamsPerSide", Integer.class));
			
		for(Flag f : out.keySet()) {
			tflag.add(f);
			defaults.put(f, out.get(f));
		}
		
		Flags sf = new Flags(tflag.toArray(new Flag[tflag.size()]), null, null);
		Settings.setFlags(sf);
		
		return sf;
	}

	public Flags createTeamFlags() {
		Map<Flag, Object> out = interp.get("t");
		
		List<Flag> tflag = new LinkedList<Flag>();
			tflag.add(new Flag("TeamName", String.class));
			tflag.add(new Flag("Institution", String.class));
			
		for(Flag f : out.keySet()) tflag.add(f);
			
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
		Map<Flag, Object> out = interp.get("s");
		
		List<Flag> sflag = new LinkedList<Flag>();
		
		sflag.add(new Flag("SpeakerName", String.class));
			
		for(Flag f : out.keySet()) sflag.add(f);
		
		try {
			for(int i = 1; i <= (Integer) Settings.getInstance().getFlagValue("numRounds"); i++) sflag.add(new MultFlag("Result",i, Integer.class));
		} catch (Exception e) {
			return null;
		}
		
		Flags speakerFlags = new Flags(sflag.toArray(new Flag[sflag.size()]), Speaker.getBuiltInStatic(), null);
		
		Speaker.setFlags(speakerFlags);
		
		return speakerFlags;
	}
	
	public void storeDefaultValues() {
		Settings settings = Settings.getInstance();
		for(Flag f : defaults.keySet()) if(settings.getFlags().containsFlag(f))
			try {
				settings.setFlagValue(f, defaults.get(f));
			} catch (StoreException e) {
				e.printStackTrace();
			}
	}
	
}
