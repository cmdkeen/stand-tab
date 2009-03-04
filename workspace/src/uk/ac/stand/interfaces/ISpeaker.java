package uk.ac.stand.interfaces;

import java.util.Map;

public interface ISpeaker extends IFlagUser {

	public ITeam getTeam();
	
	public Map<Integer, Integer> getScores();
	
	public int getScore(int round);
	
	public void addScore(int round, int score);
		
}
