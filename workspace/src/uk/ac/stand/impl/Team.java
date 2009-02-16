package uk.ac.stand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Team implements ITeam {
	
	private Map<String, Object> store;
	private ArrayList<ISpeaker> speakers;
	private Map<Integer, Integer> results;
	
	public Team() {
		speakers = new ArrayList<ISpeaker>((Integer) Required.SPEAKERS_PER_TEAM.getValue());	
		store = new HashMap<String, Object>(Competition.getInstance().getTeamData().length);
		
		store.put("Speaker", speakers);
		results = new HashMap<Integer, Integer>((Integer) Required.ROUNDS.getValue());
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

	public Map<Integer, Integer> getTeamResults() {
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

	public void setTeamResults(Map<Integer, Integer> teamResults) {
		Integer i = 0;
		for(Integer j : teamResults.values()) i+=j;
		setFlag("TotalScore", i);
		this.results = teamResults;
	}
	
	public Integer getTeamResult(int round) {
		return results.get(round);
	}

	public void addResult(int round, Integer result) {
		if(getFlag("TotalScore")==null) {
			setFlag("TotalScore",result);
		} else if(results.containsKey(round)) {
			//Update the total score
			setFlag("TotalScore",(Integer)getFlag("TotalScore")-results.get(round)+result);
		} else {
			setFlag("TotalScore",(Integer)getFlag("TotalScore")+result);
		}
		results.put(round, result);
	}
	
	public String toString() {
		String s = (String) getFlag("TeamName");
		if(s instanceof String) return s;
		return "fail";
	}

}
