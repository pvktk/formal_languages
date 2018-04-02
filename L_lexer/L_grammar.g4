
lexer grammar L_grammar;
import L_numerals;

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

COMMENT : '//' (~('\n'|'\r'))*;

BoolLit : 'true' | 'false';

Ident : [_a-zA-Z][_a-zA-Z0-9]* ;

//Числа

//Пробельные символы
WS : [ \t\r\n\f]+ -> skip ; // toss out whitespace
