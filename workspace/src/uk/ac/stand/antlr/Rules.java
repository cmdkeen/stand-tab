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

/**
 * Rules takes a Stab file and reads in any new field or setting that are defined within
 * It is also used to add in any application required settings
 * 
 * @author crh24
 *
 */
public class Rules {

	//Defaults contains any default values for data - particuarly in the cases of teams and speakers this may be needed later
	private Map<Flag, Object> defaults = new HashMap<Flag, Object>();
	//The interpreted data - mapped to whether for teams, speakers or the competition
	private Map<String, Map<Flag, Object>> interp = new HashMap<String, Map<Flag, Object>>();
		
	public Rules(File location) throws IOException, RecognitionException {
		
		//Parse the file
		ANTLRFileStream fs = new ANTLRFileStream(location.getAbsolutePath());
		stabLexer lex = new stabLexer(fs);
		TokenRewriteStream tokens = new TokenRewriteStream(lex);
		stabParser grammar = new stabParser(tokens);
		
		Program program = grammar.program(); //Extract the AST
		
		Stab.runInitial(program); //Parse the AST
		
		//Store the output immediately as may change with another interpretation 
		interp.clear();
		interp.put("c", Stab.getSettings());
		interp.put("t", Stab.getTeamFields());
		interp.put("s", Stab.getSpeakerFields());
	}
	
	public boolean validateSetting(String name, Object value) {
		return Stab.validateSetting(name, value);
	}
	
	public boolean validateTeamField(String name, Team t, Object value) {
		return Stab.validateTeamField(name, t, value);
	}
	
	public boolean validateSpeakerField(String name, Speaker s, Object value) {
		return Stab.validateSpeakerField(name, s, value);
	}
	
	/**
	 * Creates the collection of Flag classes that refer to all the settings fields needed to be gathered.
	 * 
	 * @return
	 */
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

	/**
	 * Creates the collection of Flag classes that refer to all the team fields needed to be gathered.
	 * 
	 * @return
	 */
	public Flags createTeamFlags() {
		Map<Flag, Object> out = interp.get("t");
		
		List<Flag> tflag = new LinkedList<Flag>();
			tflag.add(new Flag("TeamName", String.class));
			tflag.add(new Flag("Institution", String.class));
			
		for(Flag f : out.keySet()) tflag.add(f);
			
		try { //Each speaker and each result have seperate indicies for easy display in the GUI
			for(int i = 0; i < (Integer) Settings.getInstance().getFlagValue("speakersPerTeam"); i++) tflag.add(new MultFlag("Speaker",i, ISpeaker.class));
			for(int i = 1; i <= (Integer) Settings.getInstance().getFlagValue("numRounds"); i++) tflag.add(new MultFlag("Result",i, Integer.class));
		} catch (Exception e) {
			return null;
		}
		
		Flags teamFlags = new Flags(tflag.toArray(new Flag[tflag.size()]), Team.getBuiltInStatic(), null);
		
		Team.setFlags(teamFlags);
		
		return teamFlags;
	}
	
	/**
	 * Creates the collection of Flag classes that refer to all the speaker fields needed to be gathered.
	 * 
	 * @return
	 */
	public Flags createSpeakerFlags() {
		Map<Flag, Object> out = interp.get("s");
		
		List<Flag> sflag = new LinkedList<Flag>();
		
		sflag.add(new Flag("SpeakerName", String.class));
			
		for(Flag f : out.keySet()) sflag.add(f);
		
		try { //The results flag have a seperate index for each round
			for(int i = 1; i <= (Integer) Settings.getInstance().getFlagValue("numRounds"); i++) sflag.add(new MultFlag("Result",i, Integer.class));
		} catch (Exception e) {
			return null;
		}
		
		Flags speakerFlags = new Flags(sflag.toArray(new Flag[sflag.size()]), Speaker.getBuiltInStatic(), null);
		
		Speaker.setFlags(speakerFlags);
		
		return speakerFlags;
	}
	
	/**
	 * Goes through the default values and assigns them all
	 * A good way to start the setup of a competition.
	 */
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
