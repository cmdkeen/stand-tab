grammar stab;
options {output=AST;backtrack=true;}

tokens {
	PROGRAM;
	PESSENCE;
	PSETTINGS;
	PVALIDATION;
  	EBLOCK;
  	SBLOCK;
  	EVERSION;
}
 
@header {
	import java.util.HashMap;
}

@members {
	int eblock = 0;
	HashMap<Integer, String> essence = new HashMap<Integer, String>();
	StringBuffer buffer;
}

program	:	rules {for(Integer i : essence.keySet()) System.out.println("String" + i + ":\n" + essence.get(i));} -> ^(PROGRAM rules)
	;

rules	:	stab! settings^ NEWLINE! essence^ validate^ EOF!
	|	essence
	;
	
//The actual code block - mix of essence prime and <stab> code </stab> blocks
//Is turned into the output eprime file for tailor
//essence	:	essencedef ((stabblock)=>stabblock |  essencecode)* ;
essence	:	essencedef estatement+
	;
	
estatement
	: stabblock
	| essencecode stabblock
	| essencecode ENDESSENCE!
	| essencecode EOF!
	;

essencecode
@init {
	eblock++;
}
	:	something -> ^(EBLOCK INT[String.valueOf(eblock)])
	;
	
something //Try and store .* into a string
options{greedy=false;}
@init {
	buffer = new StringBuffer();
}
@after {
	essence.put(eblock, buffer.toString());
}
	//:	(t=. {buffer.append($t.text); buffer.append(" ");})* ->;
	:	(t=. {buffer.append($t.text); 
			int i = $t.index + 1;
			if(i<input.size()) {
				Token tkn = input.get(i);
				//System.out.println("Token channel " + tkn.getChannel() + " text: " + tkn.getText());
				while(i<input.size() && tkn!= Token.EOF_TOKEN && tkn.getChannel() == Token.HIDDEN_CHANNEL) {
					buffer.append(tkn.getText());
					i++;
					tkn = input.get(i);
				}
			}
		})* ->;
	
stabblock
	:	SBEGIN statement+ SEND;

//The stab code to be interpreted
statement	
	:	function+;
	
function:	STRING LPAREN RPAREN;	
	
//The block to specify any custom settings - and validation of any settings
settings:	SETTINGS statement* -> ^(PSETTINGS statement*)
	;
	
//The block to validate user input during runtime - hooks into provided function names
validate:	
	VALIDATION function* -> ^(PVALIDATION function*)
	;
	


stab	:	STAB NEWLINE;

essencedef
	:	ESSENCE version NEWLINE -> ^(EVERSION version)
	;
	
version	:	(DIGIT|HEX) (DOT (DIGIT|HEX))*
	;
	
SBEGIN	:	'<stab>';
SEND	:	'</stab>';
	
STAB 	:	'language STAB';
ESSENCE :	'language ESSENCE\'';
ENDESSENCE
	:	'end ESSENCE\'';
	
SETTINGS:	'-settings';
	
VALIDATION
	:	'-validation';

DIGIT 	: 	( '0' .. '9' );
HEX	:	( 'a' .. 'f' | 'A' .. 'F' );
INT	:	DIGIT+;

DOT	:	'.';
LETTER	:	( 'a' .. 'z' | 'A' .. 'Z');
STRING	:	LETTER+;
NEWLINE	:   	(('\r')? '\n' )+;
WS	:	(' '|'\t')+ {$channel=HIDDEN;};

LPAREN	: 	'(';
RPAREN	: 	')';
LBRACK	: 	'[';
RBRACK	: 	']';
COMMA	: 	',' ;
SEMI	: 	';' ;
PLUS	: 	'+' ;
MINUS	: 	'-' ;
STAR	: 	'*' ;
DIV	:	'/' ;
AMPER	: 	'&' ;
LESS	: 	'<' ;
GREATER	: 	'>' ;
EQUALS	: 	'=' ;
MOD	: 	'%' ;
LCURLY	: 	'{';
RCURLY	: 	'}';
NOTEQUAL: 	'!=' ;
COLON	:	':';
ECOMMENT:	'$';
QUOTE	:	'\'';
LQUOTE	:	'`';
LESSEQUAL	: '<=' ;
GREATEREQUAL	: '>=' ;
PLUSEQUAL	: '+=' ;
MINUSEQUAL	: '-=' ;
MULTEQUAL	: '*=' ;
DIVEQUAL	: '/=' ;
MODEQUAL	: '%=' ;
