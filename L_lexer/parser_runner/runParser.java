
import java.io.*;
import java.util.List;
import lexer.L_grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class runParser {
	public static void main(String[] args) throws IOException {
		L_grammar lexer;
		if (args.length == 0) {
			lexer = new L_grammar(CharStreams.fromFileName("example.l"));
		} else {
			lexer = new L_grammar(CharStreams.fromFileName(args[0]));
		}
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ASTBuildListener listener = new ASTBuildListener(new PrintStream(baos));
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		
		
		if (parser.getNumberOfSyntaxErrors() > 0) {
			System.out.println("Parse errors detected. No AST will be shown.");
		} else {
			System.out.print(baos);
		}
	}
}
