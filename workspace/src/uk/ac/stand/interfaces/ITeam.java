package uk.ac.stand.interfaces;

import java.util.Collection;
import java.util.Map;

import uk.ac.stand.impl.exceptions.StoreException;


public interface ITeam extends IFlagUser {

	public Collection<ISpeaker> getSpeakers();

	public void setSpeakers(Collection<ISpeaker> speakers) throws StoreException; //Also add to Speaker flag

	public Map<Integer, Integer> getTeamResults();

	public void addResult(int round, Integer result);
	
	public Integer getTeamResult(int round);
	
	public void addSpeaker(ISpeaker speaker);

}