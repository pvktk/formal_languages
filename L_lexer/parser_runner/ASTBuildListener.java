
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.io.*;
import java.util.*;

public class ASTBuildListener extends L_parserBaseListener {
	private PrintStream out;
	public ASTBuildListener(PrintStream out) {
		this.out = out;
		stack = new Stack<>();
		stack.push(-1);
	}
	
	public static class ParseErrorException extends Exception {}
	
	private int margin = -1, currMargin = -1;
	private Stack<Integer> stack;
	
	
	private void printInformation(ParserRuleContext ctx, String name, String textLex) {

		Token i = ctx.getStart(), k = ctx.getStop();
		
		while (margin < stack.peek()) {
			stack.pop();
			currMargin--;
		}

		if (margin > stack.peek()) {
			currMargin++;
			stack.push(margin);
		}
		
		for (int j = 0; j < currMargin; j++) {
			out.format("| ");
		}
		//String text = i.getText();
		if (textLex.equals("<EOF>")) {
			textLex = "";
		}
		out.format("%s (\"%s\", (%d, %d), (%d, %d) )\n",
			//voc.getSymbolicName(i.getType()),
			name,
			textLex,
			i.getLine(),
			i.getCharPositionInLine(),
			k.getLine(),
			k.getCharPositionInLine()
		);
	}
	
	private void printInformation(ParserRuleContext ctx, String name) {
		printInformation(ctx, name, ctx.getStart().getText());
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterProgram(L_parser.ProgramContext ctx) {
		printInformation(ctx, "Program", "All code");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterBinop(L_parser.BinopContext ctx) {
		printInformation(ctx, "Binary Op");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFuncDef(L_parser.FuncDefContext ctx) {
		printInformation(ctx, "Func Def");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterFuncCall(L_parser.FuncCallContext ctx) {
		printInformation(ctx, "Func Call");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExprS(L_parser.ExprSContext ctx) {
		printInformation(ctx, "Expression");
	}
	
	@Override public void enterParenthExpr(L_parser.ParenthExprContext ctx) {
		printInformation(ctx, "Expression", "()");
	}
	
	@Override public void enterEquate(L_parser.EquateContext ctx) {
		printInformation(ctx, "Operator", ":=");
	}
	
	@Override public void enterNamedIdent(L_parser.NamedIdentContext ctx) {
		printInformation(ctx, "Ident");
	}
	
	@Override public void enterBraceOp(L_parser.BraceOpContext ctx) {
		printInformation(ctx, "Operator", "{}");
	}
	
	@Override public void enterFunctionCall(L_parser.FunctionCallContext ctx) {
		printInformation(ctx, "Operator", "Function call");
	}
	
	@Override public void enterWrite(L_parser.WriteContext ctx) {
		printInformation(ctx, "Operator", "write");
	}
	
	@Override public void enterRead(L_parser.ReadContext ctx) {
		printInformation(ctx, "Operator", "read");
	}
	
	@Override public void enterWhileConstruct(L_parser.WhileConstructContext ctx) {
		printInformation(ctx, "Operator", "While Do");
	}
	
	@Override public void enterIfThenElse(L_parser.IfThenElseContext ctx) {
		printInformation(ctx, "Operator", "If Then Else");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterEveryRule(ParserRuleContext ctx) {
		margin++;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitEveryRule(ParserRuleContext ctx) {
		margin--;
	}
}
