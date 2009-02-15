package uk.ac.stand.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Speaker implements ISpeaker {

	private ITeam team;
	
	private Map<Integer, Integer> scores;
	private Map<String, Object> store;
	
	public Speaker(ITeam team) {
		this.team = team;
		scores = new HashMap<Integer, Integer>((Integer) Settings.getInstance().getValue(Required.ROUNDS));
		store = new HashMap<String, Object>(Competition.getInstance().getSpeakerData().length);
	}

	public void addScore(int round, int score) {
		scores.put(round, score);
	}

	public int getScore(int round) {
		return scores.get(round);
	}

	public Map<Integer, Integer> getScores() {
		return scores;
	}

	public ITeam getTeam() {
		return team;
	}

	public Object getFlag(String flagID) {
		return store.get(flagID);
	}

	public Map<String, Object> getFlags() {
		return store;
	}
	
	public void setFlag(String id, Object data) {
		store.put(id, data);
		
	}

	public void setFlags(Map<String, Object> flags) {
		store = flags;
	}

	public String toString() {
		return (String) store.get("SpeakerName");
	}


}
