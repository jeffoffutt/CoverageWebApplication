package coverage.logic;

import java.util.BitSet;

/**
	*
	*in order to dispaly table as textbook, the row number is changed 
 * @author wxu2
 *
 */
public class TruthTable {
	
	private int rows;
	private int vars;
	
	BitSet[] bits;	
	
	public TruthTable (int vars)
	{
		this.vars=vars;
		this.rows=(int)Math.pow(2, vars);
		bits=new BitSet[rows];		
	}
	
	public static void main(String[] args)
	{
		TruthTable table=new TruthTable(3);
		for( int i=0;i<8;i++)
		{
			BitSet bit=table.getRow(i);
			for(int j=0;j<3;j++)
				System.out.print(">"+j+":"+bit.get(j));
			System.out.println();
		}
	}
	
	public BitSet getRow(int row)
	{	
		if(row>=rows)
			return null;
		
		row=rows-row-1;
		if(bits[row]==null)
		{
			bits[row]=new BitSet();
			String bitString=Integer.toBinaryString(row);
			int start=vars-bitString.length();			
			for(int i=0;i<bitString.length();i++)
			{				
				if(bitString.charAt(i)=='1')
				{
					bits[row].set(start+i, true);//.flip(start+i);
				}
			}
		}		
		return bits[row];
	}
	
	public int getRowNum()
	{
		return rows;
	}
	
	public boolean getRowValue(int row)
	{
		return bits[rows-row-1].get(vars);
	}
	
	public boolean getValue(int row, int col)
	{
		return bits[rows-row-1].get(col);
	}
	
	public int getVarNum()
	{
		return vars;
	}
	
	public void setRowValue(int row, boolean value)
	{
		bits[rows-row-1].set(vars, value);
	}
	
	public String toString()
	{
		String result="";
		for(int i=0;i<rows;i++)
		{
			result+=i+" ";
			for( int j=0;j<vars+1;j++)
			{
				result+=printTF(bits[i].get(j))+" ";
			}
			
			result+="\n";
		}
		
		return result;
	}
	
	public static String printTF(boolean value)
	{
		if(value)
			return "T";
		else
			return "F";
	}
	
}
