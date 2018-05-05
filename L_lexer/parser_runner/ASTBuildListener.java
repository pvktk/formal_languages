
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.io.*;

public class ASTBuildListener extends L_parserBaseListener {
	private PrintStream out;
	public ASTBuildListener(PrintStream out) {
		this.out = out;
	}
	private int margin = 0;
	private void printInformation(ParserRuleContext ctx, String name) {
		Token i = ctx.getStart();
		for (int j = 0; j < margin; j++) {
			out.format("  ");
		}
		out.format("%s (\"%s\", %d, %d, %d)\n",
			//voc.getSymbolicName(i.getType()),
			name,
			i.getText(),
			i.getLine() - 1,
			i.getCharPositionInLine(),
			i.getCharPositionInLine() + i.getText().length() - 1
		);
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterProgram(L_parser.ProgramContext ctx) {
		printInformation(ctx, "Program");
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
	@Override public void enterExprSimple(L_parser.ExprSimpleContext ctx) {
		//System.out.println("ExprSimple entered");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterExpression(L_parser.ExpressionContext ctx) {
		printInformation(ctx, "Expression");
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void enterOperator(L_parser.OperatorContext ctx) {
		printInformation(ctx, "Operator");
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
