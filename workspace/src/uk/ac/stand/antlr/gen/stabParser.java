// $ANTLR 3.1.2 /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g 2009-04-09 22:10:47

	package uk.ac.stand.antlr.gen;

	import java.util.Map;
	import java.util.HashMap;
	import java.util.List;
	import java.util.LinkedList;
	import uk.ac.stand.scalafiles.Stab;
	import uk.ac.stand.scalafiles.Stab.*;
	import scala.Some;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class stabParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "SBEGIN", "SEND", "LCURLY", "RCURLY", "SEMI", "NAME", "LBRACK", "RBRACK", "OR", "AND", "NOT", "LESS", "GREATER", "EQUALS", "GREATEREQUAL", "LESSEQUAL", "NOTEQUAL", "PLUS", "MINUS", "MULT", "DIV", "MOD", "POW", "LPAREN", "RPAREN", "DOT", "INT", "BOOL", "COMMA", "COLON", "STAB", "ESSENCE", "PLUSEQUAL", "MINUSEQUAL", "MULTEQUAL", "DIVEQUAL", "MODEQUAL", "ASSIGN", "ENDESSENCE", "DEF", "WS", "USCORE", "ECOMMENT", "QUOTE", "LQUOTE", "STRING", "'break'", "'continue'", "'return'", "'if'", "'else'", "'while'", "'for'", "'function'"
    };
    public static final int LBRACK=11;
    public static final int DEF=44;
    public static final int MOD=26;
    public static final int MULTEQUAL=39;
    public static final int GREATEREQUAL=19;
    public static final int ESSENCE=36;
    public static final int EQUALS=18;
    public static final int STAB=35;
    public static final int NOT=15;
    public static final int AND=14;
    public static final int EOF=-1;
    public static final int DIVEQUAL=40;
    public static final int LPAREN=28;
    public static final int PLUSEQUAL=37;
    public static final int NOTEQUAL=21;
    public static final int T__55=55;
    public static final int MINUSEQUAL=38;
    public static final int T__56=56;
    public static final int QUOTE=48;
    public static final int T__57=57;
    public static final int RPAREN=29;
    public static final int NAME=10;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int GREATER=17;
    public static final int MODEQUAL=41;
    public static final int T__52=52;
    public static final int POW=27;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int COMMA=33;
    public static final int LESS=16;
    public static final int SEND=6;
    public static final int PLUS=22;
    public static final int DOT=30;
    public static final int SBEGIN=5;
    public static final int RBRACK=12;
    public static final int LQUOTE=49;
    public static final int BOOL=32;
    public static final int LCURLY=7;
    public static final int INT=31;
    public static final int MINUS=23;
    public static final int MULT=24;
    public static final int SEMI=9;
    public static final int COLON=34;
    public static final int WS=45;
    public static final int NEWLINE=4;
    public static final int ECOMMENT=47;
    public static final int ENDESSENCE=43;
    public static final int USCORE=46;
    public static final int RCURLY=8;
    public static final int OR=13;
    public static final int ASSIGN=42;
    public static final int DIV=25;
    public static final int LESSEQUAL=20;
    public static final int STRING=50;

    // delegates
    // delegators


        public stabParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public stabParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return stabParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g"; }


    	
    	List<FuncDef> funcdefs = new LinkedList<FuncDef>();
    	
    	Map<Integer, String> essence = new HashMap<Integer, String>();
    	
    	int eblock = 0;



    // $ANTLR start "program"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:29:1: program returns [Program p] : r= rules ;
    public final Program program() throws RecognitionException {
        Program p = null;

        List<Stats> r = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:33:2: (r= rules )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:33:4: r= rules
            {
            pushFollow(FOLLOW_rules_in_program44);
            r=rules();

            state._fsp--;
            if (state.failed) return p;

            }

            if ( state.backtracking==0 ) {

              	p = new Program(funcdefs, r);

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return p;
    }
    // $ANTLR end "program"


    // $ANTLR start "rules"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:36:1: rules returns [List<Stats> ret] : ( stab s= statements e= essence | e= essence );
    public final List<Stats> rules() throws RecognitionException {
        List<Stats> ret = null;

        List<Term> s = null;

        List<Stats> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:2: ( stab s= statements e= essence | e= essence )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==STAB) ) {
                alt1=1;
            }
            else if ( (LA1_0==ESSENCE) ) {
                alt1=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:4: stab s= statements e= essence
                    {
                    pushFollow(FOLLOW_stab_in_rules60);
                    stab();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_statements_in_rules64);
                    s=statements();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_essence_in_rules68);
                    e=essence();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {

                      			List<Stats> l = new LinkedList<Stats>();
                      			l.add(new Stats(s));
                      			l.addAll(e);
                      			ret = l;
                      		
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:44:4: e= essence
                    {
                    pushFollow(FOLLOW_essence_in_rules79);
                    e=essence();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = e;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "rules"


    // $ANTLR start "essence"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:50:1: essence returns [List<Stats> ret] : d= essencedef e= estatements ;
    public final List<Stats> essence() throws RecognitionException {
        List<Stats> ret = null;

        String d = null;

        List<Stats> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:51:2: (d= essencedef e= estatements )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:51:4: d= essencedef e= estatements
            {
            pushFollow(FOLLOW_essencedef_in_essence102);
            d=essencedef();

            state._fsp--;
            if (state.failed) return ret;
            pushFollow(FOLLOW_estatements_in_essence106);
            e=estatements();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {

              			List<Term> lt = new LinkedList<Term>();
              			lt.add(new EString(d));
              			List<Stats> ls = new LinkedList<Stats>();
              			ls.add(new Stats(lt));
              			ls.addAll(e);
              			ret = ls;
              		
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "essence"


    // $ANTLR start "estatements"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:62:1: estatements returns [List<Stats> ret] : (s= estatement )* ;
    public final List<Stats> estatements() throws RecognitionException {
        List<Stats> ret = null;

        Stats s = null;



        	List<Stats> l = new LinkedList<Stats>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:69:2: ( (s= estatement )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:69:4: (s= estatement )*
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:69:4: (s= estatement )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=NEWLINE && LA2_0<=58)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:69:5: s= estatement
            	    {
            	    pushFollow(FOLLOW_estatement_in_estatements139);
            	    s=estatement();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      l.add(s);
            	    }

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = l;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "estatements"


    // $ANTLR start "estatement"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:74:1: estatement returns [Stats ret] : ( ( stabblock )=>s= stabblock | ec= essencecode );
    public final Stats estatement() throws RecognitionException {
        Stats ret = null;

        Stats s = null;

        String ec = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:75:2: ( ( stabblock )=>s= stabblock | ec= essencecode )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==SBEGIN) ) {
                int LA3_1 = input.LA(2);

                if ( (synpred1_stab()) ) {
                    alt3=1;
                }
                else if ( (true) ) {
                    alt3=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA3_0==NEWLINE||(LA3_0>=SEND && LA3_0<=58)) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:75:4: ( stabblock )=>s= stabblock
                    {
                    pushFollow(FOLLOW_stabblock_in_estatement173);
                    s=stabblock();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = s;
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:76:4: ec= essencecode
                    {
                    pushFollow(FOLLOW_essencecode_in_estatement182);
                    ec=essencecode();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {

                      				List<Term> l = new LinkedList<Term>();
                      				l.add(new EString(ec));
                      				ret = new Stats(l);
                      			
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "estatement"


    // $ANTLR start "essencecode"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:83:1: essencecode returns [String ret] : something ;
    public final String essencecode() throws RecognitionException {
        String ret = null;


        	eblock++;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:90:2: ( something )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:90:4: something
            {
            pushFollow(FOLLOW_something_in_essencecode209);
            something();

            state._fsp--;
            if (state.failed) return ret;

            }

            if ( state.backtracking==0 ) {

              	ret = essence.get(eblock);

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "essencecode"


    // $ANTLR start "something"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:93:1: something : ( options {greedy=false; } : t=~ ( NEWLINE ) )* NEWLINE ;
    public final void something() throws RecognitionException {
        Token t=null;


        	StringBuffer buffer = new StringBuffer();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:100:2: ( ( options {greedy=false; } : t=~ ( NEWLINE ) )* NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:100:4: ( options {greedy=false; } : t=~ ( NEWLINE ) )* NEWLINE
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:100:4: ( options {greedy=false; } : t=~ ( NEWLINE ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=SBEGIN && LA4_0<=58)) ) {
                    alt4=1;
                }
                else if ( (LA4_0==NEWLINE) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:100:30: t=~ ( NEWLINE )
            	    {
            	    t=(Token)input.LT(1);
            	    if ( (input.LA(1)>=SBEGIN && input.LA(1)<=58) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      buffer.append((t!=null?t.getText():null)); 
            	      			int i = (t!=null?t.getTokenIndex():0) + 1;
            	      			if(i<input.size()) {
            	      				Token tkn = input.get(i);
            	      				while(i<input.size() && tkn!= Token.EOF_TOKEN && tkn.getChannel() == Token.HIDDEN_CHANNEL) {
            	      					buffer.append(tkn.getText());
            	      					i++;
            	      					tkn = input.get(i);
            	      				}
            	      			}
            	      		
            	    }

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match(input,NEWLINE,FOLLOW_NEWLINE_in_something252); if (state.failed) return ;

            }

            if ( state.backtracking==0 ) {

              	essence.put(eblock, buffer.toString());

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "something"


    // $ANTLR start "stabblock"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:113:1: stabblock returns [Stats ret] : SBEGIN s= statements SEND ;
    public final Stats stabblock() throws RecognitionException {
        Stats ret = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:117:2: ( SBEGIN s= statements SEND )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:117:4: SBEGIN s= statements SEND
            {
            match(input,SBEGIN,FOLLOW_SBEGIN_in_stabblock273); if (state.failed) return ret;
            pushFollow(FOLLOW_statements_in_stabblock277);
            s=statements();

            state._fsp--;
            if (state.failed) return ret;
            match(input,SEND,FOLLOW_SEND_in_stabblock279); if (state.failed) return ret;

            }

            if ( state.backtracking==0 ) {

              	ret = new Stats(s);

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "stabblock"


    // $ANTLR start "statements"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:120:1: statements returns [List<Term> ret] : (s= statement )* ;
    public final List<Term> statements() throws RecognitionException {
        List<Term> ret = null;

        List<Term> s = null;



        	List<Term> l = new LinkedList<Term>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:127:2: ( (s= statement )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:127:4: (s= statement )*
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:127:4: (s= statement )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==NEWLINE||LA5_0==LCURLY||LA5_0==NAME||LA5_0==NOT||LA5_0==MINUS||LA5_0==LPAREN||(LA5_0>=INT && LA5_0<=BOOL)||(LA5_0>=51 && LA5_0<=54)||(LA5_0>=56 && LA5_0<=58)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:127:5: s= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements308);
            	    s=statement();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      if(s!=null) l.addAll(s);
            	    }

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = l;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "statements"


    // $ANTLR start "statement"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:130:1: statement returns [List<Term> ret] : ( LCURLY ls= statements RCURLY | ss= simple_stmt | s= compound_stmt | NEWLINE );
    public final List<Term> statement() throws RecognitionException {
        List<Term> ret = null;

        List<Term> ls = null;

        List<Term> ss = null;

        Term s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:131:2: ( LCURLY ls= statements RCURLY | ss= simple_stmt | s= compound_stmt | NEWLINE )
            int alt6=4;
            switch ( input.LA(1) ) {
            case LCURLY:
                {
                alt6=1;
                }
                break;
            case NAME:
            case NOT:
            case MINUS:
            case LPAREN:
            case INT:
            case BOOL:
            case 51:
            case 52:
            case 53:
                {
                alt6=2;
                }
                break;
            case 54:
            case 56:
            case 57:
            case 58:
                {
                alt6=3;
                }
                break;
            case NEWLINE:
                {
                alt6=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:131:4: LCURLY ls= statements RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_statement327); if (state.failed) return ret;
                    pushFollow(FOLLOW_statements_in_statement331);
                    ls=statements();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RCURLY,FOLLOW_RCURLY_in_statement333); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = ls;
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:133:4: ss= simple_stmt
                    {
                    pushFollow(FOLLOW_simple_stmt_in_statement345);
                    ss=simple_stmt();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = ss;
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:135:4: s= compound_stmt
                    {
                    pushFollow(FOLLOW_compound_stmt_in_statement357);
                    s=compound_stmt();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      List<Term> lt = new LinkedList<Term>(); lt.add(s); ret = lt;
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:137:4: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement367); if (state.failed) return ret;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "statement"


    // $ANTLR start "simple_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:140:1: simple_stmt returns [List<Term> ret] : s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE ;
    public final List<Term> simple_stmt() throws RecognitionException {
        List<Term> ret = null;

        Term s = null;



        	List<Term> l = new LinkedList<Term>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:6: (s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:11: s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE
            {
            pushFollow(FOLLOW_small_stmt_in_simple_stmt402);
            s=small_stmt();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              l.add(s);
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:41: ( options {greedy=true; } : SEMI s= small_stmt )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==SEMI) ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1==NAME||LA7_1==NOT||LA7_1==MINUS||LA7_1==LPAREN||(LA7_1>=INT && LA7_1<=BOOL)||(LA7_1>=51 && LA7_1<=53)) ) {
                        alt7=1;
                    }


                }


                switch (alt7) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:65: SEMI s= small_stmt
            	    {
            	    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt414); if (state.failed) return ret;
            	    pushFollow(FOLLOW_small_stmt_in_simple_stmt418);
            	    s=small_stmt();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      l.add(s);
            	    }

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:102: ( SEMI )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==SEMI) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:147:103: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt425); if (state.failed) return ret;

                    }
                    break;

            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt429); if (state.failed) return ret;

            }

            if ( state.backtracking==0 ) {

              	ret = l;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "simple_stmt"


    // $ANTLR start "small_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:150:1: small_stmt returns [Term ret] : (r= expr_stmt | r= flow_stmt );
    public final Term small_stmt() throws RecognitionException {
        Term ret = null;

        Term r = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:154:2: (r= expr_stmt | r= flow_stmt )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==NAME||LA9_0==NOT||LA9_0==MINUS||LA9_0==LPAREN||(LA9_0>=INT && LA9_0<=BOOL)) ) {
                alt9=1;
            }
            else if ( ((LA9_0>=51 && LA9_0<=53)) ) {
                alt9=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:154:5: r= expr_stmt
                    {
                    pushFollow(FOLLOW_expr_stmt_in_small_stmt453);
                    r=expr_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:155:5: r= flow_stmt
                    {
                    pushFollow(FOLLOW_flow_stmt_in_small_stmt462);
                    r=flow_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;

            }
            if ( state.backtracking==0 ) {

              	ret = r;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "small_stmt"


    // $ANTLR start "expr_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:158:1: expr_stmt returns [Term ret] : ( ( NAME augassign test )=>v= NAME o= augassign r= test | ( NAME LBRACK test RBRACK augassign test )=> NAME LBRACK t= test RBRACK augassign test | t= test );
    public final Term expr_stmt() throws RecognitionException {
        Term ret = null;

        Token v=null;
        stabParser.augassign_return o = null;

        Exp r = null;

        Exp t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:2: ( ( NAME augassign test )=>v= NAME o= augassign r= test | ( NAME LBRACK test RBRACK augassign test )=> NAME LBRACK t= test RBRACK augassign test | t= test )
            int alt10=3;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NAME) ) {
                int LA10_1 = input.LA(2);

                if ( (synpred2_stab()) ) {
                    alt10=1;
                }
                else if ( (synpred3_stab()) ) {
                    alt10=2;
                }
                else if ( (true) ) {
                    alt10=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA10_0==NOT||LA10_0==MINUS||LA10_0==LPAREN||(LA10_0>=INT && LA10_0<=BOOL)) ) {
                alt10=3;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:4: ( NAME augassign test )=>v= NAME o= augassign r= test
                    {
                    v=(Token)match(input,NAME,FOLLOW_NAME_in_expr_stmt490); if (state.failed) return ret;
                    pushFollow(FOLLOW_augassign_in_expr_stmt494);
                    o=augassign();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt498);
                    r=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Assign((v!=null?v.getText():null), (o!=null?input.toString(o.start,o.stop):null), r);
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:160:4: ( NAME LBRACK test RBRACK augassign test )=> NAME LBRACK t= test RBRACK augassign test
                    {
                    match(input,NAME,FOLLOW_NAME_in_expr_stmt521); if (state.failed) return ret;
                    match(input,LBRACK,FOLLOW_LBRACK_in_expr_stmt523); if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt527);
                    t=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RBRACK,FOLLOW_RBRACK_in_expr_stmt529); if (state.failed) return ret;
                    pushFollow(FOLLOW_augassign_in_expr_stmt531);
                    augassign();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt533);
                    test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new ElAssign((v!=null?v.getText():null), t, (o!=null?input.toString(o.start,o.stop):null), r);
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:161:4: t= test
                    {
                    pushFollow(FOLLOW_test_in_expr_stmt542);
                    t=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = t;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "expr_stmt"


    // $ANTLR start "test"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:164:1: test returns [Exp ret] : l= and_test ( OR r= and_test )* ;
    public final Exp test() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:169:2: (l= and_test ( OR r= and_test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:169:5: l= and_test ( OR r= and_test )*
            {
            pushFollow(FOLLOW_and_test_in_test577);
            l=and_test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:169:33: ( OR r= and_test )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==OR) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:169:34: OR r= and_test
            	    {
            	    match(input,OR,FOLLOW_OR_in_test582); if (state.failed) return ret;
            	    pushFollow(FOLLOW_and_test_in_test586);
            	    r=and_test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp("or", left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = left;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "test"


    // $ANTLR start "and_test"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:172:1: and_test returns [Exp ret] : l= not_test ( AND r= not_test )* ;
    public final Exp and_test() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:177:2: (l= not_test ( AND r= not_test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:177:5: l= not_test ( AND r= not_test )*
            {
            pushFollow(FOLLOW_not_test_in_and_test617);
            l=not_test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:177:33: ( AND r= not_test )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==AND) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:177:34: AND r= not_test
            	    {
            	    match(input,AND,FOLLOW_AND_in_and_test622); if (state.failed) return ret;
            	    pushFollow(FOLLOW_not_test_in_and_test626);
            	    r=not_test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp("and", left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = left;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "and_test"


    // $ANTLR start "not_test"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:180:1: not_test returns [Exp ret] : ( NOT t= not_test | c= comparison );
    public final Exp not_test() throws RecognitionException {
        Exp ret = null;

        Exp t = null;

        Exp c = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:181:2: ( NOT t= not_test | c= comparison )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==NOT) ) {
                alt13=1;
            }
            else if ( (LA13_0==NAME||LA13_0==MINUS||LA13_0==LPAREN||(LA13_0>=INT && LA13_0<=BOOL)) ) {
                alt13=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:181:5: NOT t= not_test
                    {
                    match(input,NOT,FOLLOW_NOT_in_not_test646); if (state.failed) return ret;
                    pushFollow(FOLLOW_not_test_in_not_test650);
                    t=not_test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EOp1("not", t);
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:182:5: c= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_not_test660);
                    c=comparison();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = c;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "not_test"


    // $ANTLR start "comparison"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:185:1: comparison returns [Exp ret] : l= arith_expr (o= comp_op r= arith_expr )* ;
    public final Exp comparison() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        stabParser.comp_op_return o = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:190:2: (l= arith_expr (o= comp_op r= arith_expr )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:190:5: l= arith_expr (o= comp_op r= arith_expr )*
            {
            pushFollow(FOLLOW_arith_expr_in_comparison689);
            l=arith_expr();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:190:35: (o= comp_op r= arith_expr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=LESS && LA14_0<=NOTEQUAL)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:190:36: o= comp_op r= arith_expr
            	    {
            	    pushFollow(FOLLOW_comp_op_in_comparison696);
            	    o=comp_op();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    pushFollow(FOLLOW_arith_expr_in_comparison700);
            	    r=arith_expr();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp((o!=null?input.toString(o.start,o.stop):null), left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = left;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "comparison"

    public static class comp_op_return extends ParserRuleReturnScope {
        public String s;
    };

    // $ANTLR start "comp_op"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:193:1: comp_op returns [String s] : ( LESS | GREATER | EQUALS | GREATEREQUAL | LESSEQUAL | NOTEQUAL );
    public final stabParser.comp_op_return comp_op() throws RecognitionException {
        stabParser.comp_op_return retval = new stabParser.comp_op_return();
        retval.start = input.LT(1);

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:194:2: ( LESS | GREATER | EQUALS | GREATEREQUAL | LESSEQUAL | NOTEQUAL )
            int alt15=6;
            switch ( input.LA(1) ) {
            case LESS:
                {
                alt15=1;
                }
                break;
            case GREATER:
                {
                alt15=2;
                }
                break;
            case EQUALS:
                {
                alt15=3;
                }
                break;
            case GREATEREQUAL:
                {
                alt15=4;
                }
                break;
            case LESSEQUAL:
                {
                alt15=5;
                }
                break;
            case NOTEQUAL:
                {
                alt15=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:194:5: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_comp_op720); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "<";
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:195:4: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_comp_op727); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = ">";
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:196:4: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_comp_op734); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "==";
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:197:4: GREATEREQUAL
                    {
                    match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op741); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = ">=";
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:198:4: LESSEQUAL
                    {
                    match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op748); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "<=";
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:199:4: NOTEQUAL
                    {
                    match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op755); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "!=";
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "comp_op"


    // $ANTLR start "arith_expr"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:202:1: arith_expr returns [Exp ret] : l= term (o= ( PLUS | MINUS ) r= term )* ;
    public final Exp arith_expr() throws RecognitionException {
        Exp ret = null;

        Token o=null;
        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:207:2: (l= term (o= ( PLUS | MINUS ) r= term )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:207:5: l= term (o= ( PLUS | MINUS ) r= term )*
            {
            pushFollow(FOLLOW_term_in_arith_expr784);
            l=term();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:207:29: (o= ( PLUS | MINUS ) r= term )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=PLUS && LA16_0<=MINUS)) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:207:30: o= ( PLUS | MINUS ) r= term
            	    {
            	    o=(Token)input.LT(1);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ret;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_term_in_arith_expr799);
            	    r=term();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp((o!=null?o.getText():null), left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = left;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "arith_expr"


    // $ANTLR start "term"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:210:1: term returns [Exp ret] : l= factor (o= ( MULT | DIV | MOD ) r= factor )* ;
    public final Exp term() throws RecognitionException {
        Exp ret = null;

        Token o=null;
        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:215:2: (l= factor (o= ( MULT | DIV | MOD ) r= factor )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:215:4: l= factor (o= ( MULT | DIV | MOD ) r= factor )*
            {
            pushFollow(FOLLOW_factor_in_term829);
            l=factor();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:215:30: (o= ( MULT | DIV | MOD ) r= factor )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=MULT && LA17_0<=MOD)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:215:31: o= ( MULT | DIV | MOD ) r= factor
            	    {
            	    o=(Token)input.LT(1);
            	    if ( (input.LA(1)>=MULT && input.LA(1)<=MOD) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ret;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_factor_in_term851);
            	    r=factor();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp((o!=null?o.getText():null), left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = left;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "term"


    // $ANTLR start "factor"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:218:1: factor returns [Exp ret] : l= atom ( POW r= factor )? ;
    public final Exp factor() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:219:2: (l= atom ( POW r= factor )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:219:5: l= atom ( POW r= factor )?
            {
            pushFollow(FOLLOW_atom_in_factor873);
            l=atom();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:219:29: ( POW r= factor )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==POW) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:219:30: POW r= factor
                    {
                    match(input,POW,FOLLOW_POW_in_factor878); if (state.failed) return ret;
                    pushFollow(FOLLOW_factor_in_factor882);
                    r=factor();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EOp("^", l, r);
                    }

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "factor"


    // $ANTLR start "atom"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:222:1: atom returns [Exp ret] : ( LPAREN (t= test )? RPAREN | n= NAME LPAREN a= args RPAREN | o= NAME DOT m= NAME LPAREN a= args RPAREN | MINUS i= INT | v= value | v= var );
    public final Exp atom() throws RecognitionException {
        Exp ret = null;

        Token n=null;
        Token o=null;
        Token m=null;
        Token i=null;
        Exp t = null;

        List<Exp> a = null;

        Exp v = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:2: ( LPAREN (t= test )? RPAREN | n= NAME LPAREN a= args RPAREN | o= NAME DOT m= NAME LPAREN a= args RPAREN | MINUS i= INT | v= value | v= var )
            int alt20=6;
            switch ( input.LA(1) ) {
            case LPAREN:
                {
                alt20=1;
                }
                break;
            case NAME:
                {
                switch ( input.LA(2) ) {
                case LPAREN:
                    {
                    alt20=2;
                    }
                    break;
                case DOT:
                    {
                    alt20=3;
                    }
                    break;
                case EOF:
                case NEWLINE:
                case SEMI:
                case LBRACK:
                case RBRACK:
                case OR:
                case AND:
                case LESS:
                case GREATER:
                case EQUALS:
                case GREATEREQUAL:
                case LESSEQUAL:
                case NOTEQUAL:
                case PLUS:
                case MINUS:
                case MULT:
                case DIV:
                case MOD:
                case POW:
                case RPAREN:
                case COMMA:
                    {
                    alt20=6;
                    }
                    break;
                default:
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 2, input);

                    throw nvae;
                }

                }
                break;
            case MINUS:
                {
                alt20=4;
                }
                break;
            case INT:
            case BOOL:
                {
                alt20=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:5: LPAREN (t= test )? RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom902); if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:12: (t= test )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==NAME||LA19_0==NOT||LA19_0==MINUS||LA19_0==LPAREN||(LA19_0>=INT && LA19_0<=BOOL)) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:13: t= test
                            {
                            pushFollow(FOLLOW_test_in_atom907);
                            t=test();

                            state._fsp--;
                            if (state.failed) return ret;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_atom911); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = t;
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:224:4: n= NAME LPAREN a= args RPAREN
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_atom920); if (state.failed) return ret;
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom922); if (state.failed) return ret;
                    pushFollow(FOLLOW_args_in_atom926);
                    a=args();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom928); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Call((n!=null?n.getText():null), a);
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:226:4: o= NAME DOT m= NAME LPAREN a= args RPAREN
                    {
                    o=(Token)match(input,NAME,FOLLOW_NAME_in_atom941); if (state.failed) return ret;
                    match(input,DOT,FOLLOW_DOT_in_atom943); if (state.failed) return ret;
                    m=(Token)match(input,NAME,FOLLOW_NAME_in_atom947); if (state.failed) return ret;
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom949); if (state.failed) return ret;
                    pushFollow(FOLLOW_args_in_atom953);
                    a=args();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom955); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Method((n!=null?n.getText():null), (m!=null?m.getText():null), a);
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:228:4: MINUS i= INT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_atom966); if (state.failed) return ret;
                    i=(Token)match(input,INT,FOLLOW_INT_in_atom970); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EOp1("-",new EInt(Integer.parseInt((i!=null?i.getText():null))));
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:229:4: v= value
                    {
                    pushFollow(FOLLOW_value_in_atom979);
                    v=value();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = v;
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:230:4: v= var
                    {
                    pushFollow(FOLLOW_var_in_atom988);
                    v=var();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = v;
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "atom"


    // $ANTLR start "value"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:233:1: value returns [Value ret] : (i= INT | BOOL );
    public final Value value() throws RecognitionException {
        Value ret = null;

        Token i=null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:234:2: (i= INT | BOOL )
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==INT) ) {
                alt21=1;
            }
            else if ( (LA21_0==BOOL) ) {
                alt21=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:234:4: i= INT
                    {
                    i=(Token)match(input,INT,FOLLOW_INT_in_value1008); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EInt(Integer.parseInt((i!=null?i.getText():null)));
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:235:4: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_value1015); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EBool(Boolean.parseBoolean((i!=null?i.getText():null)));
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "value"


    // $ANTLR start "var"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:238:1: var returns [Exp ret] : (n= NAME | n= NAME LBRACK t= test RBRACK );
    public final Exp var() throws RecognitionException {
        Exp ret = null;

        Token n=null;
        Exp t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:239:2: (n= NAME | n= NAME LBRACK t= test RBRACK )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==NAME) ) {
                int LA22_1 = input.LA(2);

                if ( (LA22_1==LBRACK) ) {
                    alt22=2;
                }
                else if ( (LA22_1==EOF||LA22_1==NEWLINE||LA22_1==SEMI||(LA22_1>=RBRACK && LA22_1<=AND)||(LA22_1>=LESS && LA22_1<=POW)||LA22_1==RPAREN||LA22_1==COMMA) ) {
                    alt22=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 22, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:239:4: n= NAME
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_var1035); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Var((n!=null?n.getText():null));
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:240:4: n= NAME LBRACK t= test RBRACK
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_var1044); if (state.failed) return ret;
                    match(input,LBRACK,FOLLOW_LBRACK_in_var1046); if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_var1050);
                    t=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RBRACK,FOLLOW_RBRACK_in_var1052); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new ArrayEl((n!=null?n.getText():null), t);
                    }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "var"


    // $ANTLR start "args"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:243:1: args returns [List<Exp> ret] : a= test ( COMMA a= test )* ;
    public final List<Exp> args() throws RecognitionException {
        List<Exp> ret = null;

        Exp a = null;



        	List<Exp> l = new LinkedList<Exp>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:250:2: (a= test ( COMMA a= test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:250:4: a= test ( COMMA a= test )*
            {
            pushFollow(FOLLOW_test_in_args1082);
            a=test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              l.add(a);
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:250:28: ( COMMA a= test )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==COMMA) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:250:29: COMMA a= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_args1087); if (state.failed) return ret;
            	    pushFollow(FOLLOW_test_in_args1091);
            	    a=test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      l.add(a);
            	    }

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = l;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "args"


    // $ANTLR start "flow_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:253:1: flow_stmt returns [Term ret] : (s= break_stmt | s= continue_stmt | s= return_stmt );
    public final Term flow_stmt() throws RecognitionException {
        Term ret = null;

        Term s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:257:2: (s= break_stmt | s= continue_stmt | s= return_stmt )
            int alt24=3;
            switch ( input.LA(1) ) {
            case 51:
                {
                alt24=1;
                }
                break;
            case 52:
                {
                alt24=2;
                }
                break;
            case 53:
                {
                alt24=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:257:5: s= break_stmt
                    {
                    pushFollow(FOLLOW_break_stmt_in_flow_stmt1119);
                    s=break_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:258:5: s= continue_stmt
                    {
                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt1127);
                    s=continue_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:259:5: s= return_stmt
                    {
                    pushFollow(FOLLOW_return_stmt_in_flow_stmt1135);
                    s=return_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;

            }
            if ( state.backtracking==0 ) {

              	ret = s;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "flow_stmt"


    // $ANTLR start "break_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:262:1: break_stmt returns [Term ret] : 'break' ;
    public final Term break_stmt() throws RecognitionException {
        Term ret = null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:263:2: ( 'break' )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:263:5: 'break'
            {
            match(input,51,FOLLOW_51_in_break_stmt1151); if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = new Break();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "break_stmt"


    // $ANTLR start "continue_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:266:1: continue_stmt returns [Term ret] : 'continue' ;
    public final Term continue_stmt() throws RecognitionException {
        Term ret = null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:267:2: ( 'continue' )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:267:5: 'continue'
            {
            match(input,52,FOLLOW_52_in_continue_stmt1169); if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = new Continue();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "continue_stmt"


    // $ANTLR start "return_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:270:1: return_stmt returns [Term ret] : 'return' (t= test )? ;
    public final Term return_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:271:2: ( 'return' (t= test )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:271:5: 'return' (t= test )?
            {
            match(input,53,FOLLOW_53_in_return_stmt1187); if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:271:14: (t= test )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==NAME||LA25_0==NOT||LA25_0==MINUS||LA25_0==LPAREN||(LA25_0>=INT && LA25_0<=BOOL)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:271:15: t= test
                    {
                    pushFollow(FOLLOW_test_in_return_stmt1192);
                    t=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Return(new Some<Exp>(t));
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(ret==null) ret = new Return();
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "return_stmt"


    // $ANTLR start "compound_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:274:1: compound_stmt returns [Term ret] : (t= if_stmt | t= while_stmt | t= for_stmt | t= funcdef );
    public final Term compound_stmt() throws RecognitionException {
        Term ret = null;

        Term t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:278:2: (t= if_stmt | t= while_stmt | t= for_stmt | t= funcdef )
            int alt26=4;
            switch ( input.LA(1) ) {
            case 54:
                {
                alt26=1;
                }
                break;
            case 56:
                {
                alt26=2;
                }
                break;
            case 57:
                {
                alt26=3;
                }
                break;
            case 58:
                {
                alt26=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:278:4: t= if_stmt
                    {
                    pushFollow(FOLLOW_if_stmt_in_compound_stmt1222);
                    t=if_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:279:5: t= while_stmt
                    {
                    pushFollow(FOLLOW_while_stmt_in_compound_stmt1230);
                    t=while_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:280:5: t= for_stmt
                    {
                    pushFollow(FOLLOW_for_stmt_in_compound_stmt1238);
                    t=for_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:281:5: t= funcdef
                    {
                    pushFollow(FOLLOW_funcdef_in_compound_stmt1246);
                    t=funcdef();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;

            }
            if ( state.backtracking==0 ) {

              	ret = t;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "compound_stmt"


    // $ANTLR start "if_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:284:1: if_stmt returns [Term ret] : 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )? ;
    public final Term if_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;

        List<Term> s = null;

        List<Term> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:285:2: ( 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:285:4: 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )?
            {
            match(input,54,FOLLOW_54_in_if_stmt1262); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_if_stmt1264); if (state.failed) return ret;
            pushFollow(FOLLOW_test_in_if_stmt1268);
            t=test();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_if_stmt1270); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_if_stmt1274);
            s=statement();

            state._fsp--;
            if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:285:42: ( 'else' e= statement )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==55) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:285:43: 'else' e= statement
                    {
                    match(input,55,FOLLOW_55_in_if_stmt1277); if (state.failed) return ret;
                    pushFollow(FOLLOW_statement_in_if_stmt1281);
                    e=statement();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new If(t, new Stats(s), new Some<Stats>(new Stats(e)));
                    }

                    }
                    break;

            }

            if ( state.backtracking==0 ) {
              if(ret==null) ret = new If(t, new Stats(s));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "if_stmt"


    // $ANTLR start "while_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:289:1: while_stmt returns [Term ret] : 'while' LPAREN t= test RPAREN s= statement ;
    public final Term while_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:2: ( 'while' LPAREN t= test RPAREN s= statement )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:4: 'while' LPAREN t= test RPAREN s= statement
            {
            match(input,56,FOLLOW_56_in_while_stmt1307); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_while_stmt1309); if (state.failed) return ret;
            pushFollow(FOLLOW_test_in_while_stmt1313);
            t=test();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_while_stmt1315); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_while_stmt1319);
            s=statement();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = new While(t, new Stats(s));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "while_stmt"


    // $ANTLR start "for_stmt"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:293:1: for_stmt returns [Term ret] : 'for' LPAREN v= NAME COLON c= var RPAREN s= statement ;
    public final Term for_stmt() throws RecognitionException {
        Term ret = null;

        Token v=null;
        Exp c = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:294:2: ( 'for' LPAREN v= NAME COLON c= var RPAREN s= statement )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:294:4: 'for' LPAREN v= NAME COLON c= var RPAREN s= statement
            {
            match(input,57,FOLLOW_57_in_for_stmt1337); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_for_stmt1339); if (state.failed) return ret;
            v=(Token)match(input,NAME,FOLLOW_NAME_in_for_stmt1343); if (state.failed) return ret;
            match(input,COLON,FOLLOW_COLON_in_for_stmt1345); if (state.failed) return ret;
            pushFollow(FOLLOW_var_in_for_stmt1349);
            c=var();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_for_stmt1351); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_for_stmt1355);
            s=statement();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = new For((v!=null?v.getText():null), c, new Stats(s));
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "for_stmt"


    // $ANTLR start "funcdef"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:297:1: funcdef returns [Term ret] : 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY ;
    public final Term funcdef() throws RecognitionException {
        Term ret = null;

        Token name=null;
        Token a=null;
        List<Term> s = null;



        	List<String> args = new LinkedList<String>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:2: ( 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:4: 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY
            {
            match(input,58,FOLLOW_58_in_funcdef1383); if (state.failed) return ret;
            name=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1387); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_funcdef1389); if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:32: (a= NAME ( COMMA a= NAME )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==NAME) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:33: a= NAME ( COMMA a= NAME )*
                    {
                    a=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1394); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      args.add((a!=null?a.getText():null));
                    }
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:61: ( COMMA a= NAME )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:306:62: COMMA a= NAME
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_funcdef1399); if (state.failed) return ret;
                    	    a=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1403); if (state.failed) return ret;
                    	    if ( state.backtracking==0 ) {
                    	      args.add((a!=null?a.getText():null));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_funcdef1411); if (state.failed) return ret;
            match(input,LCURLY,FOLLOW_LCURLY_in_funcdef1413); if (state.failed) return ret;
            pushFollow(FOLLOW_statements_in_funcdef1417);
            s=statements();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RCURLY,FOLLOW_RCURLY_in_funcdef1419); if (state.failed) return ret;

            }

            if ( state.backtracking==0 ) {

              	FuncDef func = new FuncDef((name!=null?name.getText():null), args, new Stats(s));
              	funcdefs.add(func);
              	ret = func;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "funcdef"


    // $ANTLR start "stab"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:309:1: stab : STAB NEWLINE ;
    public final void stab() throws RecognitionException {
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:309:6: ( STAB NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:309:8: STAB NEWLINE
            {
            match(input,STAB,FOLLOW_STAB_in_stab1429); if (state.failed) return ;
            match(input,NEWLINE,FOLLOW_NEWLINE_in_stab1431); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "stab"


    // $ANTLR start "essencedef"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:1: essencedef returns [String v] : ESSENCE ver= version NEWLINE ;
    public final String essencedef() throws RecognitionException {
        String v = null;

        String ver = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:312:2: ( ESSENCE ver= version NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:312:4: ESSENCE ver= version NEWLINE
            {
            match(input,ESSENCE,FOLLOW_ESSENCE_in_essencedef1444); if (state.failed) return v;
            pushFollow(FOLLOW_version_in_essencedef1448);
            ver=version();

            state._fsp--;
            if (state.failed) return v;
            match(input,NEWLINE,FOLLOW_NEWLINE_in_essencedef1450); if (state.failed) return v;
            if ( state.backtracking==0 ) {
              v = "language ESSENCE\' " + ver;
            }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return v;
    }
    // $ANTLR end "essencedef"

    public static class augassign_return extends ParserRuleReturnScope {
        public String op;
    };

    // $ANTLR start "augassign"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:315:1: augassign returns [String op] : ( PLUSEQUAL | MINUSEQUAL | MULTEQUAL | DIVEQUAL | MODEQUAL | ASSIGN );
    public final stabParser.augassign_return augassign() throws RecognitionException {
        stabParser.augassign_return retval = new stabParser.augassign_return();
        retval.start = input.LT(1);

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:316:2: ( PLUSEQUAL | MINUSEQUAL | MULTEQUAL | DIVEQUAL | MODEQUAL | ASSIGN )
            int alt30=6;
            switch ( input.LA(1) ) {
            case PLUSEQUAL:
                {
                alt30=1;
                }
                break;
            case MINUSEQUAL:
                {
                alt30=2;
                }
                break;
            case MULTEQUAL:
                {
                alt30=3;
                }
                break;
            case DIVEQUAL:
                {
                alt30=4;
                }
                break;
            case MODEQUAL:
                {
                alt30=5;
                }
                break;
            case ASSIGN:
                {
                alt30=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:316:4: PLUSEQUAL
                    {
                    match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1467); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "+=";
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:317:5: MINUSEQUAL
                    {
                    match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign1476); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "-=";
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:318:5: MULTEQUAL
                    {
                    match(input,MULTEQUAL,FOLLOW_MULTEQUAL_in_augassign1485); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "*=";
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:319:5: DIVEQUAL
                    {
                    match(input,DIVEQUAL,FOLLOW_DIVEQUAL_in_augassign1494); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "/=";
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:320:5: MODEQUAL
                    {
                    match(input,MODEQUAL,FOLLOW_MODEQUAL_in_augassign1503); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "%=";
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:321:5: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_augassign1512); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "=";
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "augassign"


    // $ANTLR start "version"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:324:1: version returns [String ret] : s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )* ;
    public final String version() throws RecognitionException {
        String ret = null;

        Token s=null;


        	StringBuffer sb = new StringBuffer();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:331:2: (s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:331:4: s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )*
            {
            s=(Token)input.LT(1);
            if ( input.LA(1)==NAME||input.LA(1)==INT ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            if ( state.backtracking==0 ) {
              sb.append((s!=null?s.getText():null));
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:331:39: (s= DOT s= ( INT | NAME ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==DOT) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:331:40: s= DOT s= ( INT | NAME )
            	    {
            	    s=(Token)match(input,DOT,FOLLOW_DOT_in_version1553); if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      sb.append((s!=null?s.getText():null));
            	    }
            	    s=(Token)input.LT(1);
            	    if ( input.LA(1)==NAME||input.LA(1)==INT ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ret;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    if ( state.backtracking==0 ) {
            	      sb.append((s!=null?s.getText():null));
            	    }

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }

            if ( state.backtracking==0 ) {

              	ret = sb.toString();

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ret;
    }
    // $ANTLR end "version"

    // $ANTLR start synpred1_stab
    public final void synpred1_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:75:4: ( stabblock )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:75:5: stabblock
        {
        pushFollow(FOLLOW_stabblock_in_synpred1_stab166);
        stabblock();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred1_stab

    // $ANTLR start synpred2_stab
    public final void synpred2_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:4: ( NAME augassign test )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:5: NAME augassign test
        {
        match(input,NAME,FOLLOW_NAME_in_synpred2_stab479); if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred2_stab481);
        augassign();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred2_stab483);
        test();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_stab

    // $ANTLR start synpred3_stab
    public final void synpred3_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:160:4: ( NAME LBRACK test RBRACK augassign test )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:160:5: NAME LBRACK test RBRACK augassign test
        {
        match(input,NAME,FOLLOW_NAME_in_synpred3_stab506); if (state.failed) return ;
        match(input,LBRACK,FOLLOW_LBRACK_in_synpred3_stab508); if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred3_stab510);
        test();

        state._fsp--;
        if (state.failed) return ;
        match(input,RBRACK,FOLLOW_RBRACK_in_synpred3_stab512); if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred3_stab514);
        augassign();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred3_stab516);
        test();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_stab

    // Delegated rules

    public final boolean synpred2_stab() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred2_stab_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred1_stab() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred1_stab_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred3_stab() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_stab_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_rules_in_program44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stab_in_rules60 = new BitSet(new long[]{0x0778001190808490L});
    public static final BitSet FOLLOW_statements_in_rules64 = new BitSet(new long[]{0x0778001190808490L});
    public static final BitSet FOLLOW_essence_in_rules68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essence_in_rules79 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essencedef_in_essence102 = new BitSet(new long[]{0x07FFFFFFFFFFFFF0L});
    public static final BitSet FOLLOW_estatements_in_essence106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_estatement_in_estatements139 = new BitSet(new long[]{0x07FFFFFFFFFFFFF2L});
    public static final BitSet FOLLOW_stabblock_in_estatement173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essencecode_in_estatement182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_something_in_essencecode209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_something242 = new BitSet(new long[]{0x07FFFFFFFFFFFFF0L});
    public static final BitSet FOLLOW_NEWLINE_in_something252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBEGIN_in_stabblock273 = new BitSet(new long[]{0x07780001908084D0L});
    public static final BitSet FOLLOW_statements_in_stabblock277 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_SEND_in_stabblock279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements308 = new BitSet(new long[]{0x0778000190808492L});
    public static final BitSet FOLLOW_LCURLY_in_statement327 = new BitSet(new long[]{0x0778000190808590L});
    public static final BitSet FOLLOW_statements_in_statement331 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RCURLY_in_statement333 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_statement345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_statement357 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_statement367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt402 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt414 = new BitSet(new long[]{0x0038000190808400L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt418 = new BitSet(new long[]{0x0000000000000210L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt425 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_simple_stmt429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_stmt_in_small_stmt453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flow_stmt_in_small_stmt462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_expr_stmt490 = new BitSet(new long[]{0x000007E000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt494 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_expr_stmt498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_expr_stmt521 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_LBRACK_in_expr_stmt523 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_expr_stmt527 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RBRACK_in_expr_stmt529 = new BitSet(new long[]{0x000007E000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt531 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_expr_stmt533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_expr_stmt542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_test577 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_OR_in_test582 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_and_test_in_test586 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_not_test_in_and_test617 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_AND_in_and_test622 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_not_test_in_and_test626 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_NOT_in_not_test646 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_not_test_in_not_test650 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arith_expr_in_comparison689 = new BitSet(new long[]{0x00000000003F0002L});
    public static final BitSet FOLLOW_comp_op_in_comparison696 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_arith_expr_in_comparison700 = new BitSet(new long[]{0x00000000003F0002L});
    public static final BitSet FOLLOW_LESS_in_comp_op720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_comp_op734 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op741 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op748 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_arith_expr784 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_set_in_arith_expr791 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_term_in_arith_expr799 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_factor_in_term829 = new BitSet(new long[]{0x0000000007000002L});
    public static final BitSet FOLLOW_set_in_term836 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_factor_in_term851 = new BitSet(new long[]{0x0000000007000002L});
    public static final BitSet FOLLOW_atom_in_factor873 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_POW_in_factor878 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_factor_in_factor882 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom902 = new BitSet(new long[]{0x00000001B0808400L});
    public static final BitSet FOLLOW_test_in_atom907 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom920 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_atom922 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_args_in_atom926 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom941 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_DOT_in_atom943 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_atom947 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_atom949 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_args_in_atom953 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom955 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom966 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_INT_in_atom970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_atom979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_atom988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_value1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_value1015 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_var1035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_var1044 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_LBRACK_in_var1046 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_var1050 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RBRACK_in_var1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_args1082 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_COMMA_in_args1087 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_args1091 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt1119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_break_stmt1151 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_continue_stmt1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_return_stmt1187 = new BitSet(new long[]{0x0000000190808402L});
    public static final BitSet FOLLOW_test_in_return_stmt1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt1222 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt1230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt1238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_if_stmt1262 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_if_stmt1264 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_if_stmt1268 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_if_stmt1270 = new BitSet(new long[]{0x0778000190808490L});
    public static final BitSet FOLLOW_statement_in_if_stmt1274 = new BitSet(new long[]{0x0080000000000002L});
    public static final BitSet FOLLOW_55_in_if_stmt1277 = new BitSet(new long[]{0x0778000190808490L});
    public static final BitSet FOLLOW_statement_in_if_stmt1281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_while_stmt1307 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_while_stmt1309 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_while_stmt1313 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_while_stmt1315 = new BitSet(new long[]{0x0778000190808490L});
    public static final BitSet FOLLOW_statement_in_while_stmt1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_for_stmt1337 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_for_stmt1339 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_for_stmt1343 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt1345 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_var_in_for_stmt1349 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_RPAREN_in_for_stmt1351 = new BitSet(new long[]{0x0778000190808490L});
    public static final BitSet FOLLOW_statement_in_for_stmt1355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_funcdef1383 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_funcdef1387 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_LPAREN_in_funcdef1389 = new BitSet(new long[]{0x0000000020000400L});
    public static final BitSet FOLLOW_NAME_in_funcdef1394 = new BitSet(new long[]{0x0000000220000000L});
    public static final BitSet FOLLOW_COMMA_in_funcdef1399 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_NAME_in_funcdef1403 = new BitSet(new long[]{0x0000000220000000L});
    public static final BitSet FOLLOW_RPAREN_in_funcdef1411 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_LCURLY_in_funcdef1413 = new BitSet(new long[]{0x0778000190808590L});
    public static final BitSet FOLLOW_statements_in_funcdef1417 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RCURLY_in_funcdef1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAB_in_stab1429 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_stab1431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESSENCE_in_essencedef1444 = new BitSet(new long[]{0x0000000080000400L});
    public static final BitSet FOLLOW_version_in_essencedef1448 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_essencedef1450 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSEQUAL_in_augassign1467 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSEQUAL_in_augassign1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTEQUAL_in_augassign1485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVEQUAL_in_augassign1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODEQUAL_in_augassign1503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_augassign1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_version1542 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_DOT_in_version1553 = new BitSet(new long[]{0x0000000080000400L});
    public static final BitSet FOLLOW_set_in_version1559 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_stabblock_in_synpred1_stab166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_synpred2_stab479 = new BitSet(new long[]{0x000007E000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred2_stab481 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_synpred2_stab483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_synpred3_stab506 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_LBRACK_in_synpred3_stab508 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_synpred3_stab510 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RBRACK_in_synpred3_stab512 = new BitSet(new long[]{0x000007E000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred3_stab514 = new BitSet(new long[]{0x0000000190808400L});
    public static final BitSet FOLLOW_test_in_synpred3_stab516 = new BitSet(new long[]{0x0000000000000002L});

}