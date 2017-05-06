package coverage.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import coverage.logic.ExprLexer;
import coverage.logic.ExprParser;
import coverage.logic.Expression;
import coverage.logic.InvalidOperatorException;
import coverage.logic.Logic;
import coverage.logic.TruthTable;
import coverage.logic.VarExpression;


/**
 * @author wuzhi
 * 
 * Modified by Lin Deng 05/05/2017
 * Added function for sharing an expression with URL
 *
 */
public class LogicCoverage extends HttpServlet {
	
	static String exprStr;
	static Logic logic;
	
	String hiddenLink = "https://cs.gmu.edu:8443/offutt/coverage/LogicCoverage?";
	boolean showShareButton = false;
	

	public void doGet (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	public void doPost (HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Logic Coverage</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"#DDEEDD\">");
        out.println("<p align=\"center\"><b><font size=\"5\">Logic Coverage Web Application</font></b></p>\n");        
        
        
        // add js lib for graph display
        String js =
//        "<script src=\"jquery-min.js\"></script>\n"
//        +"<script src=\"springy.js\"></script>\n"
//        +"<script src=\"springyui.js\"></script>\n"
        
        // js code for retrieving the url
        // and prompt to users to copy
        "<script>"
        +"function copyToClipboard(text) {"
   //     +"url = window.location.href;"
//        +"text = url + text;"
        +"window.prompt(\"Copy to clipboard: Ctrl+C\", text);"
        +"}"
        +"</script>"
        ;
        
        out.println(js);
     
        String action =request.getParameter("action");
        
        
        // build hidden link
        hiddenLink = "https://cs.gmu.edu:8443/offutt/coverage/LogicCoverage?";
        showShareButton = false;
        String expStr = request.getParameter("expression");
        
        
        if (expStr!=null)
		{
		
        	expStr = expStr.trim();        
        	// only & need to replace!!!!
        	expStr = expStr.replaceAll("\\s+","%20");
//        	expStr = expStr.replaceAll("!","%21");
        	expStr = expStr.replaceAll("&","%26");
//        	expStr = expStr.replaceAll("|","%7C");
//        	expStr = expStr.replaceAll(">","%3E");
//        	expStr = expStr.replaceAll("^","%5E");
//        	expStr = expStr.replaceAll("=","%3D");
			hiddenLink = hiddenLink + "expression="+ expStr + "&";
		}
        
		if(action!=null)
		{
			// process the whitespace in action
			String actionStr = new String(action);
			actionStr = actionStr.trim();
			actionStr = actionStr.replaceAll("\\s+", "%20");
			hiddenLink = hiddenLink + "action=" + actionStr;
			if (!action.equals("New Graph"))
				showShareButton = true;  // only display share button when there is an action
		}
		else
		{
			showShareButton = false;
		}
		
		if (expStr != null) {
			if (expStr.equals("") ) // if provided nothing
			{
				showShareButton = false;
			}
		}
		
		// if the last one is & or ?
		// trim it out
		
		if(hiddenLink.charAt(hiddenLink.length()-1)=='&')
		{
			hiddenLink = hiddenLink.substring(0, hiddenLink.length()-1);
		}
        

        if( action ==null ||action.equalsIgnoreCase("New Expression")||action.equalsIgnoreCase("Logic Coverage"))
        {
        	exprStr=null;
        	logic=null;        	
        	out.println(printForm());
        }
        else if (action.equalsIgnoreCase("Graph Coverage"))
        {
      	  //RequestDispatcher dispatcher=request.getRequestDispatcher("GraphCoverage");
      	  //dispatcher.forward(request, response);
      	  response.sendRedirect("GraphCoverage");
      	  
        }
        else if (action.equalsIgnoreCase("Data Flow Coverage"))
        {
      	 // RequestDispatcher dispatcher=request.getRequestDispatcher("DFGraphCoverage1");
      	 // dispatcher.forward(request, response);
      	  response.sendRedirect("DFGraphCoverage");
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT Coverage"))
        {
      	  response.sendRedirect("MinimalMUMCUTCoverage");
        }
        else if(action.equalsIgnoreCase("GACC"))
        {   
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		List<List<int[]>> gacc=logic.getAllGACC();
        		String coverage=printCoverage(gacc);
        		coverage="<font face=\"Garamond\">The following result for GACC is based on the truth table on the right:</font><br>"+coverage;
        		out.println(printResult(coverage, truthTable));
        	}              	
        }else if(action.equalsIgnoreCase("CACC"))
        {
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		List<List<int[]>> cacc=logic.getAllCACC();
        		String coverage=printCoverage(cacc);
        		coverage="<font face=\"Garamond\">The following result for CACC is based on the truth table on the right:</font><br>"+coverage;
        		out.println(printResult(coverage, truthTable));
        	}       	        	
        }else if(action.equalsIgnoreCase("RACC"))
        {
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		List<List<int[]>> racc=logic.getAllRACC();
        		String coverage=printCoverage(racc);
        		coverage="<font face=\"Garamond\">The following result for RACC is based on the truth table on the right:</font><br>"+coverage;
        		out.println(printResult(coverage, truthTable));
        	}
        }else if (action.equalsIgnoreCase("GICC"))
        {
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		List<List<int[]>> gicc=logic.getAllGICC();
        		//String coverage=printCoverage(gicc);
        		String coverage=printCoverage1(gicc);
        		coverage="<font face=\"Garamond\">The following result for GICC is based on the truth table on the right:</font><br>"+coverage;
        		out.println(printResult(coverage, truthTable));
        	}        	
        }else if (action.equalsIgnoreCase("RICC"))
        {
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		List<List<int[]>> ricc=logic.getAllRICC();
        		//String coverage=printCoverage(ricc);
        		String coverage=printCoverage1(ricc);
        		coverage="<font face=\"Garamond\">The following result for RICC is based on the truth table on the right:</font><br>"+coverage;
        		out.println(printResult(coverage, truthTable));
        	}        	
        }else if  (action.equalsIgnoreCase("Truth Table"))
        {
        	String warning=processRequest(request);
        	out.println(printForm());
        	if(warning!=null)
        		out.println(printResult(warning, ""));
        	else
        	{
        		String truthTable = null;
				try {
					truthTable = printTruthTable();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.println(printResult("Please check the predicate", ""));
				}
        		out.println(printResult(" ", truthTable));
        	}
        }
        
        out.println("<p style=\"font-size:80%;font-family:monospace\">\n");
        out.println("Companion software\n");
        out.println("<br>to <i>Introduction to Software Testing</i>, Ammann and Offutt.\n");
        out.println("<br>Implementation by Wuzhi Xu, Nan Li, and Lin Deng.\n");
        out.println("<br>&copy; 2007-2017, all rights reserved.\n");
        out.println("<br>Last update: 05-May-2017</font></p>\n");
        out.println("</body>");
        out.println("</html>");
	}
	
	
	
	private String processRequest(HttpServletRequest request)
	{		
		String warning = null;
		try 
		{
			exprStr = request.getParameter("expression");
			if(exprStr == null || exprStr.trim().equals(""))
				warning = "The expression is empty";
			System.out.println(exprStr);
			ExprLexer lexer = new ExprLexer(new StringReader(exprStr + ";"));
			ExprParser parser = null;
			try{parser = new ExprParser(lexer);}
			catch(Exception e){throw new InvalidInputException("The predicate cannot be parsed, please check the logic operators and clauses");}		

			parser.setASTNodeType("antlr.CommonAST");
			parser.expr();
			antlr.CommonAST ast = (antlr.CommonAST)parser.getAST();			

			if (ast != null) 
			{	
				Expression expr = null;
				try{
					expr = Expression.createExpression(ast);	
				}
				catch (Exception e)
				{
					throw new InvalidInputException("The predicate cannot be parsed, please check the logic operators and clauses");
				}
				logic = new Logic(expr);
			} else {
				warning = "The expression is empty";
			}
		} catch (RecognitionException e) {
			warning = e.getMessage();
		} catch (TokenStreamException e) {
			warning = e.getMessage();
		} catch (Exception e) {
			warning = e.getMessage();
		}
			
		if(warning != null)
			warning = "<font face=\"Garamond\" color=\"red\"><b>" + warning + "</b></font>";
		
		return warning;
	}
	
	private String printForm()
	{
		String result=""
			+"<form name = \"logicCoverageForm\" method=\"post\" action=\"LogicCoverage\"> \n"
			+"<table border=\"1\" width=\"800\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\" align=\"center\"> \n"
			+"<tr><td align=\"justify\">Please enter your logic expression in the text box below, using   \n"
			+"        the following logic operators. The variables can be any string. Parentheses can be used in the logic expression. \n"
			+"(Please be aware that this application gets slow for expressions that have more than 5 or 6 variables.)\n"
			+"        </font></td></tr> \n"
			+"<tr><td> <table width=\"100%\" border=\"0\"> \n"
			+"	<tr align=center><td align=right> Not :<font size=4> <b>!</b></font></font></td> \n"
			+"		<td width=\"20%\"> And :<font size=4> <b>&</b></font></font></td> \n"
			+"		<td align=left> Or :<font size=4> <b>|</b></font></font></td></tr> \n"
			+"	<tr align=center><td align=right> Implication :<font size=4> <b>></b></font></font></td> \n"
			+"		<td> Exclusive Or :<font size=4> <b>^</b></font></font></td> \n"
			+"		<td align=left> Equivalence :<font size=4> <b>=</b></font></font></td></tr> \n"
			+"<tr><td align=\"center\"  colspan=\"3\"><font face=\"Garamond\" size=\"3\"> P =  \n"
			+"	<input type=\"text\" name=\"expression\" size=\"70\"";
			if(exprStr != null)
				result += " value=\""+exprStr+"\"";
			
			result += "></font></td></tr> \n"
			+"	</table> \n"
			+"	</td>\n"			
			+"	</tr>\n" 
			+"	<table width=\"800\" align=\"center\"> \n"
			+"	<tr><td></tr> <tr><td></tr>\n"
			+"	<tr><td></tr> <tr><td></tr>\n"
			+"<tr><td align=right width=\"20%\">Test Requirements:</td> \n"
			+"	<td width=\"80%\" colspan=\"3\">\n"
			+"	<input type=\"submit\" value=\"Truth Table\" name=\"action\">  </td></tr>\n" 
			+" <tr><td></tr> <tr><td></tr>\n"
			+"	<tr>\n" 
			+" <td align=right width = \"20%\">Tests:</td>\n" 
			+"	<td width=\"80%\" colspan=\"3\">\n" 
			+"	<input type=\"submit\" value=\"GACC\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"CACC\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"RACC\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"GICC\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"RICC\" name=\"action\"> </td></tr>\n"
			+" <tr><td></tr> <tr><td></tr>\n"
			+"	<tr>\n" 
			+" <td align=right width = \"20%\" >Others:</td>\n" 
			+"	<td aligh=\"center\" width=\"80%\" colspan=\"3\">\n"
			+"	<input type=\"submit\" value=\"New Expression\" name=\"action\">" 
			+"	&nbsp;<input type=\"submit\" value=\"Graph Coverage\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"Data Flow Coverage\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT Coverage\" name=\"action\"></td></tr> \n" ;
//			+"     </table> \n"
//			+"     </form> \n";
//		
			
			// only display the share button when an action has been submitted
			// i.e. a graph is displayed
			// otherwise, hide the button
			if(showShareButton)
			{
				result = result
				+"<tr>\n" ;			
			}else
			{
				result = result
				+"<tr style=\"visibility:collapse;\">\n" ;
			}
			
			result = result 
			+"  <td align=right width = \"15%\" ><b>Share Expression:</b></td>\n" 
			+"  <td aligh=\"center\" width=\"85%\" >\n" 
			+"	  &nbsp;<img onclick=\"javascript:copyToClipboard('"+ hiddenLink +"')\" src=\"share.png\" style=\"width:70px;height:20px;\"/>" 
			+"  </td>\n"	
			+"</tr>\n"
			+"</table>\n"
			//leave this form out and put it in the printPrimePaths()	
			// need to leave it out for enabling infeasible paths
			+"    </form>\n"
		;			
			
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return a String putting the corresponding data in a table for GACC, RACC, and CACC
	 */
	private String printCoverage(List<List<int[]>> data)
	{
		//start a table
		String result = "<table id = \"tableResult\" border=1 width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" >\n";
		//get a variable array
		VarExpression[] vs = logic.getVariableArray();
		//set up names of all columns
		if(data.size() != 0)
			result += "<tr><th>Major Clause</th><th>Set of possible tests</th><tr>";
		//go through each variable
		for(int i = 0;i < data.size();i++)
		{
			//get one List<int[]> at one time and skip the rest part if this list is null or empty
			List<int[]> gc = data.get(i);
			if(gc == null || gc.size() == 0)
				continue;
			
			result += "<tr><th><font size=\"4\">" + vs[i].getName() + "</font></th><td>";		
			//print out all pairs for one variable
			for(int j = 0;j < gc.size();j++)
			{
				//print the left parentheses
				if(j == 0)
					result += "(";
				else
					result += ", (";	
				//print the data in the pair
				for(int k = 0;k < gc.get(j).length;k++)
					if(k == 0)
						result += printRow(gc.get(j)[k]);
					else
						result += "," + printRow(gc.get(j)[k]);
				
				result += ")";
			}
				
			result += "</td></tr>\n";
		}
		
		result += "</table>";
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return a String putting the corresponding data in a table for GICC and RICC
	 */
	private String printCoverage1(List<List<int[]>> data)
	{
		//start a table
		String result = "<table id =\"tableResult\" border=1 width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" >\n";
		//get a variable array
		VarExpression[] vs = logic.getVariableArray();
		//set up names of all columns
		if(data.size() != 0)
			result += "<tr><th>Major Clause</th><th colspan =\"2\">Set of possible tests</th><tr>";
		//go through each variable
		for(int i = 0;i < data.size();i++)
		{
			//get one List<int[]> at one time and skip the rest part if this list is null or empty
			List<int[]> gc = data.get(i);
			if(gc == null || gc.size() == 0)
				continue;
			
			result += "<tr><th><font size=\"4\">" + vs[i].getName() + "</font></th><td>";		
			//get the current TruthTable
			TruthTable table = logic.getTruthTable();
			//print out all pairs for one variable
			for(int j = 0;j < gc.size();j++)
			{
				if(j == 0 && table.getRowValue(gc.get(j)[0]))
					result += "P = T: (";
				else if(j == 0 && !table.getRowValue(gc.get(j)[0]))
					result += "No feasible pairs for P = T</td><td>P = F: (";
				else if(!table.getRowValue(gc.get(j)[0]) && j != 0)
				{
					j--;
					if((table.getRowValue(gc.get(j)[0])))
						result += "</td><td>P = F: (";
					else 
						result += ", (";	
					j++;
				}
				else
					result += ", (";	
				 
				//print the left parentheses
			//	if(j == 0)
				//	result += "(";
			//	else
				//	result += ", (";	
				//print the data in the pair
				for(int k = 0;k < gc.get(j).length;k++)
					if(k == 0)
						result += printRow(gc.get(j)[k]);
					else
						result += "," + printRow(gc.get(j)[k]);
				
				result += ")";
				if(j == gc.size() - 1 && table.getRowValue(gc.get(j)[0]))
					result += "</td><td>No feasible pairs for P = F</td>";
			}
				
			result += "</td></tr>\n";
		}
		
		result += "</table>";
		return result;
	}
	
	private String printResult(String msg, String table)
	{
		String result=""
			+"<table id = \"tableException\" border=0 width=\"100%\">\n"
			+"<tr><td width=\"50%\" valign=\"top\">"+msg+" &nbsp;</td>\n"
			+"<td width=\"50%\" valign=\"top\"> "+table+"</td></tr></table>\n";

		return result;
	}
	/*
	 * print the truth table
	 */
	private String printTruthTable() throws InvalidInputException
	{
		String result = "";
		String warning = "";
		VarExpression[] vars = logic.getVariableArray();
		
		TruthTable table = logic.getTruthTable();
		//return the table header
		result += "<font face=\"Garamond\">Truth Table:</font><br>"
			+ "<table border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\"> \n"
			+ "<tr><th>Row#</th><th bgcolor = \"#000000\"></th>";
		for(int col = 0;col<vars.length;col++)
			result += "<th><font size=\"4\">"+vars[col].getName()+"</font></th>";
		result += "<th bgcolor = \"#000000\"></th><th>P</th><th bgcolor = \"#000000\"></th>";
		for(int col = 0;col < vars.length;col++)
			result += "<th><font size=\"4\">P" + vars[col].getName() + "</font></th>";
		result += "</tr>\n";
		
		//for each row, put the corresponding data in the cell
		for(int row = 0;row < table.getRowNum();row++)
		{
			result += "<tr align=\"center\"><td>" + printRow(row) + "</td>";
			result += "<td bgcolor = \"#000000\"></td>";
			for(int col = 0;col < table.getVarNum();col++){
				String value = "  ";
				if(Logic.printTF(table.getValue(row, col)) ==  "T"){
					value = "T";
					result += "<td>" + value + "</td>";
				}
				else
					result += "<td>&nbsp;</td>";
			}
			//a blank column to separate P from variables
			result += "<td bgcolor = \"#000000\"></td>";
			//the column P
			if(logic.printTF(table.getValue(row, table.getVarNum())) == "T")
				result += "<td>" + Logic.printTF(table.getValue(row, table.getVarNum())) + "</td>";
			else
				result += "<td>&nbsp;</td>";
			//a blank column to separate P from Clauses determining the predicate
			result += "<td bgcolor = \"#000000\"></td>";
			for(int col = table.getVarNum() + 1;col < table.getVarNum() * 2 + 1;col++){
				String value = "  ";
				if(Logic.printTF(table.getValue(row, col)) ==  "T")
					{value = "T";
					result += "<td>" + value + "</td>";}
				else
					result += "<td>&nbsp;</td>";
			}
			result += "</tr>\n";
		}
		
		result += "</table>";
		
		return result;
	}
	
	private String printRow( int row)
	{
		return ""+(row+1);
	}
}
