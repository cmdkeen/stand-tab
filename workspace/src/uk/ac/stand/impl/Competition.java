package uk.ac.stand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Competition {

	//Singleton Factory
	
	private static Competition instance = null;
	
	private Competition() {
		setupComplete = false; //Force setup to be run before can start doing anything
	}
	
	public static Competition getInstance() {
		if(instance==null) instance = new Competition();
		return instance;
	}
	
	//Competition global setup data
	private boolean setupComplete = false;
	
	private String[] teamData = null;
	private int[] teamDataMul = null;
	
	private String[] speakerData = null;
	private int[] speakerDataMul = null;
	
	//Competition global data
	private ArrayList<ITeam> teams = null;
	private ArrayList<ISpeaker> speakers = null;
	private Map<Integer,Draw> rounds = null;
	
			
	public void loadRules() {
		//TODO implement
		//Clever thing to do with speakers and other multiple value things, create as Speaker, Speaker - then in SpeakerNumber have the associated value
		//For now:
		
		String ttemp[] = {"TeamName","Institution","Speaker","Speaker","TotalScore"};
		int nttemp[] = {0,0,1,2,0}; //0 indicates that it is not a multi attribute piece of data
		teamData = ttemp;
		teamDataMul = nttemp;
		
		String stemp[] = {"SpeakerName", "TotalScore"};
		int sttemp[] = {0,0};
		speakerData = stemp;
		speakerDataMul = sttemp;
		
	}

	public String[] getTeamData() {
		return teamData;
	}

	public String[] getSpeakerData() {
		return speakerData;
	}

	public int[] getTeamDataMul() {
		return teamDataMul;
	}

	public int[] getSpeakerDataMul() {
		return speakerDataMul;
	}

	public Collection<ITeam> getTeams() {
		return teams;
	}
	
	public Collection<ISpeaker> getSpeakers() {
		return speakers;
	}
	
	public void addTeam(ITeam team) {
		teams.add(team);
	}
	
	public void addSpeaker(ISpeaker speaker) {
		speakers.add(speaker);
		speaker.getTeam().addSpeaker(speaker); //Add the speaker to the specified team - i.e. we can't have unassigned speakers
	}

	public boolean isSetupComplete() {
		return setupComplete;
	}
	
	public void addDraw(int round, Draw draw) {
		rounds.put(round, draw);
	}
	
	public Draw getDraw(int round) {
		if(!rounds.containsKey(round)) return null;
		return rounds.get(round);
	}
	
	public boolean setup() {
		Settings settings = Settings.getInstance();
		
		if(!settings.setupComplete()) return false;
		
		loadRules();
		
		Integer numTeams = (Integer) settings.getValue(Required.NUMBER_OF_TEAMS);
		teams = new ArrayList<ITeam>(numTeams);
		speakers = new ArrayList<ISpeaker>(numTeams * (Integer) settings.getValue(Required.SPEAKERS_PER_TEAM));
		
		rounds = new HashMap<Integer,Draw>((Integer)settings.getValue(Required.ROUNDS));
		
		setupComplete = true;
		return true;
	}
}
