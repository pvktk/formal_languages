
lexer grammar L_grammar;

@header {
package lexer;
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
Op_Mult : '*';
Op_Div : '/';
Op_Mod : '%';
Op_Equate : '=';
Op_Eq : '==';
Op_Neq : '!=';
Op_G : '>';
Op_GE : '>=';
Op_L : '<';
Op_LE : '<=';
Op_AND : '&&';
Op_OR : '||';

//Разделители
Colon : '('|')'|';';

COMMENTS : '//' (~('\n'|'\r'))* -> skip;

BoolLit : 'true' | 'false';

Ident : [_a-zA-Z][_a-zA-Z0-9]* ;

//Числа
Num : Int|Float;
Int : DecInt|HexInt|OctInt;
DecInt : '0'|[1-9][0-9]*;
HexInt : '0x'('0'|[1-9a-fA-F][0-9a-fA-F]*);
OctInt : '0'[1-7][0-7];

Float : DecFloat;
DecFloat : [0-9]*'.'[0-9]*;

//Пробельные символы
WS : [ \t\r\n\f]+ -> skip ; // toss out whitespace
