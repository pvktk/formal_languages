
import lexer.L_grammar;
import org.antlr.v4.runtime.*;

public class TestLexer {
	public static void main(String[] args) {
	{ //Разные типы токенов
		L_grammar lexer = new L_grammar(CharStreams.fromString("123 if true  ;\t\r\n + truest"));
		Vocabulary voc = lexer.getVocabulary();
		Token t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getCharPositionInLine() == 0;
		assert t.getLine() == 1;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("KW_If");
		assert t.getCharPositionInLine() == 4;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("BoolLit") : voc.getSymbolicName(t.getType());
		assert t.getCharPositionInLine() == 7;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("DotCom") : voc.getSymbolicName(t.getType());
		assert t.getCharPositionInLine() == 13;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_Plus") : voc.getSymbolicName(t.getType());
		assert t.getCharPositionInLine() == 1 : t.getCharPositionInLine();
		assert t.getLine() == 2;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Ident") : voc.getSymbolicName(t.getType());
		assert t.getText().equals("truest");
		assert t.getCharPositionInLine() == 3;
	}
	{ //префиксы и суффиксы друг друга
		L_grammar lexer = new L_grammar(CharStreams.fromString("128if ifs128 0x123cdefgh iffalse"));
		Vocabulary voc = lexer.getVocabulary();
		Token t = lexer.nextToken();
		assert t.getCharPositionInLine() == 0;
		assert t.getText().equals("128");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 3;
		assert t.getText().equals("if");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 6;
		assert t.getText().equals("ifs128");
		assert voc.getSymbolicName(t.getType()).equals("Ident");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 13 : t.getCharPositionInLine();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("0x123cdef");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 22;
		assert t.getText().equals("gh");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 25;
		assert t.getText().equals("iffalse");
	}
	{ //комментарии
		L_grammar lexer = new L_grammar(CharStreams.fromString("128 //true read 123\n0 /* abc\n /* def \n*/ /*//ab*/\n /**/"));
		Vocabulary voc = lexer.getVocabulary();
		Token t = lexer.nextToken();
		assert t.getCharPositionInLine() == 0;
		assert t.getText().equals("128");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("COMMENT");
		assert t.getText().equals("//true read 123");
		
		t = lexer.nextToken();
		assert t.getCharPositionInLine() == 0;
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("0");
		assert t.getLine() == 2;
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("MultilineComment");
		assert t.getText().equals("/* abc\n /* def \n*/") : t.getText();
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("MultilineComment");
		assert t.getText().equals("/*//ab*/");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("MultilineComment");
		assert t.getText().equals("/**/");
	}
	{ //числа
		L_grammar lexer = new L_grammar(CharStreams.fromString(
			"071 081 1xf3 0x010 0x110 00 2.718281828 -5 5. 3.14 .5 3.14e+25 1e10 1e-1"));
		Vocabulary voc = lexer.getVocabulary();
		Token t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("071");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num") : voc.getSymbolicName(t.getType());
		assert t.getText().equals("0");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("81");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("1");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Ident");
		assert t.getText().equals("xf3");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("0x010") : t.getText();
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("0x110");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("00") : t.getText();
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("2.718281828");
		
		t = lexer.nextToken();
		t=lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num") : voc.getSymbolicName(t.getType());
		assert t.getText().equals("5");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("5.");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("3.14");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals(".5");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("3.14e+25");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("1e10");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Num");
		assert t.getText().equals("1e-1");
	}
	{//парные операторы
		L_grammar lexer = new L_grammar(CharStreams.fromString("== = >=>"));
		Vocabulary voc = lexer.getVocabulary();
		Token t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_Eq");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_Equate");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_GE");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_G");
	}
	{// умножение и степень
		L_grammar lexer = new L_grammar(CharStreams.fromString("a * b ** c"));
		Vocabulary voc = lexer.getVocabulary();
		
		Token t = lexer.nextToken();
		assert t.getText().equals("a");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_Mult");
		assert t.getText().equals("*");
		
		t = lexer.nextToken();
		assert t.getText().equals("b");
		
		t = lexer.nextToken();
		assert voc.getSymbolicName(t.getType()).equals("Op_Power");
		assert t.getText().equals("**");
	}
		System.out.println("Test complete");
	}
}
