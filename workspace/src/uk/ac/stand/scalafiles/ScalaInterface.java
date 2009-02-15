package uk.ac.stand.scalafiles;

import java.util.LinkedList;
import java.util.List;

import uk.ac.stand.minion.EssenceToMinion;
import uk.ac.stand.minion.RunnerTest;
import uk.ac.stand.scalafiles.Language.*;

public class ScalaInterface {

	public static void main(String[] args)
    {
		/*
		String[] aas = { "Sum(Sum(Var(\"x\"),Var(\"x\")),Sum(Const(7),Var(\"y\")))" };
        simpleInterpreter.main(aas);
        */
		
		test2();
        
    } 
	
	public static void test1() {
		Language l = new Language();
		
		EssenceToMinion etm = new EssenceToMinion(RunnerTest.in, RunnerTest.param);
		
		LinkedList<String> sols = etm.runMinionOpt();
		
		System.out.println("Value: " + etm.getObjectiveValue());
		
		Var[] vars = new Var[2];
		
		int[] t = {3,4};
		
		vars[0] = l.createArray(t, l.new EInt());
		vars[1] = l.createArray(t, l.new EInt());
		
		//vars[0] = l.EInt("1:1",1);
		//vars[1] = new EInt("1:1",1);
		
		extractData(sols, vars);
		
		for(Var v : vars) v.toString();
	}
	
	public static void test2() {
		Language l = new Language();
		
		EssenceToMinion etm = new EssenceToMinion(RunnerTest.otherTest, "");
		
		LinkedList<String> sols = etm.runMinionFind();
		
		System.out.println("Value: " + etm.getObjectiveValue());
		
		Var[] vars = new Var[1];
		
		int[] t = {8,4,3};
		
		vars[0] = l.createArray(t, l.new EInt());
				
		//vars[0] = l.EInt("1:1",1);
		//vars[1] = new EInt("1:1",1);
		
		System.out.println(etm.minionOut);
		
		extractData(sols, vars);
		
		for(Var v : vars) v.toString();
	}
	
	public static void extractData(List<String> sols, Var[] vars) {
		
		vars[0].set(sols.toArray(new String[sols.size()]));
		
	}
	
	
	
}
