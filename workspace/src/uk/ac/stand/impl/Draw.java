package uk.ac.stand.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import uk.ac.stand.interfaces.ITeam;

public class Draw implements Serializable {
	
	private static final long serialVersionUID = 50320091L;

	private int round;
	private ArrayList<Room> rooms;
	private Map<ITeam, Position> positions;
	
	private int curRoom = 0;
	
	public Draw(int round) {
		this.round = round;
		rooms = new ArrayList<Room>();
		positions = new HashMap<ITeam, Position>();
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
			Room r = new Room("Room: " + curRoom++);
			
			for(int j = 0; j < positions.length; j++) {
				r.addTeam(positions[j], teams[(i * positions.length) + j]);
				this.positions.put(teams[(i * positions.length) + j],positions[j]);
			}
			
			rooms.add(r);
		}
		
	}
	
	/**
	 * Allows many teams to be added, each position is mapped to a list of teams. 
	 * Position x in each list refers to the teams in the same room 
	 * 
	 * @param map
	 * @param numAdded the number of rooms being added
	 */
	public void addTeams(Map<Position,LinkedList<ITeam>> map, int numAdded) {
		for(int i = 0; i < numAdded/map.keySet().size(); i++) {
			
			Room r = new Room("Room: " + curRoom++);
			for(Position p : map.keySet()) {
				if(map.get(p).size()==0) {
					System.out.println("Draw Error - Expecting a team for " + p + " but was not assigned - exiting - see addTeams(map," + numAdded + ")");
					System.exit(-1);
				}
				ITeam t = map.get(p).removeFirst();
				r.addTeam(p, t);
				this.positions.put(t, p);
			}
			rooms.add(r);
		}
		
	}
	
	public Position getPosition(ITeam t) {
		return this.positions.get(t);
	}

	public int getRound() {
		return round;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
		
}
