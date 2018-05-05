
import lexer.L_grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import lexer.L_grammar;

public class TestParser {
	public static void main(String[] args) {
	{
		String input = "\nf(a,b,c){\n\tf(1,2//\n\t); //comment\n}\n\na=1+2+4;\n\nwrite a;\n\nwhile 1 do (write x);\n\nif (1 == 2) then (write 1) else (read p);\n";

		L_grammar lexer = new L_grammar(CharStreams.fromString(input));
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ASTBuildListener listener = new ASTBuildListener(new PrintStream(out));
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		
		String etalon = "  Program (\"f\", 1, 0, 0)\n    Func Def (\"f\", 1, 0, 0)\n      Operator (\"f\", 2, 1, 1)\n        Func Call (\"f\", 2, 1, 1)\n          Expression (\"1\", 2, 3, 3)\n          Expression (\"2\", 2, 5, 5)\n    Operator (\"a\", 6, 0, 0)\n      Expression (\"1\", 6, 2, 2)\n        Binary Op (\"+\", 6, 3, 3)\n        Expression (\"2\", 6, 4, 4)\n          Binary Op (\"+\", 6, 5, 5)\n          Expression (\"4\", 6, 6, 6)\n    Operator (\"write\", 8, 0, 4)\n      Expression (\"a\", 8, 6, 6)\n    Operator (\"while\", 10, 0, 4)\n      Expression (\"1\", 10, 6, 6)\n      Operator (\"(\", 10, 11, 11)\n        Operator (\"write\", 10, 12, 16)\n          Expression (\"x\", 10, 18, 18)\n    Operator (\"if\", 12, 0, 1)\n      Expression (\"(\", 12, 3, 3)\n          Expression (\"1\", 12, 4, 4)\n            Binary Op (\"==\", 12, 6, 7)\n            Expression (\"2\", 12, 9, 9)\n      Operator (\"(\", 12, 17, 17)\n        Operator (\"write\", 12, 18, 22)\n          Expression (\"1\", 12, 24, 24)\n      Operator (\"(\", 12, 32, 32)\n        Operator (\"read\", 12, 33, 36)\n";	
		String res = new String(out.toByteArray());
		assert etalon.equals(res) : etalon + "\n" + res;
		
	}
	
		System.out.println("Test complete");
	}
}
