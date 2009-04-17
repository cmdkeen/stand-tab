package uk.ac.stand.interfaces;

import java.util.Collection;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;


public interface ITeam extends IFlagUser {

	/**
	 * The speakers this team has assigned.
	 * 
	 * @return
	 */
	public Collection<ISpeaker> getSpeakers();

	/**
	 * Set all the speakers for this team in one go
	 * 
	 * @param speakers
	 * @throws StoreException
	 */
	public void setSpeakers(Collection<ISpeaker> speakers) throws StoreException;

	/**
	 * Returns the results this team has had over a period. Mapping from round to score.
	 * 
	 * @return
	 */
	public Map<Integer, Integer> getTeamResults();

	/**
	 * Adds a team score
	 * 
	 * @param round
	 * @param result
	 */
	public void addResult(int round, Integer result);
	
	/**
	 * Returns the score the team received for the specified round
	 * 
	 * @param round
	 * @return
	 */
	public Integer getTeamResult(int round);
	
	/**
	 * Attempts to add a speaker to the team
	 * Will fail if already enough speakers
	 * 
	 * @param speaker
	 * @return whether the adding succeeded
	 */
	public boolean addSpeaker(ISpeaker speaker);

}