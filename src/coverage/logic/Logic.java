/**
 * 
 */
package coverage.logic;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *It is the main class for calculating the logic coverage here
 *
 * @author Wuzhi Xu
 * 
 * Date: Jan 23, 2007
 * 
 * TODO:
 */
public class Logic {
	Expression expr;
	List<VarExpression> vars;
	TruthTable table;//a variable of TruthTable
	
	final static boolean[] TF=new boolean[]{true, false};
	
	/**
	 * 
	 * @param expr the expression that need to calculate the coverage
	 */
	public Logic(Expression expr)
	{
		//initialization
		this.expr = expr;
		vars = new ArrayList<VarExpression>();
		findVariables(null, expr, true);
		table = createTruthTable();
	}
	
	/**
	 * This method should be deprecated in the future, because it expose the inside data structure to the user
	 * Also, two-demension array is too much, replace array with bitset in the future.
	 * 
	 * @return boolean[][] a two dimension array that contains the truth table
	 */
	public TruthTable getTruthTable()
	{
		return table;
	}
	
	/**
	 * 
	 * @return int the number of variables in the expression
	 */
	public int sizeOfVariables()
	{
		return vars.size();
	}
	
	/**
	 * @deprecated replaced by getVariableArray();
	 * @return Iterator<VarExpression> the interator that contains all variables
	 */
	public Iterator<VarExpression> getVariableInterator()
	{
		return vars.iterator();
	}
	
	/**
	 * to avoid exposing the inside data structure, return array
	 * 
	 * @return VarExpression[] an array that contains all variables
	 */
	public VarExpression[] getVariableArray()
	{		
		return vars.toArray(new VarExpression[0]);
	}
	
	/**
	 *  If there is three variables, there are 
	 * three array in the list. The array is int[*][2](* means the length might be 0..n). The order of array in 
	 * the list is the same as the order of variables in the variables array and truth table.
	 * 
	 * @return List<int[][]> A list of all test pairs for each variables.
	 */
	public List<List<int[]>> getAllGACC()
	{
		//create a new List<List<int[]>>
		List<List<int[]>> result = new ArrayList<List<int[]>>();
		//go through each variable
		for(int k = 0;k < vars.size();k++)
		{
			//a List<Integer> storing row numbers as the corresponding predicate is true
			List<Integer> t = new ArrayList<Integer>();
			//a List<Integer> storing row numbers as the corresponding predicate is false
			List<Integer> f = new ArrayList<Integer>();
			//go through each row of the table
			for(int i = 0;i < table.getRowNum();i++)
			{
				//in each row, calculate if this variable determines the predicate
				//if it does, put it to t List else put it to f List
				//First, in this row, set the corresponding values from the TruthTable to the variables 
				for(int j = 0;j < vars.size();j++)
				{
					vars.get(j).setValue(table.getRow(i).get(j));
				}
				//Second, calculate P (x = true) (x is the variable)
				vars.get(k).setValue(true);
				boolean tvar = expr.getValue();
				//calculate P (x = false) (x is the variable)
				vars.get(k).setValue(false);
				boolean fvar = expr.getValue();
				//Last but not least, if Px = P (x = true) exclusive OR P (x = false) is true, the variable determines the predicate
				if(tvar^fvar)
				{
					//in this row, if the variable is true, add it to t, else to f
					if(table.getRow(i).get(k))
						t.add(i);
					else
						f.add(i);
				}//end if
			}//end for loop of the variable i
			
			//int[][] gacc=new int[t.size()*f.size()][2];
			//create a List<int[]>
			List<int[]> gacc = new ArrayList<int[]>();		
			//take the matched elements from t and f lists and put them into gacc
			for(int i = 0;i < t.size();i++)
				for(int j = 0;j < f.size();j++)
				{
					int[] pair = new int[2];
					//get an element from t
					pair[0] = t.get(i);
					//get another element from f
					pair[1] = f.get(j);
					gacc.add(pair);					
				}
			//add one gacc
			result.add(gacc);
		}//end for loop of the variable k

		return result;
	}
	
	/**
	 * Same as getAllGACC
	 * 
	 * @return List<int[][]> A list of all test pairs for each variables.
	 */
	public List<List<int[]>> getAllCACC()
	{
		//create a new List<List<int[]>>
		List<List<int[]>> result=new ArrayList<List<int[]>>();
		//go through each variable
		for(int k = 0;k < vars.size();k++)
		{
			//a List<Integer> storing row numbers as the corresponding predicate is true
			List<Integer> t = new ArrayList<Integer>();
			//a List<Integer> storing row numbers as the corresponding predicate is false
			List<Integer> f = new ArrayList<Integer>();
			//go through each row of the table
			for(int i = 0;i < table.getRowNum();i++)
			{
				//in each row, calculate if this variable determines the predicate
				//if it does, put it to t List else put it to f List
				//First, in this row, set the corresponding values from the TruthTable to the variables 
				for(int j = 0;j < vars.size();j++)
				{
					vars.get(j).setValue(table.getRow(i).get(j));
				}
				//Second, calculate P (x = true) (x is the variable)
				vars.get(k).setValue(true);
				boolean tvar = expr.getValue();
				//calculate P (x = false) (x is the variable)
				vars.get(k).setValue(false);
				boolean fvar = expr.getValue();
				//Last but not least, if Px = P (x = true) exclusive OR P (x = false) is true, the variable determines the predicate
				if(tvar^fvar)
				{
					//in this row, if the variable is true, add it to t, else to f
					if(table.getRow(i).get(k))
						t.add(i);
					else
						f.add(i);
				}//end if
			}//end for loop with variable i
			
			//create a List<int[]>
			List<int[]> cacc = new ArrayList<int[]>();
			for(int i = 0;i < t.size();i++)
				for(int j = 0;j < f.size();j++)		
				{
					//check if values of predicate are opposite
					//get rid of the elements of which their predicate values are the same
					if(table.getRowValue(t.get(i))^
							table.getRowValue(f.get(j)))
					{
						int[] pair = new int[2];
						pair[0] = t.get(i);
						pair[1] = f.get(j);
						cacc.add(pair);
					}
				}
						
			result.add(cacc);
		}

		return result;
	}
	
	/**
	 * Same as getAllGACC
	 * 
	 * @return List<int[][]> A list of all test pairs for each variables.
	 */
	public List<List<int[]>> getAllRACC()
	{
		List<List<int[]>> result=new ArrayList<List<int[]>>();
		
		for(int k=0;k<vars.size();k++)
		{
			List<Integer> t=new ArrayList<Integer>();
			List<Integer> f=new ArrayList<Integer>();
			for(int i=0;i<table.getRowNum();i++)
			{
				for(int j=0;j<vars.size();j++)
				{
					vars.get(j).setValue(table.getValue(i, j));
				}
				vars.get(k).setValue(true);
				boolean tvar=expr.getValue();
				vars.get(k).setValue(false);
				boolean fvar=expr.getValue();
				if(tvar^fvar)
				{
					if(table.getValue(i, k))
						t.add(i);
					else
						f.add(i);
				}
			}
			
			List<int[]> racc=new ArrayList<int[]>();
			for(int i=0;i<t.size();i++)
				for(int j=0;j<f.size();j++)
				{
					BitSet trow=table.getRow(t.get(i));
					BitSet frow=table.getRow(f.get(j));
					int m=0;
					for(;m<table.getVarNum();m++)
						if(m==k)
							continue;
						else if(trow.get(m)!=frow.get(m))
							break;
					
					if(m==table.getVarNum())
					{
						int[] pair=new int[2];
						pair[0]=t.get(i);
						pair[1]=f.get(j);
						racc.add(pair);
					}
				}
						
			result.add(racc);
		}

		return result;
	}
	
	/**
	 *   If there is three variables, there are 
	 * three array in the list. The array is int[*][4](* means the length might be 0..n). The order of array in 
	 * the list is the same as the order of variables in the variables array and truth table.
	 * 
	 * @return List<int[][]> A list of all test pairs for each variables.
	 * 
	 */
	public List<List<int[]>> getAllGICC()
	{
		List<List<int[]>> result = new ArrayList<List<int[]>>();
		
		for(int k = 0;k < vars.size();k++)
		{
			/*
			List<Integer> tt=new ArrayList<Integer>();
			List<Integer> tf=new ArrayList<Integer>();
			List<Integer> ft=new ArrayList<Integer>();
			List<Integer> ff=new ArrayList<Integer>();
			*/
			List<Integer> t = new ArrayList<Integer>();
			List<Integer> f = new ArrayList<Integer>();
			for(int i = 0;i < table.getRowNum();i++)
			{
				for(int j = 0;j < vars.size();j++)
				{
					vars.get(j).setValue(table.getValue(i, j));
				}
				vars.get(k).setValue(true);
				boolean tvar = expr.getValue();
				vars.get(k).setValue(false);
				boolean fvar = expr.getValue();
				if(!(tvar^fvar))
				{
					/*if(table.getValue(i, k))
					{
						if(table.getRowValue(i))
							tt.add(i);
						else
							tf.add(i);						
					}
					else
					{
						if(table.getRowValue(i))
							ft.add(i);
						else
							ff.add(i);
					}*/
					if(table.getRow(i).get(k))
						t.add(i);
					else 
						f.add(i);
				}//end if
			}//end for loop of variable i

			List<int[]> gicc = new ArrayList<int[]>();
			List<int[]> giccForTrue = new ArrayList<int[]>();
			List<int[]> giccForFalse = new ArrayList<int[]>();
		/*	for(int i=0;i<tt.size();i++)
				for(int j=0;j<tf.size();j++)
					for(int l=0;l<ft.size();l++)
						for(int m=0;m<ff.size();m++)
						{
							int[] pair=new int[4];
							pair[0]=tt.get(i);
							pair[1]=tf.get(j);
							pair[2]=ft.get(l);
							pair[3]=ff.get(m);
							gicc.add(pair);
						}
						*/
			for(int i = 0;i < t.size();i++)
				for(int j = 0;j < f.size();j++)
				{
					if(table.getRowValue(t.get(i)) == table.getRowValue(f.get(j)))
					{
						int[] pair = new int[2];
						pair[0] = t.get(i);
						pair[1] = f.get(j);
						if(table.getRowValue(t.get(i)))
							giccForTrue.add(pair);
						else
							giccForFalse.add(pair);
						//gicc.add(pair);
					}
				}
			for(int z = 0;z < giccForTrue.size();z++)
				gicc.add(giccForTrue.get(z));
			for(int z = 0;z < giccForFalse.size();z++)
				gicc.add(giccForFalse.get(z));
			result.add(gicc);
		}

		return result;
	}
	
	public List<List<int[]>> getAllRICC()
	{
		List<List<int[]>> result = new ArrayList<List<int[]>>();
		
		for(int k = 0;k < vars.size();k++)
		{
		/*	List<Integer> tt=new ArrayList<Integer>();
			List<Integer> tf=new ArrayList<Integer>();
			List<Integer> ft=new ArrayList<Integer>();
			List<Integer> ff=new ArrayList<Integer>();
			*/
			List<Integer> t = new ArrayList<Integer>();
			List<Integer> f = new ArrayList<Integer>();
			for(int i = 0;i < table.getRowNum();i++)
			{
				for(int j = 0;j < vars.size();j++)
				{
					vars.get(j).setValue(table.getValue(i, j));
				}
				vars.get(k).setValue(true);
				boolean tvar=expr.getValue();
				vars.get(k).setValue(false);
				boolean fvar=expr.getValue();
				if(!(tvar^fvar))
				{
					/*if(table.getValue(i, k))
					{
						if(table.getRowValue(i))
							tt.add(i);
						else
							tf.add(i);						
					}
					else
					{
						if(table.getRowValue(i))
							ft.add(i);
						else
							ff.add(i);
					}*/
					if(table.getRow(i).get(k))
						t.add(i);
					else 
						f.add(i);
				}
			}

			List<int[]> ricc = new ArrayList<int[]>();
			List<int[]> riccForTrue = new ArrayList<int[]>();
			List<int[]> riccForFalse = new ArrayList<int[]>();
		/*	for(int i=0;i<tt.size();i++)
				for(int j=0;j<tf.size();j++)
					for(int l=0;l<ft.size();l++)
						for(int m=0;m<ff.size();m++)
						{
							
							BitSet ttrow=table.getRow(tt.get(i));
							BitSet tfrow=table.getRow(tf.get(j));
							BitSet ftrow=table.getRow(ft.get(l));
							BitSet ffrow=table.getRow(ff.get(m));
							
							int n=0;
							for(;n<table.getVarNum();n++)
								if(n==k)
									continue;
								else if((ttrow.get(n)!=ftrow.get(n))
										||(tfrow.get(n)!=ffrow.get(n)))
									break;
														
							if(n==table.getVarNum())
							{
								int[] pair=new int[4];
								pair[0]=tt.get(i);
								pair[1]=tf.get(j);
								pair[2]=ft.get(l);
								pair[3]=ff.get(m);
								ricc.add(pair);
							}							

						}
						*/

			for(int i = 0;i < t.size();i++)
				for(int j = 0;j < f.size();j++)
				{
					BitSet trow=table.getRow(t.get(i));
					BitSet frow=table.getRow(f.get(j));
					int m=0;
					for(;m<table.getVarNum();m++)
						if(m==k)
							continue;
						else if(trow.get(m)!=frow.get(m))
							break;
					
					if(m==table.getVarNum())
					{
						int[] pair=new int[2];
						pair[0]=t.get(i);
						pair[1]=f.get(j);
						if(table.getRowValue(t.get(i)))
							riccForTrue.add(pair);
						else
							riccForFalse.add(pair);
						//ricc.add(pair);
					}
				}
			for(int z = 0;z < riccForTrue.size();z++)
				ricc.add(riccForTrue.get(z));
			for(int z = 0;z < riccForFalse.size();z++)
				ricc.add(riccForFalse.get(z));
			result.add(ricc);
		}

		return result;
	}
	/**
	 * 
	 * @return a TruthTable based on the predicate
	 */
	public TruthTable createTruthTable()
	{
		//get the size of the variables
		int varSize = sizeOfVariables();
		//return a TruthTable
		TruthTable result = new TruthTable(varSize);
		//get the number of the rows of the TruthTable
		int rowNum = result.getRowNum();
		//put values to the corresponding places
		for( int i =0 ;i < rowNum;i++)
		{
			//get a BitSet in which T or F has been placed correctly
			BitSet row = result.getRow(i);
			for(int j = 0;j < varSize;j++)
			{
				vars.get(j).setValue(row.get(j));
			}
			//put the value of the predicate 
			row.set(varSize, expr.getValue());	
			//in each row, go through every variable
			for(int z = 0; z < vars.size();z++)
			{	
				//in each row, calculate if this variable determines the predicate
				//First, in this row, set the corresponding values from the TruthTable to the variables 
				for(int k = 0; k < vars.size();k++)
				{
					vars.get(k).setValue(row.get(k));
				}
				//Second, calculate P (x = true) (x is the variable)
				vars.get(z).setValue(true);
				boolean tvar = expr.getValue();
				//calculate P (x = false) (x is the variable)
				vars.get(z).setValue(false);
				boolean fvar = expr.getValue();
				//Last but not least, if Px = P (x = true) exclusive OR P (x = false) is true, the variable determines the predicate
				if(tvar^fvar)
					row.set(varSize + 1 + z,true);
				//else
				//	row.set(varSize + 1 + z,false);
			}
		}
		return result;
	}
	
	/**
	 * A recursive method to collect all variables in the expression
	 * @param p
	 * @param c
	 * @param operand
	 */
	private void findVariables(Expression p, Expression c, boolean operand)
	{
		if(c instanceof VarExpression)
		{
			{	
				boolean isExist=false;
				for(int i=0;i<vars.size();i++)
				{
					if(((VarExpression)c).isSame(vars.get(i)))
					{
						if(p!=null)
						{
							if(operand)
								p.setLeft(vars.get(i));
							else
								p.setRight(vars.get(i));
						}
						isExist=true;
						break;
					}					
				}
				
				if(!isExist)
					vars.add((VarExpression)c);				
			}
		}else if(c instanceof NotExpression)
		{
			findVariables(c, c.getRight(), false);
		}else
		{
			findVariables(c, c.getLeft(), true);
			findVariables(c, c.getRight(), false);
		}
	}
	
	/**
	 * Transform true to T and false to F
	 * 
	 * @param bool a boolean value
	 * @return String "T" or "F"
	 */
	public static String printTF(boolean bool)	
	{
		if(bool) return "T";
		else  return "F";
	}

}
