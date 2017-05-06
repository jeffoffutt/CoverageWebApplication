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
 * Modified by Lin Deng to add JS support
 * Date: Mar, 2016
 * 
 * Modified by Lin Deng 05/05/2017
 * Added function for sharing a graph with URL
 *
 */

public class DFGraphCoverage extends HttpServlet{
	
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
	
	
	String hiddenLink = "https://cs.gmu.edu:8443/offutt/coverage/DFGraphCoverage?";
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
        out.println("<title>Data Flow Graph Coverage</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"#DDEEDD\">");
        out.println("<p align=\"center\">");
        out.println("  <b><font size=\"5\">Data Flow Graph Coverage Web Application</font></b>");
        out.println("</p>\n");
       
        
        
        // add js lib for graph display
        String js =
        "<script src=\"jquery-min.js\"></script>\n"
        +"<script src=\"springy.js\"></script>\n"
        +"<script src=\"springyui.js\"></script>\n"
        
        // js code for retrieving the url
        // and prompt to users to copy
        +"<script>"
        +"function copyToClipboard(text) {"
   //     +"url = window.location.href;"
//        +"text = url + text;"
        +"window.prompt(\"Copy to clipboard: Ctrl+C\", text);"
        +"}"
        +"</script>"
        ;
                
        out.println(js);
        
        String action =request.getParameter("action");

///////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////

        // build hidden link
        hiddenLink = "https://cs.gmu.edu:8443/offutt/coverage/DFGraphCoverage?";
        showShareButton = false;
        String initialNodeStr = request.getParameter("initialNode");
		String edgesStr = request.getParameter("edges");
		String endNodeStr = request.getParameter("endNode");
		
		String defsStr = request.getParameter("defs");
		String usesStr = request.getParameter("uses");
		
		// process edgesStr
		// edges=1+2%0D%0A1+3%0D%0A2+4%0D%0A3+4%0D%0A3+5%0D%0A4+5%0D%0A

		String edgesLink = "";
		
		// construct the link
		if(edgesStr!=null)
		{
			// split edges
			String[] lines = edgesStr.split("\\r?\\n");		
        
			for(String str : lines)
			{
				// little verification??
				
				// replace white space to +
				str = str.trim();
				str = str.replaceAll("\\s+","+");
				edgesLink = edgesLink + str +"%0D%0A";
			}
			hiddenLink = hiddenLink + "edges="+ edgesLink + "&";
		}
		
//		initialNode=1&endNode=5&action=Edges
		if (initialNodeStr!=null)
		{
			// if more than one
			// add +
			initialNodeStr = initialNodeStr.trim();
			initialNodeStr = initialNodeStr.replaceAll("\\s+","+");
			
			hiddenLink = hiddenLink + "initialNode="+ initialNodeStr + "&";
		}
		
		if (endNodeStr!=null)
		{
			// if more than one
			// add +
			endNodeStr = endNodeStr.trim();
			endNodeStr = endNodeStr.replaceAll("\\s+","+");
			hiddenLink = hiddenLink + "endNode="+ endNodeStr + "&";
		}
		
		
		String defsLink = "";
		if (defsStr != null)
		{
			// split defs
			String[] lines = defsStr.split("\\r?\\n");
			for(String str : lines)
			{
				// little verification??
				
				// replace white space to +
				str = str.trim();
				str = str.replaceAll("\\s+","%20");
				defsLink = defsLink + str +"%0D%0A";
			}
			hiddenLink = hiddenLink + "defs="+ defsLink + "&";
			
		}
		String usesLink = "";
		if (usesStr != null)
		{
			// split uses
			String[] lines = usesStr.split("\\r?\\n");
			for(String str : lines)
			{
				// little verification??
				
				// replace white space to +
				str = str.trim();
				str = str.replaceAll("\\s+","%20");
				usesLink = usesLink + str +"%0D%0A";
			}
			hiddenLink = hiddenLink + "uses="+ usesLink + "&";
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
		
		if (initialNodeStr != null && endNodeStr != null && edgesStr != null
			&& defsStr != null && usesStr != null) {
			if (initialNodeStr.equals("") && endNodeStr.equals("")
					&& edgesStr.equals("") && defsStr.equals("") && usesStr.equals("") ) // if provided nothing
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
		
        

        
        
  ///////////////////////////////////////////////////////////////      
  ///////////////////////////////////////////////////////////////      
        
        
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
        	if(dfg!=null)
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
        out.println("<br>Implementation by Wuzhi Xu, Nan Li, Lin Deng, and Scott Brown.");
        out.println("<br>&copy; 2007-2017, all rights reserved.");
        out.println("<br>Last update: 05-May-2017");
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
    	//	System.out.println("variable: " + v.getName());
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
		if(paths.size() > 0){
			for(int i = 0;i < paths.size();i++)		
				result += paths.get(i).toString() + "<br>";
		}
		else if(paths.size() <=0 || paths == null)
			result += "No path or No path needed" + "<br>";
		
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
		
		/*while(defsOnNodes.hasNext())
			System.out.println("defs on nodes: " + defsOnNodes.next());
		
		while(usesOnNodes.hasNext())
			System.out.println("uses on nodes: " + usesOnNodes.next());
		
		for(int i = 0;i < usesOnEdges.size();i++)
			System.out.println("[uses" + usesOnEdges.get(i).toString()) ;
			
		for(int i = 0;i < defsOnEdges.size();i++)
			System.out.println("[defs " + defsOnEdges.get(i).toString());*/
		//from defs on nodes to uses on edges
		if(usesOnEdges.size() > 0){
			while(defsOnNodes.hasNext()){	
				Node n = defsOnNodes.next();
				for(int i = 0;i < usesOnEdges.size();i++){
					result += "[" + n.toString()+ ", " + usesOnEdges.get(i).toString() + "]"+ "<br>";
				}		
			}
		}
		
		//from defs on edges to uses on nodes
		if(defsOnEdges.size() > 0){
			while(usesOnNodes.hasNext()){	
				Node n = usesOnNodes.next();
				for(int i = 0;i < defsOnEdges.size();i++){
					//System.out.println("[" + defsOnEdges.get(i).toString()+ ", " + usesOnNodes.next().toString() + "]");
					result += "[" + defsOnEdges.get(i).toString()+ ", " + n.toString() + "]" + "<br>";
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
		// get all edges
		List<Path> edgePaths = g.findEdges();
		// System.out.println(edgePaths);
		// process edges to generate a graph
		Iterator nodeItr = g.getNodeIterator();
		String nodeStr = "";
		String edgeStr = "";
		// produce strings used for JS
		while(nodeItr.hasNext())
		{
			//System.out.println(nodeItr.next());
			Node node = (Node) nodeItr.next();
			nodeStr += "var node"+ node +" = graph.newNode({label: '"+node;
			if(g.isInitialNode(node))  // initial to be brown
			{
				nodeStr +="', color: '#FFCC00', font: 'italic bolder 16px Verdana, sans-serif'});\n";
			}
			else if(g.isEndingNode(node))  // end to be blue
			{
				nodeStr +="', color: '#CC00FF', font: 'italic bolder 16px Verdana, sans-serif'});\n";
			}
			else // normal node t obe black
			{
				nodeStr +="'});\n";
			}
		}
				
		for(Path edge : edgePaths)
		{
//			System.out.println(edge);
			edgeStr += "graph.newEdge( node"+edge.get(0)+", node"+edge.get(1)+");\n";
			
		}
		
		
		
		
		String result = "<table id = \"tableResult\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"
		+"  <tr><td width=\"50%\"  valign=\"top\">" + msg + "</td>\n"
		+"    <td width=\"50%\" valign=\"top\">" ;
//		+"<font face=\"Garamond\">Node color: <font color=gray>Initial Node</font>,\n"
//		+"<font color=black>Ending Node</font>, \n"
//		+"<font color=blue>Passed Node</font>, \n"
//		+"<font color=red>Unpassed Node</font></font><br> \n"

		if(g.sizeOfNodes()>0){
		result += "<script>"
		        +"var graph = new Springy.Graph();\n"
		        +nodeStr
		        +edgeStr;
		           
		        // function
		        
		       result += " jQuery(function(){"
		      +"var springy = window.springy = jQuery('#springydemo').springy({"
		        +"graph: graph,"
		        +"nodeSelected: function(node){"
		        +"console.log('Node selected: ' + JSON.stringify(node.data));}"
		        +"   });"
		        +"  });"
		      +"  </script>\n";
			
				
				result += "<div>\n"; 
				result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
						+ "Node color: <font color=\"#FFCC00\">Initial Node</font>, <font color=\"#CC00FF\">Final Node</font><br>\n";
				result += "<canvas id=\"springydemo\" width=\"500\" height=\"400\" />\n";
				result += "</div>\n"
						+"</td></tr>\n";
		}

		result+="</table>\n";

		
		return result;
	}
	/**
	 * 
	 * @return a String consisting of the form
	 */
	private String printForm()
	{
		String form = ""
			+"<FORM name = dataFlowCoverageForm action=DFGraphCoverage method=post> \n"
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
			+"		<tr><td></tr> <tr><td></tr>";
//			+"		&nbsp;"
//			+"		</tbody></table> \n"
//			+"</FORM> \n";
		
		// only display the share button when an action has been submitted
		// i.e. a graph is displayed
		// otherwise, hide the button
		if(showShareButton)
		{
			form = form
			+"<tr>\n" ;			
		}else
		{
			form = form
			+"<tr style=\"visibility:collapse;\">\n" ;
		}
		
		form = form 
		+"  <td align=right width = \"15%\" ><b>Share Data Flow Graph:</b></td>\n" 
		+"  <td aligh=\"center\" width=\"85%\" >\n" 
		+"	  &nbsp;<img onclick=\"javascript:copyToClipboard('"+ hiddenLink +"')\" src=\"share.png\" style=\"width:70px;height:20px;\"/>" 
		+"  </td>\n"	
		+"</tr>\n"
		+"</table>\n"
		//leave this form out and put it in the printPrimePaths()	
		// need to leave it out for enabling infeasible paths
		+"    </form>\n"
	;
		
		
		
		
		return form;
	}

}
