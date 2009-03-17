package uk.ac.stand.testing;

import java.util.LinkedList;

import uk.ac.stand.minion.EssenceToMinion;

public class RunnerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		Runner run = new Runner();
		
		run.toMinion("");
		
		String out = run.runMinion();
		*/
		
		EssenceToMinion etm = new EssenceToMinion(interactiveTest, "");
		
		//LinkedList<String> sols = etm.runMinionOpt();
		etm.runMinionInteractive();
			
		while(etm.isRunning()) {
		
			LinkedList<String> sols = etm.getLatestResult();
					
			if(sols==null) {
				System.out.println("Nothing found");
				return;
			}

			System.out.println("Value: " + etm.getObjectiveValue());
			
			Integer[][] v = EssenceToMinion.getIntValues2D(sols);
			
			for(int i = 0; i < v.length; i++) {
				for(int j = 0; j < v[i].length; j++) {
					System.out.print(v[i][j] + " ");
				}
				System.out.println();
			}
		
		}
		
		//Once more once finished running to get last value
		LinkedList<String> sols = etm.getLatestResult();
		
		if(sols==null) {
			System.out.println("Nothing found");
			return;
		}
		
		System.out.println("Value: " + etm.getObjectiveValue());
		
		Integer[][] v = EssenceToMinion.getIntValues2D(sols);
		
		for(int i = 0; i < v.length; i++) {
			for(int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public static String in = "language ESSENCE' 1.b.a\n" + 
	"\n" + 
	"$given weight, tpr, size, rounds : int\n" + 
	"\n" + 
	"given weight, tpr, ppsize, cppsize, desiredsize, rounds : int\n" + 
	"\n" + 
	"given pp : matrix indexed by [int(1..ppsize),int(1..tpr)] of int(0..(rounds-1))\n" + 
	"given cpp:  matrix indexed by [int(1..cppsize),int(1..tpr)] of int(0..(rounds-1))\n" + 
	"\n" + 
	"find allocations : matrix indexed by [int(1..ppsize),int(1..tpr)] of bool\n" + 
	"find pullups: matrix indexed by [int(1..cppsize),int(1..tpr)] of bool\n" + 
	"\n" + 
	"minimising (sum team : int(1..ppsize) . sum pos : int(1..tpr) . \n" + 
	"	allocations[team, pos] * pp[team,pos]* weight\n" + 
	"	)\n" + 
	"	+\n" + 
	"	(sum team : int(1..cppsize) . sum pos : int(1..tpr) . \n" + 
	"	pullups[team, pos] * cpp[team,pos]* weight\n" + 
	"	)\n" + 
	"\n" + 
	"such that\n" + 
	"\n" + 
	"$each row only contains a single 1 - ie. only assigned for one team\n" + 
	"forall team : int(1..ppsize) .\n" + 
	"	(sum allocs : int(1..tpr) . allocations[team, allocs]) = 1,\n" + 
	"\n" + 
	"$the total sum of pullups = the number of pullups needed\n" + 
	"(sum team : int(1..cppsize) . sum pos : int(1..tpr) . \n" + 
	"	pullups[team, pos]) = (desiredsize-ppsize),\n" + 
	"\n" + 
	"$each column adds up to the number of rooms required\n" + 
	"forall col : int(1..tpr) . \n" + 
	"	(sum team : int(1..ppsize) . allocations[team,col]) \n" + 
	"+ (sum team : int(1..cppsize) . pullups[team,col]) \n" + 
	"= (desiredsize/tpr)\n" + 
	"";
	
	public static String param = "language ESSENCE' 1.b.a\n" + 
	"\n" + 
	"parameter ppsize is 3\n" + 
	"parameter cppsize is 3\n" + 
	"parameter desiredsize is 4\n" + 
	"parameter tpr is 4\n" + 
	"parameter weight is 2\n" + 
	"parameter rounds is 3\n" + 
	"\n" + 
	"parameter pp is  [\n" + 
	"	[0,1,1,1],\n" + 
	"	[1,0,1,1],\n" + 
	"	[1,1,1,0]\n" + 
	"	]\n" + 
	"\n" + 
	"parameter cpp is [\n" + 
	"	[0,1,1,1],\n" + 
	"	[1,0,1,1],\n" + 
	"	[1,1,0,1]\n" + 
	"	]\n" + 
	"";
	
	public static String otherTest = "language ESSENCE' 1.b.a\n" + 
			"\n" + 
			"find b : matrix indexed by [int(1..8),int(1..4), int(1..3)] of int(1..100)\n" +
			"such that\n" +
			"alldifferent(b[..,1,1])";
	
	public static String interactiveTest = "language ESSENCE' 1.b.a\n" + 
			"\n" + 
			"letting n be domain int(1..10000)\n" + 
			"\n" + 
			"find i : n\n" + 
			"\n" + 
			"maximising i";

}
