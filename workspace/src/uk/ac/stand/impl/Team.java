package uk.ac.stand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.IResult;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Team implements ITeam {
	
	private Map<String, Object> store;
	private ArrayList<ISpeaker> speakers;
	private Map<Integer, IResult> results;
	
	public Team() {
		speakers = new ArrayList<ISpeaker>((Integer) Required.SPEAKERS_PER_TEAM.getValue());	
		store = new HashMap<String, Object>(Competition.getInstance().getTeamData().length);
		
		store.put("Speaker", speakers);
		results = new HashMap<Integer, IResult>((Integer) Required.ROUNDS.getValue());
	}

	public Object getFlag(String flagID) {
		return store.get(flagID);
	}

	public Map<String, Object> getFlags() {
		return store;
	}

	public Collection<ISpeaker> getSpeakers() {
		return speakers;
	}

	public Map<Integer, IResult> getTeamResults() {
		return results;
	}

	public void setFlag(String id, Object data) {
		store.put(id, data);
	}

	public void setFlags(Map<String, Object> flags) {
		store = flags;
	}

	public void addSpeaker(ISpeaker speaker) {
		//TODO decide what to do about failure - throw or return false? - probably return
		if(!speakers.contains(speaker) && speakers.size()<(Integer) Required.SPEAKERS_PER_TEAM.getValue()) speakers.add(speaker);
	}
	
	public void setSpeakers(Collection<ISpeaker> speakers) {
		this.speakers = new ArrayList<ISpeaker>(speakers); 
		setFlag("Speaker",this.speakers);
	}

	public void setTeamResults(Map<Integer, IResult> teamResults) {
		this.results = teamResults;
	}

	public void addResult(int round, IResult result) {
		results.put(round, result);
	}
	
	public String toString() {
		String s = (String) getFlag("TeamName");
		if(s instanceof String) return s;
		return "fail";
	}

}
