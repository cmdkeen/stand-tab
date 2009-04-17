package uk.ac.stand.scalafiles;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import uk.ac.stand.minion.EssenceToMinion;
import uk.ac.stand.scalafiles.Language.*;
import uk.ac.stand.testing.RunnerTest;



/**
 * Interface between Scala and Java
 * Used to assist in translating Minion output of known sizes into Java variables 
 * 
 * @author crh24
 *
 */
public class ScalaInterface {

	public static void main(String[] args)
    {
		test1();
    } 
	
	public static void test1() {
		Language l = new Language();
		
		EssenceToMinion etm = new EssenceToMinion(RunnerTest.in, RunnerTest.param);
		
		LinkedList<String> sols = etm.runMinion();
		
		System.out.println("Value: " + etm.getObjectiveValue());
		
		Var[] vars = new Var[2];
		
		int[] t = {3,4};
		
		vars[0] = l.createArray(t, l.new EInt());
		vars[1] = l.createArray(t, l.new EInt());
				
		extractData(sols, vars);
		
		for(Var v : vars) v.toString();
	}
	
	public static void test2() {
		Language l = new Language();
		
		EssenceToMinion etm = new EssenceToMinion(RunnerTest.otherTest, "");
		
		LinkedList<String> sols = etm.runMinion();
		
		Var[] vars = new Var[1];
		
		int[] t = {8,4,3};
		
		vars[0] = l.createArray(t, l.new EInt());
				
		//vars[0] = l.EInt("1:1",1);
		//vars[1] = new EInt("1:1",1);
		
		extractData(sols, vars);
		
		for(Var v : vars) v.toString();
	}
	
	public static void extractData(List<String> sols, Var[] vars) {
		
		List<String> data = new LinkedList<String>();
		StringTokenizer st = null;
		
		for(String s : sols) {
			st = new StringTokenizer(s);
			
			while(st.hasMoreTokens()) data.add(st.nextToken());
		}
		
		Iterator<String> i = data.iterator();
		
		for(Var v : vars) v.set(i);
		
		//for(Var v : vars) System.out.println(v.toString());
		
	}
		
	public static int[] extractIntArray(EArray ea) {
		Var[] ia = ea.getValue();
		
		int[] ret = new int[ia.length];
		
		for(int i = 0; i < ia.length; i++) ret[i] = (Integer)ia[i].getValue();
		
		return ret;
	}
	
	
	
}
