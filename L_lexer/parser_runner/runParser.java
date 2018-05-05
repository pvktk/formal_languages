
import java.io.*;
import java.util.List;
import lexer.L_grammar;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class runParser {
	public static void treeTraverse(ParseTree t) {
		int nChilds = t.getChildCount();
		if (nChilds == 0) {
			System.out.println(t.getParent().getText() + "--" + t.getText());
		}
		for (int i = 0; i < nChilds; i++) {
			treeTraverse(t.getChild(i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		L_grammar lexer;
		if (args.length == 0) {
			lexer = new L_grammar(CharStreams.fromFileName("example.l"));
		} else {
			lexer = new L_grammar(CharStreams.fromFileName(args[0]));
		}
		
		L_parser parser = new L_parser(new CommonTokenStream(lexer));
		
		ASTBuildListener listener = new ASTBuildListener(System.out);
		
		ParseTreeWalker.DEFAULT.walk(listener, parser.program());
		//treeTraverse(parser.expression());
	}
}
