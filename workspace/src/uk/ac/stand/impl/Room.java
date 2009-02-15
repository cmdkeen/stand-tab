package uk.ac.stand.impl;

import java.util.HashMap;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.interfaces.ITeam;

public class Room {

	private Map<Position, ITeam> teams;
	private String roomName;
	
	public Room() {
		this("unnamed");
	}
	
	public Room(String roomName) {
		this.roomName = roomName;
		
		teams = new HashMap<Position,ITeam>(((Integer)Required.TEAMS_PER_SIDE.getValue()) * 2);
	}
	
	public boolean isComplete() {
		return (teams.size()==(((Integer)Required.TEAMS_PER_SIDE.getValue()) * 2));
	}
	
	public void addTeam(Position p, ITeam t) {
		teams.put(p, t);
	}

	public Map<Position, ITeam> getTeams() {
		return teams;
	}
	
	public ITeam getTeam(Position p) {
		return teams.get(p);
	}

	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String name) {
		roomName = name;
	}
	
	
}
