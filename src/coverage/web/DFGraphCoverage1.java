package coverage.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coverage.graph.DFGraph;
import coverage.graph.Edge;
import coverage.graph.Graph;
import coverage.graph.GraphUtil;
import coverage.graph.InvalidGraphException;
import coverage.graph.Node;
import coverage.graph.Path;
import coverage.graph.Variable;

/**
 * @author wuzhi
 * 
 * Modified by Nan Li
 * Date: Feb, 2009
 *
 */

public class DFGraphCoverage1 extends HttpServlet{
	
	static DFGraph dfg;
	static String defs;
	static String uses;
	static String warning;
	static Graph g = new Graph();
	static List<Path> paths;
	static String edges;
	static String initialNode;
	static String endNode;
	
	//for the applet
	static List<Path> appletPaths;
	static String title;
	
	
	
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
        out.println("<title>Data Flow Graph Coverage</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"#DDEEDD\">");
        out.println("<p align=\"center\">");
        out.println("  <b><font size=\"5\">Data Flow Graph Coverage Web Application</font></b>");
        out.println("</p>\n");
        String action =request.getParameter("action");

        if(action ==null || action.equalsIgnoreCase("New Graph")|| action.equalsIgnoreCase("Data Flow Coverage"))
        {
        	//initialize all variables
        	g = new Graph();
         title = null;
         paths = null;
      	dfg = null;
        	defs = null;
        	uses = null;
        	warning = null;
        	edges = "";
        	initialNode = "";
        	endNode = "";

        	out.println(printForm());
        }
        //deal with DU Paths
        else if(action.equalsIgnoreCase("DU Paths"))
        {
      	  //return a warning message through validating the request
      	  warning = validate(request); 
      	  //if size of edges, final nodes, and nodes of a graph is not zero, continue the following steps
           if(g.sizeOfEdges() != 0 && g.sizeOfEndingNode() != 0 && g.sizeOfNodes() != 0){
         	  //create a data flow graph
         	  HttpSession hs = request.getSession();
         	  hs.setAttribute("graph", g);
         	  Graph g = (Graph)hs.getAttribute("graph");    		
         	  dfg = g.createDFGraph();   
		        title = "DU Paths";
		        dfg.removeVariables();
		        //return a String consisting of paths of DU Paths
		        String table = "";
		        if(readDefUse(request))  
		      	  table = getCoverage(0);
		       //return the warning messages
		        String result = "";
		        if(warning != null)
		      	  result = warning + "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        else
		      	  result = "    <b>" + title + " for all variables are:</b><br>\n" + table;
		         //return html forms	
		        	out.println(printForm());
		        	out.println(printResult(result));
            }
              	
        }
        //deal with DU Pairs
        else if(action.equalsIgnoreCase("DU Pairs"))
        {
      	  //return a warning message through validating the request
      	  warning = validate(request); 
      	  //if size of edges, final nodes, and nodes of a graph is not zero, continue the following steps
           if(g.sizeOfEdges()!= 0 && g.sizeOfEndingNode()!= 0 && g.sizeOfNodes()!= 0){
         	  //create a data flow graph
         	  HttpSession hs = request.getSession();
         	  hs.setAttribute("graph", g);
		        Graph g = (Graph)hs.getAttribute("graph");    		
		        dfg = g.createDFGraph();   
		        title = "DU Pairs";
		        dfg.removeVariables();
		        //return a String consisting of paths of DU Pairs
		        String table = "";
		        if(readDefUse(request))  
		       	  table = getCoverage(4);
		        //return the warning messages
		        String result = "";
		        if(warning != null)
		      	  result = warning + "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        else
		      	  result = "    <b>" + title + " for all variables are:</b><br>\n" + table;
		         //return html forms	
		        	out.println(printForm());
		        	out.println(printResult(result));
           }
        }
        //deal with All Def Coverage
        else if(action.equalsIgnoreCase("All Def Coverage"))
        {
      	  //return a warning message through validating the request
      	  warning = validate(request); 
      	  //if size of edges, final nodes, and nodes of a graph is not zero, continue the following steps
           if(g.sizeOfEdges()!= 0 && g.sizeOfEndingNode()!= 0 && g.sizeOfNodes()!= 0){
         	  //create a data flow graph
         	  HttpSession hs = request.getSession();
           	  hs.setAttribute("graph", g);
		        Graph g = (Graph)hs.getAttribute("graph");    		
		        dfg = g.createDFGraph(); 
		        title = "All Def Coverage";
		        dfg.removeVariables();
		        //return a String consisting of paths of All Def Coverage
		        String table = "";
		        if(readDefUse(request))  
		      	  table = getCoverage(1);
		        //return the warning messages
		        String result = "";
		        if(warning != null)
		      	  result = warning + "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        else
		      	  result = "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        //return html forms	
		        out.println(printForm());
		        out.println(printResult(result));
           }	        	        	
        }
        //deal with All Use Coverage
        else if(action.equalsIgnoreCase("All Use Coverage"))
        {
      	  //return a warning message through validating the request
      	  warning = validate(request); 
           //if size of edges, final nodes, and nodes of a graph is not zero, continue the following steps
           if(g.sizeOfEdges()!= 0 && g.sizeOfEndingNode()!= 0 && g.sizeOfNodes()!= 0){
         	  //create a data flow graph
         	  HttpSession hs = request.getSession();
           	  hs.setAttribute("graph", g);
		        Graph g = (Graph)hs.getAttribute("graph");    		
		        dfg = g.createDFGraph(); 
		        title = "All Use Coverage";
		        dfg.removeVariables();
		        readDefUse(request);  
		        //return a String consisting of paths of All Use Coverage
		        String table = "";
		        if(readDefUse(request))  
		      	  table = getCoverage(2);
		        //return the warning messages
		        String result = "";
		        if(warning != null)
		      	  result = warning + "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        else
		      	  result = "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        //return html forms
		        out.println(printForm());
		        out.println(printResult(result));
           }
        }
        //deal with All DU Path Coverage
        else if (action.equalsIgnoreCase("All DU Path Coverage"))
        {
      	  //return a warning message through validating the request
      	  warning = validate(request); 
      	  //if size of edges, final nodes, and nodes of a graph is not zero, continue the following steps
           if(g.sizeOfEdges()!= 0 && g.sizeOfEndingNode()!= 0 && g.sizeOfNodes()!= 0){
         	  //create a data flow graph
         	  HttpSession hs = request.getSession();
           	  hs.setAttribute("graph", g);
		        Graph g = (Graph)hs.getAttribute("graph");    		
		     	  dfg = g.createDFGraph(); 
		     	  title = "All DU Path Coverage";
		     	  dfg.removeVariables();
		     	  //return a String consisting of paths of All DU Path Coverage
		        String table = "";
		     	  if(readDefUse(request))      
		     		  table = getCoverage(3);
		     	  //return the warning messages
		     	  String result = "";
		     	  if(warning != null)
		     		  result = warning + "    <b>" + title + " for all variables are:</b><br>\n" + table;
	       	  else
	       		  result = "    <b>" + title + " for all variables are:</b><br>\n" + table;
		        //return html forms	
	        	  out.println(printForm());
	      	  out.println(printResult(result));
           }
      	  
        }else if (action.equalsIgnoreCase("New DU Info"))
        {
        	//reset all global variables
        	dfg.removeVariables();
        	defs = null;
        	uses = null;
        	warning = null;
        	title = null;
        	//print the form in Html
        	out.println(printForm());
        }
        //jump to the Graph Coverage Application
        else if (action.equalsIgnoreCase("Graph Coverage"))
        {
      	  response.sendRedirect("GraphCoverage");
        }
        //jump to the Logic Coverage Application
        else if (action.equalsIgnoreCase("Logic Coverage"))
        {
      	 // RequestDispatcher dispatcher=request.getRequestDispatcher("LogicCoverage");
      	 // dispatcher.forward(request, response);
      	  response.sendRedirect("LogicCoverage");
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT Coverage"))
        {
      	  response.sendRedirect("MinimalMUMCUTCoverage");
        }
        
        out.println("<p style=\"font-size:80%;font-family:monospace\">");
        out.println("Companion software");
        out.println("<br>to <i>Introduction to Software Testing</i>, Ammann and Offutt.");
        out.println("<br>Implementation by Wuzhi Xu and Nan Li.");
        out.println("<br>&copy; 2007-2010, all rights reserved.");
        out.println("<br>Last update: 22-February-2010");
        out.println("</font>");
        out.println("</p>");
        out.println("</body>");
        out.println("</html>");
	}
	
	/**
	 * 
	 * @param request
	 * @return true if the graph, definitions, and uses can be read correctly else return false
	 */
	private boolean readDefUse(HttpServletRequest request) 
	{	
		//get the definitions and uses from the request
		defs = request.getParameter("defs");
		uses = request.getParameter("uses");
		try			
		{
			//get information about definitions,uses, and a data flow graph
			GraphUtil.readDefUse(defs, uses, dfg);
			return true;
		}catch (InvalidInputException e)
    	{
    		warning = printWarning(e);
    	}
		
		return false;
	}
	/**
	 * 
	 * @param e
	 * @return a warning message the graph is invalid
	 */
	private String printWarning(Exception e)
	{
		String warning = ""
			+ "    <font face=\"Garamond\">The input graph is invalid because:<br>\n"
			+ "    <font color=red>\n" + "    <b>" + e.getMessage()
			+ "<br> Cannot generate a set of test paths to satisfy the coverage</b>\n" + "    </font>\n" + "    </font>\n" + "    <br>\n";
		
		return warning;
	}
	
	/**
	 * Get the corresponding coverage for each request
	 * @param type
	 * @return a table to show paths for different coverage request
	 */
	private String getCoverage(int type)
	{
		String table = "   <table cellSpacing=\"0\" cellPadding=\"0\" width=\"100%\" bgColor=\"#eeffee\" border=\"1\">\n" +
							"   <tr>\n" + "     <th>Variable</th><th>" + title + "   </th>\n" + "   </tr>\n";
    	//get an iterator of variables of the data flow graph
		Iterator<Variable> vars = dfg.getVariableIterator();
    	appletPaths = new ArrayList<Path>();
    	//for each variable, get the corresponding coverage paths
    	while(vars.hasNext())
    	{
    		List<Path> paths = null;
    		Variable v = vars.next();
    		try {
				switch (type)
				{
					case 0://DU paths
						/*paths = new ArrayList<Path>();
						Iterator<Path> it = v.getDuPath();
						while(it.hasNext())
						{ 
							paths.add(it.next());
						}*/
						paths = dfg.findDUPaths(v);
						break;
					case 1://All Def Coverage
						paths = dfg.findAllDef(v);
						break;
					case 2://All Use Coverage
						paths = dfg.findAllUse(v);
						break;
					case 3://All DU Paths Coverage
						paths = dfg.findAllDUPath(v);
						break;	
					case 4://DU Pairs
						paths = dfg.findDuPairs(v);
						break;
				}
    		} 
    		catch (InvalidGraphException e) {
				if(warning == null || warning.equals(""))
					warning = printWarning(e);
				else
				{
					//return a new error message if the message is different from the current error message
					String tempWarningMessage = printWarning(e);
					if(!warning.equals(tempWarningMessage))
						warning += printWarning(e);
				}
			}	
    		
			if(paths != null)
			{	
				//	return variables and the corresponding paths
				if(type == 4)				
					table += printDUPairs(v, paths,dfg.getDefsOnNodes(v), dfg.getUsesOnNodes(v), dfg.getDefsOnEdges(v), dfg.getUsesOnEdges(v));
				else
					table += printVarPaths(v, paths);
				//add all paths to appletPaths
				appletPaths.addAll(paths);	
			}	
    	}//end while loop
    	
    	table += "   </table>\n";
    	return table;
	}
	/**
	 * get the parameters from the request and create a graph with edges, an initial node, and final nodes
	 * @param request
	 * @throws InvalidInputException
	 */
	private void createGraph(HttpServletRequest request) throws InvalidInputException
	{
		initialNode = request.getParameter("initialNode");
		edges = request.getParameter("edges");
		endNode = request.getParameter("endNode");
		g = GraphUtil.readGraph(edges, initialNode, endNode);
	}
	/**
	 * 
	 * @param request
	 * @return a warning message
	 */
	private String validate(HttpServletRequest request)
	{
		//return a warning if anything wrong with createGraph()
		String warning = null;
		try
    	{    		
			createGraph(request);
    	}catch(InvalidInputException e)
    	{
    		warning = printWarning(e);
    		return warning;
    	}
		//return a warning if anything wrong with coverage.graph.Graph.validate()
    	try
    	{
    		g.validate();
    	}catch(InvalidGraphException e)
    	{
    		warning = printWarning(e);			
    	}
    	
    	return warning;
	}
	/**
	 * 
	 * @param var
	 * @param paths
	 * @return a String consisting of a variable and the corresponding paths
	 */
	private String printVarPaths(Variable var, List<Path> paths)
	{		
		String result = "   <tr>\n" +"     <td>" + var.getName() + "</td>\n" + "     <td>";
		//for the defs and uses among nodes
		for(int i = 0;i < paths.size();i++)		
			result += paths.get(i).toString() + "<br>";
		
		result += "</td>\n" + "   </tr>\n";
		return result;		
	}
	
	/**
	 * 
	 * @param var
	 * @param paths
	 * @return a String consisting of a variable and the corresponding paths
	 */
	private String printDUPairs(Variable var, List<Path> paths,Iterator<Node> defsOnNodes, Iterator<Node> usesOnNodes, List<Edge> defsOnEdges, List<Edge> usesOnEdges)
	{		
		String result = "   <tr>\n" +"     <td>" + var.getName() + "</td>\n" + "     <td>";
		//for the defs and uses among nodes
		for(int i = 0;i < paths.size();i++)		
			result += paths.get(i).toString() + "<br>";
		
		//from defs on nodes to uses on edges
		if(usesOnEdges.size() > 0){
			while(defsOnNodes.hasNext()){	
				for(int i = 0;i < usesOnEdges.size();i++){
					result += "[" + defsOnNodes.next().toString()+ ", " + usesOnEdges.get(i).toString() + "]"+ "<br>";
				}		
			}
		}
		//from defs on edges to uses on nodes
		if(defsOnEdges.size() > 0){
			while(usesOnNodes.hasNext()){	
				for(int i = 0;i < defsOnEdges.size();i++){
					result += "[" + defsOnEdges.get(i).toString()+ ", " + usesOnNodes.next().toString() + "]" + "<br>";
				}		
			}
		}	
		//from defs on edges to uses on edges
		if(defsOnEdges.size() > 0 && usesOnEdges.size() > 0){
			for(int i = 0;i < defsOnEdges.size();i++){
				for(int j = 0;j < usesOnEdges.size();j++)
					result += "[" + defsOnEdges.get(i).toString()+ ", " + usesOnEdges.get(j).toString() + "]"+ "<br>";
			}		
		}
		result += "</td>\n" + "   </tr>\n";
		return result;		
	}
	

	/**
	 * 
	 * @param msg
	 * @return a String consisting of two tables. One table represents coverage paths and the other represents the applet
	 */
	private String printResult(String msg)
	{
		String result = "<table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"
		+ "<tr>\n" + "  <td width=\"50%\"  valign=\"top\">\n" + msg + "  </td>\n"
		+ "  <td width=\"50%\" valign=\"top\">\n" 
		+ "    <font face=\"Garamond\">Node color:\n"
		+ "     <font color=\"gray\">Initial Node</font>,\n"
		+ "     <font color=\"black\">Ending Node</font>, \n"
		+ "     <font color=\"blue\">Passed Node</font>, \n"
		+ "     <font color=\"red\">Unpassed Node</font>\n"
		+ "    </font><br> \n"
		//a table displaying the applet
		+ "    <table border=\"1\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#EEFFEE\">\n"
		+ "    <tr><td>\n" 
		+ "      <applet code=\"coverage.applet.GraphApplet\"\n"
		+ "              archive=\"lib/graph.jar,lib/jung.jar,lib/colt.jar,lib/commons-collections.jar\"\n"
		+ "              width=\"500\" height=\"500\">\n";
		//get the paths of a specific coverage and the title for the applet
		if(appletPaths != null)
		{
			result += "           <param name=\"path\" value=\"" + GraphUtil.outputPath(appletPaths) + "\">\n"
			+ "           <param name=\"title\" value=\"" + title + "\">\n";
		}
		//get the variables and the graph for the applet
		result += "           <param name=\"variables\" value=\"" + GraphUtil.outputVariables(dfg) + "\">\n " 
		+"          <param name=\"graph\" value=\""+ GraphUtil.outputGraph(dfg) + "\">\n"
	
		+"      </applet>\n"
		+"    </td></tr>\n"
		+"    </table>\n"
		+"  </td>\n"
		+"</tr>\n"
		+"</table>\n";
		
		return result;

	}
	/**
	 * 
	 * @return a String consisting of the form
	 */
	private String printForm()
	{
		String form = ""
			+"<FORM action=DFGraphCoverage1 method=post> \n"
			+"<div style=\"text-align:center; font-weight:bold; font-size:125%\">Graph Information</div> \n"
			+"<table border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\">\n"
			+"<tr><td width=\"33%\">\n"
			+"		 <table border=0>\n"
			+"      <tr><td>Please enter your "
			+"				<font color=\"green\"><b>graph edges</b></font>"
			+"				in the text box below. \n"
			+"        	Put each edge in one line. Enter edges as pairs of nodes, separated by spaces.(e.g.: 1 3)</td></tr> \n"
			+"      <tr align = center ><td> <textarea rows=\"5\" name=\"edges\" cols=\"25\">"+edges+"</textarea></td></tr>\n" 
			+"		 </table>\n" 
			+"		</td>\n"				
			+"    <td width=\"33%\" valign=\"top\">\n" 
			+"		 <table border=0>\n"
			+"      <tr><td>Enter <font color=\"green\"><b>initial nodes</b></font> below (can be more than one). If the text box  \n"
			+"        below is empty, the first node in the left box will be the initial node.</td></tr> \n"
			+"      <tr align = center><td><p><input type=\"text\" name=\"initialNode\" size=\"5\" value=\""+initialNode+"\"></td></tr>\n" 
			+"		 </table>\n" 
			+"		</td>\n"
			+"    <td width=\"34%\" valign=\"top\">\n" 
			+"		<table border=0>\n" 
			+"      <tr><td>Enter <font color=\"green\"><b>final nodes</b></font> below (can be more than one), \n"
			+"        separated by spaces.</td></tr>\n"
			+"    <tr align = center><td><input type=\"text\" name=\"endNode\" size=\"30\" value=\""+endNode+"\"></td></tr>\n"
			+"		</table>\n" 
			+"		</td>\n"			
			+"		</tr>\n" 
			+"		</table>\n"	
			+"<div style=\"text-align:center; font-weight:bold; font-size:125%\">Data Flow Information</div> \n "
			+"<TABLE cellSpacing=0 cellPadding=0 width=\"100%\" bgColor=#eeffee border=1>  \n"
			+"  <TBODY><TR><TD width=\"50%\">  \n"
			+"      <TABLE border=0> \n"
			+"        <TBODY>" 
			+"				<TR><TD>Please enter your <font color=\"green\"><b>defs</b></font> in the text box  \n"
			+"            below. Put one variable and all defs for the variable in one line,  \n"
			+"            separated by spaces. Put the variable name at the beginning of the line.(e.g.:  x 1 2) </FONT>"
			+"				</TD></TR> \n"
			+"        <TR align=center><TD><TEXTAREA name=\"defs\" cols=30 rows=5>\n";
		if(defs != null)
			form += defs;
		form += "</TEXTAREA></TD></TR></TBODY></TABLE></TD> \n"
			+"    <TD width=\"50%\" vAlign=top> \n"
			+"      <TABLE border=0> \n"
			+"        <TBODY><TR><TD>Please enter your <font color=\"green\"><b>uses</b></font> in the text box below. Put one variable and  \n"
			+"                     all uses for the variable in one line, separated by spaces. Put the variable name \n"
			+"                     at the beginning of the line. (e.g.:  x 3 4 2,3)</FONT></TD></TR> \n"
			+"        <TR align=center><TD><P><TEXTAREA name=\"uses\" cols=30 rows=5>\n";
		if(uses != null)
			form += uses;
		form += "</TEXTAREA> \n"
			+"            </P>  </TD></TR></TBODY></TABLE>      </TD></TR> </TBODY></TABLE>\n"
			+" <table width=\"100%\">" 
			+"		<tr><td></tr> <tr><td></tr>"
			+"		<tr><td></tr> <tr><td></tr>"
			+"		<TR><TD align =right width = \"15%\">Test Requirements: </td><td width=\"85%\">" 
			+"		<input type=submit name=\"action\" value=\"DU Pairs\"> " 
			+"		&nbsp;<input type=submit name=\"action\" value=\"DU Paths\">"
		 //+"		&nbsp;<input type=submit name=\"action\" value=\"Def Paths\" disabled> " 
		//	+"		&nbsp;<input type=submit name=\"action\" value=\"Def Pairs\" disabled> " 	
			+"		</td></tr>\n"
			+"		<tr><td></tr> <tr><td></tr>"
			+"    <tr><td align=right width = \"15%\">Test Paths: </td> <td width=\"85%\">" 
			+" 	<INPUT type=submit value=\"All Def Coverage\" name=\"action\"> \n"
			+"    &nbsp;<input type=submit value=\"All Use Coverage\" name=\"action\"> " 
			+"		&nbsp;<INPUT type=submit value=\"All DU Path Coverage\" name=\"action\"> " 
			+"		</td></tr>\n"   
			+"		<tr><td></tr> <tr><td></tr>"
			+"    <tr><td align=right width = \"15%\"d >Others: </td><td width=\"85%\">" 
			+"  	<INPUT type=submit value=\"New Graph\" name=\"action\">\n"  
			+"		&nbsp;<INPUT type=submit name=\"action\" value=\"New DU Info\"> " 
			+"		&nbsp;<INPUT type=submit name=\"action\" value=\"Graph Coverage\"> " 
			+"		&nbsp;<INPUT type=submit name=\"action\" value=\"Logic Coverage\"> " 
			+"		&nbsp;<INPUT type=submit name=\"action\" value=\"Minimal-MUMCUT Coverage\"> "
			+"		 </TD></TR>\n"
			+"		<tr><td></tr> <tr><td></tr>"
			+"		&nbsp;"
			+"		</tbody></table> \n"
			+"</FORM> \n";
		
		return form;
	}

}
