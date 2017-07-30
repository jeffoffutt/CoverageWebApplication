header{
package coverage.logic;
}

{
import java.io.*;
}

class ExprParser extends Parser;

options {
	codeGenMakeSwitchThreshold = 3;
	codeGenBitsetTestThreshold = 4;
	buildAST=true;
	ASTLabelType = "antlr.CommonAST"; // change default of "AST"
}

expr : equalExpr SEM! ;

equalExpr
	:	eorExpr
		(
			EQUAL^
			eorExpr 
		)*
	;

eorExpr
	:	implExpr 
		(
			EOR^
			implExpr
		)*
	;

implExpr
	:	orExpr
		(
			IMPL^
			orExpr
		)*
	;

orExpr
	:	andExpr
		(
			OR^
			andExpr
		)*
	;
	
andExpr
	:	postfixExpr
		(
			AND^
			postfixExpr
		)*
	;
	
postfixExpr
	:	notExpr
	|	atom
	;

notExpr
	:	NOT^ atom
	;

atom
	:	ID
	|	LPAREN! equalExpr RPAREN!
	;

class ExprLexer extends Lexer;

WS	:	(' '
	|	'\t'
	|	'\n'
	|	'\r')
		{ _ttype = Token.SKIP; }
	;

LPAREN:	'('
	;

RPAREN:	')'
	;

EQUAL :	'='
	;
	
EOR	:	'^'
	;

IMPL : '>'
    ;

OR	:	'|'
	;
	
AND	:	'&'
	;

NOT	:	'!'
	;

SEM	: ';'
	;

ID
options {
	testLiterals = true;
}
	:	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*
	;

