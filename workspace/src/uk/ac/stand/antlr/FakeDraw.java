package uk.ac.stand.antlr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import uk.ac.stand.enums.Required;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Position;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.interfaces.ITeam;
import uk.ac.stand.minion.EssenceToMinion;
import uk.ac.stand.scalafiles.*;

public class FakeDraw {
	
	//TODO Replace this!!!! - probably with a class that takes a rule set
	
	public static String[] draws = {"Random WUDC", "WUDC", "WSDC"};
	
	public FakeDraw() {
		
	}
	
	public Draw doDraw(int round, String drawType) {
		Draw d = null;
		
		ArrayList<String> dr = new ArrayList<String>(Arrays.asList(draws)); //Really kludgy
		
		switch(dr.indexOf(drawType)) {
		case 0:
			d = RandWUDC(round);
			break;
		case 1:
			d  = WUDC(round);
			break;
		case 2:
			d = WSDC(round);
			break;
		default:
			System.err.println("Error on supplied draw type: " + drawType);
			System.exit(-1);
		}
		
		return d;
	}
	
	private static Draw RandWUDC(int round) {
		Draw ret = new Draw(round);
		
		ArrayList<ITeam> teams = Competition.getInstance().getTeams();
		Collections.shuffle(teams);
		ret.addTeams(teams.toArray(new ITeam[teams.size()]));
		
		return ret;
	}
	
	private static Draw WUDC(int round) {
		Draw ret = new Draw(round);
		Map<Integer, LinkedList<ITeam>> mpools = new HashMap<Integer, LinkedList<ITeam>>();
		
		for(ITeam t : Competition.getInstance().getTeams()) {
			int score = 0;
			for(Integer r : t.getTeamResults().keySet()) score += t.getTeamResults().get(r);
			
			if(!mpools.containsKey(score)) mpools.put(score, new LinkedList<ITeam>());
			mpools.get(score).add(t);
		}
		
		LinkedList<Integer> order = new LinkedList<Integer>(mpools.keySet());
		Collections.sort(order);
		Collections.reverse(order);
		
		List<List<ITeam>> pools = new LinkedList<List<ITeam>>();
		
		int tpr = (Integer)Settings.getInstance().getValue(Required.TEAMS_PER_SIDE) * 2; //4
		
		LinkedList<ITeam> j = new LinkedList<ITeam>();
		for(Integer i : order) {
			LinkedList<ITeam> tlist = mpools.get(i);
			if(j.size()==0) {
				j.addAll(tlist); tlist.clear();
			}
			if(j.size()>0 && j.size()%tpr!=0) {
				while(j.size()%tpr!=0 && tlist.size()>0) j.add(tlist.remove());
			}
			if(j.size()%tpr==0) {
				pools.add(j);
				j = new LinkedList<ITeam>();
			}
			if(tlist.size()>0) {j.addAll(tlist); tlist.clear();}
		}
		 
		System.out.println(pools.size() + " pools");
		
		for(List<ITeam> p : pools) {
			if(p.size()==0) continue;
			
			for(ITeam t : p) System.out.print(t.getFlag("TeamName") + " ");
			System.out.println();
			
			EssenceToMinion etm = new EssenceToMinion(WUDC_In, WUDC_Param(p, round));
			
			LinkedList<String> sols = etm.runMinionOpt();
			
			Language l = new Language();
			
			Language.Var[] vars = new Language.Var[1];
			
			int[] t = {p.size(),(Integer)Settings.getInstance().getValue(Required.TEAMS_PER_SIDE) * 2};
			
			vars[0] = l.createArray(t, l.new EInt());
			
			ScalaInterface.extractData(sols, vars);
			
			Language.Var[] ea = (Language.Var[])vars[0].getValue();
			
			int[][] output = new int[ea.length][];
			for(int i = 0; i < ea.length; i++) {
				output[i] = ScalaInterface.extractIntArray((Language.EArray)ea[i]);
			}
			
			Map<Position, LinkedList<ITeam>> map = new HashMap<Position, LinkedList<ITeam>>();
			Position[] pos = Position.getPositionArray();
			ArrayList<ITeam> teams = new ArrayList<ITeam>(p);
			for(int col = 0; col < pos.length; col++) {
				LinkedList<ITeam> ll = new LinkedList<ITeam>();
				
				for(int row = 0; row < output.length; row++) {
					if(output[row][col]==1)  ll.add(teams.get(row));
				}
				
				map.put(pos[col], ll);
			}
		
		
			ret.addTeams(map, teams.size());
		}
		
		return ret;
	}
	
	private static Draw WSDC(int round) {
		EssenceToMinion etm = new EssenceToMinion(WSDC_In, WSDC_Param());
		
		LinkedList<String> sols = etm.runMinionOpt();
		
		Language l = new Language();
		
		Language.Var[] vars = new Language.Var[1];
		
		int[] t = {Competition.getInstance().getTeams().size(),(Integer)Settings.getInstance().getValue(Required.TEAMS_PER_SIDE) * 2};
		
		vars[0] = l.createArray(t, l.new EInt());
						
		ScalaInterface.extractData(sols, vars);
		
		//Language.EArray[] ea = (Language.EArray[])vars[0].getValue();
		
		
		
		return null;
	}

	private static String WUDC_In = "language ESSENCE' 1.b.a\n" + 
			"\n" + 
			"given weight, tpr, size, rounds : int\n" + 
			"\n" + 
			"letting TEAMS be domain int(1..size)\n" + 
			"letting TPR be domain int(1..tpr)\n" + 
			"letting ROUNDS be domain int(1..rounds)\n" + 
			"\n" + 
			"given pp : matrix indexed by [TEAMS,TPR] of ROUNDS\n" + 
			"\n" + 
			"find a : matrix indexed by [TEAMS,TPR] of bool\n" + 
			"\n" + 
			"minimising sum t : TEAMS . sum p : TPR . \n" + 
			"	a[t, p] * pp[t,p]* weight\n" + 
			"\n" + 
			"such that\n" + 
			"\n" + 
			"forall t : TEAMS .\n" + 
			"	(sum c : TPR . a[t, c]) = 1,\n" + 
			"\n" + 
			"forall col : TPR .\n" + 
			"	(sum t : TEAMS . a[t, col]) = (size/tpr)\n" + 
			"\n" + 
			"\n" + 
			"";
	private static String WUDC_Param (List<ITeam> p, int round) {
		
			String ret = "language ESSENCE' 1.b.a";

			ret += "parameter size is " + p.size();
			int tpr = (Integer)Settings.getInstance().getValue(Required.TEAMS_PER_SIDE) * 2;
			ret += "\nparameter tpr is " + tpr;
			ret += "\nparameter weight is 2";
			ret += "\nparameter rounds is " + round;

			ret += "\nparameter pp is  [";
			
			ITeam[] t = p.toArray(new ITeam[p.size()]);
			int[] pp = new int[tpr];
			
			Map<Integer, Draw> draws = Competition.getInstance().getDraws();
			
			for(int j = 0; j< t.length; j++) {
				for(Integer i : draws.keySet()) {
					Draw r = draws.get(i);
					if(r.getPosition(t[j])==null) {
						System.out.println("ERROR: " + t[j].getFlag("TeamName"));
						//System.exit(-1);
					}
					//System.out.println(r.getPosition(t[j]).absNumber());
					pp[r.getPosition(t[j]).absNumber()]++;
				}
				ret += "[";
				
					for(int k = 0; k < tpr-1; k++) {
						ret += pp[k] + ",";
					}
					ret += pp[tpr-1];
				
				ret += "],\n";
				
				pp = new int[tpr];
			}
			
			System.out.println(ret.substring(0, ret.length()-2) + "]");
			
			//Drop last comma and char return
			return ret.substring(0, ret.length()-2) + "]";
	}
	
	private static String WSDC_In = "";
	private static String WSDC_Param() {
		return "";
	}
	
}
