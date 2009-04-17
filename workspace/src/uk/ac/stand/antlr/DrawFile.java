package uk.ac.stand.antlr;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.TokenRewriteStream;

import uk.ac.stand.antlr.gen.stabLexer;
import uk.ac.stand.antlr.gen.stabParser;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.interfaces.ITeam;
import uk.ac.stand.minion.EssenceToMinion;

import uk.ac.stand.scalafiles.ScalaInterface;
import uk.ac.stand.scalafiles.Stab;
import uk.ac.stand.scalafiles.Stab.*;

public class DrawFile {

	private stabParser grammar = null;

	public DrawFile(File f) throws Exception {
		ANTLRFileStream fs = new ANTLRFileStream(f.getAbsolutePath());
		stabLexer lex = new stabLexer(fs);
		TokenRewriteStream tokens = new TokenRewriteStream(lex);
		grammar = new stabParser(tokens);
	}
	
	public Draw doDraw(int round) throws Exception {
		//Parse the program
		Program program = grammar.program();
		
		//eprime[0] = eprime file, eprime[1] = param file
		ArrayList<String> eprime = Stab.runDraw(program, round);
		
		System.out.println("eprime\n\n" + eprime.get(0));
		System.out.println("param\n\n" + eprime.get(1));
		
		EssenceToMinion etm = new EssenceToMinion(eprime.get(0), eprime.get(1));
		
		etm.translate();
		List<Integer> vars = etm.getVariableBounds();
		
		LinkedList<String> solutions = etm.runMinion();
		
		if(solutions==null) {
			System.err.println("Round " + round + " draw failed with no solution found");
			return null;
		}
		
		Integer[][] values = EssenceToMinion.getIntValues2D(solutions.subList(0, vars.get(0)));
		
		Draw d = new Draw(round);
		
		String allocationType = etm.getVariableNames().get(0);
		
		if(allocationType.equals("alloc")) {
			//matrix[room, position]
			//Discover whether alloc indexes from 1..Teams or 0..teams-1
			boolean zero = false;
			for(Integer[] i : values) for(int j : i) if(j==0) zero=true; 
			
			for(Integer[] i : values) {
				ITeam[] temp = new ITeam[i.length];
				List<ITeam> teams = Competition.getInstance().getTeams();
				for(int j = 0; j < i.length; j++) {
					if(zero) {
						temp[j] = teams.get(i[j]);
					} else {
						temp[j] = teams.get(i[j]-1);
					}
				}
				d.addTeams(temp);
			}
		} else if(allocationType.equals("round_alloc")) {
			//matrix[round, room, position]
			
			//Not implemented yet
			throw new Exception("Draw allocation type unimplemented: " + allocationType);
		} else {
			throw new Exception("Unknown draw allocation type: " + allocationType);
		}
		
		return d;
	}
	
}
