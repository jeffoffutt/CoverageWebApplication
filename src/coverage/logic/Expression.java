/**
 * 
 */
package coverage.logic;

import antlr.collections.AST;

/**
 * It is abstract class for all kinds of expression. 
 * 
 * @author Wuzhi Xu
 * 
 * Date: Jan 23, 2007
 * 
 * TODO:
 */
public abstract class Expression {

	/**
	 * 
	 * @return the value after the whole expression evaluated
	 */
	public abstract boolean getValue();
	
	/**
	 * In NotExpression and VarExpression, it does nothing.
	 * @param left 
	 */
	public abstract void setLeft(Expression left);
	
	/**
	 * In VarExpression, it does nothing.
	 * @param right
	 */
	public abstract void setRight(Expression right);
	
	/**
	 *In NotExpression and VarExpression, it does nothing.
	 * @return null if the expression is NotExpression and VarExpression
	 */
	public abstract Expression getLeft();
	
	/**
	 * In VarExpression, it does nothing.
	 * @return null if the expression is VarExpression
	 */
	public abstract Expression getRight();
	
	/**
	 * 
	 * @param ast the ast tree generated from the ExprParser
	 * @return expression an expression
	 * @throws InvalidOperatorException
	 */
	public static Expression createExpression(AST ast)
	throws InvalidOperatorException
	{
		Expression expr=null;		
		int type=ast.getType();
		switch(type)
		{
		case ExprParserTokenTypes.EQUAL:
			expr=new BiExpression(BiExpression.EQUAL);			
			break;
		case ExprParserTokenTypes.EOR:
			expr=new BiExpression(BiExpression.EOR);
			break;
		case ExprParserTokenTypes.IMPL:
			expr=new BiExpression(BiExpression.IMPLY);
			break;
		case ExprParserTokenTypes.OR:
			expr=new BiExpression(BiExpression.OR);
			break;
		case ExprParserTokenTypes.AND:
			expr=new BiExpression(BiExpression.AND);
			break;
		case ExprParserTokenTypes.NOT:
			expr=new NotExpression();
			break;
		case ExprParserTokenTypes.ID:
			expr=new VarExpression(ast.getText(), true);
			break;
		}
		
		if(expr instanceof BiExpression)
		{
			Expression left=createExpression(ast.getFirstChild());
			Expression right=createExpression(ast.getFirstChild().getNextSibling());
			((BiExpression)expr).setLeft(left);
			((BiExpression)expr).setRight(right);
		}else if(expr instanceof NotExpression)
		{
			Expression right=createExpression(ast.getFirstChild());
			((NotExpression)expr).setRight(right);
		}
		
		return expr;
	}
}
