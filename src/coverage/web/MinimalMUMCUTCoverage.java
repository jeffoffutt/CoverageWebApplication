package coverage.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import coverage.minimalMUMCUT.InvalidException;
import coverage.minimalMUMCUT.MinimalMUMCUT;

/**
 * 
 * @author Nan Li
 *
 */

public class MinimalMUMCUTCoverage extends HttpServlet{
	static String exprStr;
	static String infeasibleLiterals;
	static MinimalMUMCUT mMUMCUT;
	static Integer maxTestSetSize;
	
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
        out.println("<title>Minimal MUMCUT Coverage</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"#DDEEDD\">");
        out.println("<p align=\"center\"><b><font size=\"5\">Minimal MUMCUT Coverage Web Application</font></b></p>\n");        
        
        String action =request.getParameter("action");
        
        if( action == null || action.equalsIgnoreCase("New Expression")|| action.equalsIgnoreCase("MinimalMUMCUT Coverage"))
        {
      	  exprStr = null;
      	  infeasibleLiterals = null;
      	  mMUMCUT = null;
      	  maxTestSetSize = null;
        	  out.println(printForm());
        }
        else if (action.equalsIgnoreCase("Graph Coverage"))
        {
      	  response.sendRedirect("GraphCoverage");     	  
        }
        else if (action.equalsIgnoreCase("Data Flow Coverage"))
        {
      	  response.sendRedirect("DFGraphCoverage");
        }
        else if(action.equalsIgnoreCase("Logic Coverage"))
        {     	 
       	  response.sendRedirect("LogicCoverage");
        }
        else if(action.equalsIgnoreCase("MUTP Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning;
           warning = processRequest(request,"MUTP");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));  
         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
         	        	  
           }
      
        }
        else if(action.equalsIgnoreCase("MUTP Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning;
           warning = processRequest(request,"MUTP Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  //System.out.println("mini: " + mMUMCUT.getMinimizationData());
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>MUTP Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData()));  
         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
         	        	  
           }
      
        }
        else if(action.equalsIgnoreCase("CUTPNFP Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"CUTPNFP");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));   

         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
      
        }
        else if(action.equalsIgnoreCase("CUTPNFP Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"CUTPNFP Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{ 
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>CUTPNFP Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData())); 
         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
      
        }
        else if(action.equalsIgnoreCase("MNFP Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"MNFP");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{       	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));
         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        }
        else if(action.equalsIgnoreCase("MNFP Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"MNFP Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{  
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>MNFP Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData())); 
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"Minimal-MUMCUT");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));      
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
       
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"Minimal-MUMCUT Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{           	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Minimal-MUMCUT Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData())); 
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
       
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT/SMOTP Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"Minimal-MUMCUT/SMOTP");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));  
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        
        }
        else if (action.equalsIgnoreCase("Minimal-MUMCUT/SMOTP Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"Minimal-MUMCUT/SMOTP Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Minimal-MUMCUT/SMOTP Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData())); 
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        
        }
        else if (action.equalsIgnoreCase("NFP Heuristic"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"NFP");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Test Data: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getData()));
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Number and types of mutants generated: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutantTypes()));
        
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Mutants: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMutant()));  
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        
        }
        else if (action.equalsIgnoreCase("NFP Minimization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"NFP Minimization");
           out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{
         	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>NFP Minimization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMinimizationData())); 
         	  // get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        
        }
        else if (action.equalsIgnoreCase("Fault Detection Maximization"))
        {
      	  long start = System.currentTimeMillis();
      	  String warning = processRequest(request,"Fault Detection Maximization");
      	  
      	  if(maxTestSetSize.intValue() == -1)
      		  warning = warning + "<font face=\"Garamond\" color=\"red\"><b>" + "Invalid Maximization Test Set Size Format. Please enter a positive integer for the test set size" + "</b></font>";
          
      	  out.println(printForm());
           
           if(warning != null && !warning.equals("")){
         	  out.println(printResult(warning, ""));     
           }
           else{         	        	  
         	  out.println("<br><tr><td width=\"50%\" valign=\"top\"><b>Fault Detection Maximization: &nbsp;</b></td>\n");
         	  out.println(printCoverage(mMUMCUT.getMaximizationData())); 
         	  //get the execution time  	  
         	  long end = System.currentTimeMillis();
         	  long duration = end - start;
         	  out.println("running time = " + duration + " millisecs");
           }
        
        }
        
        out.println("<p style=\"font-size:80%;font-family:monospace\">\n");
        out.println("Companion software\n");
        out.println("<br>to <i>Introduction to Software Testing</i>, Ammann and Offutt.\n");
        out.println("<br>Implementation by Gary Kaminski and Nan Li.\n");
        out.println("<br>&copy; 2007-2013, all rights reserved.\n");
        out.println("<br>Last update: 24-April-2013</font></p>\n");
        out.println("</body>");
        out.println("</html>");
	}
	
	
	/**
	 * based on the user predicate input, check the format of the predicate and set up the warning system
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	private String processRequest(HttpServletRequest request, String choice)
	{	
		String warning = null;	
		//get the predicate from the user input
	   exprStr = request.getParameter("expression");

	   //set maxTestSetSize a default value -1, 
	   if(request.getParameter("maxTestSetSize").equals("") || request.getParameter("maxTestSetSize") == null || maxTestSetSize == null)
		   maxTestSetSize = new Integer(-1);
	   
	   if(exprStr == null || exprStr.trim().equals("")){
				warning = "<font face=\"Garamond\" color=\"red\"><b>" + "The expression is empty. " + "</b></font>";
				return warning;
	   }

	   infeasibleLiterals = request.getParameter("infeasibleLiterals");
	   
	   //deal with the case when inputting a value to text field of maximization test set size 
	   if(!request.getParameter("maxTestSetSize").equals("")  && request.getParameter("maxTestSetSize") != null){
	   	if(!Pattern.matches("[1-9]([0-9])*", request.getParameter("maxTestSetSize")))	   
	   	{
	   		warning = "Invalid Maximization Test Set Size Format. Please enter a positive integer for the test set size";  		
	   	}
	   	else{
	   		//if(request.getParameter("maxTestSetSize") == null)
	   		//	maxTestSetSize = new Integer(-1);
	   		//else
	   			maxTestSetSize = new Integer(request.getParameter("maxTestSetSize"));   
	   	}
	   }	   
	   
	   Pattern p2 = Pattern.compile("([a-z]=(0|1),)+[a-z]=(0|1)(;([a-z]=(0|1),)+[a-z]=(0|1))*");
	   Matcher m2 = p2.matcher(infeasibleLiterals);
	   
	   Pattern p2Upper = Pattern.compile("([A-Z]=(0|1),)+[A-Z]=(0|1)(;([A-Z]=(0|1),)+[A-Z]=(0|1))*");
	   Matcher m2Upper = p2Upper.matcher(infeasibleLiterals);
	     
	   if ((!infeasibleLiterals.equals("") && (!m2.find() || !m2.group().equals(infeasibleLiterals))) && (!infeasibleLiterals.equals("") && (!m2Upper.find() || !m2Upper.group().equals(infeasibleLiterals))))
	   	warning = "Invalid Infeasible Variable Format";
	   
	   //check the format of the predicate
	   // String pattern = "!?[a-z]((( & )|( \\| ))!?[a-z])*";
      String pattern = "(\\x20)*!?(\\x20)*[a-z]((\\x20)*((&)|(\\|))(\\x20)*!?(\\x20)*[a-z])*(\\x20)*";	   

      Pattern p = Pattern.compile(pattern);      
      Matcher m = p.matcher(exprStr);
      
      //String patternUpper = "!?[A-Z]((( & )|( \\| ))!?[A-Z])*";
      String patternUpper = "(\\x20)*!?(\\x20)*[A-Z]((\\x20)*((&)|(\\|))(\\x20)*!?(\\x20)*[A-Z])*(\\x20)*";
      Pattern pUpper = Pattern.compile(patternUpper);      
      Matcher mUpper = pUpper.matcher(exprStr);

	   
      //  if ((!m.find() || !m.group().equals(exprStr)) && (!mUpper.find() || !mUpper.group().equals(exprStr)))
      if(!Pattern.matches(pattern, exprStr) && !Pattern.matches(patternUpper, exprStr))
      {
      	warning = "Invalid Predicate Format, please read the description about the correct format of predicates above";
      	warning = "<font face=\"Garamond\" color=\"red\"><b>" + warning + "</b></font>";
      	return warning;
      }
      //add the characters of the predicate to a linked hash set
      Set<String> justLiterals = new LinkedHashSet<String>();
      //
      StringTokenizer st = new StringTokenizer(exprStr, "&|\\|", true);
      exprStr = "";
      while(st.hasMoreTokens()){
      	String temp = st.nextToken();
      	//handle the cases that clauses have !
      	if(temp.indexOf("!") != -1){
      		char temp1 = temp.charAt(temp.indexOf("!"));
      		temp = temp.substring(temp.indexOf("!") + 1);
      		temp = temp1 + temp.trim();
      	}     	
      	exprStr = exprStr + temp.trim() + " "; 
      }
      exprStr = exprStr.substring(0, exprStr.length() - 1);
      //System.out.println("expr: " + exprStr);

      for (int z=0; z < exprStr.length(); z++)
      {
    	  if (Character.isLetter(exprStr.charAt(z)))
    		  justLiterals.add(exprStr.substring(z,z+1));
      }  

      //get the object of MinimalMUMCUT
      try{
      	mMUMCUT = new MinimalMUMCUT(exprStr, infeasibleLiterals, choice, maxTestSetSize.intValue());
      }
      catch(InvalidException e){
      	warning = e.getMessage();
      }
      
		if(warning != null)
			warning = "<font face=\"Garamond\" color=\"red\"><b>" + warning + "</b></font>";
		else
			warning = "";

		return warning;
	}
	
	private String printForm()
	{
		String result = ""
			+"<form name = \"minimalMUMCUTCoverageForm\" method=\"post\" action=\"MinimalMUMCUTCoverage\"> \n"
			+"<table border=\"1\" width=\"800\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\" align=\"center\"> \n"
			+	"<tr><td align=\"justify\">Please check that all literals are lower case letters or upper case letters (No combination of lower case letters and upper case letters allowed), preceded by an optional ' ! ' to denote negation.   \n"
			+		"Please check that literals are separated by ' & ' and terms are separated by ' | '. \n"
			+		"A correct example is : a & b | c & d. In the result, 0 represents FALSE and 1 represents TRUE; \n"
			+		"the order of 0s and 1s in the test points maps to the order of the variables entered in the predicate. For example, \n"
			+		"if the predicate is a & b, then 10 means a = 1 and b = 0. If the predicate is entered as b & a, then 10 means that b = 1 and a = 0. \n"
			+		"(Please be aware that this application gets slow for expressions that have more than 5 or 6 variables.)\n"
			+	"</td></tr> \n"
			+	"<tr><td> " 
			+		"<table width=\"100%\" border=\"0\"> \n"
			+			"<tr align=center><td align=right> Not :<font size=4> <b>!</b></font></td> \n"
			+				"<td width=\"20%\"> And :<font size=4> <b>&</b></font></td> \n"
			+				"<td align=left> Or :<font size=4> <b>|</b></font></td>" 
			+			"</tr> \n"
			+			"<tr>" 
			+   			"<td align=\"center\"  colspan=\"3\"><font face=\"Garamond\" size=\"3\"> P =  \n"
			+        		"<input type=\"text\" name=\"expression\" size=\"70\"";
			               	if(exprStr != null)
			               		result += " value = \"" + exprStr + "\"";
			               result += "></font>"
			+  			"</td>"
			+			"</tr> \n"
			+ 			"<tr></tr>"
			+		"</table> \n"
			+	"</td></tr>\n"
			+	"<tr><td align=\"justify\">"
			+  	"Please check that infeasible literal combinations are separated by semicolons and that literal assignments to 0 or 1 "
			+ 		"are separated by commas.  For example if a = 0 and b = 0 is infeasible and if c = 0 and d = 0 are infeasible, the format is "
			+     "a = 0, b = 0; c = 0, d = 0."
			+	"</td></tr> \n"
			+ 	"<tr></tr>"
			+ 	"<tr></tr>"
			+	"<tr><td> " 
			+		"<table width=\"100%\" border=\"0\"> \n"
			+			"<tr>" 
			+   			"<td align=\"center\"  colspan=\"3\"><font face=\"Garamond\" size=\"3\"> Enter infeasible literal combination (if any) in format a = 0, b = 0; c = 1, d = 1: </font><input type=\"text\" name=\"infeasibleLiterals\" size=\"70\"";
			               if(infeasibleLiterals != null)
			               	result += "value = \"" + infeasibleLiterals + "\"";
			               result += "></td>"
			+			"</tr> \n"
			+ 			"<tr></tr>"
			+ 			"<tr></tr>"
			+		"</table> \n"
			+	"</td></tr>\n"
			+	"<tr><td> " 
			+		"<table width=\"100%\" border=\"0\"> \n"
		   +			"<tr>" 
			+   			"<td align=\"center\"  colspan=\"3\"><font face=\"Garamond\" size=\"3\"> Enter a positive integer for the test set size (only for Fault Detection Maximization): </font><input type=\"text\" name=\"maxTestSetSize\" size=\"70\"";
			               if(maxTestSetSize != null && maxTestSetSize != -1)
			               	result += "value = \"" + maxTestSetSize + "\"";
			               result += "></td>"
			+			"</tr> \n"
			+			"<tr>"
			+				"<td align=\"right\"><a href = \"help.html\">Go to the help page</a><td>"
			+			"</tr> \n"
			+		"</table> \n"
			+	"</td></tr>\n";			
		
			result += ""	
			+"	<table width=\"1100\" align=\"center\"> \n"
			+"	<tr><td></tr> <tr><td></tr>\n"
			+"	<tr><td></tr> <tr><td></tr>\n" 
			+" <tr><td></tr> <tr><td></tr>\n"
			+"	<tr>\n" 
			+" <td align=right width = \"10%\">Criteria:</td>\n" 
			+"	<td width=\"90%\" colspan=\"3\">\n" 
			+"	<input type=\"submit\" value=\"MUTP Heuristic\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"CUTPNFP Heuristic\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"MNFP Heuristic\" name=\"action\">   \n"
			+"	&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT Heuristic\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT/SMOTP Heuristic\" name=\"action\"><br>\n"
			+"	&nbsp;<input type=\"submit\" value=\"NFP Heuristic\" name=\"action\"><br>\n"
			
			
			+"	<input type=\"submit\" value=\"MUTP Minimization\" name=\"action\"><br>\n"
			+"	&nbsp;<input type=\"submit\" value=\"CUTPNFP Minimization\" name=\"action\"><br> \n"
			+"	&nbsp;<input type=\"submit\" value=\"MNFP Minimization\" name=\"action\"><br>\n"
			+"	&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT Minimization\" name=\"action\"><br>\n"			
			+"	&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT/SMOTP Minimization\" name=\"action\"><br>\n"
			+"	&nbsp;<input type=\"submit\" value=\"NFP Minimization\" name=\"action\"><br>\n"

			
			+"	&nbsp;<input type=\"submit\" value=\"Fault Detection Maximization\" name=\"action\"> </td></tr>\n"
			+" <tr><td></tr> <tr><td></tr>\n"
			+"	<tr>\n" 
			+" <td align=right width = \"20%\" >Others:</td>\n" 
			+"	<td aligh=\"center\" width=\"80%\" colspan=\"3\">\n"
			+"	<input type=\"submit\" value=\"New Expression\" name=\"action\">" 
			+"	&nbsp;<input type=\"submit\" value=\"Graph Coverage\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"Data Flow Coverage\" name=\"action\"> \n"
			+"	&nbsp;<input type=\"submit\" value=\"Logic Coverage\" name=\"action\"></td></tr> \n"
			+"     </table> \n"
			+"     </form> \n";
		
		return result;
	}
	
	/**
	 * 
	 * @param data
	 * @return a String putting the corresponding data in a table for the coverage criteria
	 */
	private String printCoverage(String msg)
	{
		String s1[] = msg.split("\\n");
		String result = ""
			+ "<table id = \"tableCoverage\" border=0 width=\"100%\">\n";
		for(int i = 0;i < s1.length;i++){
			//System.out.println(i + s1[i]);
			result = result + "<tr><td width=\"50%\" valign=\"top\">" + s1[i] + " &nbsp;</td>\n</tr>";
		}
		result = result + "</table>\n";

		return result;
	}
 
	
	private String printResult(String msg, String table)
	{
		String result = ""
			+ "<table id = \"tableResult\" border=0 width=\"100%\">\n"
			+ "<tr><td width=\"50%\" valign=\"top\">" + msg + " &nbsp;</td>\n"
			+ "<td width=\"50%\" valign=\"top\"> " + table + "</td></tr></table>\n";

		return result;
	}
	
}
