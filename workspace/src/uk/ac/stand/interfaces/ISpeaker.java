package uk.ac.stand.interfaces;

import java.util.Map;

public interface ISpeaker {

	public ITeam getTeam();
	
	public Map<Integer, Integer> getScores();
	
	public int getScore(int round);
	
	public void addScore(int round, int score);
	
	/**
	 * Returns all of the optional data as a map
	 * @return a mapping of data name to the data
	 */
	public Map<String, Object> getFlags();
	
	/**
	 * @param flagID 	the name of the piece of data required
	 * @return 			the data
	 */
	public Object getFlag(String flagID);

	/**
	 * Assigns an entire mapping to the Team
	 * @param flags 	a map of data to string identifiers
	 */
	public void setFlags(Map<String, Object> flags);
	
	/**
	 * Assigns an object to the flag - all multiple data objects must be added using types that implement Collection (ie. no basic arrays)
	 * 
	 * @param id		a string identifier for the data e.g. "alias"
	 * @param data		the data for that identifier
	 */
	public void setFlag(String id, Object data);
	
}
