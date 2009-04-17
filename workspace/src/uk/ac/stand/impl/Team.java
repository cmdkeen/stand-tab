package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Team extends FlagUser implements Serializable, ITeam {
	
	private static Flags flags = null;
	
	//Every function here must be referenced in runBuiltInFunction and return something
	//TODO add in MultFlag("positions", Integer.class) - then rewrite Scala position array
	private static Flag[] functions = {new Flag("TotalScore", Integer.class), new Flag("TotalSpeakerScore", Integer.class)};
	private static final long serialVersionUID = 50320091L;
	
	public static Flag[] getBuiltInStatic() {
		return functions;
	}
	
	public static Flags getFlagsStatic() {
		if(flags==null) flags = Competition.getInstance().getTeamFlags(); //Get around serialisation
		return flags;
	}
	
	public static void setFlags(Flags flags) {
		Team.flags = flags;
	}
		
	private Map<Integer, Integer> results;
	private ArrayList<ISpeaker> speakers;
	
	public Team() throws StoreException {
		speakers = new ArrayList<ISpeaker>();			
		results = new HashMap<Integer, Integer>();
	}

	public void addResult(int round, Integer result) {
		Flag f = flags.getFlagFromSimilar(new MultFlag("Result",round,Integer.class));
		if(f!=null) {
			results.put(round, result);
		}
		
	}

	public boolean addSpeaker(ISpeaker speaker) {
		//TODO decide what to do about failure - throw or return false? - probably return
		try {
			if(!speakers.contains(speaker) && speakers.size()<(Integer) Settings.getInstance().getFlagValue("speakersPerTeam")) {
				Flag f = flags.getFlagFromSimilar(new MultFlag("Speaker",speakers.size(), ISpeaker.class));
				//If a flag to access this speaker exists
				if(f!=null) {
					speakers.add(speaker);
					return true;
				}
				
			}
		} catch (Exception e) {
			//Do nothing just let return false
		}
		return false;
	}

	public Flag[] getBuiltInFunctions() {
		return functions;
	}
	
	public Flags getFlags() {
		if(flags==null) flags = Competition.getInstance().getTeamFlags(); //Get around serialisation
		return flags;
	}

	public Collection<ISpeaker> getSpeakers() {
		return speakers;
	}

	public Integer getTeamResult(int round) {
		return results.get(round);
	}
	
	public Map<Integer, Integer> getTeamResults() {
		return results;
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

	public Object runInterpretedFunction(Flag flag, Object... args) throws Exception {
			return flags.getFunction(flag).run(this, args);
	}
	
	public void setSpeakers(Collection<ISpeaker> speakers) throws StoreException {
		this.speakers = new ArrayList<ISpeaker>(speakers); 
		for(int i = 0; i < speakers.size(); i++) {
			//getFlagFromSimilar used to a) check such a flag expected b) enter the value with the same object as in Flags
			Flag f = flags.getFlagFromSimilar(new MultFlag("Speaker",i, ISpeaker.class));
			if(f==null) {
				System.err.println("Error on flag " + new MultFlag("Speaker",i, ISpeaker.class));
				System.exit(-1);
			}
			//Slightly dodge
			setFlagValue(f, this.speakers.get(i));
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

}
