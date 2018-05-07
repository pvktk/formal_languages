parser grammar L_parser;

options {
	tokenVocab=L_grammar;
	}

program : funcDef* operator* EOF;
//program : funcDef EOF;

binop :
	Op_Plus
	|Op_Minus
	|Op_Power
	|Op_Mult
	|Op_Div
	|Op_Mod
	|Op_Equate
	|Op_Eq
	|Op_Neq
	|Op_G
	|Op_GE
	|Op_L
	|Op_LE
	|Op_AND
	|Op_OR;


funcDef : Ident OpenParenth (|Ident(Comma Ident)*) CloseParenth OpenBrace operator* CloseBrace;

funcCall : Ident OpenParenth (|expression(Comma expression)*) CloseParenth;

exprSimple : funcCall | Ident | Num | (OpenParenth expression CloseParenth);

expression : exprSimple | (exprSimple binop expression);

operator : 
	Ident Op_Equate expression DotCom//# Equate
	| OpenBrace operator* CloseBrace// # ParenthOp
	| funcCall DotCom//# functionCall
	| KW_Write expression DotCom//#write
	| KW_Read Ident DotCom//# read
	| KW_While expression KW_Do operator//# WhileConstruct
	| KW_If expression KW_Then operator KW_Else operator; //# IfThenElse; 

