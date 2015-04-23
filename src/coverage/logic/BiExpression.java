package coverage.logic;

/**
 * It is the binary expression class. It includes five logic operators: equivalence, implication, exclusive or,
 * or, and. 
 * 
 * @author Wuzhi Xu
 * 
 * Date: Jan 23, 2007
 * 
 * TODO:
 */
public class BiExpression extends Expression {
	
	public static final int EQUAL=0;
	public static final int IMPLY=1;
	public static final int EOR=2;
	public static final int OR=3;
	public static final int AND=4;
	
	Expression left;
	Expression right;
	int type;
	
	/**
	 * 
	 * @param type the type of binary operations
	 * @throws InvalidOperatorException if the type is not one of five binary operations.
	 */
	public BiExpression(int type)
	throws InvalidOperatorException
	{
		if(type>4 ||type<0)
			throw new InvalidOperatorException("there is no such operator type "+type);
	
		this.type=type;
	}

	/**
	 * @return Expression the expression on the left side of operator
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * @param left the left side expression
	 */
	public void setLeft(Expression left) {
		this.left = left;
	}

	/**
	 * 
	 * @return Expression the expression on the right side of operator
	 */
	public Expression getRight() {
		return right;
	}

	/**
	 * @param right the right side expression
	 */
	public void setRight(Expression right) {
		this.right = right;
	}

	/**
	 * 
	 * @return int the operation type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @return boolean the boolean value after this expression is evaluated.
	 */
	public boolean getValue()
	{
		
		switch(type)
		{
			case EQUAL:
				return (left.getValue()==right.getValue());
			case EOR:
				return (left.getValue()^right.getValue());
			case IMPLY:
				return (!left.getValue()||right.getValue());
			case OR:
				return (left.getValue()||right.getValue());
			case AND:
				return (left.getValue()&& right.getValue());
		}
		
		System.err.println("incorrect operator");
		return false;
	}
	
	public String toString()
	{
		String leftStr="";
		if(left instanceof BiExpression)
			leftStr="("+left.toString()+")";
		else
			leftStr=left.toString();
		
		String rightStr="";
		if(right instanceof BiExpression)
			rightStr="("+right.toString()+")";
		else
			rightStr=right.toString();

		switch(type)
		{
			case EQUAL:
				return leftStr+"="+rightStr;
			case EOR:
				return leftStr+"^"+rightStr;
			case IMPLY:
				return leftStr+">"+rightStr;
			case OR:
				return leftStr+"|"+rightStr;
			default:
				return leftStr+"&"+rightStr;
		}
	}
}