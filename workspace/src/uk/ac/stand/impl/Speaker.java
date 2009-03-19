package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Speaker extends FlagUser implements ISpeaker, Serializable {

	private static Flags flags = null;
	
	private static Flag[] functions = {new Flag("TotalScore", Integer.class)}; //The built in functions - i.e. hard coded in this class

	private static final long serialVersionUID = 50320091L;
	
	public static Flag[] getBuiltInStatic() {
		return functions;
	}
	public static Flags getFlagsStatic() {
		if(flags==null) flags = Competition.getInstance().getSpeakerFlags(); //Get around serialisation
		return flags;
	}
	
	public static void setFlags(Flags flags) {
		Speaker.flags = flags;
	}
	
	private Map<Integer, Integer> scores;
	
	private ITeam team;
	
	public Speaker(ITeam team) throws StoreException {
		this.team = team;
		scores = new HashMap<Integer, Integer>();
	}

	public void addScore(int round, int score) {
		Flag f = flags.getFlagFromSimilar(new MultFlag("Result",round, Integer.class));
		if(f!=null) {
			scores.put(round, score);
		}
	}

	public Flag[] getBuiltInFunctions() {
		return functions;
	}

	public Flags getFlags() {
		if(flags==null) flags = Competition.getInstance().getTeamFlags(); //Get around serialisation
		return flags;
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
	
	public Object runBuiltInFunction(Flag name, Object... args) {
		for(int i = 0; i<functions.length; i++) {
			if(functions[i].equals(name)) {
				switch (i) {
				case 0:
					return totalScore();
				}
			}
		}
		return null; //No function by that name found
	}

	public Object runInterpretedFunction(Flag flag, Object... args)	throws Exception {
		return flags.getFunction(flag).run(this, args);
	}

	public String toString() {
		String s;
		try {
			s = (String) getFlagValue(flags.getFlagFromString("SpeakerName"));
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		if(s instanceof String) return s;
		return "fail";
	}

	private Integer totalScore() {
		int r = 0;
		for(Integer i : scores.values()) r += i;
		return r;
	}
	

}
