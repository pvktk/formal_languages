
lexer grammar L_grammar;

//Числа
import L_numerals;

@header {
package lexer;
}

channels {
	COMMENTS
}

//Ключевые слова
KW_If : 'if';
KW_Then : 'then';
KW_Else : 'else';
KW_While : 'while';
KW_Do : 'do';
KW_Read : 'read';
KW_Write : 'write';

//Операторы
Op_Plus : '+';
Op_Minus : '-';
Op_Power : '**';
Op_Mult : '*';
Op_Div : '/';
Op_Mod : '%';
Op_Equate : ':=';
Op_Eq : '==';
Op_Neq : '!=';
Op_G : '>';
Op_GE : '>=';
Op_L : '<';
Op_LE : '<=';
Op_AND : '&&';
Op_OR : '||';

//Разделители
OpenBrace : '{';
CloseBrace : '}';
OpenParenth : '(';
CloseParenth : ')';
DotCom : ';';
Comma : ',';
Colon : OpenBrace | CloseBrace | OpenParenth | CloseParenth | DotCom | Comma;

MultilineComment : '/*' .*? '*/' ->channel(COMMENTS);
COMMENT : '//' (~('\n'|'\r'))* ->channel(COMMENTS);

BoolLit : 'true' | 'false';

Ident : [_a-zA-Z][_a-zA-Z0-9]* ;

//Пробельные символы
WS : [ \t\r\n\f]+ -> skip ;
