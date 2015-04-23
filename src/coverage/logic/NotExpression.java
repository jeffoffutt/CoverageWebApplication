/**
 * 
 */
package coverage.logic;

/**
 * It is unary Not expression
 * 
 * @author Wuzhi Xu
 * 
 * Date: Jan 23, 2007
 * 
 * TODO:
 */
public class NotExpression extends Expression {

	Expression right;
	
	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}

	public boolean getValue()
	{
		return (!right.getValue());
	}
	
	public String toString()
	{
		if(right instanceof BiExpression)
			return "!("+right.toString()+")";
		else
			return "!"+right.toString();
	}

	@Override
	/**
	 * It is empty method, because left is empty
	 * 
	 */
	public void setLeft(Expression left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * It is empty method
	 * @return a null expression
	 */
	public Expression getLeft() {
		// TODO Auto-generated method stub
		return null;
	}
}
