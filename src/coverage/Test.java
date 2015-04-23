package coverage;

import java.io.StringReader;
import java.util.BitSet;
import java.util.List;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import coverage.graph.Variable;
import coverage.logic.ExprLexer;
import coverage.logic.ExprParser;
import coverage.logic.Expression;
import coverage.logic.InvalidOperatorException;
import coverage.logic.Logic;
import coverage.logic.TruthTable;
import coverage.logic.VarExpression;

public class Test {
	
	public static final boolean[] TF={true, false};
	
	public static void main(String[] args)
	{
		try 
		{
			String exprStr="a&b|b&c|a&c";

			ExprLexer lexer = new ExprLexer(new StringReader(exprStr+";"));
			ExprParser parser = new ExprParser(lexer);

			parser.setASTNodeType("antlr.CommonAST");
			parser.expr();
			antlr.CommonAST ast = (antlr.CommonAST)parser.getAST();			

			if (ast != null) 
			{				
				Expression expr=Expression.createExpression(ast);	
				System.out.println(expr.toString());
				
				Logic logic=new Logic(expr);
				//printExpr(expr, 0);
				
				TruthTable table=logic.createTruthTable();
							
				System.out.println(table.toString());


			} else {
				System.out.println("The expression is empty");
			}
		} catch (RecognitionException e) {
			e.printStackTrace();
		} catch (TokenStreamException e) {
			e.printStackTrace();
		} catch (InvalidOperatorException e) {
			e.printStackTrace();
		}
	}
	
	public static void printExpr(Expression expr, int level)
	{
		String indent="  ";
		for(int i=0;i<level;i++)
			indent+=indent;
		
		System.out.println(level+indent+expr.toString());
		Expression left=expr.getLeft();
		Expression right=expr.getRight();
		if(left!=null)
			printExpr(left, level+1);
		
		if(right!=null)
			printExpr(right, level+1);
	}

}
