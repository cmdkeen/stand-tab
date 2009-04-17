package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.antlr.DrawFile;
import uk.ac.stand.antlr.Rules;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Competition implements Serializable {
	private static final long serialVersionUID = 50320091L;
	
	//Singleton Factory
	private static Competition instance = null;
	
	private Competition() {
		setupComplete = false; //Force setup to be run before can start doing anything
	}
	
	public static Competition getInstance() {
		if(instance==null) instance = new Competition();
		return instance;
	}
	
	/**
	 * Sets a new Competition, used to load a previously exported Competition
	 * 
	 * @param c the new Competition
	 */
	public static void setInstance(Competition c) {
		instance = c;
	}
	
	//Competition global setup data
	private boolean setupComplete = false;
	private Rules settingsRules = null;
	private DrawFile drawFile = null;
		
	public DrawFile getDrawFile() {
		return drawFile;
	}

	public void setDrawFile(DrawFile drawFile) {
		this.drawFile = drawFile;
	}

	//Competition global data
	private ArrayList<ITeam> teams = null;
	private ArrayList<ISpeaker> speakers = null;
	private Map<Integer,Draw> rounds = null;
	
	//The flags for the other classes are stored here so that they are serialised. But only once as Competition is a Singleton, 
	//there is no need to have each instance of a class keep a copy of the Flags for the whole class
	private Flags teamFlags, speakerFlags, settingsFlags = null; 
	
	public Flags getSettingsFlags() {
		return settingsFlags;
	}
	
	public Flags getTeamFlags() {
		return teamFlags;
	}

	public Flags getSpeakerFlags() {
		return speakerFlags;
	}

	/**
	 * Takes the specified rules file and applies any settings rules it has
	 * Will use this rules file for any validation
	 * 
	 * @param r
	 */
	public void loadRules(Rules r) {
		settingsRules = r;
		settingsFlags = r.createSettingsFlags();
	}
	
	/**
	 * Loads team and speaker fields from the specified rules file
	 * The application settings will have been set by this point as these flags
	 * require application specific data such as the number of rounds
	 * 
	 * @param r
	 */
	public void loadDependantRules(Rules r) {
		teamFlags = r.createTeamFlags();
		speakerFlags = r.createSpeakerFlags();
	}

	public ArrayList<ITeam> getTeams() {
		return teams;
	}
	
	public ArrayList<ISpeaker> getSpeakers() {
		return speakers;
	}
	
	public Rules getSettingsRules() {
		return settingsRules;
	}
	
	/**
	 * Adds a team to competition
	 * 
	 * @param team
	 */
	public void addTeam(ITeam team) {
		for(ITeam t : teams) {
			try{ 
			Flag fname = Team.getFlagsStatic().getFlagFromString("TeamName");
			String name = (String)t.getFlagValue(fname);
			if(name.equals((String)team.getFlagValue(fname))) return; //Don't enter as name already exists
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		teams.add(team);
	}
	
	/**
	 * Adds a speaker to the competition
	 * 
	 * @param speaker
	 */
	public void addSpeaker(ISpeaker speaker) {
		speakers.add(speaker);
		speaker.getTeam().addSpeaker(speaker); //Add the speaker to the specified team - i.e. we can't have unassigned speakers
	}

	/**
	 * @return whether the compeition is ready to run or still needs settings to be entered
	 */
	public boolean isSetupComplete() {
		return setupComplete;
	}
	
	/**
	 * Adds a draw for the given round
	 * 
	 * @param round
	 * @param draw
	 */
	public void addDraw(int round, Draw draw) {
		rounds.put(round, draw);
	}
	
	public Draw getDraw(int round) {
		if(!rounds.containsKey(round)) return null;
		return rounds.get(round);
	}
	
	/**
	 * If all the settings have been entered then initialises the fields and marks the competition (via isSetupComplete) as being ready to run.
	 * Moves the state of the application from one of entering settings to entering competitor data.
	 * 
	 * @return whether has initialised
	 */
	public boolean init() {
		Settings settings = Settings.getInstance();
		
		if(!settings.setupComplete()) return false;
		
		try {
			for(Flag f : settings.getFlags().getFields()) settingsRules.validateSetting(f.getName(), settings.getFlagValue(f));
		} catch (Exception e) {
			System.err.println("Competition.init\n" + e.getMessage());
			return false;
		}
		
		teams = new ArrayList<ITeam>();
		speakers = new ArrayList<ISpeaker>();
		rounds = new HashMap<Integer,Draw>();
			
		setupComplete = true;
		return true;
	}

	public Map<Integer,Draw> getDraws() {
		return rounds;
	}
	
}
