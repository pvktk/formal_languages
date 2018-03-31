
import java.io.*;
import java.util.List;
import lexer.L_grammar;
import org.antlr.v4.runtime.*;

public class runLexer {
	public static void main(String[] args) throws IOException {
		L_grammar lexer;
		if (args.length == 0) {
			lexer = new L_grammar(CharStreams.fromFileName("example.l"));
		} else {
			lexer = new L_grammar(CharStreams.fromFileName(args[0]));
		}
		
		List<? extends Token> tokens = lexer.getAllTokens();
		Vocabulary voc = lexer.getVocabulary();

		for (Token i : tokens) {
			System.out.format("%10s %6s %6d %6d %6d\n",
				voc.getSymbolicName(i.getType()),
				i.getText(),
				i.getLine() - 1,
				i.getCharPositionInLine(),
				i.getCharPositionInLine() + i.getText().length() - 1
			);
		}
	}
}
