package uk.ac.stand.antlr;

//import org.antlr.runtime.*;
//import org.antlr.runtime.tree.*;

public class TestRunner {
/*

	
	static String prog="program worlds {\n" + 
			"teams as System.getTeams()\n" + 
			"visible function createRooms () {\n" + 
			"pools as Pool(teams, speakerPoints) \n" + 
			"while(pools.notEmpty()) {\n" + 
			"	t as pools.pop() \n" + 
			"	while(t.size()%4!=0) {\n" + 
			"		v as pools.pop()\n" + 
			"		if(v.size()<(t.size()%4)) {\n" + 
			"			a = Pullup(v)\n" + 
			"			t.add(a)\n" + 
			"			pools.push(v)\n" + 
			"		} else {\n" + 
			"			t.add(v)\n" + 
			"		}\n" + 
			"	}\n" + 
			"	Allocate(t,rooms)\n" + 
			"}\n" + 
			"}\n" + 
			"}";
	
	static String fileName="__Test___input.txt";
	
	public static void main(String[] args) throws Exception {
		//Also avail ANTLRFileStream
		//System.out.println(prog);
		CharStream cs = new ANTLRStringStream(prog);
		CharStream fs = new ANTLRFileStream(fileName);
		
		//System.out.println(cs);
		
		System.out.println("Lexing start");
		
        StAnDPLLexer lexer = new StAnDPLLexer(fs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        
        System.out.println("Parsing start");
        
        StAnDPLParser parser = new StAnDPLParser(tokens);
        
        System.out.println("Fetch tree");
        
        CommonTree t  = (CommonTree) parser.program().getTree();

        System.out.println("Tree fetched");
        
        CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
        
        System.out.println("Parse end");
        
        StAnDPLWalker evaluator = new StAnDPLWalker(nodes); //Need to create constructor to take functionDefs
        //-StAnDPLWalker evaluator = new StAnDPLWalker(nodes, parser.functionDefinitions);
        
        
        
        evaluator.program();

        System.out.println("Walker end");
        
        for(CommonTree func : parser.functionDefinitions) {
        	System.out.println(func.getChild(2));
        }

	}
*/
}
