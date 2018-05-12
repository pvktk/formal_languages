
import lexer.L_grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import lexer.L_grammar;

public class TestParser {
	public static void main(String[] args) {
	{
		String input = "\n" + 
				"fn(a,b,c){\n" + 
				"	f(1,2//\n" + 
				"	); //comment\n" + 
				"}\n" + 
				"\n" + 
				"a :=1+2+4;\n" + 
				"\n" + 
				"write a;\n" + 
				"\n" + 
				"while 1 do {write x;}\n" + 
				"/*\n" + 
				"//if (1 == 2) then (write 1) else (read p);\n" + 
				"*/\n" + 
				" if (x == 5) then {{}} else {y := x-34;}\n";

		L_grammar lexer = new L_grammar(CharStreams.fromString(input));
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ASTBuildListener listener = new ASTBuildListener(new PrintStream(out));
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		
		String etalon = "Program (\"All code\", (2, 0), (16, 0) )\n" + 
				"| Func Def (\"fn\", (2, 0), (5, 0) )\n" + 
				"| | Ident (\"a\", (2, 3), (2, 3) )\n" + 
				"| | Ident (\"b\", (2, 5), (2, 5) )\n" + 
				"| | Ident (\"c\", (2, 7), (2, 7) )\n" + 
				"| | Operator (\"{}\", (2, 9), (5, 0) )\n" + 
				"| | | Operator (\"Function call\", (3, 1), (4, 2) )\n" + 
				"| | | | Func Call (\"f\", (3, 1), (4, 1) )\n" + 
				"| | | | | Expression (\"1\", (3, 3), (3, 3) )\n" + 
				"| | | | | Expression (\"2\", (3, 5), (3, 5) )\n" + 
				"| Operator (\":=\", (7, 0), (7, 9) )\n" + 
				"| | Ident (\"a\", (7, 0), (7, 0) )\n" + 
				"| | | Expression (\"1\", (7, 4), (7, 4) )\n" + 
				"| | | Binary Op (\"+\", (7, 5), (7, 5) )\n" + 
				"| | | | Expression (\"2\", (7, 6), (7, 6) )\n" + 
				"| | | | Binary Op (\"+\", (7, 7), (7, 7) )\n" + 
				"| | | | | Expression (\"4\", (7, 8), (7, 8) )\n" + 
				"| Operator (\"write\", (9, 0), (9, 7) )\n" + 
				"| | Expression (\"a\", (9, 6), (9, 6) )\n" + 
				"| Operator (\"While Do\", (11, 0), (11, 20) )\n" + 
				"| | Expression (\"1\", (11, 6), (11, 6) )\n" + 
				"| | Operator (\"{}\", (11, 11), (11, 20) )\n" + 
				"| | | Operator (\"write\", (11, 12), (11, 19) )\n" + 
				"| | | | Expression (\"x\", (11, 18), (11, 18) )\n" + 
				"| Operator (\"If Then Else\", (15, 1), (15, 39) )\n" + 
				"| | Expression (\"()\", (15, 4), (15, 11) )\n" + 
				"| | | Expression (\"x\", (15, 5), (15, 5) )\n" + 
				"| | | Binary Op (\"==\", (15, 7), (15, 7) )\n" + 
				"| | | | Expression (\"5\", (15, 10), (15, 10) )\n" + 
				"| | Operator (\"{}\", (15, 18), (15, 21) )\n" + 
				"| | | Operator (\"{}\", (15, 19), (15, 20) )\n" + 
				"| | Operator (\"{}\", (15, 28), (15, 39) )\n" + 
				"| | | Operator (\":=\", (15, 29), (15, 38) )\n" + 
				"| | | | Ident (\"y\", (15, 29), (15, 29) )\n" + 
				"| | | | | Expression (\"x\", (15, 34), (15, 34) )\n" + 
				"| | | | | Binary Op (\"-\", (15, 35), (15, 35) )\n" + 
				"| | | | | | Expression (\"34\", (15, 36), (15, 36) )\n";	
		String res = new String(out.toByteArray());
		//assert etalon.equals(res) : etalon + "\n" + res;
		assert res.length() == etalon.length() : res.length() + " " + etalon.length();
		//System.out.print(res);
		for (int i = 0; i < res.length(); i++) {
			assert res.charAt(i) == etalon.charAt(i) : i + " " + res.substring(i, i + 10) + " " + etalon.substring(i, i + 10);
		}
		
	}
	
		System.out.println("Test complete");
	}
}
