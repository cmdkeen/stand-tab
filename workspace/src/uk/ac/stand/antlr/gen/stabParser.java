// $ANTLR 3.1.2 /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g 2009-04-17 02:30:30

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "NEWLINE", "COMMENT", "SBEGIN", "SEND", "LCURLY", "RCURLY", "SEMI", "NAME", "LBRACK", "RBRACK", "OR", "AND", "NOT", "LESS", "GREATER", "EQUALS", "GREATEREQUAL", "LESSEQUAL", "NOTEQUAL", "PLUS", "MINUS", "MULT", "DIV", "MOD", "POW", "LPAREN", "RPAREN", "DOT", "INT", "BOOL", "EPRIMEBLOCK", "COMMA", "COLON", "STAB", "ESSENCE", "PLUSEQUAL", "MINUSEQUAL", "MULTEQUAL", "DIVEQUAL", "MODEQUAL", "ASSIGN", "ENDESSENCE", "DEF", "WS", "USCORE", "ECOMMENT", "QUOTE", "LQUOTE", "ESC", "'break'", "'continue'", "'return'", "'if'", "'else'", "'while'", "'for'", "'function'"
    };
    public static final int LBRACK=12;
    public static final int DEF=46;
    public static final int MOD=27;
    public static final int ESC=52;
    public static final int MULTEQUAL=41;
    public static final int GREATEREQUAL=20;
    public static final int ESSENCE=38;
    public static final int EQUALS=19;
    public static final int STAB=37;
    public static final int NOT=16;
    public static final int AND=15;
    public static final int T__60=60;
    public static final int EOF=-1;
    public static final int DIVEQUAL=42;
    public static final int LPAREN=29;
    public static final int PLUSEQUAL=39;
    public static final int NOTEQUAL=22;
    public static final int T__55=55;
    public static final int MINUSEQUAL=40;
    public static final int T__56=56;
    public static final int QUOTE=50;
    public static final int T__57=57;
    public static final int RPAREN=30;
    public static final int NAME=11;
    public static final int T__58=58;
    public static final int GREATER=18;
    public static final int MODEQUAL=43;
    public static final int POW=28;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int COMMA=35;
    public static final int T__59=59;
    public static final int LESS=17;
    public static final int SEND=7;
    public static final int PLUS=23;
    public static final int COMMENT=5;
    public static final int DOT=31;
    public static final int SBEGIN=6;
    public static final int RBRACK=13;
    public static final int LQUOTE=51;
    public static final int BOOL=33;
    public static final int LCURLY=8;
    public static final int INT=32;
    public static final int MINUS=24;
    public static final int MULT=25;
    public static final int SEMI=10;
    public static final int EPRIMEBLOCK=34;
    public static final int COLON=36;
    public static final int WS=47;
    public static final int NEWLINE=4;
    public static final int ECOMMENT=49;
    public static final int ENDESSENCE=45;
    public static final int USCORE=48;
    public static final int RCURLY=9;
    public static final int OR=14;
    public static final int ASSIGN=44;
    public static final int DIV=26;
    public static final int LESSEQUAL=21;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:36:1: rules returns [List<Stats> ret] : ( stab s= statements (e= essence )? | e= essence );
    public final List<Stats> rules() throws RecognitionException {
        List<Stats> ret = null;

        List<Term> s = null;

        List<Stats> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:2: ( stab s= statements (e= essence )? | e= essence )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==STAB) ) {
                alt2=1;
            }
            else if ( (LA2_0==ESSENCE) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:4: stab s= statements (e= essence )?
                    {
                    pushFollow(FOLLOW_stab_in_rules60);
                    stab();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_statements_in_rules64);
                    s=statements();

                    state._fsp--;
                    if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:23: (e= essence )?
                    int alt1=2;
                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==ESSENCE) ) {
                        alt1=1;
                    }
                    switch (alt1) {
                        case 1 :
                            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:37:23: e= essence
                            {
                            pushFollow(FOLLOW_essence_in_rules68);
                            e=essence();

                            state._fsp--;
                            if (state.failed) return ret;

                            }
                            break;

                    }

                    if ( state.backtracking==0 ) {

                      			List<Stats> l = new LinkedList<Stats>();
                      			if(s!=null) l.add(new Stats(s));
                      			if(e!=null) l.addAll(e);
                      			ret = l;
                      		
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:44:4: e= essence
                    {
                    pushFollow(FOLLOW_essence_in_rules80);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:49:1: essence returns [List<Stats> ret] : d= essencedef e= estatements ;
    public final List<Stats> essence() throws RecognitionException {
        List<Stats> ret = null;

        String d = null;

        List<Stats> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:50:2: (d= essencedef e= estatements )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:50:4: d= essencedef e= estatements
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:61:1: estatements returns [List<Stats> ret] : (s= estatement )* ;
    public final List<Stats> estatements() throws RecognitionException {
        List<Stats> ret = null;

        Stats s = null;



        	List<Stats> l = new LinkedList<Stats>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:68:2: ( (s= estatement )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:68:4: (s= estatement )*
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:68:4: (s= estatement )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==NEWLINE||(LA3_0>=SBEGIN && LA3_0<=60)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:68:5: s= estatement
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
            	    break loop3;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:73:1: estatement returns [Stats ret] : ( ( stabblock )=>s= stabblock | ec= essencecode );
    public final Stats estatement() throws RecognitionException {
        Stats ret = null;

        Stats s = null;

        String ec = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:74:2: ( ( stabblock )=>s= stabblock | ec= essencecode )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==SBEGIN) ) {
                int LA4_1 = input.LA(2);

                if ( (synpred1_stab()) ) {
                    alt4=1;
                }
                else if ( (true) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA4_0==NEWLINE||(LA4_0>=SEND && LA4_0<=60)) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:74:4: ( stabblock )=>s= stabblock
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
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:75:4: ec= essencecode
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:82:1: essencecode returns [String ret] : something ;
    public final String essencecode() throws RecognitionException {
        String ret = null;


        	eblock++;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:89:2: ( something )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:89:4: something
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:92:1: something : ( options {greedy=false; } : t=~ ( NEWLINE | COMMENT ) )* NEWLINE ;
    public final void something() throws RecognitionException {
        Token t=null;


        	StringBuffer buffer = new StringBuffer();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:99:2: ( ( options {greedy=false; } : t=~ ( NEWLINE | COMMENT ) )* NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:99:4: ( options {greedy=false; } : t=~ ( NEWLINE | COMMENT ) )* NEWLINE
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:99:4: ( options {greedy=false; } : t=~ ( NEWLINE | COMMENT ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=SBEGIN && LA5_0<=60)) ) {
                    alt5=1;
                }
                else if ( (LA5_0==NEWLINE) ) {
                    alt5=2;
                }


                switch (alt5) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:99:30: t=~ ( NEWLINE | COMMENT )
            	    {
            	    t=(Token)input.LT(1);
            	    if ( (input.LA(1)>=SBEGIN && input.LA(1)<=60) ) {
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
            	    break loop5;
                }
            } while (true);

            match(input,NEWLINE,FOLLOW_NEWLINE_in_something254); if (state.failed) return ;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:112:1: stabblock returns [Stats ret] : SBEGIN s= statements SEND ;
    public final Stats stabblock() throws RecognitionException {
        Stats ret = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:116:2: ( SBEGIN s= statements SEND )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:116:4: SBEGIN s= statements SEND
            {
            match(input,SBEGIN,FOLLOW_SBEGIN_in_stabblock275); if (state.failed) return ret;
            pushFollow(FOLLOW_statements_in_stabblock279);
            s=statements();

            state._fsp--;
            if (state.failed) return ret;
            match(input,SEND,FOLLOW_SEND_in_stabblock281); if (state.failed) return ret;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:119:1: statements returns [List<Term> ret] : (s= statement )* ;
    public final List<Term> statements() throws RecognitionException {
        List<Term> ret = null;

        List<Term> s = null;



        	List<Term> l = new LinkedList<Term>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:126:2: ( (s= statement )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:126:4: (s= statement )*
            {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:126:4: (s= statement )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==NEWLINE||LA6_0==LCURLY||LA6_0==NAME||LA6_0==NOT||LA6_0==MINUS||LA6_0==LPAREN||(LA6_0>=INT && LA6_0<=EPRIMEBLOCK)||(LA6_0>=53 && LA6_0<=56)||(LA6_0>=58 && LA6_0<=60)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:126:5: s= statement
            	    {
            	    pushFollow(FOLLOW_statement_in_statements310);
            	    s=statement();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      if(s!=null) l.addAll(s);
            	    }

            	    }
            	    break;

            	default :
            	    break loop6;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:129:1: statement returns [List<Term> ret] : ( LCURLY ls= statements RCURLY | ss= simple_stmt | s= compound_stmt | NEWLINE );
    public final List<Term> statement() throws RecognitionException {
        List<Term> ret = null;

        List<Term> ls = null;

        List<Term> ss = null;

        Term s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:130:2: ( LCURLY ls= statements RCURLY | ss= simple_stmt | s= compound_stmt | NEWLINE )
            int alt7=4;
            switch ( input.LA(1) ) {
            case LCURLY:
                {
                alt7=1;
                }
                break;
            case NAME:
            case NOT:
            case MINUS:
            case LPAREN:
            case INT:
            case BOOL:
            case EPRIMEBLOCK:
            case 53:
            case 54:
            case 55:
                {
                alt7=2;
                }
                break;
            case 56:
            case 58:
            case 59:
            case 60:
                {
                alt7=3;
                }
                break;
            case NEWLINE:
                {
                alt7=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:130:4: LCURLY ls= statements RCURLY
                    {
                    match(input,LCURLY,FOLLOW_LCURLY_in_statement329); if (state.failed) return ret;
                    pushFollow(FOLLOW_statements_in_statement333);
                    ls=statements();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RCURLY,FOLLOW_RCURLY_in_statement335); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = ls;
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:132:4: ss= simple_stmt
                    {
                    pushFollow(FOLLOW_simple_stmt_in_statement347);
                    ss=simple_stmt();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = ss;
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:134:4: s= compound_stmt
                    {
                    pushFollow(FOLLOW_compound_stmt_in_statement359);
                    s=compound_stmt();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      List<Term> lt = new LinkedList<Term>(); lt.add(s); ret = lt;
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:136:4: NEWLINE
                    {
                    match(input,NEWLINE,FOLLOW_NEWLINE_in_statement369); if (state.failed) return ret;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:139:1: simple_stmt returns [List<Term> ret] : s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE ;
    public final List<Term> simple_stmt() throws RecognitionException {
        List<Term> ret = null;

        Term s = null;



        	List<Term> l = new LinkedList<Term>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:6: (s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:11: s= small_stmt ( options {greedy=true; } : SEMI s= small_stmt )* ( SEMI )? NEWLINE
            {
            pushFollow(FOLLOW_small_stmt_in_simple_stmt404);
            s=small_stmt();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              l.add(s);
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:41: ( options {greedy=true; } : SEMI s= small_stmt )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==SEMI) ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1==NAME||LA8_1==NOT||LA8_1==MINUS||LA8_1==LPAREN||(LA8_1>=INT && LA8_1<=EPRIMEBLOCK)||(LA8_1>=53 && LA8_1<=55)) ) {
                        alt8=1;
                    }


                }


                switch (alt8) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:65: SEMI s= small_stmt
            	    {
            	    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt416); if (state.failed) return ret;
            	    pushFollow(FOLLOW_small_stmt_in_simple_stmt420);
            	    s=small_stmt();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      l.add(s);
            	    }

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:102: ( SEMI )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==SEMI) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:146:103: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_simple_stmt427); if (state.failed) return ret;

                    }
                    break;

            }

            match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt431); if (state.failed) return ret;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:149:1: small_stmt returns [Term ret] : (r= expr_stmt | r= flow_stmt );
    public final Term small_stmt() throws RecognitionException {
        Term ret = null;

        Term r = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:153:2: (r= expr_stmt | r= flow_stmt )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NAME||LA10_0==NOT||LA10_0==MINUS||LA10_0==LPAREN||(LA10_0>=INT && LA10_0<=EPRIMEBLOCK)) ) {
                alt10=1;
            }
            else if ( ((LA10_0>=53 && LA10_0<=55)) ) {
                alt10=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:153:5: r= expr_stmt
                    {
                    pushFollow(FOLLOW_expr_stmt_in_small_stmt455);
                    r=expr_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:154:5: r= flow_stmt
                    {
                    pushFollow(FOLLOW_flow_stmt_in_small_stmt464);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:157:1: expr_stmt returns [Term ret] : ( ( NAME augassign test )=>v= NAME o= augassign r= test | ( NAME LBRACK test RBRACK augassign test )=>v= NAME LBRACK t= test RBRACK o= augassign r= test | t= test );
    public final Term expr_stmt() throws RecognitionException {
        Term ret = null;

        Token v=null;
        stabParser.augassign_return o = null;

        Exp r = null;

        Exp t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:158:2: ( ( NAME augassign test )=>v= NAME o= augassign r= test | ( NAME LBRACK test RBRACK augassign test )=>v= NAME LBRACK t= test RBRACK o= augassign r= test | t= test )
            int alt11=3;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:158:4: ( NAME augassign test )=>v= NAME o= augassign r= test
                    {
                    v=(Token)match(input,NAME,FOLLOW_NAME_in_expr_stmt492); if (state.failed) return ret;
                    pushFollow(FOLLOW_augassign_in_expr_stmt496);
                    o=augassign();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt500);
                    r=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Assign((v!=null?v.getText():null), (o!=null?input.toString(o.start,o.stop):null), r);
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:4: ( NAME LBRACK test RBRACK augassign test )=>v= NAME LBRACK t= test RBRACK o= augassign r= test
                    {
                    v=(Token)match(input,NAME,FOLLOW_NAME_in_expr_stmt525); if (state.failed) return ret;
                    match(input,LBRACK,FOLLOW_LBRACK_in_expr_stmt527); if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt531);
                    t=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,RBRACK,FOLLOW_RBRACK_in_expr_stmt533); if (state.failed) return ret;
                    pushFollow(FOLLOW_augassign_in_expr_stmt537);
                    o=augassign();

                    state._fsp--;
                    if (state.failed) return ret;
                    pushFollow(FOLLOW_test_in_expr_stmt541);
                    r=test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new ElAssign((v!=null?v.getText():null), t, (o!=null?input.toString(o.start,o.stop):null), r);
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:160:4: t= test
                    {
                    pushFollow(FOLLOW_test_in_expr_stmt550);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:163:1: test returns [Exp ret] : l= and_test ( OR r= and_test )* ;
    public final Exp test() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:168:2: (l= and_test ( OR r= and_test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:168:5: l= and_test ( OR r= and_test )*
            {
            pushFollow(FOLLOW_and_test_in_test585);
            l=and_test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:168:33: ( OR r= and_test )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==OR) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:168:34: OR r= and_test
            	    {
            	    match(input,OR,FOLLOW_OR_in_test590); if (state.failed) return ret;
            	    pushFollow(FOLLOW_and_test_in_test594);
            	    r=and_test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp("or", left, r);
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
    // $ANTLR end "test"


    // $ANTLR start "and_test"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:171:1: and_test returns [Exp ret] : l= not_test ( AND r= not_test )* ;
    public final Exp and_test() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:176:2: (l= not_test ( AND r= not_test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:176:5: l= not_test ( AND r= not_test )*
            {
            pushFollow(FOLLOW_not_test_in_and_test625);
            l=not_test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:176:33: ( AND r= not_test )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==AND) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:176:34: AND r= not_test
            	    {
            	    match(input,AND,FOLLOW_AND_in_and_test630); if (state.failed) return ret;
            	    pushFollow(FOLLOW_not_test_in_and_test634);
            	    r=not_test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp("and", left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop13;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:179:1: not_test returns [Exp ret] : ( NOT t= not_test | c= comparison );
    public final Exp not_test() throws RecognitionException {
        Exp ret = null;

        Exp t = null;

        Exp c = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:180:2: ( NOT t= not_test | c= comparison )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==NOT) ) {
                alt14=1;
            }
            else if ( (LA14_0==NAME||LA14_0==MINUS||LA14_0==LPAREN||(LA14_0>=INT && LA14_0<=EPRIMEBLOCK)) ) {
                alt14=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:180:5: NOT t= not_test
                    {
                    match(input,NOT,FOLLOW_NOT_in_not_test654); if (state.failed) return ret;
                    pushFollow(FOLLOW_not_test_in_not_test658);
                    t=not_test();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EOp1("not", t);
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:181:5: c= comparison
                    {
                    pushFollow(FOLLOW_comparison_in_not_test668);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:184:1: comparison returns [Exp ret] : l= arith_expr (o= comp_op r= arith_expr )* ;
    public final Exp comparison() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        stabParser.comp_op_return o = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:189:2: (l= arith_expr (o= comp_op r= arith_expr )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:189:5: l= arith_expr (o= comp_op r= arith_expr )*
            {
            pushFollow(FOLLOW_arith_expr_in_comparison697);
            l=arith_expr();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:189:35: (o= comp_op r= arith_expr )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=LESS && LA15_0<=NOTEQUAL)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:189:36: o= comp_op r= arith_expr
            	    {
            	    pushFollow(FOLLOW_comp_op_in_comparison704);
            	    o=comp_op();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    pushFollow(FOLLOW_arith_expr_in_comparison708);
            	    r=arith_expr();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp((o!=null?input.toString(o.start,o.stop):null), left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop15;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:192:1: comp_op returns [String s] : ( LESS | GREATER | EQUALS | GREATEREQUAL | LESSEQUAL | NOTEQUAL );
    public final stabParser.comp_op_return comp_op() throws RecognitionException {
        stabParser.comp_op_return retval = new stabParser.comp_op_return();
        retval.start = input.LT(1);

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:193:2: ( LESS | GREATER | EQUALS | GREATEREQUAL | LESSEQUAL | NOTEQUAL )
            int alt16=6;
            switch ( input.LA(1) ) {
            case LESS:
                {
                alt16=1;
                }
                break;
            case GREATER:
                {
                alt16=2;
                }
                break;
            case EQUALS:
                {
                alt16=3;
                }
                break;
            case GREATEREQUAL:
                {
                alt16=4;
                }
                break;
            case LESSEQUAL:
                {
                alt16=5;
                }
                break;
            case NOTEQUAL:
                {
                alt16=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:193:5: LESS
                    {
                    match(input,LESS,FOLLOW_LESS_in_comp_op728); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "<";
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:194:4: GREATER
                    {
                    match(input,GREATER,FOLLOW_GREATER_in_comp_op735); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = ">";
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:195:4: EQUALS
                    {
                    match(input,EQUALS,FOLLOW_EQUALS_in_comp_op742); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "==";
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:196:4: GREATEREQUAL
                    {
                    match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op749); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = ">=";
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:197:4: LESSEQUAL
                    {
                    match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op756); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.s = "<=";
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:198:4: NOTEQUAL
                    {
                    match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op763); if (state.failed) return retval;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:201:1: arith_expr returns [Exp ret] : l= term (o= ( PLUS | MINUS ) r= term )* ;
    public final Exp arith_expr() throws RecognitionException {
        Exp ret = null;

        Token o=null;
        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:206:2: (l= term (o= ( PLUS | MINUS ) r= term )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:206:5: l= term (o= ( PLUS | MINUS ) r= term )*
            {
            pushFollow(FOLLOW_term_in_arith_expr792);
            l=term();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:206:29: (o= ( PLUS | MINUS ) r= term )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>=PLUS && LA17_0<=MINUS)) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:206:30: o= ( PLUS | MINUS ) r= term
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

            	    pushFollow(FOLLOW_term_in_arith_expr807);
            	    r=term();

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
    // $ANTLR end "arith_expr"


    // $ANTLR start "term"
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:209:1: term returns [Exp ret] : l= factor (o= ( MULT | DIV | MOD ) r= factor )* ;
    public final Exp term() throws RecognitionException {
        Exp ret = null;

        Token o=null;
        Exp l = null;

        Exp r = null;


        Exp left = null;
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:214:2: (l= factor (o= ( MULT | DIV | MOD ) r= factor )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:214:4: l= factor (o= ( MULT | DIV | MOD ) r= factor )*
            {
            pushFollow(FOLLOW_factor_in_term837);
            l=factor();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              left = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:214:30: (o= ( MULT | DIV | MOD ) r= factor )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=MULT && LA18_0<=MOD)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:214:31: o= ( MULT | DIV | MOD ) r= factor
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

            	    pushFollow(FOLLOW_factor_in_term859);
            	    r=factor();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      left = new EOp((o!=null?o.getText():null), left, r);
            	    }

            	    }
            	    break;

            	default :
            	    break loop18;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:217:1: factor returns [Exp ret] : l= atom ( POW r= factor )? ;
    public final Exp factor() throws RecognitionException {
        Exp ret = null;

        Exp l = null;

        Exp r = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:218:2: (l= atom ( POW r= factor )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:218:5: l= atom ( POW r= factor )?
            {
            pushFollow(FOLLOW_atom_in_factor881);
            l=atom();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              ret = l;
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:218:29: ( POW r= factor )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==POW) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:218:30: POW r= factor
                    {
                    match(input,POW,FOLLOW_POW_in_factor886); if (state.failed) return ret;
                    pushFollow(FOLLOW_factor_in_factor890);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:221:1: atom returns [Exp ret] : ( LPAREN (t= test )? RPAREN | ( var DOT NAME LPAREN ( args )? RPAREN )=>o= var DOT m= NAME LPAREN (a= args )? RPAREN | ( NAME LPAREN ( args )? RPAREN )=>n= NAME LPAREN (a= args )? RPAREN | MINUS i= INT | v= value | v= var );
    public final Exp atom() throws RecognitionException {
        Exp ret = null;

        Token m=null;
        Token n=null;
        Token i=null;
        Exp t = null;

        Exp o = null;

        List<Exp> a = null;

        Exp v = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:222:2: ( LPAREN (t= test )? RPAREN | ( var DOT NAME LPAREN ( args )? RPAREN )=>o= var DOT m= NAME LPAREN (a= args )? RPAREN | ( NAME LPAREN ( args )? RPAREN )=>n= NAME LPAREN (a= args )? RPAREN | MINUS i= INT | v= value | v= var )
            int alt23=6;
            alt23 = dfa23.predict(input);
            switch (alt23) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:222:5: LPAREN (t= test )? RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom910); if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:222:12: (t= test )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==NAME||LA20_0==NOT||LA20_0==MINUS||LA20_0==LPAREN||(LA20_0>=INT && LA20_0<=EPRIMEBLOCK)) ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:222:13: t= test
                            {
                            pushFollow(FOLLOW_test_in_atom915);
                            t=test();

                            state._fsp--;
                            if (state.failed) return ret;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_atom919); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = t;
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:4: ( var DOT NAME LPAREN ( args )? RPAREN )=>o= var DOT m= NAME LPAREN (a= args )? RPAREN
                    {
                    pushFollow(FOLLOW_var_in_atom944);
                    o=var();

                    state._fsp--;
                    if (state.failed) return ret;
                    match(input,DOT,FOLLOW_DOT_in_atom946); if (state.failed) return ret;
                    m=(Token)match(input,NAME,FOLLOW_NAME_in_atom950); if (state.failed) return ret;
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom952); if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:66: (a= args )?
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==NAME||LA21_0==NOT||LA21_0==MINUS||LA21_0==LPAREN||(LA21_0>=INT && LA21_0<=EPRIMEBLOCK)) ) {
                        alt21=1;
                    }
                    switch (alt21) {
                        case 1 :
                            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:66: a= args
                            {
                            pushFollow(FOLLOW_args_in_atom956);
                            a=args();

                            state._fsp--;
                            if (state.failed) return ret;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_atom959); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = (a==null) ? new Method(o, (m!=null?m.getText():null), new LinkedList<Exp>()) : new Method(o, (m!=null?m.getText():null), a);
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:4: ( NAME LPAREN ( args )? RPAREN )=>n= NAME LPAREN (a= args )? RPAREN
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_atom984); if (state.failed) return ret;
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom986); if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:48: (a= args )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==NAME||LA22_0==NOT||LA22_0==MINUS||LA22_0==LPAREN||(LA22_0>=INT && LA22_0<=EPRIMEBLOCK)) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:48: a= args
                            {
                            pushFollow(FOLLOW_args_in_atom990);
                            a=args();

                            state._fsp--;
                            if (state.failed) return ret;

                            }
                            break;

                    }

                    match(input,RPAREN,FOLLOW_RPAREN_in_atom993); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = (a==null) ? new Call((n!=null?n.getText():null), new LinkedList<Exp>()) : new Call((n!=null?n.getText():null), a);
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:227:4: MINUS i= INT
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_atom1004); if (state.failed) return ret;
                    i=(Token)match(input,INT,FOLLOW_INT_in_atom1008); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EOp1("-",new EInt(Integer.parseInt((i!=null?i.getText():null))));
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:228:4: v= value
                    {
                    pushFollow(FOLLOW_value_in_atom1017);
                    v=value();

                    state._fsp--;
                    if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = v;
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:229:4: v= var
                    {
                    pushFollow(FOLLOW_var_in_atom1026);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:232:1: value returns [Value ret] : (i= INT | b= BOOL | s= EPRIMEBLOCK );
    public final Value value() throws RecognitionException {
        Value ret = null;

        Token i=null;
        Token b=null;
        Token s=null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:233:2: (i= INT | b= BOOL | s= EPRIMEBLOCK )
            int alt24=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt24=1;
                }
                break;
            case BOOL:
                {
                alt24=2;
                }
                break;
            case EPRIMEBLOCK:
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
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:233:4: i= INT
                    {
                    i=(Token)match(input,INT,FOLLOW_INT_in_value1046); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EInt(Integer.parseInt((i!=null?i.getText():null)));
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:234:4: b= BOOL
                    {
                    b=(Token)match(input,BOOL,FOLLOW_BOOL_in_value1055); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new EBool(Boolean.parseBoolean((b!=null?b.getText():null)));
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:237:4: s= EPRIMEBLOCK
                    {
                    s=(Token)match(input,EPRIMEBLOCK,FOLLOW_EPRIMEBLOCK_in_value1068); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      String es = (s!=null?s.getText():null); es.substring(9,es.length()-9); ret = new EString(es);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:240:1: var returns [Exp ret] : (n= NAME | n= NAME ( LBRACK t= test RBRACK )+ );
    public final Exp var() throws RecognitionException {
        Exp ret = null;

        Token n=null;
        Exp t = null;



        	List<Exp> l = new LinkedList<Exp>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:244:2: (n= NAME | n= NAME ( LBRACK t= test RBRACK )+ )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==NAME) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==LBRACK) ) {
                    alt26=2;
                }
                else if ( (LA26_1==EOF||LA26_1==NEWLINE||LA26_1==SEMI||(LA26_1>=RBRACK && LA26_1<=AND)||(LA26_1>=LESS && LA26_1<=POW)||(LA26_1>=RPAREN && LA26_1<=DOT)||LA26_1==COMMA) ) {
                    alt26=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ret;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:244:4: n= NAME
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_var1094); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      ret = new Var((n!=null?n.getText():null));
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:245:4: n= NAME ( LBRACK t= test RBRACK )+
                    {
                    n=(Token)match(input,NAME,FOLLOW_NAME_in_var1103); if (state.failed) return ret;
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:245:11: ( LBRACK t= test RBRACK )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==LBRACK) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:245:12: LBRACK t= test RBRACK
                    	    {
                    	    match(input,LBRACK,FOLLOW_LBRACK_in_var1106); if (state.failed) return ret;
                    	    pushFollow(FOLLOW_test_in_var1110);
                    	    t=test();

                    	    state._fsp--;
                    	    if (state.failed) return ret;
                    	    match(input,RBRACK,FOLLOW_RBRACK_in_var1112); if (state.failed) return ret;
                    	    if ( state.backtracking==0 ) {
                    	      l.add(t);
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                    	    if (state.backtracking>0) {state.failed=true; return ret;}
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);

                    if ( state.backtracking==0 ) {
                      ret = new ArrayEl((n!=null?n.getText():null), l);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:248:1: args returns [List<Exp> ret] : a= test ( COMMA a= test )* ;
    public final List<Exp> args() throws RecognitionException {
        List<Exp> ret = null;

        Exp a = null;



        	List<Exp> l = new LinkedList<Exp>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:255:2: (a= test ( COMMA a= test )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:255:4: a= test ( COMMA a= test )*
            {
            pushFollow(FOLLOW_test_in_args1146);
            a=test();

            state._fsp--;
            if (state.failed) return ret;
            if ( state.backtracking==0 ) {
              l.add(a);
            }
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:255:28: ( COMMA a= test )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==COMMA) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:255:29: COMMA a= test
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_args1151); if (state.failed) return ret;
            	    pushFollow(FOLLOW_test_in_args1155);
            	    a=test();

            	    state._fsp--;
            	    if (state.failed) return ret;
            	    if ( state.backtracking==0 ) {
            	      l.add(a);
            	    }

            	    }
            	    break;

            	default :
            	    break loop27;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:258:1: flow_stmt returns [Term ret] : (s= break_stmt | s= continue_stmt | s= return_stmt );
    public final Term flow_stmt() throws RecognitionException {
        Term ret = null;

        Term s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:262:2: (s= break_stmt | s= continue_stmt | s= return_stmt )
            int alt28=3;
            switch ( input.LA(1) ) {
            case 53:
                {
                alt28=1;
                }
                break;
            case 54:
                {
                alt28=2;
                }
                break;
            case 55:
                {
                alt28=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 28, 0, input);

                throw nvae;
            }

            switch (alt28) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:262:5: s= break_stmt
                    {
                    pushFollow(FOLLOW_break_stmt_in_flow_stmt1183);
                    s=break_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:263:5: s= continue_stmt
                    {
                    pushFollow(FOLLOW_continue_stmt_in_flow_stmt1191);
                    s=continue_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:264:5: s= return_stmt
                    {
                    pushFollow(FOLLOW_return_stmt_in_flow_stmt1199);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:267:1: break_stmt returns [Term ret] : 'break' ;
    public final Term break_stmt() throws RecognitionException {
        Term ret = null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:268:2: ( 'break' )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:268:5: 'break'
            {
            match(input,53,FOLLOW_53_in_break_stmt1215); if (state.failed) return ret;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:271:1: continue_stmt returns [Term ret] : 'continue' ;
    public final Term continue_stmt() throws RecognitionException {
        Term ret = null;

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:272:2: ( 'continue' )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:272:5: 'continue'
            {
            match(input,54,FOLLOW_54_in_continue_stmt1233); if (state.failed) return ret;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:275:1: return_stmt returns [Term ret] : 'return' (t= test )? ;
    public final Term return_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:276:2: ( 'return' (t= test )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:276:5: 'return' (t= test )?
            {
            match(input,55,FOLLOW_55_in_return_stmt1251); if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:276:14: (t= test )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==NAME||LA29_0==NOT||LA29_0==MINUS||LA29_0==LPAREN||(LA29_0>=INT && LA29_0<=EPRIMEBLOCK)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:276:15: t= test
                    {
                    pushFollow(FOLLOW_test_in_return_stmt1256);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:279:1: compound_stmt returns [Term ret] : (t= if_stmt | t= while_stmt | t= for_stmt | t= funcdef );
    public final Term compound_stmt() throws RecognitionException {
        Term ret = null;

        Term t = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:283:2: (t= if_stmt | t= while_stmt | t= for_stmt | t= funcdef )
            int alt30=4;
            switch ( input.LA(1) ) {
            case 56:
                {
                alt30=1;
                }
                break;
            case 58:
                {
                alt30=2;
                }
                break;
            case 59:
                {
                alt30=3;
                }
                break;
            case 60:
                {
                alt30=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ret;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:283:4: t= if_stmt
                    {
                    pushFollow(FOLLOW_if_stmt_in_compound_stmt1286);
                    t=if_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:284:5: t= while_stmt
                    {
                    pushFollow(FOLLOW_while_stmt_in_compound_stmt1294);
                    t=while_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:285:5: t= for_stmt
                    {
                    pushFollow(FOLLOW_for_stmt_in_compound_stmt1302);
                    t=for_stmt();

                    state._fsp--;
                    if (state.failed) return ret;

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:286:5: t= funcdef
                    {
                    pushFollow(FOLLOW_funcdef_in_compound_stmt1310);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:289:1: if_stmt returns [Term ret] : 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )? ;
    public final Term if_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;

        List<Term> s = null;

        List<Term> e = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:2: ( 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )? )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:4: 'if' LPAREN t= test RPAREN s= statement ( 'else' e= statement )?
            {
            match(input,56,FOLLOW_56_in_if_stmt1326); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_if_stmt1328); if (state.failed) return ret;
            pushFollow(FOLLOW_test_in_if_stmt1332);
            t=test();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_if_stmt1334); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_if_stmt1338);
            s=statement();

            state._fsp--;
            if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:42: ( 'else' e= statement )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==57) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:290:43: 'else' e= statement
                    {
                    match(input,57,FOLLOW_57_in_if_stmt1341); if (state.failed) return ret;
                    pushFollow(FOLLOW_statement_in_if_stmt1345);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:294:1: while_stmt returns [Term ret] : 'while' LPAREN t= test RPAREN s= statement ;
    public final Term while_stmt() throws RecognitionException {
        Term ret = null;

        Exp t = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:295:2: ( 'while' LPAREN t= test RPAREN s= statement )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:295:4: 'while' LPAREN t= test RPAREN s= statement
            {
            match(input,58,FOLLOW_58_in_while_stmt1371); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_while_stmt1373); if (state.failed) return ret;
            pushFollow(FOLLOW_test_in_while_stmt1377);
            t=test();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_while_stmt1379); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_while_stmt1383);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:298:1: for_stmt returns [Term ret] : 'for' LPAREN v= NAME COLON c= atom RPAREN s= statement ;
    public final Term for_stmt() throws RecognitionException {
        Term ret = null;

        Token v=null;
        Exp c = null;

        List<Term> s = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:299:2: ( 'for' LPAREN v= NAME COLON c= atom RPAREN s= statement )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:299:4: 'for' LPAREN v= NAME COLON c= atom RPAREN s= statement
            {
            match(input,59,FOLLOW_59_in_for_stmt1401); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_for_stmt1403); if (state.failed) return ret;
            v=(Token)match(input,NAME,FOLLOW_NAME_in_for_stmt1407); if (state.failed) return ret;
            match(input,COLON,FOLLOW_COLON_in_for_stmt1409); if (state.failed) return ret;
            pushFollow(FOLLOW_atom_in_for_stmt1413);
            c=atom();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RPAREN,FOLLOW_RPAREN_in_for_stmt1415); if (state.failed) return ret;
            pushFollow(FOLLOW_statement_in_for_stmt1419);
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:302:1: funcdef returns [Term ret] : 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY ;
    public final Term funcdef() throws RecognitionException {
        Term ret = null;

        Token name=null;
        Token a=null;
        List<Term> s = null;



        	List<String> args = new LinkedList<String>();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:2: ( 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:4: 'function' name= NAME LPAREN (a= NAME ( COMMA a= NAME )* )? RPAREN LCURLY s= statements RCURLY
            {
            match(input,60,FOLLOW_60_in_funcdef1447); if (state.failed) return ret;
            name=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1451); if (state.failed) return ret;
            match(input,LPAREN,FOLLOW_LPAREN_in_funcdef1453); if (state.failed) return ret;
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:32: (a= NAME ( COMMA a= NAME )* )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==NAME) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:33: a= NAME ( COMMA a= NAME )*
                    {
                    a=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1458); if (state.failed) return ret;
                    if ( state.backtracking==0 ) {
                      args.add((a!=null?a.getText():null));
                    }
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:61: ( COMMA a= NAME )*
                    loop32:
                    do {
                        int alt32=2;
                        int LA32_0 = input.LA(1);

                        if ( (LA32_0==COMMA) ) {
                            alt32=1;
                        }


                        switch (alt32) {
                    	case 1 :
                    	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:311:62: COMMA a= NAME
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_funcdef1463); if (state.failed) return ret;
                    	    a=(Token)match(input,NAME,FOLLOW_NAME_in_funcdef1467); if (state.failed) return ret;
                    	    if ( state.backtracking==0 ) {
                    	      args.add((a!=null?a.getText():null));
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    break loop32;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_funcdef1475); if (state.failed) return ret;
            match(input,LCURLY,FOLLOW_LCURLY_in_funcdef1477); if (state.failed) return ret;
            pushFollow(FOLLOW_statements_in_funcdef1481);
            s=statements();

            state._fsp--;
            if (state.failed) return ret;
            match(input,RCURLY,FOLLOW_RCURLY_in_funcdef1483); if (state.failed) return ret;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:314:1: stab : STAB NEWLINE ;
    public final void stab() throws RecognitionException {
        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:314:6: ( STAB NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:314:8: STAB NEWLINE
            {
            match(input,STAB,FOLLOW_STAB_in_stab1493); if (state.failed) return ;
            match(input,NEWLINE,FOLLOW_NEWLINE_in_stab1495); if (state.failed) return ;

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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:316:1: essencedef returns [String v] : ESSENCE ver= version NEWLINE ;
    public final String essencedef() throws RecognitionException {
        String v = null;

        String ver = null;


        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:317:2: ( ESSENCE ver= version NEWLINE )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:317:4: ESSENCE ver= version NEWLINE
            {
            match(input,ESSENCE,FOLLOW_ESSENCE_in_essencedef1508); if (state.failed) return v;
            pushFollow(FOLLOW_version_in_essencedef1512);
            ver=version();

            state._fsp--;
            if (state.failed) return v;
            match(input,NEWLINE,FOLLOW_NEWLINE_in_essencedef1514); if (state.failed) return v;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:320:1: augassign returns [String op] : ( PLUSEQUAL | MINUSEQUAL | MULTEQUAL | DIVEQUAL | MODEQUAL | ASSIGN );
    public final stabParser.augassign_return augassign() throws RecognitionException {
        stabParser.augassign_return retval = new stabParser.augassign_return();
        retval.start = input.LT(1);

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:321:2: ( PLUSEQUAL | MINUSEQUAL | MULTEQUAL | DIVEQUAL | MODEQUAL | ASSIGN )
            int alt34=6;
            switch ( input.LA(1) ) {
            case PLUSEQUAL:
                {
                alt34=1;
                }
                break;
            case MINUSEQUAL:
                {
                alt34=2;
                }
                break;
            case MULTEQUAL:
                {
                alt34=3;
                }
                break;
            case DIVEQUAL:
                {
                alt34=4;
                }
                break;
            case MODEQUAL:
                {
                alt34=5;
                }
                break;
            case ASSIGN:
                {
                alt34=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;
            }

            switch (alt34) {
                case 1 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:321:4: PLUSEQUAL
                    {
                    match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign1531); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "+=";
                    }

                    }
                    break;
                case 2 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:322:5: MINUSEQUAL
                    {
                    match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign1540); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "-=";
                    }

                    }
                    break;
                case 3 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:323:5: MULTEQUAL
                    {
                    match(input,MULTEQUAL,FOLLOW_MULTEQUAL_in_augassign1549); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "*=";
                    }

                    }
                    break;
                case 4 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:324:5: DIVEQUAL
                    {
                    match(input,DIVEQUAL,FOLLOW_DIVEQUAL_in_augassign1558); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "/=";
                    }

                    }
                    break;
                case 5 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:325:5: MODEQUAL
                    {
                    match(input,MODEQUAL,FOLLOW_MODEQUAL_in_augassign1567); if (state.failed) return retval;
                    if ( state.backtracking==0 ) {
                      retval.op = "%=";
                    }

                    }
                    break;
                case 6 :
                    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:326:5: ASSIGN
                    {
                    match(input,ASSIGN,FOLLOW_ASSIGN_in_augassign1576); if (state.failed) return retval;
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
    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:329:1: version returns [String ret] : s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )* ;
    public final String version() throws RecognitionException {
        String ret = null;

        Token s=null;


        	StringBuffer sb = new StringBuffer();

        try {
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:336:2: (s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )* )
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:336:4: s= ( INT | NAME ) (s= DOT s= ( INT | NAME ) )*
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
            // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:336:39: (s= DOT s= ( INT | NAME ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==DOT) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:336:40: s= DOT s= ( INT | NAME )
            	    {
            	    s=(Token)match(input,DOT,FOLLOW_DOT_in_version1617); if (state.failed) return ret;
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
            	    break loop35;
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
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:74:4: ( stabblock )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:74:5: stabblock
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
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:158:4: ( NAME augassign test )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:158:5: NAME augassign test
        {
        match(input,NAME,FOLLOW_NAME_in_synpred2_stab481); if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred2_stab483);
        augassign();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred2_stab485);
        test();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred2_stab

    // $ANTLR start synpred3_stab
    public final void synpred3_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:4: ( NAME LBRACK test RBRACK augassign test )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:159:5: NAME LBRACK test RBRACK augassign test
        {
        match(input,NAME,FOLLOW_NAME_in_synpred3_stab508); if (state.failed) return ;
        match(input,LBRACK,FOLLOW_LBRACK_in_synpred3_stab510); if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred3_stab512);
        test();

        state._fsp--;
        if (state.failed) return ;
        match(input,RBRACK,FOLLOW_RBRACK_in_synpred3_stab514); if (state.failed) return ;
        pushFollow(FOLLOW_augassign_in_synpred3_stab516);
        augassign();

        state._fsp--;
        if (state.failed) return ;
        pushFollow(FOLLOW_test_in_synpred3_stab518);
        test();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred3_stab

    // $ANTLR start synpred4_stab
    public final void synpred4_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:4: ( var DOT NAME LPAREN ( args )? RPAREN )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:5: var DOT NAME LPAREN ( args )? RPAREN
        {
        pushFollow(FOLLOW_var_in_synpred4_stab927);
        var();

        state._fsp--;
        if (state.failed) return ;
        match(input,DOT,FOLLOW_DOT_in_synpred4_stab929); if (state.failed) return ;
        match(input,NAME,FOLLOW_NAME_in_synpred4_stab931); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred4_stab933); if (state.failed) return ;
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:25: ( args )?
        int alt36=2;
        int LA36_0 = input.LA(1);

        if ( (LA36_0==NAME||LA36_0==NOT||LA36_0==MINUS||LA36_0==LPAREN||(LA36_0>=INT && LA36_0<=EPRIMEBLOCK)) ) {
            alt36=1;
        }
        switch (alt36) {
            case 1 :
                // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:223:25: args
                {
                pushFollow(FOLLOW_args_in_synpred4_stab935);
                args();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred4_stab938); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred4_stab

    // $ANTLR start synpred5_stab
    public final void synpred5_stab_fragment() throws RecognitionException {   
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:4: ( NAME LPAREN ( args )? RPAREN )
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:5: NAME LPAREN ( args )? RPAREN
        {
        match(input,NAME,FOLLOW_NAME_in_synpred5_stab971); if (state.failed) return ;
        match(input,LPAREN,FOLLOW_LPAREN_in_synpred5_stab973); if (state.failed) return ;
        // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:17: ( args )?
        int alt37=2;
        int LA37_0 = input.LA(1);

        if ( (LA37_0==NAME||LA37_0==NOT||LA37_0==MINUS||LA37_0==LPAREN||(LA37_0>=INT && LA37_0<=EPRIMEBLOCK)) ) {
            alt37=1;
        }
        switch (alt37) {
            case 1 :
                // /Users/chris/Documents/Computer Science/SH Project/code/antlr/stab.g:225:17: args
                {
                pushFollow(FOLLOW_args_in_synpred5_stab975);
                args();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }

        match(input,RPAREN,FOLLOW_RPAREN_in_synpred5_stab978); if (state.failed) return ;

        }
    }
    // $ANTLR end synpred5_stab

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
    public final boolean synpred4_stab() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred4_stab_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred5_stab() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred5_stab_fragment(); // can never throw exception
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


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA11_eotS =
        "\12\uffff";
    static final String DFA11_eofS =
        "\12\uffff";
    static final String DFA11_minS =
        "\1\13\1\0\10\uffff";
    static final String DFA11_maxS =
        "\1\42\1\0\10\uffff";
    static final String DFA11_acceptS =
        "\2\uffff\1\3\5\uffff\1\1\1\2";
    static final String DFA11_specialS =
        "\1\uffff\1\0\10\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\4\uffff\1\2\7\uffff\1\2\4\uffff\1\2\2\uffff\3\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "157:1: expr_stmt returns [Term ret] : ( ( NAME augassign test )=>v= NAME o= augassign r= test | ( NAME LBRACK test RBRACK augassign test )=>v= NAME LBRACK t= test RBRACK o= augassign r= test | t= test );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA11_1 = input.LA(1);

                         
                        int index11_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred2_stab()) ) {s = 8;}

                        else if ( (synpred3_stab()) ) {s = 9;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index11_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 11, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA23_eotS =
        "\12\uffff";
    static final String DFA23_eofS =
        "\12\uffff";
    static final String DFA23_minS =
        "\1\13\1\uffff\1\0\7\uffff";
    static final String DFA23_maxS =
        "\1\42\1\uffff\1\0\7\uffff";
    static final String DFA23_acceptS =
        "\1\uffff\1\1\1\uffff\1\4\1\5\2\uffff\1\2\1\3\1\6";
    static final String DFA23_specialS =
        "\2\uffff\1\0\7\uffff}>";
    static final String[] DFA23_transitionS = {
            "\1\2\14\uffff\1\3\4\uffff\1\1\2\uffff\3\4",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "221:1: atom returns [Exp ret] : ( LPAREN (t= test )? RPAREN | ( var DOT NAME LPAREN ( args )? RPAREN )=>o= var DOT m= NAME LPAREN (a= args )? RPAREN | ( NAME LPAREN ( args )? RPAREN )=>n= NAME LPAREN (a= args )? RPAREN | MINUS i= INT | v= value | v= var );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA23_2 = input.LA(1);

                         
                        int index23_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred4_stab()) ) {s = 7;}

                        else if ( (synpred5_stab()) ) {s = 8;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index23_2);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 23, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_rules_in_program44 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stab_in_rules60 = new BitSet(new long[]{0x1DE0004721010910L});
    public static final BitSet FOLLOW_statements_in_rules64 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_essence_in_rules68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essence_in_rules80 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essencedef_in_essence102 = new BitSet(new long[]{0x1FFFFFFFFFFFFFD0L});
    public static final BitSet FOLLOW_estatements_in_essence106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_estatement_in_estatements139 = new BitSet(new long[]{0x1FFFFFFFFFFFFFD2L});
    public static final BitSet FOLLOW_stabblock_in_estatement173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_essencecode_in_estatement182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_something_in_essencecode209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_something242 = new BitSet(new long[]{0x1FFFFFFFFFFFFFD0L});
    public static final BitSet FOLLOW_NEWLINE_in_something254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SBEGIN_in_stabblock275 = new BitSet(new long[]{0x1DE0000721010990L});
    public static final BitSet FOLLOW_statements_in_stabblock279 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_SEND_in_stabblock281 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_statements310 = new BitSet(new long[]{0x1DE0000721010912L});
    public static final BitSet FOLLOW_LCURLY_in_statement329 = new BitSet(new long[]{0x1DE0000721010B10L});
    public static final BitSet FOLLOW_statements_in_statement333 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RCURLY_in_statement335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_stmt_in_statement347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compound_stmt_in_statement359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NEWLINE_in_statement369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt404 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt416 = new BitSet(new long[]{0x00E0000721010800L});
    public static final BitSet FOLLOW_small_stmt_in_simple_stmt420 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_SEMI_in_simple_stmt427 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_simple_stmt431 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_stmt_in_small_stmt455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_flow_stmt_in_small_stmt464 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_expr_stmt492 = new BitSet(new long[]{0x00001F8000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt496 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_expr_stmt500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_expr_stmt525 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LBRACK_in_expr_stmt527 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_expr_stmt531 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RBRACK_in_expr_stmt533 = new BitSet(new long[]{0x00001F8000000000L});
    public static final BitSet FOLLOW_augassign_in_expr_stmt537 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_expr_stmt541 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_test_in_expr_stmt550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_and_test_in_test585 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_OR_in_test590 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_and_test_in_test594 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_not_test_in_and_test625 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_AND_in_and_test630 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_not_test_in_and_test634 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_NOT_in_not_test654 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_not_test_in_not_test658 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_not_test668 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arith_expr_in_comparison697 = new BitSet(new long[]{0x00000000007E0002L});
    public static final BitSet FOLLOW_comp_op_in_comparison704 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_arith_expr_in_comparison708 = new BitSet(new long[]{0x00000000007E0002L});
    public static final BitSet FOLLOW_LESS_in_comp_op728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATER_in_comp_op735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EQUALS_in_comp_op742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LESSEQUAL_in_comp_op756 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOTEQUAL_in_comp_op763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_arith_expr792 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_set_in_arith_expr799 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_term_in_arith_expr807 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_factor_in_term837 = new BitSet(new long[]{0x000000000E000002L});
    public static final BitSet FOLLOW_set_in_term844 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_factor_in_term859 = new BitSet(new long[]{0x000000000E000002L});
    public static final BitSet FOLLOW_atom_in_factor881 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_POW_in_factor886 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_factor_in_factor890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom910 = new BitSet(new long[]{0x0000000761010800L});
    public static final BitSet FOLLOW_test_in_atom915 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom919 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_atom944 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DOT_in_atom946 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NAME_in_atom950 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_atom952 = new BitSet(new long[]{0x0000000761010800L});
    public static final BitSet FOLLOW_args_in_atom956 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom959 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_atom984 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_atom986 = new BitSet(new long[]{0x0000000761010800L});
    public static final BitSet FOLLOW_args_in_atom990 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_atom993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUS_in_atom1004 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_INT_in_atom1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_value_in_atom1017 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_atom1026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_value1046 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_value1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EPRIMEBLOCK_in_value1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_var1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_var1103 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LBRACK_in_var1106 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_var1110 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RBRACK_in_var1112 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_test_in_args1146 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_COMMA_in_args1151 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_args1155 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_break_stmt_in_flow_stmt1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continue_stmt_in_flow_stmt1191 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_return_stmt_in_flow_stmt1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_break_stmt1215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_continue_stmt1233 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_return_stmt1251 = new BitSet(new long[]{0x0000000721010802L});
    public static final BitSet FOLLOW_test_in_return_stmt1256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_if_stmt_in_compound_stmt1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_stmt_in_compound_stmt1294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_for_stmt_in_compound_stmt1302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_funcdef_in_compound_stmt1310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_if_stmt1326 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_if_stmt1328 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_if_stmt1332 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_if_stmt1334 = new BitSet(new long[]{0x1DE0000721010910L});
    public static final BitSet FOLLOW_statement_in_if_stmt1338 = new BitSet(new long[]{0x0200000000000002L});
    public static final BitSet FOLLOW_57_in_if_stmt1341 = new BitSet(new long[]{0x1DE0000721010910L});
    public static final BitSet FOLLOW_statement_in_if_stmt1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_58_in_while_stmt1371 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_while_stmt1373 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_while_stmt1377 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_while_stmt1379 = new BitSet(new long[]{0x1DE0000721010910L});
    public static final BitSet FOLLOW_statement_in_while_stmt1383 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_for_stmt1401 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_for_stmt1403 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NAME_in_for_stmt1407 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_COLON_in_for_stmt1409 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_atom_in_for_stmt1413 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_for_stmt1415 = new BitSet(new long[]{0x1DE0000721010910L});
    public static final BitSet FOLLOW_statement_in_for_stmt1419 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_funcdef1447 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NAME_in_funcdef1451 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_funcdef1453 = new BitSet(new long[]{0x0000000040000800L});
    public static final BitSet FOLLOW_NAME_in_funcdef1458 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_COMMA_in_funcdef1463 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NAME_in_funcdef1467 = new BitSet(new long[]{0x0000000840000000L});
    public static final BitSet FOLLOW_RPAREN_in_funcdef1475 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_LCURLY_in_funcdef1477 = new BitSet(new long[]{0x1DE0000721010B10L});
    public static final BitSet FOLLOW_statements_in_funcdef1481 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RCURLY_in_funcdef1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAB_in_stab1493 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_stab1495 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ESSENCE_in_essencedef1508 = new BitSet(new long[]{0x0000000100000800L});
    public static final BitSet FOLLOW_version_in_essencedef1512 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_NEWLINE_in_essencedef1514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PLUSEQUAL_in_augassign1531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MINUSEQUAL_in_augassign1540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MULTEQUAL_in_augassign1549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIVEQUAL_in_augassign1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MODEQUAL_in_augassign1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_augassign1576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_version1606 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_DOT_in_version1617 = new BitSet(new long[]{0x0000000100000800L});
    public static final BitSet FOLLOW_set_in_version1623 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_stabblock_in_synpred1_stab166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_synpred2_stab481 = new BitSet(new long[]{0x00001F8000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred2_stab483 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_synpred2_stab485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_synpred3_stab508 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LBRACK_in_synpred3_stab510 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_synpred3_stab512 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RBRACK_in_synpred3_stab514 = new BitSet(new long[]{0x00001F8000000000L});
    public static final BitSet FOLLOW_augassign_in_synpred3_stab516 = new BitSet(new long[]{0x0000000721010800L});
    public static final BitSet FOLLOW_test_in_synpred3_stab518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_var_in_synpred4_stab927 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_DOT_in_synpred4_stab929 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_NAME_in_synpred4_stab931 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred4_stab933 = new BitSet(new long[]{0x0000000761010800L});
    public static final BitSet FOLLOW_args_in_synpred4_stab935 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred4_stab938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_synpred5_stab971 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_LPAREN_in_synpred5_stab973 = new BitSet(new long[]{0x0000000761010800L});
    public static final BitSet FOLLOW_args_in_synpred5_stab975 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_RPAREN_in_synpred5_stab978 = new BitSet(new long[]{0x0000000000000002L});

}