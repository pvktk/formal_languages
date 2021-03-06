
import lexer.L_grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import lexer.L_grammar;

public class TestParser {
	public static void main(String[] args) {
	{
		String input = 
				"\n" + 
				"fn(a, b, c){\n" + 
				"	f(1,2//\n" + 
				"	); //comment\n" + 
				"}\n" + 
				"\n" + 
				"fUnary(p1)\n" + 
				"	read b;\n" + 
				"\n" + 
				"a :=1+2+4;\n" + 
				"\n" + 
				"write a;\n" + 
				"\n" + 
				"write (1 + x);\n" + 
				"\n" + 
				"while 1 do {write x;}\n" + 
				"/*\n" + 
				"//if (1 == 2) then (write 1) else (read p);\n" + 
				"*/\n" + 
				" if (x == 5) then {{f(1,2);}} else {y := x-34;}\n";

		L_grammar lexer = new L_grammar(CharStreams.fromString(input));
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ASTBuildListener listener = new ASTBuildListener(new PrintStream(out));
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		
		String etalon = "Program\n" + 
				"| Func Def (\"fn\", (2, 0), (5, 0) )\n" + 
				"| | Ident (\"a\", (2, 3), (2, 3) )\n" + 
				"| | Ident (\"b\", (2, 6), (2, 6) )\n" + 
				"| | Ident (\"c\", (2, 9), (2, 9) )\n" + 
				"| | Block (\"\", (2, 11), (5, 0) )\n" + 
				"| | | Func Call (\"f\", (3, 1), (4, 1) )\n" + 
				"| | | | Expression (\"1\", (3, 3), (3, 3) )\n" + 
				"| | | | Expression (\"2\", (3, 5), (3, 5) )\n" + 
				"| Func Def (\"fUnary\", (7, 0), (8, 7) )\n" + 
				"| | Ident (\"p1\", (7, 7), (7, 7) )\n" + 
				"| | Operator (\"read\", (8, 1), (8, 7) )\n" + 
				"| | | Ident (\"b\", (8, 6), (8, 6) )\n" + 
				"| Operator (\":=\", (10, 0), (10, 9) )\n" + 
				"| | Ident (\"a\", (10, 0), (10, 0) )\n" + 
				"| | Binary Op (\"+\", (10, 4), (10, 8) )\n" + 
				"| | | Expression (\"1\", (10, 4), (10, 4) )\n" + 
				"| | | Binary Op (\"+\", (10, 6), (10, 8) )\n" + 
				"| | | | Expression (\"2\", (10, 6), (10, 6) )\n" + 
				"| | | | Expression (\"4\", (10, 8), (10, 8) )\n" + 
				"| Operator (\"write\", (12, 0), (12, 7) )\n" + 
				"| | Expression (\"a\", (12, 6), (12, 6) )\n" + 
				"| Operator (\"write\", (14, 0), (14, 13) )\n" + 
				"| | Binary Op (\"+\", (14, 7), (14, 11) )\n" + 
				"| | | Expression (\"1\", (14, 7), (14, 7) )\n" + 
				"| | | Expression (\"x\", (14, 11), (14, 11) )\n" + 
				"| Operator (\"While Do\", (16, 0), (16, 20) )\n" + 
				"| | Expression (\"1\", (16, 6), (16, 6) )\n" + 
				"| | Block (\"\", (16, 11), (16, 20) )\n" + 
				"| | | Operator (\"write\", (16, 12), (16, 19) )\n" + 
				"| | | | Expression (\"x\", (16, 18), (16, 18) )\n" + 
				"| Operator (\"If Then Else\", (20, 1), (20, 46) )\n" + 
				"| | Binary Op (\"==\", (20, 5), (20, 10) )\n" + 
				"| | | Expression (\"x\", (20, 5), (20, 5) )\n" + 
				"| | | Expression (\"5\", (20, 10), (20, 10) )\n" + 
				"| | Block (\"\", (20, 18), (20, 28) )\n" + 
				"| | | Block (\"\", (20, 19), (20, 27) )\n" + 
				"| | | | Func Call (\"f\", (20, 20), (20, 25) )\n" + 
				"| | | | | Expression (\"1\", (20, 22), (20, 22) )\n" + 
				"| | | | | Expression (\"2\", (20, 24), (20, 24) )\n" + 
				"| | Block (\"\", (20, 35), (20, 46) )\n" + 
				"| | | Operator (\":=\", (20, 36), (20, 45) )\n" + 
				"| | | | Ident (\"y\", (20, 36), (20, 36) )\n" + 
				"| | | | Binary Op (\"-\", (20, 41), (20, 43) )\n" + 
				"| | | | | Expression (\"x\", (20, 41), (20, 41) )\n" + 
				"| | | | | Expression (\"34\", (20, 43), (20, 43) )\n";

		String res = new String(out.toByteArray());
		assert etalon.equals(res) : etalon + "\n" + res;
		//assert res.length() == etalon.length() : res.length() + " " + etalon.length();
		//System.out.print(res);
		//for (int i = 0; i < res.length(); i++) {
		//	assert res.charAt(i) == etalon.charAt(i) : i + " " + res.substring(i, i + 10) + " " + etalon.substring(i, i + 10);
		//}
		
	}
	
	{ //increment, decrement, plusEq, minusEq
		String input = 
				"++X;\n" + 
				"\n" + 
				"--X;\n" + 
				"\n" + 
				"s -= 1+4;\n" + 
				"\n" + 
				"k += p;\n" + 
				"\n" + 
				"if (x == 0) then\n" + 
				"	{read skip;}\n" + 
				"else {\n" + 
				"	x += 1;\n" + 
				"	x -= 1;\n" + 
				"	write (x);\n" + 
				"}\n";

		L_grammar lexer = new L_grammar(CharStreams.fromString(input));
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ASTBuildListener listener = new ASTBuildListener(new PrintStream(out));
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		
		String etalon = "Program\n" + 
				"| Operator (\":=\")\n" + 
				"| | Ident (\"X\", (1, 2), (1, 2) )\n" + 
				"| | Binary Op (\"+\")\n" + 
				"| | | Ident (\"X\", (1, 2), (1, 2) )\n" + 
				"| | | Expression (\"1\")\n" + 
				"| Operator (\":=\")\n" + 
				"| | Ident (\"X\", (3, 2), (3, 2) )\n" + 
				"| | Binary Op (\"-\")\n" + 
				"| | | Ident (\"X\", (3, 2), (3, 2) )\n" + 
				"| | | Expression (\"1\")\n" + 
				"| Operator (\":=\")\n" + 
				"| | Ident (\"s\", (5, 0), (5, 0) )\n" + 
				"| | Binary Op (\"-\")\n" + 
				"| | | Ident (\"s\", (5, 0), (5, 0) )\n" + 
				"| | | Binary Op (\"+\", (5, 5), (5, 7) )\n" + 
				"| | | | Expression (\"1\", (5, 5), (5, 5) )\n" + 
				"| | | | Expression (\"4\", (5, 7), (5, 7) )\n" + 
				"| Operator (\":=\")\n" + 
				"| | Ident (\"k\", (7, 0), (7, 0) )\n" + 
				"| | Binary Op (\"+\")\n" + 
				"| | | Ident (\"k\", (7, 0), (7, 0) )\n" + 
				"| | | Expression (\"p\", (7, 5), (7, 5) )\n" + 
				"| Operator (\"If Then Else\", (9, 0), (15, 0) )\n" + 
				"| | Binary Op (\"==\", (9, 4), (9, 9) )\n" + 
				"| | | Expression (\"x\", (9, 4), (9, 4) )\n" + 
				"| | | Expression (\"0\", (9, 9), (9, 9) )\n" + 
				"| | Block (\"\", (10, 1), (10, 12) )\n" + 
				"| | | Operator (\"read\", (10, 2), (10, 11) )\n" + 
				"| | | | Ident (\"skip\", (10, 7), (10, 7) )\n" + 
				"| | Block (\"\", (11, 5), (15, 0) )\n" + 
				"| | | Operator (\":=\")\n" + 
				"| | | | Ident (\"x\", (12, 1), (12, 1) )\n" + 
				"| | | | Binary Op (\"+\")\n" + 
				"| | | | | Ident (\"x\", (12, 1), (12, 1) )\n" + 
				"| | | | | Expression (\"1\", (12, 6), (12, 6) )\n" + 
				"| | | Operator (\":=\")\n" + 
				"| | | | Ident (\"x\", (13, 1), (13, 1) )\n" + 
				"| | | | Binary Op (\"-\")\n" + 
				"| | | | | Ident (\"x\", (13, 1), (13, 1) )\n" + 
				"| | | | | Expression (\"1\", (13, 6), (13, 6) )\n" + 
				"| | | Operator (\"write\", (14, 1), (14, 10) )\n" + 
				"| | | | Expression (\"x\", (14, 8), (14, 8) )\n";

		String res = new String(out.toByteArray());
		assert etalon.equals(res) : etalon + "\n" + res;
		
	}
	
		System.out.println("Test complete");
	}
}
