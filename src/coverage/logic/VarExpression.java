/**
 * 
 */
package coverage.logic;

/**
 * It is variable expression class. 
 * 
 * @author Wuzhi Xu
 * 
 * Date: Jan 23, 2007
 * 
 * TODO:
 */
public class VarExpression extends Expression {

	boolean var;
	String name;
	
	/**
	 * 
	 * @param name the variable name
	 * @param var the value. When it is initialized from ast, the defualt value is true.
	 */
	public VarExpression(String name, boolean var)
	{
		this.name=name;
		this.var=var;
	}
	
	public boolean getValue()
	{
		return var;
	}

	public void setValue(boolean var)
	{
		this.var=var;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * determine if two VarExpression represent the same variable.
	 * 
	 * @param varE 
	 * @return
	 */
	public boolean isSame(VarExpression varE)
	{
		if(name.equals(varE.getName()))
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return name;
	}

	@Override
	/**
	 * It is empty method
	 */
	public void setLeft(Expression left) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * It is empty method
	 */
	public void setRight(Expression right) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * It is empty method
	 * @return null
	 */
	public Expression getLeft() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * It is empty method
	 * @return null
	 */
	public Expression getRight() {
		// TODO Auto-generated method stub
		return null;
	}
}
