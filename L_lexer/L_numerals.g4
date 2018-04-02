
lexer grammar L_numerals;

Num : IntegerLiteral | FloatingPointLiteral;

fragment IntegerLiteral:
    DecimalIntegerLiteral
    |HexIntegerLiteral
    |OctalIntegerLiteral
    |BinaryIntegerLiteral;

fragment DecimalIntegerLiteral:
    DecimalNumeral IntegerTypeSuffix?;

fragment HexIntegerLiteral:
    HexNumeral IntegerTypeSuffix?;

fragment OctalIntegerLiteral:
    OctalNumeral IntegerTypeSuffix?;

fragment BinaryIntegerLiteral:
    BinaryNumeral IntegerTypeSuffix?;

fragment IntegerTypeSuffix:
    'l'|'L';
    
fragment DecimalNumeral:
    '0'
    |NonZeroDigit Digits?
    |NonZeroDigit Underscores Digits;

fragment Digits:
    Digit
    |Digit DigitsAndUnderscores? Digit;

fragment Digit : [0-9];

fragment NonZeroDigit:
    [1-9];

fragment DigitsAndUnderscores:
    DigitOrUnderscore+;

fragment DigitOrUnderscore:
    Digit
    |'_';

fragment Underscores: '_'+;

fragment HexNumeral:
    '0' 'x' HexDigits
    |'0' 'X' HexDigits;

fragment HexDigits:
    HexDigit
    |HexDigit HexDigitsAndUnderscores? HexDigit;

fragment HexDigit:
    [0-9]|[a-f]|[A-F];

fragment HexDigitsAndUnderscores:
    HexDigitOrUnderscore+;

fragment HexDigitOrUnderscore:
    HexDigit|'_';
    
fragment OctalNumeral:
    '0' OctalDigits
    |'0' Underscores OctalDigits;

fragment OctalDigits:
    OctalDigit
    |OctalDigit OctalDigitsAndUnderscores? OctalDigit;

fragment OctalDigit: [0-7];

fragment OctalDigitsAndUnderscores:
    OctalDigitOrUnderscore+;

fragment OctalDigitOrUnderscore:
    OctalDigit|'_';

fragment BinaryNumeral:
    '0' 'b' BinaryDigits 
    |'0' 'B' BinaryDigits;

fragment BinaryDigits:
    BinaryDigit 
    |BinaryDigit BinaryDigitsAndUnderscores? BinaryDigit;

fragment BinaryDigit:
    '0'| '1'; 

fragment BinaryDigitsAndUnderscores:
    BinaryDigitOrUnderscore+;

fragment BinaryDigitOrUnderscore:
    BinaryDigit|'_';

//Floats
fragment FloatingPointLiteral:
    DecimalFloatingPointLiteral
    |HexadecimalFloatingPointLiteral;

fragment DecimalFloatingPointLiteral:
    Digits '.' Digits? ExponentPart? FloatTypeSuffix?
    |'.' Digits ExponentPart? FloatTypeSuffix?
    |Digits ExponentPart FloatTypeSuffix?
    |Digits ExponentPart? FloatTypeSuffix;

fragment ExponentPart:
    ExponentIndicator SignedInteger;

fragment ExponentIndicator: [eE];

fragment SignedInteger:
    Sign? Digits;

fragment Sign: [+-];

fragment FloatTypeSuffix: [fFdD];

fragment HexadecimalFloatingPointLiteral:
    HexSignificand BinaryExponent FloatTypeSuffix?;

fragment HexSignificand:
    HexNumeral
    |HexNumeral '.'
    |'0' 'x' HexDigits? '.' HexDigits
    |'0' 'X' HexDigits? '.' HexDigits;

fragment BinaryExponent:
    BinaryExponentIndicator SignedInteger;

fragment BinaryExponentIndicator: [pP];
