package uk.ac.stand.testing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uk.ac.stand.enums.Required;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Room;
import uk.ac.stand.interfaces.ISpeaker;
import uk.ac.stand.interfaces.ITeam;

public class Simulation {

	private Random rng = null;
	
	public Simulation() {
		this(System.currentTimeMillis());
	}
	
	public Simulation(long seed) {
		rng = new Random(seed);
		
	}
	
	public void makeResults(int round) {
		Draw d = Competition.getInstance().getDraw(round);
		int tpr = d.getRooms().get(0).getTeams().keySet().size();
		
		ArrayList<Integer> scores = new ArrayList<Integer>(tpr);
		
		for(int i = 0; i < tpr; i++) scores.add(i);
		
		Map<ITeam, Integer> tr = new HashMap<ITeam,Integer>();
		Map<ISpeaker, Integer> sr = new HashMap<ISpeaker, Integer>();
		
		for(Room r : d.getRooms()) {
			Collections.shuffle(scores, rng);	
			
			//Random list of speaks, in pairs sorted by position
			List<Integer> s = generateSpeaks(tpr,(Integer)Required.SPEAKERS_PER_TEAM.getValue());
			
			tr.clear();
			sr.clear();
			
			List<ITeam> teams = new ArrayList<ITeam>(r.getTeams().values());
			Collections.shuffle(teams, rng);
			
			int sp = 0;
			
			for(int i = 0; i < teams.size(); i++) {
				teams.get(i).addResult(round, scores.get(i));
				for(ISpeaker speaker : teams.get(i).getSpeakers()) {
					speaker.addScore(round, s.get(sp));
					sp++;
				}
			}
			
		}
		
	}
	
	private List<Integer> generateSpeaks(int tpr, int spt) {
		List<Integer> s = new ArrayList<Integer>(tpr * spt);
		List<Integer> ts = new ArrayList<Integer>(spt);
		
		//Generate the speaks
		s.clear();
		int floor = rng.nextInt(16) + 55; //Minimum between 55 - 70 inc
		int prev = 0;
		int running = 0;
		
		for(int i = 0; i < tpr; i++) {
			ts.clear();
 			for(int j = 0; j < spt; j++) {
				int n = floor + rng.nextInt(4);
 				ts.add(n);
 				running += n;
			}
 			if(running <= prev) ts.add(ts.remove(ts.size()-1) + (prev - running +1)); //Remove the last element and replace with one large enough
 			prev = running;
 			running = 0;
 			
 			s.addAll(ts);
 			
 			//Increase the lower bound for the next position
 			floor += rng.nextInt(4) + 1;
		}
		
		return s;
	}
	
	

	
}
