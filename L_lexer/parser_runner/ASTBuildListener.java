
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
import java.util.*;

public class ASTBuildListener extends L_parserBaseListener {
	private PrintStream out;
	public ASTBuildListener(PrintStream out) {
		this.out = out;
	}
	
	public static class ParseErrorException extends Exception {}
	
	private int margin = -1;
	
	
	private void printInformation(ParserRuleContext ctx, String name, String textLex) {

		Token i = ctx.getStart(), k = ctx.getStop();
		
		for (int j = 0; j < margin; j++) {
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
	
	private void printInformation(String name, String textLex) {
		
		for (int j = 0; j < margin; j++) {
			out.format("| ");
		}
		//String text = i.getText();
		if (textLex.equals("<EOF>")) {
			textLex = "";
		}
		out.format("%s (\"%s\")\n",
			//voc.getSymbolicName(i.getType()),
			name,
			textLex
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
		margin++;
		out.println("Program");
	}
	
	@Override public void exitProgram(L_parser.ProgramContext ctx) {
		margin--;
	}
	
	@Override public void enterBinopRoot(L_parser.BinopRootContext ctx) {
		margin++;
		printInformation(ctx, "Binary Op", ctx.getChild(1).getText());
	}
	
	@Override public void exitBinopRoot(L_parser.BinopRootContext ctx) {
		margin--;
	}

	@Override public void enterFuncDef(L_parser.FuncDefContext ctx) {
		margin++;
		printInformation(ctx, "Func Def");
	}
	
	@Override public void exitFuncDef(L_parser.FuncDefContext ctx) {
		margin--;
	}

	@Override public void enterFuncCall(L_parser.FuncCallContext ctx) {
		margin++;
		printInformation(ctx, "Func Call");
	}

	@Override public void exitFuncCall(L_parser.FuncCallContext ctx) {
		margin--;
	}
	
	
	@Override public void enterExprS(L_parser.ExprSContext ctx) {
		margin++;
		printInformation(ctx, "Expression");
	}
	
	@Override public void exitExprS(L_parser.ExprSContext ctx) {
		margin--;
	}
	
	@Override public void enterEquate(L_parser.EquateContext ctx) {
		margin++;
		printInformation(ctx, "Operator", ":=");
	}
	
	@Override public void exitEquate(L_parser.EquateContext ctx) {
		margin--;
	}
	
	@Override public void enterBraceOp(L_parser.BraceOpContext ctx) {
		margin++;
		printInformation(ctx, "Block", "");
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitBraceOp(L_parser.BraceOpContext ctx) {
		margin--;
	}
	
	
	@Override public void enterNamedIdent(L_parser.NamedIdentContext ctx) {
		margin++;
		printInformation(ctx, "Ident");
	}
	
	@Override public void exitNamedIdent(L_parser.NamedIdentContext ctx) {
		margin--;
	}
	
	@Override public void enterWrite(L_parser.WriteContext ctx) {
		margin++;
		printInformation(ctx, "Operator", "write");
	}
	
	@Override public void exitWrite(L_parser.WriteContext ctx) {
		margin--;
	}
	
	@Override public void enterRead(L_parser.ReadContext ctx) {
		margin++;
		printInformation(ctx, "Operator", "read");
	}
	
	@Override public void exitRead(L_parser.ReadContext ctx) {
		margin--;
	}
	
	@Override public void enterWhileConstruct(L_parser.WhileConstructContext ctx) {
		margin++;
		printInformation(ctx, "Operator", "While Do");
	}
	
	@Override public void exitWhileConstruct(L_parser.WhileConstructContext ctx) {
		margin--;
	}
	
	@Override public void enterIfThenElse(L_parser.IfThenElseContext ctx) {
		margin++;
		printInformation(ctx, "Operator", "If Then Else");
	}
	
	@Override public void exitIfThenElse(L_parser.IfThenElseContext ctx) {
		margin--;
	}
	
	//sugar
	
	@Override public void enterIncrement(L_parser.IncrementContext ctx) {
		margin++;
		printInformation("Operator", ":=");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Binary Op", "+");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Expression", "1");
		margin -= 2;
		
		ctx.children= null;
	}

	@Override public void exitIncrement(L_parser.IncrementContext ctx) {
		margin--;
	}
	
	
	@Override public void enterDecrement(L_parser.DecrementContext ctx) {
		margin++;
		printInformation("Operator", ":=");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Binary Op", "-");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Expression", "1");
		margin -= 2;
		ctx.children= null;
	}

	@Override public void exitDecrement(L_parser.DecrementContext ctx) {
		margin--;
	}

	@Override public void enterPlusEq(L_parser.PlusEqContext ctx) {
		margin++;
		printInformation("Operator", ":=");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Binary Op", "+");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		margin--;
		ParseTreeWalker.DEFAULT.walk(this, ctx.getRuleContext(ParserRuleContext.class, 1));
		margin -= 1;
		ctx.children= null;
		
	}

	@Override public void exitPlusEq(L_parser.PlusEqContext ctx) {
		margin--;
	}

	@Override public void enterMinusEq(L_parser.MinusEqContext ctx) {
		margin++;
		printInformation("Operator", ":=");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		printInformation("Binary Op", "-");
		
		margin++;
		printInformation(ctx.getRuleContext(ParserRuleContext.class, 0), "Ident");
		margin--;
		ParseTreeWalker.DEFAULT.walk(this, ctx.getRuleContext(ParserRuleContext.class, 1));
		margin -= 1;
		ctx.children= null;
	}

	@Override public void exitMinusEq(L_parser.MinusEqContext ctx) {
		margin--;
	}
}
