grammar stab;
options {output=AST;}

tokens {
	PROGRAM;
	PESSENCE;
	PSETTINGS;
	PVALNAMEATION;
  	EBLOCK;
  	SBLOCK;
  	EVERSION;
  	
  	EINT;
  	EBOOL;
  	ESTRING;
  	EARRAY;
  	
  	FUNCTION;
  	FARGS;
  	
  	IF;
  	WHILE;
  	FOR;
  	
  	EOP1;
  	EOP2;
  	
  	AUGASSIGN;
  	TEST;
  	
  	VAR;
  	ELEM;
  	CALL;
  	
  	BREAK;
  	CONTINUE;
  	RETURN;
}
 
@header {
	import java.util.Map;
	import java.util.HashMap;
}

@members {

	class Data {
		Map<Integer, String> essence;
		Map<String, CommonTree> functions;	
		
		public Data(Map<Integer, String> e, Map<String, CommonTree> f) {
			essence = e;
			functions = f;
		}
	}

	int eblock = 0;
	HashMap<Integer, String> essence = new HashMap<Integer, String>();
	HashMap<String, CommonTree> functions = new HashMap<String, CommonTree>();
	
	StringBuffer buffer;
}

program	returns [Data data]
@after {
	$data = new Data(essence, functions);
}
	:	rules {for(Integer i : essence.keySet()) System.out.println(essence.get(i));} -> ^(PROGRAM rules)
	;

rules	:	stab statement* essence -> ^(SBLOCK statement*) essence
	|	essence
	;
	
//The actual code block - mix of essence prime and <stab> code </stab> blocks
//Is turned into the output eprime file for tailor
//essence	:	essencedef ((stabblock)=>stabblock |  essencecode)* ;
essence	:	essencedef estatement* -> ^(PESSENCE essencedef estatement+)
	;
	
estatement
	: (stabblock) => stabblock
	| essencecode
	;

essencecode
@init {
	eblock++;
}
	:	something -> ^(EBLOCK INT[String.valueOf(eblock)])
	;

something
@init {
	buffer = new StringBuffer();
}
@after {
	essence.put(eblock, buffer.toString());
}
	:	(options{greedy=false;} : t=~(NEWLINE) {buffer.append($t.text); 
			int i = $t.index + 1;
			if(i<input.size()) {
				Token tkn = input.get(i);
				while(i<input.size() && tkn!= Token.EOF_TOKEN && tkn.getChannel() == Token.HIDDEN_CHANNEL) {
					buffer.append(tkn.getText());
					i++;
					tkn = input.get(i);
				}
			}
		} )* NEWLINE!
	;
	
stabblock
	:	SBEGIN statement* SEND -> ^(SBLOCK statement*)
	;

//The stab code to be interpreted
statement	
	:	LCURLY statement* RCURLY -> ^(SBLOCK statement*)
	|	simple_stmt
	|	compound_stmt
	|	NEWLINE!
	;
	
simple_stmt
    	:   	s+=small_stmt (options {greedy=true;}:SEMI s+=small_stmt)* (SEMI)? NEWLINE -> $s*
	;
	
small_stmt
	: 	expr_stmt
	| 	flow_stmt
	;
	
expr_stmt
	:	test
		(	augassign^ test
		|	ASSIGN^ test
		)?
	;
	    	
test	: 	and_test (OR^ and_test)*
	;

and_test
	: 	not_test (AND^ not_test)*
	;

not_test
	: 	NOT^ not_test
	| 	comparison
	;

comparison
	:	 arith_expr (comp_op^ arith_expr)*
	;

comp_op	: 	LESS
	|	GREATER
	|	EQUALS
	|	GREATEREQUAL
	|	LESSEQUAL
	|	NOTEQUAL
	;

arith_expr	
	: 	term ((PLUS|MINUS)^ term)*
	;

term	:	factor ((MULT | DIV | MOD )^ factor)*
	;

factor	: 	atom (POW^ factor)?
	;

atom	: 	LPAREN (test)? RPAREN -> test
	|	NAME -> ^(VAR NAME)
	|	NAME LBRACK test RBRACK -> ^(ELEM NAME ^(TEST test))//array
	|	NAME LPAREN args RPAREN -> ^(CALL NAME ^(FARGS args)) //func call
	|	o=NAME DOT m=NAME LPAREN args RPAREN -> ^(CALL $o $m ^(FARGS args)) //method call
	| 	INT -> ^(EINT INT)
	| 	STRING -> ^(ESTRING STRING)
	|	BOOL -> ^(EBOOL BOOL)
	;
	
args	:	a+=test (COMMA a+=test)* -> $a*;
	
flow_stmt
	: 	break_stmt
	| 	continue_stmt
	| 	return_stmt
	;

break_stmt
	: 	'break' -> ^(BREAK)
	;

continue_stmt
	: 	'continue' -> ^(CONTINUE)
	;

return_stmt
	: 	'return' (test)? -> ^(RETURN test)
	;
	
compound_stmt
	:	if_stmt
	| 	while_stmt
	| 	for_stmt
	| 	funcdef
	;
	
if_stmt	:	'if'^ LPAREN! test RPAREN! statement ('else' statement)?
	;
	
while_stmt
	:	'while'^ LPAREN! test RPAREN! statement
	;
	
for_stmt:	'for'^ LPAREN! test RPAREN! statement
	;
	
funcdef	
@after {
	functions.put($name.text, (CommonTree)$funcdef.tree);
}
	:	'function' name=NAME LPAREN args RPAREN -> ^(FUNCTION $name ^(FARGS args)) 
	;
	
val	:	INT -> ^(EINT INT)
	|	STRING -> ^(ESTRING STRING)
	|	BOOL -> ^(EBOOL BOOL)
	;

stab	:	STAB! NEWLINE!;

essencedef
	:	ESSENCE version NEWLINE -> ^(EVERSION version)
	;

augassign
	:	PLUSEQUAL | MINUSEQUAL | MULTEQUAL | DIVEQUAL | MODEQUAL
	;
	
version	:	(INT|NAME) (DOT (INT|NAME))*;

	
SBEGIN	:	'<stab>';
SEND	:	'</stab>';
	
STAB 	:	'language STAB';
ESSENCE :	'language ESSENCE\'';
ENDESSENCE
	:	'end ESSENCE\'';

DEF	:	'define';
	
BOOL	:	'true' | 'false';

NOT	:	'!' | 'not';
AND	: 	'&&' | 'and';
OR	:	'||' | 'or';

INT	:	'0' 
    	|   	'1'..'9' ('0'..'9')*  
    	;

DOT	:	'.';

NEWLINE	:   	(('\r')? '\n' )+;
WS	:	(' '|'\t')+ {$channel=HIDDEN;};

USCORE	:	'_';
LCURLY	: 	'{';
RCURLY	: 	'}';
LPAREN	: 	'(';
RPAREN	: 	')';
LBRACK	: 	'[';
RBRACK	: 	']';
COMMA	: 	',' ;
SEMI	: 	';' ;
POW	:	'^';

COLON	:	':';
ECOMMENT:	'$';
QUOTE	:	'\'';
LQUOTE	:	'`';

ASSIGN	:	'=';

PLUS	: 	'+' ;
MINUS	: 	'-' ;
MULT	: 	'*' ;
DIV	:	'/' ;

LESS	: 	'<' ;
GREATER	: 	'>' ;
EQUALS	: 	'==' ;
MOD	: 	'%' ;
NOTEQUAL: 	'!=' ;
LESSEQUAL	: '<=' ;
GREATEREQUAL	: '>=' ;
PLUSEQUAL	: '+=' ;
MINUSEQUAL	: '-=' ;
MULTEQUAL	: '*=' ;
DIVEQUAL	: '/=' ;
MODEQUAL	: '%=' ;


NAME:	( 'a' .. 'z' | 'A' .. 'Z' | '_')
        ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
    ;
/** Match various string types.  Note that greedy=false implies '''
 *  should make us exit loop not continue.
 */
STRING
    	:   ('r'|'u'|'ur')?
        (   '\'\'\'' (options {greedy=false;}:.)* '\'\'\''
        |   '"""' (options {greedy=false;}:.)* '"""'
        |   '"' (~('\\'|'\n'|'"'))* '"'
        |   '\'' (~('\\'|'\n'|'\''))* '\''
        )
	;
