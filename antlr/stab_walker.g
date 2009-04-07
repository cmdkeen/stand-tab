tree grammar stab_walker;

options {
    tokenVocab=stab;
    ASTLabelType=CommonTree;
}

@header {
    import java.util.HashMap;
}

@members {
	class InterpretedData {
	
	
	}
	
	private final Map<String, CommonTree> functions;

	/** Remember local variables.
	 */
	private final Map<String, Object> localMemory = new HashMap<String, Object>();

	/** Remember global variables set by =. */
	private Map<String, Object> globalMemory = new HashMap<String, Object>();
}

program	[Data data] returns [InterpretedData interp]
@init {
	functions = data.f;
}
@after {
	$interp = new InterpretedData();
}
	:	 ^(PROGRAM rules)
	;
	
rules	:	^(SBLOCK statement*) essence
	|	essence
	;
	
essence	:	^(PESSENCE essencedef estatement+)
	;
	
estatement
	: stabblock
	| essencecode
	;

essencecode
	:	^(EBLOCK INT)
	;
	
stabblock
	:	^(SBLOCK statement*)
	;

statement	
	:	^(SBLOCK statement*)
	|	simple_stmt
	|	compound_stmt
	;
	
simple_stmt
    	:   	small_stmt*
	;
	
small_stmt
	: 	expr_stmt
	| 	flow_stmt
	;
	
expr_stmt
	:	test
	|	test PLUSEQUAL^ test
	|	test MINUSEQUAL^ test
	|	test MULTEQUAL^ test
	|	test DIVEQUAL^ test
	|	test MODEQUAL^ test
	|	test ASSIGN^ test
	;
	    	
test	: 	and_test
	|	^(OR and_test and_test)
	;

and_test
	:	not_test
	|	^(AND not_test not_test)
	;

not_test
	: 	NOT^ not_test
	| 	comparison
	;

comparison
	:	arith_expr //arith_expr (comp_op^ arith_expr)*
	|	^(LESS arith_expr arith_expr)
	|	^(GREATER arith_expr arith_expr)
	|	^(EQUALS arith_expr arith_expr)
	|	^(GREATEREQUAL arith_expr arith_expr)
	|	^(LESSEQUAL arith_expr arith_expr)
	|	^(NOTEQUAL arith_expr arith_expr)
	;

arith_expr	
	: 	term
	|	^(PLUS term term)
	|	^(MINUS term term)
	;

term	:	factor
	|	^(MULT factor factor)
	|	^(DIV factor factor)
	|	^(MOD factor factor)
	;

factor	: 	atom
	|	^(POW atom factor)
	;

atom	: 	test
	|	^(VAR NAME)
	|	^(ELEM NAME ^(TEST test))//array
	|	^(CALL NAME ^(FARGS args)) //func call
	|	^(CALL NAME NAME ^(FARGS args)) //method call
	| 	^(EINT INT)
	| 	^(ESTRING STRING)
	|	^(EBOOL BOOL)
	;
	
args	:	test*;
	
flow_stmt
	: 	break_stmt
	| 	continue_stmt
	| 	return_stmt
	;

break_stmt
	: 	^(BREAK)
	;

continue_stmt
	: 	^(CONTINUE)
	;

return_stmt
	: 	^(RETURN test)
	;
	
compound_stmt
	:	if_stmt
	| 	while_stmt
	| 	for_stmt
	| 	funcdef
	;
	
if_stmt	:	'if'^ test statement 'else' statement
	|	'if'^ test statement
	;
	
while_stmt
	:	'while' test statement
	;
	
for_stmt:	'for' test statement
	;
	
funcdef	
	:	^(FUNCTION NAME ^(FARGS args)) 
	;
	
val	:	^(EINT INT)
	|	^(ESTRING STRING)
	|	^(EBOOL BOOL)
	;

essencedef
	:	^(EVERSION version)
	;

version	:	(INT|NAME) (DOT (INT|NAME))*;	
	;
	
