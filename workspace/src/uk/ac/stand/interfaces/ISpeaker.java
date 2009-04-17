package uk.ac.stand.interfaces;

import java.util.Map;

public interface ISpeaker extends IFlagUser {

	/**
	 * The team this speaker belongs to
	 * 
	 * @return
	 */
	public ITeam getTeam();
	
	/**
	 * The individual score this speaker has received over the competition
	 * 
	 * @return each round mapped to a score
	 */
	public Map<Integer, Integer> getScores();
	
	/**
	 * The score this speaker received for a round
	 * 
	 * @param round
	 * @return
	 */
	public int getScore(int round);
	
	/**
	 * Sets a score for a particular round
	 * 
	 * @param round
	 * @param score
	 */
	public void addScore(int round, int score);
		
}
