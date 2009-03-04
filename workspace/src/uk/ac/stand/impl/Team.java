package uk.ac.stand.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Team extends FlagUser implements ITeam {
	
	//Every function here must be referenced in runBuiltInFunction and return something
	private static Flag[] functions = {new Flag("TotalScore"), new Flag("TotalSpeakerScore")};
	private static Flags flags = null;
	
	public static void setFlags(Flags flags) {
		Team.flags = flags;
	}
	
	public Flags getFlags() {
		return flags;
	}
	
	public static Flags getFlagsStatic() {
		return flags;
	}
		
	private ArrayList<ISpeaker> speakers;
	private Map<Integer, Integer> results;
	
	public Team() {
		speakers = new ArrayList<ISpeaker>((Integer) Required.SPEAKERS_PER_TEAM.getValue());
		setFlagValue(flags.getFlagFromString("Speaker"), speakers);
				
		results = new HashMap<Integer, Integer>((Integer) Required.ROUNDS.getValue());
		setFlagValue(flags.getFlagFromString("Result"),results);
	}

	public Collection<ISpeaker> getSpeakers() {
		return speakers;
	}

	public Map<Integer, Integer> getTeamResults() {
		return results;
	}

	public void addSpeaker(ISpeaker speaker) {
		//TODO decide what to do about failure - throw or return false? - probably return
		if(!speakers.contains(speaker) && speakers.size()<(Integer) Required.SPEAKERS_PER_TEAM.getValue()) {
			Flag f = flags.getFlagFromSimilar(new MultFlag("Speaker",speakers.size()));
			//If a flag to access this speaker exists
			if(f!=null) {
				speakers.add(speaker);
			}
			
		}
	}
	
	public void setSpeakers(Collection<ISpeaker> speakers) {
		this.speakers = new ArrayList<ISpeaker>(speakers); 
		for(int i = 0; i < speakers.size(); i++) {
			//getFlagFromSimilar used to a) check such a flag expected b) enter the value with the same object as in Flags
			Flag f = flags.getFlagFromSimilar(new MultFlag("Speaker",i));
			if(f==null) {
				System.err.println("Error on flag " + new MultFlag("Speaker",i));
				System.exit(-1);
			}
			//Slightly dodge
			setFlagValue(f,this.speakers.get(i));
		}
	}

	public Integer getTeamResult(int round) {
		return results.get(round);
	}

	public void addResult(int round, Integer result) {
		Flag f = flags.getFlagFromSimilar(new MultFlag("Result",round));
		if(f!=null) {
			results.put(round, result);
		}
		
	}
	
	public String toString() {
		String s;
		try {
			s = (String) getFlagValue(flags.getFlagFromString("TeamName"));
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		if(s instanceof String) return s;
		return "fail";
	}

	public Object runBuiltInFunction(Flag name, Object... args) {
		//Could use binarySearch on a sorted functions, but would required guaranteeing the switch cases are perfectly in order (as can't switch on String)
		for(int i = 0; i<functions.length; i++) {
			if(functions[i].equals(name)) {
				switch (i) {
				case 0:
					return totalScore();
				case 1:
					return totalSpeakerScore();
				}
			}
		}
		return null; //No function by that name found
	}

	private Integer totalScore() {
		int r = 0;
		for(Integer i : results.values()) r += i;
		return r;
	}
	
	private Integer totalSpeakerScore() {
		int r = 0;
		for(ISpeaker s : speakers) for(Integer i : s.getScores().values()) r += i;
		return r;
	}

	public Object runInterpretedFunction(Flag flag, Object... args) throws Exception {
			return flags.getFunction(flag).run(this, args);
	}

	public Flag[] getBuiltInFunctions() {
		return functions;
	}
	
	public static Flag[] getBuiltInStatic() {
		return functions;
	}


}
