package uk.ac.stand.interfaces;

import java.util.Collection;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;


public interface ITeam extends IFlagUser {
	
	//TODO javadoc

	public Collection<ISpeaker> getSpeakers();

	public void setSpeakers(Collection<ISpeaker> speakers) throws StoreException; //Also add to Speaker flag

	public Map<Integer, Integer> getTeamResults();

	public void addResult(int round, Integer result);
	
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