parser grammar L_parser;

options {
	tokenVocab=L_grammar;
	//output=template;
	//rewrite=true;
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


funcDef : Ident OpenParenth (|namedIdent(Comma namedIdent)*) CloseParenth operator;

funcCall : Ident OpenParenth (|expression(Comma expression)*) CloseParenth;

exprSimple : funcCall # exprS
		| Ident # exprS
		| Num # exprS
		| OpenParenth expression CloseParenth # ParenthExpr;

expression : exprSimple | (exprSimple binop expression);

namedIdent : Ident;

operator : 
	namedIdent Op_Equate expression DotCom # Equate
	| OpenBrace operator* CloseBrace # BraceOp
	| funcCall DotCom # functionCall
	| KW_Write expression DotCom #write
	| KW_Read Ident DotCom # read
	| KW_While expression KW_Do operator # WhileConstruct
	| KW_If expression KW_Then operator KW_Else operator # IfThenElse; 

