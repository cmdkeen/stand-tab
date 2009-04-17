package uk.ac.stand.testing;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import uk.ac.stand.scalafiles.Stab;
import uk.ac.stand.scalafiles.Stab.*;
import uk.ac.stand.antlr.DrawFile;
import uk.ac.stand.antlr.Rules;
import uk.ac.stand.antlr.gen.stabLexer;
import uk.ac.stand.antlr.gen.stabParser;
import uk.ac.stand.impl.Competition;
import uk.ac.stand.impl.Draw;
import uk.ac.stand.impl.Settings;
import uk.ac.stand.impl.exceptions.StoreException;

public class SystemTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args)  {
		
		/*
		 * System flow
		 * 
		 * loadRules?
		 * 		ANTLR
		 * 		Scala
		 * 			add Settings
		 * 
		 * Enter settings info
		 * Enter teams and speakers
		 * 
		 * Rounds
		 * 		Load rules
		 * 		ANTLR
		 * 		Scala
		 * 		Minion
		 * 		Extract sols
		 * 		
		 * 		*** Debate
		 * 		
		 * 		Enter results
		 * 		Validate?
		 */
		
		try {
			setupCompetition();
			
			Competition.getInstance().addDraw(1, randomDraw(1));
			
			Simulation sim = new Simulation();
			
			sim.makeResults(1);
			
			Competition.getInstance().addDraw(2, drawRound(2));
			
			sim.makeResults(2);
			
			Competition.getInstance().addDraw(3, drawRound(3));
			
			sim.makeResults(3);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		

	}
	
	public static Draw randomDraw(int round) throws Exception {
		File draw_file = new File("rules/random.stab");
		
		System.out.println(parse(draw_file).statString());
		
		DrawFile df = new DrawFile(draw_file);
		
		Draw d = df.doDraw(round);
		
		return d;
	}
	
	public static Draw drawRound(int round) throws Exception {
		File draw_file = new File("rules/wudc.stab");
		
		System.out.println(parse(draw_file).statString());
		
		DrawFile df = new DrawFile(draw_file);
		
		Draw d = df.doDraw(round);
		
		return d;
	}
	
	public static void setupCompetition() throws Exception {
		File test_file = new File("rules/comp_setup.stab");
		
		System.out.println(parse(test_file).statString());
		
		Rules r = initCompetition(test_file);
		
		initCompetitionSettings();
		
		Competition.getInstance().loadDependantRules(r);
		
		Competition.getInstance().init();
		
		DataSetup.addTeamsSpeakers();
	}
	
	private static void initCompetitionSettings() {
		Settings s = Settings.getInstance();
		
		try {
			s.setFlagValue("numRounds", 3);
			s.setFlagValue("numTeams", 20);
			s.setFlagValue("speakersPerTeam", 2);
			s.setFlagValue("teamsPerSide", 2);
		} catch (StoreException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	private static Rules initCompetition(File f) {
		Rules rules= null;
		try {
			rules = new Rules(f);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (RecognitionException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Competition.getInstance().loadRules(rules);
		rules.storeDefaultValues();
			
		return rules;
	}
	
	private static Program parse(File f) throws IOException, RecognitionException {
		ANTLRFileStream fs = new ANTLRFileStream(f.getAbsolutePath());
		stabLexer lex = new stabLexer(fs);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		
		//for(Object t : tokens.getTokens()) System.out.println(((CommonToken)t).getText());
		
		stabParser grammar = new stabParser(tokens);
		
		return grammar.program();
		//Stab.runInitial(program);
	}

}
