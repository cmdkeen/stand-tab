package uk.ac.stand.minion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author chris
 *
 */
public class EssenceToMinion {

	//global variables for interfacing with Minion
	private String minionOut = null;
	private String essenceIn = null;
	private String essenceParam = null;
	private int objectiveValue = -1;
	private Runner run = null;
	private Process minion = null;
	
	//Minion's output
	private BufferedReader in = null;
	//The last result read from in
	private LinkedList<String> lastResult = null;
	
	//Having examined the minion input we know what the variables are and their sizes
	private List<String> varNames = null;
	private List<Integer> varBounds = null;
	
	public EssenceToMinion(String essenceIn, String essenceParam) {
		this.essenceIn = essenceIn;
		this.essenceParam = essenceParam;
	}
	
	public String getMinionInput() {
		if(run==null) return "";
		if(run.getMinionInput()==null) return "";
		return run.getMinionInput();
	}
	
	/**
	 * Take Essence' code and translate it into Minion input
	 * 
	 * @return
	 */
	public String translate() {
		run = new Runner(essenceIn, essenceParam);
		
		run.toMinion();
		
		return run.getMinionInput();
	}
	
	/**
	 * Whether a current Minion process is registered as running
	 * 
	 * @return
	 */
	public boolean isRunning() {
		if(minion==null) return false;
		try {
			minion.exitValue();
			//Process doesn't provide a nice interface
			//Check to see if the process is dead, if it is not it throws and Exception
		} catch (IllegalThreadStateException e) {
			return true;
		}
		return false;
	}
	
	/**
	 * Kill the current minion process
	 */
	public void killMinion() {
		minion.destroy();
	}
	

	/**
	 * Given the current state of the Minion stdout connection read backwards until a valid result is encountered
	 * 
	 * @return the most recent solution from Minion
	 */
	public LinkedList<String> getLatestResult() {
		LinkedList<String> lines = new LinkedList<String>();
		LinkedList<String> solutions;
		
		try {
			in.mark(10000); //Allow the stream to reset if nothing found
			
			String s = in.readLine();
			//Deal with minion constantly reading stuff
			int num = 0;
			//Line test is to stop continuously reading if minion just keeps printing better results
			//However if running finished make sure get best result
			
			if(isRunning()) {
				while(s != null && num < 200) {
					lines.add(s);
					s = in.readLine();
					num++;
				}
			} else {
				while(s != null) {
					lines.add(s);
					s = in.readLine();
				}
			}
			solutions = extractValueSol(lines);
			if(solutions==null) in.reset(); //Solution wasn't valid so reset the stream when ready to try again
			
		} catch (IOException e) {
			System.err.println("Error reading latest result:\n" + e.getMessage());
			return null;
		}
		
		//Found something so return it
		if(solutions==null && lastResult!=null) return lastResult;
		//Didn't find anything new, so just return the last result from before this call
		lastResult = solutions;
		
		return solutions;
	}
	
	/**
	 * Starts Minion running in interactive mode for when it will take a long time to search through
	 * the solutions seeking to optimise a solution. 
	 */
	public void runMinionInteractive() {
		if(run==null) {
			run = new Runner(essenceIn, essenceParam);
			run.toMinion();
		}

		minion = run.runMinionInteractive();
		
		System.out.println("Running");
		
		in = new BufferedReader(new InputStreamReader(minion.getInputStream()));
		
		System.out.println("Stream fetched");
		
	}
	
	/**
	 * Run minion and return the best (ie. final solution found)
	 * Unless a minimising or maximising objective function in the essence code then will just return the first result minion finds.
	 * 
	 * @return the ordered list of results or null if no solution found
	 */
	public LinkedList<String> runMinion() {
		if(run==null) {
			run = new Runner(essenceIn, essenceParam);
			run.toMinion();
		}
		
		minionOut = run.runMinion();
			
		BufferedReader br = new BufferedReader(new StringReader(minionOut));
		LinkedList<String> lines = new LinkedList<String>();
		
		try {
			String s = br.readLine();
			while(s != null) {
				lines.add(s);
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return extractValueSol(lines);
		
	}
	
	/**
	 * Tries to extract a solution set from the given input stream. If it fails it returns null.
	 * Failure is defined by not conforming to an acceptable input pattern from minion.
	 * If this doesn't succeed on input from a terminated minion process then minion failed to find a solution.
	 * 
	 * @param input a linked list of each input line
	 * @return a linked list of the solutions
	 */
	private LinkedList<String> extractValueSol(LinkedList<String> input) {
		ListIterator<String> li = input.listIterator(input.size()); //Start at the end
		
		if(!li.hasPrevious()) {
			System.err.println("Error - extractSols input had no lines");
			return null;
		}
		
		Scanner sc = null;
		
		LinkedList<String> sols = new LinkedList<String>();
		boolean done = false;
		boolean foundSol = false;
		while(!done) {
			if(!li.hasPrevious()) {
				if(foundSol) break; //Found all the solutions
				return null; //Found no solutions so fail
			}

			//Go back until find the block of solutions
			//Solutions are "sol: x y z"
			sc = new Scanner(li.previous());
			sc.useDelimiter(":");
			
			if(sc.hasNext()) {
				String s = sc.next();
				
				//Objective function solution value
				if(s.equals("Solution found with Value")) {
					objectiveValue = Integer.parseInt(sc.next().trim());
				}
				if(s.equals("Sol")) {
					foundSol = true;
					sols.add(sc.next());
					sc.close();
				}
			} else {
				if(foundSol) done = true;
			}
		}
		
		Collections.reverse(sols); //Put solutions back in order
		
		return sols;
	}
		
	/**
	 * Extracts from a series of lines of Minion solutions a 2d array of integers
	 * 
	 * @param sols a list of solutions in order, consisting of ints seperated by spaces
	 * @return
	 */
	public static Integer[][] getIntValues2D(List<String> sols) {
		if(sols==null) return null;
		
		Integer[][] ret = new Integer[sols.size()][];
		
		int i = 0;
		ListIterator<String> li = sols.listIterator();
		while(li.hasNext()) {
			ret[i] = getIntValues(li.next());
			i++;
		}
		
		return ret;
	}
	
	/**
	 * From a single line of Minion output extracts a 1d array of integers
	 * 
	 * @param sol a solution of ints seperated by spaces
	 * @return
	 */
	public static Integer[] getIntValues(String sol) {
		if(sol==null) return null;
		
		StringTokenizer st = new StringTokenizer(sol);
		
		Integer[] ret = new Integer[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = Integer.parseInt(st.nextToken());
		}
		
		return ret;
	}
	
	/**
	 * Extracts from a series of lines of Minion solutions a 2d array of integers
	 * 
	 * @param sols solutions of doubles seperated by spaces
	 * @return
	 */
	public static Double[][] getDoubleValues2D(List<String> sols) {
		if(sols==null) return null;
		Double[][] ret = new Double[sols.size()][];
		
		int i = 0;
		ListIterator<String> li = sols.listIterator();
		while(li.hasNext()) {	
			ret[i] = getDoubleValues(li.next());		
			i++;
		}
		
		return ret;
	}
	
	/**
	 * From a single line of Minion output extracts a 1d array of doubles
	 * 
	 * @param sol a solution of doubles seperated by spaces
	 * @return
	 */
	public static Double[] getDoubleValues(String sol) {
		if(sol==null) return null;
		StringTokenizer st = new StringTokenizer(sol);
		
		Double[] ret = new Double[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = Double.parseDouble(st.nextToken());
		}
		
		return ret;
	}
	
	public static String[][] getStringValues2D(List<String> sols) {
		if(sols==null) return null;
		String[][] ret = new String[sols.size()][];
		
		int i = 0;
		ListIterator<String> li = sols.listIterator();
		while(li.hasNext()) {	
			ret[i] = getStringValues(li.next());
			i++;
		}
		
		return ret;
	}
	
	public static String[] getStringValues(String sol) {
		if(sol==null) return null;
		StringTokenizer st = new StringTokenizer(sol);
		
		String[] ret = new String[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = st.nextToken();
		}
		
		return ret;
	}
	
	

	/**
	 * Every time a solution is read in the objective value is updated to that result.
	 * 
	 * @return the most recent objective value
	 */
	public int getObjectiveValue() {
		return objectiveValue;
	}
	
	/**
	 * Extract from the Minion input data what variables of what sizes are going to be output by Minion
	 * 
	 * @throws IOException
	 */
	private void outputSizes() throws IOException {
		if(run==null || run.getMinionInput()==null) return;
		
		varNames = new LinkedList<String>();
		varBounds = new LinkedList<Integer>();
		
		String min = run.getMinionInput();
		int start = min.indexOf("**VARIABLES**");
		int end = min.indexOf("#", start);
		
		BufferedReader br = new BufferedReader(new StringReader(min.substring(start, end)));
		
		String l = br.readLine();
		while(l!=null) {		
			String[] parts = l.split(" ");
			if(parts.length==3 && parts[0].equals("DISCRETE")) {
				//Is a matrix so create buffer
				varNames.add(parts[1].substring(0, parts[1].indexOf('[')));
				
				String[] d = parts[1].substring(parts[1].indexOf('[')+1, parts[1].indexOf("]")).split(",");
				
				int[] dimensions = new int[d.length];
				for(int i = 0; i < d.length; i++) dimensions[i] = Integer.parseInt(d[i]);		
				
				varBounds.add(dimensions[0]);
			}
			
			l = br.readLine();
		}
	}
	
	public List<String> getVariableNames() {
		if(varNames==null)
			try {
				outputSizes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return varNames;
	}
	
	public List<Integer> getVariableBounds() {
		if(varBounds==null)
			try {
				outputSizes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return varBounds;
	}
}
