package uk.ac.stand.networking;

import java.io.Serializable;
import java.util.Map;

import uk.ac.stand.interfaces.ISpeaker;

public class Result implements Serializable {

	/**
	 * Serializable class for the transmission of results data between back end and front end
	 */
	private static final long serialVersionUID = 2032009L;
	
	public String roomName;
	public Integer teamScore;
	public Map<ISpeaker, Integer> speakerScores;
	
}
