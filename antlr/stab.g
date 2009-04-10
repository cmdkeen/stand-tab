grammar stab;

@lexer::header {
	package uk.ac.stand.antlr.gen;
	
}
 
@header {
	package uk.ac.stand.antlr.gen;

	import java.util.Map;
	import java.util.HashMap;
	import java.util.List;
	import java.util.LinkedList;
	import uk.ac.stand.scalafiles.Stab;
	import uk.ac.stand.scalafiles.Stab.*;
	import scala.Some;
}

@members {
	
	List<FuncDef> funcdefs = new LinkedList<FuncDef>();
	
	Map<Integer, String> essence = new HashMap<Integer, String>();
	
	int eblock = 0;
}

program	returns [Program p]
@after {
	$p = new Program(funcdefs, $r.ret);
}
	:	r=rules
	;
	
rules	returns [List<Stats> ret]
	:	stab s=statements e=essence
		{
			List<Stats> l = new LinkedList<Stats>();
			l.add(new Stats($s.ret));
			l.addAll($e.ret);
			$ret = l;
		}
	|	e=essence {$ret = $e.ret;}
	;
	
//The actual code block - mix of essence prime and <stab> code </stab> blocks
//Is turned into the output eprime file for tailor
//essence	:	essencedef ((stabblock)=>stabblock |  essencecode)* ;
essence	returns [List<Stats> ret]
	:	d=essencedef e=estatements
		{
			List<Term> lt = new LinkedList<Term>();
			lt.add(new EString($d.v));
			List<Stats> ls = new LinkedList<Stats>();
			ls.add(new Stats(lt));
			ls.addAll($e.ret);
			$ret = ls;
		}
	;
	
estatements returns [List<Stats> ret]
@init {
	List<Stats> l = new LinkedList<Stats>();
}
@after {
	$ret = l;
}
	:	(s=estatement
			{l.add($s.ret);}
		)*
	;
	
estatement returns [Stats ret]
	: (stabblock) => s=stabblock {$ret = $s.ret;}
	| ec=essencecode {
				List<Term> l = new LinkedList<Term>();
				l.add(new EString($ec.ret));
				$ret = new Stats(l);
			}
	;

essencecode returns [String ret]
@init {
	eblock++;
}
@after {
	$ret = essence.get(eblock);
}
	:	something
	;

something
@init {
	StringBuffer buffer = new StringBuffer();
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
		} )* NEWLINE
	;
	
stabblock returns [Stats ret]
@after {
	$ret = new Stats($s.ret);
}
	:	SBEGIN s=statements SEND
	;
	
statements returns [List<Term> ret]
@init {
	List<Term> l = new LinkedList<Term>();
}
@after {
	$ret = l;
}
	:	(s=statement {if($s.ret!=null) l.addAll($s.ret);})*
	;

statement returns [List<Term> ret]
	:	LCURLY ls=statements RCURLY
			{$ret = $ls.ret;}
	|	ss=simple_stmt
			{$ret = $ss.ret;}
	|	s=compound_stmt
			{List<Term> lt = new LinkedList<Term>(); lt.add($s.ret); $ret = lt;}
	|	NEWLINE
	;
	
simple_stmt returns [List<Term> ret]
@init {
	List<Term> l = new LinkedList<Term>();
}
@after {
	$ret = l;
}
    	:   	s=small_stmt {l.add($s.ret);} (options {greedy=true;}:SEMI s=small_stmt {l.add($s.ret);})* (SEMI)? NEWLINE
	;
	
small_stmt returns [Term ret]
@after {
	$ret = r;
}
	: 	r=expr_stmt 
	| 	r=flow_stmt
	;
	
expr_stmt returns [Term ret]
	:	(NAME augassign test) => v=NAME o=augassign r=test {$ret = new Assign($v.text, $o.text, $r.ret);}
	|	(NAME LBRACK test RBRACK augassign test) => NAME LBRACK t=test RBRACK augassign test {$ret = new ElAssign($v.text, $t.ret, $o.text, $r.ret);}
	|	t=test {$ret = $t.ret;}
	;
	    	
test returns [Exp ret]
@init{Exp left = null;}
@after {
	$ret = left;
}
	: 	l=and_test {left = $l.ret;} (OR r=and_test {left = new EOp("or", left, $r.ret);})*
	;

and_test returns [Exp ret]
@init{Exp left = null;}
@after {
	$ret = left;
}
	: 	l=not_test {left = $l.ret;} (AND r=not_test {left = new EOp("and", left, $r.ret);})*
	;

not_test returns [Exp ret]
	: 	NOT t=not_test {$ret = new EOp1("not", $t.ret);}
	| 	c=comparison {$ret = $c.ret;}
	;

comparison returns [Exp ret]
@init{Exp left = null;}
@after {
	$ret = left;
}
	:	 l=arith_expr {left = $l.ret;} (o=comp_op r=arith_expr {left = new EOp($o.text, left, $r.ret);})*
	;

comp_op	returns [String s]
	: 	LESS {$s = "<";}
	|	GREATER {$s = ">";}
	|	EQUALS {$s = "==";}
	|	GREATEREQUAL {$s = ">=";}
	|	LESSEQUAL {$s = "<=";}
	|	NOTEQUAL {$s = "!=";}
	;

arith_expr returns [Exp ret]
@init{Exp left = null;}
@after {
	$ret = left;
}
	: 	l=term {left = $l.ret;} (o=(PLUS|MINUS) r=term {left = new EOp($o.text, left, $r.ret);})*
	;

term returns [Exp ret]
@init{Exp left = null;}
@after {
	$ret = left;
}
	:	l=factor {left = $l.ret;} (o=(MULT | DIV | MOD ) r=factor {left = new EOp($o.text, left, $r.ret);})*
	;

factor	returns [Exp ret]
	: 	l=atom {$ret = $l.ret;} (POW r=factor {$ret = new EOp("^", $l.ret, $r.ret);})?
	;

atom	returns [Exp ret]
	: 	LPAREN (t=test)? RPAREN {$ret = $t.ret;}
	|	n=NAME LPAREN a=args RPAREN 
			{$ret = new Call($n.text, $a.ret);}
	|	o=NAME DOT m=NAME LPAREN a=args RPAREN 
			{$ret = new Method($n.text, $m.text, $a.ret);}
	|	MINUS i=INT {$ret = new EOp1("-",new EInt(Integer.parseInt($i.text)));}
	|	v=value {$ret = $v.ret;}
	|	v=var {$ret = $v.ret;}
	;
	
value	returns [Value ret]
	:	i=INT {$ret = new EInt(Integer.parseInt($i.text));}
	|	BOOL {$ret = new EBool(Boolean.parseBoolean($i.text));}
	;
	
var	returns [Exp ret]
	:	n=NAME {$ret = new Var($n.text);}
	|	n=NAME LBRACK t=test RBRACK {$ret = new ArrayEl($n.text, $t.ret);}
	;
	
args	returns [List<Exp> ret]
@init {
	List<Exp> l = new LinkedList<Exp>();
}
@after {
	$ret = l;
}
	:	a=test {l.add($a.ret);} (COMMA a=test {l.add($a.ret);})*
	;
	
flow_stmt returns [Term ret]
@after {
	$ret = $s.ret;
}
	: 	s=break_stmt
	| 	s=continue_stmt
	| 	s=return_stmt
	;

break_stmt returns [Term ret]
	: 	'break' {$ret = new Break();}
	;

continue_stmt returns [Term ret]
	: 	'continue' {$ret = new Continue();}
	;

return_stmt returns [Term ret]
	: 	'return' (t=test {$ret = new Return(new Some<Exp>($t.ret));})? {if($ret==null) $ret = new Return();} 
	;
	
compound_stmt returns [Term ret]
@after {
	$ret = $t.ret;
}
	:	t=if_stmt
	| 	t=while_stmt
	| 	t=for_stmt
	| 	t=funcdef
	;
	
if_stmt	returns [Term ret]
	:	'if' LPAREN t=test RPAREN s=statement ('else' e=statement {$ret = new If($t.ret, new Stats($s.ret), new Some<Stats>(new Stats($e.ret)));})? 
			{if($ret==null) $ret = new If($t.ret, new Stats($s.ret));}
	;
	
while_stmt returns [Term ret]
	:	'while' LPAREN t=test RPAREN s=statement {$ret = new While($t.ret, new Stats($s.ret));}
	;
	
for_stmt returns [Term ret]
	:	'for' LPAREN v=NAME COLON c=var RPAREN s=statement {$ret = new For($v.text, $c.ret, new Stats($s.ret));}
	;
	
funcdef returns [Term ret]
@init {
	List<String> args = new LinkedList<String>();
}
@after {
	FuncDef func = new FuncDef($name.text, args, new Stats($s.ret));
	funcdefs.add(func);
	$ret = func;
}
	:	'function' name=NAME LPAREN (a=NAME {args.add($a.text);} (COMMA a=NAME {args.add($a.text);})*)? RPAREN LCURLY s=statements RCURLY
	;

stab	:	STAB NEWLINE;

essencedef returns [String v]
	:	ESSENCE ver=version NEWLINE {$v = "language ESSENCE\' " + $ver.ret;}
	;

augassign returns [String op]
	:	PLUSEQUAL {$op = "+=";} 
	| 	MINUSEQUAL {$op = "-=";} 
	| 	MULTEQUAL {$op = "*=";} 
	| 	DIVEQUAL {$op = "/=";} 
	| 	MODEQUAL {$op = "\%=";} 
	| 	ASSIGN {$op = "=";}
	;
	
version	returns [String ret]
@init {
	StringBuffer sb = new StringBuffer();
}
@after {
	$ret = sb.toString();
}
	:	s=(INT|NAME) {sb.append($s.text);} (s=DOT {sb.append($s.text);} s=(INT|NAME) {sb.append($s.text);})*;

	
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
