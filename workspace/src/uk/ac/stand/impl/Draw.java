package uk.ac.stand.impl;

import java.util.ArrayList;

import uk.ac.stand.interfaces.ITeam;

public class Draw {

	private int round;
	private ArrayList<Room> rooms;
	
	public Draw(int round) {
		this.round = round;
		rooms = new ArrayList<Room>();
	}
	
	/**
	 * Takes a number of teams, divisible by the number of teams in a room, and puts them into rooms.
	 * Assumes that any team can be drawn against any other team of a different position.
	 * 
	 * @param teams an array of teams arranged so that when viewed in conjunction with an array of positions the given position for a team can be determined
	 */
	public void addTeams(ITeam[] teams) {
		Position[] positions = Position.getPositionArray();
		
		for(int i = 0; i < (teams.length / positions.length); i++) {
			//For each room
			Room r = new Room();
			
			for(int j = 0; j < positions.length; j++) {
				r.addTeam(positions[j], teams[(i * positions.length) + j]);
			}
			
			rooms.add(r);
		}
		
	}

	public int getRound() {
		return round;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
}
