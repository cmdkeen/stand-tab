package uk.ac.stand.testing;

import uk.ac.stand.minion.EssenceToMinion;

public class Hacking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EssenceToMinion etm = new EssenceToMinion(in, param);
		
		etm.runMinion();
		System.out.println(etm.getMinionInput());
		
		/*
		LinkedList<String> sols = etm.runMinionOpt();
		
		System.out.println("Value: " + etm.getObjectiveValue());
		
		Integer[][] v = EssenceToMinion.getIntValues2D(sols);
		
		for(int i = 0; i < v.length; i++) {
			for(int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}

	private static String in = "language ESSENCE' 1.b.a\n" + 
			"\n" + 
			"given setsize, numTeams, rounds : int\n" + 
			"\n" + 
			"letting SETSIZE be domain int(0..setsize-1)\n" + 
			"letting TEAMS be domain int(0..numTeams-1)\n" + 
			"letting SIDE be domain int(1..2)\n" + 
			"letting ROUNDS be domain int(0..rounds-1)\n" + 
			"\n" + 
			"given set : matrix indexed by [TEAMS] of SETSIZE\n" + 
			"\n" + 
			"find a : matrix indexed by [ROUNDS, TEAMS] of TEAMS,\n" + 
			"s : matrix indexed by [ROUNDS, TEAMS] of SIDE,\n" + 
			"se: matrix indexed by [ROUNDS, TEAMS] of SETSIZE\n" + 
			"\n" + 
			"such that\n" + 
			"\n" + 
			"$different opponents\n" + 
			"forall r : ROUNDS . alldifferent(a[r,..]),\n" + 
			"$everyone competiting \n" + 
			"forall t : TEAMS . alldifferent(a[..,t]),\n" + 
			"$don't face themselves\n" + 
			"forall t : TEAMS . forall r : ROUNDS . a[r,t] != t,\n" + 
			"\n" + 
			"$reflexive\n" + 
			"forall t : TEAMS . forall r : ROUNDS . (t = a[r,a[r,t]]),\n" + 
			"\n" + 
			"$1P and 1O team\n" + 
			"forall r : ROUNDS . forall t : TEAMS . (s[r,t] + s[r,a[r,t]] = 3),\n" + 
			"\n" + 
			"$same numbers of prop and opp over competition\n" + 
			"$forall t : TEAMS . (sum r : ROUNDS . s[r,t]) = 12,\n" + 
			"\n" + 
			"forall t: TEAMS . forall r : ROUNDS . set[t] = se[r,a[r,t]],\n" + 
			"\n" + 
			"forall t : TEAMS . atmost(se[..,t],[5,5,5,5],[1,2,3,4]) /\\ atleast(se[..,t],[1,1,1,1],[1,2,3,4])\n" + 
			"";
	
	private static String param = "language ESSENCE' 1.b.a\n" + 
			"\n" + 
			"parameter setsize is 4\n" + 
			"parameter rounds is 8\n" + 
			"parameter numTeams is 38\n" + 
			"\n" + 
			"parameter set is [\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"1,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"2,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"3,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4,\n" + 
			"4\n" + 
			"]";
}
