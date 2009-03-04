package uk.ac.stand.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Speaker extends FlagUser implements ISpeaker {

	private ITeam team;
	
	private static Flag[] functions = {new Flag("TotalScore")}; //The built in functions - i.e. hard coded in this class
	private static Flags flags = null;
	
	public static void setFlags(Flags flags) {
		Speaker.flags = flags;
	}
	
	public Flags getFlags() {
		return flags;
	}
	
	public static Flags getFlagsStatic() {
		return flags;
	}
	
	private Map<Integer, Integer> scores;
	
	public Speaker(ITeam team) {
		this.team = team;
		scores = new HashMap<Integer, Integer>((Integer) Settings.getInstance().getValue(Required.ROUNDS));
		
		setFlagValue(flags.getFlagFromString("Result"), scores);
	}

	public void addScore(int round, int score) {
		Flag f = flags.getFlagFromSimilar(new MultFlag("Result",round));
		if(f!=null) {
			scores.put(round, score);
		}
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

	public Flag[] getBuiltInFunctions() {
		return functions;
	}
	
	public static Flag[] getBuiltInStatic() {
		return functions;
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

	private Integer totalScore() {
		int r = 0;
		for(Integer i : scores.values()) r += i;
		return r;
	}

	public Object runInterpretedFunction(Flag flag, Object... args)	throws Exception {
		return flags.getFunction(flag).run(this, args);
	}
	

}
