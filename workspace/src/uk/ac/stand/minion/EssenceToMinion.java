package uk.ac.stand.minion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EssenceToMinion {

	public String minionOut = null;
	String essenceIn = null;
	String essenceParam = null;
	int objectiveValue = -1;
	Runner run = null;
	
	public EssenceToMinion(String essenceIn, String essenceParam) {
		this.essenceIn = essenceIn;
		this.essenceParam = essenceParam;
		
	}
	
	public String getMinionInput() {
		if(run==null) return "";
		if(run.getMinionInput()==null) return "";
		return run.getMinionInput();
	}
	
	public LinkedList<String> runMinionOpt() {
		run = new Runner(essenceIn, essenceParam);
		
		run.toMinion("");
		minionOut = run.runMinion();
		
		//System.out.println(minionOut);
		
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
		
		
		ListIterator<String> li = lines.listIterator(lines.size());
		
		if(!li.hasPrevious()) {
			System.out.println("Error - Minion had a problem");
			return null;
		}
		
		Scanner sc = null;
		
		boolean notFound = true;
		while(notFound) {
			sc = new Scanner(li.previous());
			sc.useDelimiter(":");
			if(sc.next().equals("Solution found with Value")) {
				objectiveValue = Integer.parseInt(sc.next().trim());
				notFound = false;
			}
			sc.close();
		}
		
		LinkedList<String> sols = new LinkedList<String>();
		boolean done = false;
		boolean foundSol = false;
		while(!done) {
			sc = new Scanner(li.previous());
			sc.useDelimiter(":");
			
			if(sc.hasNext() && sc.next().equals("Sol")) {
				foundSol = true;
				sols.add(sc.next());
				sc.close();
			} else {
				if(foundSol) done = true;
			}
		}
		
		Collections.reverse(sols); //Put solutions back in order
		
		return sols;
		
	}
	
	public LinkedList<String> runMinionFind() {
		//TODO implement properly
		run = new Runner(essenceIn, essenceParam);
		
		run.toMinion("");
		minionOut = run.runMinion();
		
		//System.out.println(minionOut);
		
		BufferedReader br = new BufferedReader(new StringReader(minionOut));
		
		LinkedList<String> sols = new LinkedList<String>();
		Scanner sc = null;
		
		try {
			String s = br.readLine();
			while(s != null) {
				
				
				sc = new Scanner(s);
				sc.useDelimiter(":");
				
				if(sc.hasNext() && sc.next().equals("Sol")) {
					sols.add(sc.next());
					sc.close();
				}
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		return sols;
	}
	
	public static Integer[][] getIntValues2D(List<String> sols) {
		
		Integer[][] ret = new Integer[sols.size()][];
		
		int i = 0;
		ListIterator<String> li = sols.listIterator();
		while(li.hasNext()) {
			ret[i] = getIntValues(li.next());
			i++;
		}
		
		return ret;
	}
	
	public static Integer[] getIntValues(String sol) {
		StringTokenizer st = new StringTokenizer(sol);
		
		Integer[] ret = new Integer[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = Integer.parseInt(st.nextToken());
		}
		
		return ret;
	}
	
	public static Double[][] getDoubleValues2D(List<String> sols) {
		Double[][] ret = new Double[sols.size()][];
		
		int i = 0;
		ListIterator<String> li = sols.listIterator();
		while(li.hasNext()) {	
			ret[i] = getDoubleValues(li.next());		
			i++;
		}
		
		return ret;
	}
	
	public static Double[] getDoubleValues(String sol) {
		StringTokenizer st = new StringTokenizer(sol);
		
		Double[] ret = new Double[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = Double.parseDouble(st.nextToken());
		}
		
		return ret;
	}
	
	public static String[][] getStringValues2D(List<String> sols) {
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
		StringTokenizer st = new StringTokenizer(sol);
		
		String[] ret = new String[st.countTokens()];
		
		for(int j = 0; j < ret.length; j++) {
			ret[j] = st.nextToken();
		}
		
		return ret;
	}
	
	

	public int getObjectiveValue() {
		return objectiveValue;
	}
	
	
}
