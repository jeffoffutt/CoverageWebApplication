package coverage.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import coverage.web.GraphCoverage;
import static org.easymock.EasyMock.*;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GraphCoverageTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void testMockObjects() {
		//initialize
		GraphCoverage gc = new GraphCoverage();
		File file = new File("test.html");
		try {
			file.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		PrintWriter writer = null;
		HttpServletRequest request = null;			
		HttpServletResponse response = null;
		
		//Nodes
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("Nodes");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		replay(request);
		replay(response);
		
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("tableResult"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //Prime Path Coverage
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("Prime Path Coverage");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<td>[1,2,3,4,2,3,4,2,6,7]</td><td>[3,4,2,6,7], [2,3,4,2], [1,2,3,4], [3,4,2,3], [4,2,3,4]</td>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //edges
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Edges");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("[1,2]<br>\n[1,5]<br>\n[2,3]<br>\n[3,4]<br>\n[4,2]<br>\n[2,6]<br>\n[5,5]<br>\n[5,7]<br>\n[6,7]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //edge-pairs
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Edge-Pair");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("1. [1,2,3]<br>\n2. [1,2,6]<br>\n3. [1,5,5]<br>\n4. [1,5,7]<br>\n5. [2,3,4]<br>\n6. [3,4,2]<br>\n7. [4,2,3]<br>\n8. [4,2,6]<br>\n9. [2,6,7]<br>\n10. [5,5,5]<br>\n11. [5,5,7]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //simple paths
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Simple Paths");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("[1,2]<br>\n[1,5]<br>\n[2,3]<br>\n[3,4]<br>\n[4,2]<br>\n[2,6]<br>\n[5,5]<br>\n[5,7]<br>\n[6,7]<br>\n[1,2,3]<br>\n[1,2,6]<br>\n[1,5,7]<br>\n[2,3,4]<br>\n[3,4,2]<br>\n[4,2,3]<br>\n[4,2,6]<br>\n[2,6,7]<br>\n[1,2,3,4]<br>\n[1,2,6,7]<br>\n[2,3,4,2]<br>\n[3,4,2,3]<br>\n[3,4,2,6]<br>\n[4,2,3,4]<br>\n[4,2,6,7]<br>\n[3,4,2,6,7]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //prime path
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Prime Paths");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("1. [3,4,2,6,7]<br>\n2. [2,3,4,2]<br>\n3. [1,2,6,7]<br>\n4. [1,2,3,4]<br>\n5. [3,4,2,3]<br>\n6. [4,2,3,4]<br>\n7. [1,5,7]<br>\n8. [5,5]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //node coverage
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Node Coverage");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("[1,5,7]<br>\n[1,2,3,4,2,6,7]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //edge coverage
		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		 request = createStrictMock(HttpServletRequest.class);
		 response = createStrictMock(HttpServletResponse.class);
		
    	expect(request.getParameter("action")).andReturn("Edge Coverage");
		expect(request.getParameter("initialNode")).andReturn("1");
		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
		expect(request.getParameter("endNode")).andReturn("7");	
		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
		
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
        try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("[1,5,5,7]<br>\n[1,2,3,4,2,6,7]<br>"));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //edge coverage
 		try {
 			writer = new PrintWriter(file);
 		} catch (FileNotFoundException e2) {
 			e2.printStackTrace();
 		}
 		 request = createStrictMock(HttpServletRequest.class);
 		 response = createStrictMock(HttpServletResponse.class);
 		
     	expect(request.getParameter("action")).andReturn("Edge-Pair Coverage");
 		expect(request.getParameter("initialNode")).andReturn("1");
 		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
 		expect(request.getParameter("endNode")).andReturn("7");	
 		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
 		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
 		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
 		
 		response.setContentType("text/html");
 		try {
 			expect(response.getWriter()).andReturn(writer);
 		} catch (IOException e1) {
 			e1.printStackTrace();
 		}
 		
 		replay(request);
 		replay(response);
 		
 		try {
 			gc.doPost(request, response);
 		} catch (ServletException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		verify(request);
 		verify(response);
 		writer.flush();
         try {
 			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
 			        .contains("<tr><td>[1,2,6,7]</td><td>[1,2,6], [2,6,7]</td><tr><td>[1,5,7]</td><td>[1,5,7]</td><tr><td>[1,2,3,4,2,3,4,2,6,7]</td><td>[1,2,3], [2,3,4], [3,4,2], [4,2,3], [4,2,6], [2,6,7]</td><tr><td>[1,5,5,5,7]</td><td>[1,5,5], [5,5,5], [5,5,7]</td>"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
        
         //prime path coverage
  		try {
 			writer = new PrintWriter(file);
 		} catch (FileNotFoundException e2) {
 			e2.printStackTrace();
 		}
 		 request = createStrictMock(HttpServletRequest.class);
 		 response = createStrictMock(HttpServletResponse.class);
 		
     	expect(request.getParameter("action")).andReturn("Prime Path Coverage");
 		expect(request.getParameter("initialNode")).andReturn("1");
 		expect(request.getParameter("edges")).andReturn("1 2\r1 5\r2 3\r3 4\r4 2\r2 6\r5 5\r5 7\r6 7");
 		expect(request.getParameter("endNode")).andReturn("7");	
 		expect(request.getParameter("infeasiblePrimePaths")).andReturn(null);
 		expect(request.getParameter("infeasibleEdgePairs")).andReturn(null);
 		expect(request.getParameter("infeasibleSubpaths")).andReturn(null);
 		
 		response.setContentType("text/html");
 		try {
 			expect(response.getWriter()).andReturn(writer);
 		} catch (IOException e1) {
 			e1.printStackTrace();
 		}
 		
 		replay(request);
 		replay(response);
 		
 		try {
 			gc.doPost(request, response);
 		} catch (ServletException e) {
 			e.printStackTrace();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
 		
 		verify(request);
 		verify(response);
 		writer.flush();
         try {
 			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
 			        .contains("<tr><td>[1,2,6,7]</td><td>[1,2,6,7]</td><tr><td>[1,2,3,4,2,3,4,2,6,7]</td><td>[3,4,2,6,7], [2,3,4,2], [1,2,3,4], [3,4,2,3], [4,2,3,4]</td><tr><td>[1,5,7]</td><td>[1,5,7]</td><tr><td>[1,5,5,7]</td><td>[5,5]</td>"));
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
         
        //new Graph
/*		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("New Graph");
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
       try {
			assertTrue(FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<input type=\"text\" name=\"initialNode\" size=\"5\" value=\"\">") && FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<input type=\"text\" name=\"endNode\" size=\"30\" value=\"\">") );
		} catch (IOException e) {
			e.printStackTrace();
		}
       
       //data flow coverage
	   	try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("Data Flow Coverage");
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			response.sendRedirect("DFGraphCoverage");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
	   try {
			assertTrue(!FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<input type=\"text\" name=\"initialNode\" size=\"5\" value=\"\">"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
       //logic coverage
	   	try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("Logic Coverage");
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			response.sendRedirect("LogicCoverage");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
	   try {
			assertTrue(!FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<input type=\"text\" name=\"initialNode\" size=\"5\" value=\"\">"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
	   //minimalMUMCUT
	   	try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		request = createStrictMock(HttpServletRequest.class);
		response = createStrictMock(HttpServletResponse.class);
		
		expect(request.getParameter("action")).andReturn("Minimal-MUMCUT Coverage");
		response.setContentType("text/html");
		try {
			expect(response.getWriter()).andReturn(writer);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			response.sendRedirect("MinimalMUMCUTCoverage");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		replay(request);
		replay(response);
		
		try {
			gc.doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		verify(request);
		verify(response);
		writer.flush();
	   try {
			assertTrue(!FileUtils.readFileToString(new File("test.html"), "UTF-8")
			        .contains("<input type=\"text\" name=\"initialNode\" size=\"5\" value=\"\">"));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	   
	}
	
	
	@Test
	public void test() {
		final WebClient webClient = new WebClient();
		WebRequest request = null;
		 try {
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/GraphCoverage" ), HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		HtmlPage page = null;
		try {
			page = webClient.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HtmlForm form = page.getFormByName("graphCoverageForm");
		assertEquals("graphCoverageForm", form.getNameAttribute());
		 
		final HtmlSubmitInput buttonNodes = form.getInputByValue("Nodes");
		assertEquals("action", buttonNodes.getNameAttribute());
		
		final HtmlSubmitInput buttonEdges = form.getInputByValue("Edges");
		
		final HtmlSubmitInput buttonEdgePairs = form.getInputByValue("Edge-Pair");
		
		final HtmlSubmitInput buttonSimplePaths = form.getInputByValue("Simple Paths");
		
		final HtmlSubmitInput buttonPrimePaths = form.getInputByValue("Prime Paths");
		
		final HtmlSubmitInput buttonNodeCoverage = form.getInputByValue("Node Coverage");
		
		final HtmlSubmitInput buttonEdgeCoverage = form.getInputByValue("Edge Coverage");
		
		final HtmlSubmitInput buttonEdgePairCoverage = form.getInputByValue("Edge-Pair Coverage");
		
		final HtmlSubmitInput buttonPrimePathCoverage = form.getInputByValue("Prime Path Coverage");
		
		final HtmlSubmitInput buttonNewGraph = form.getInputByValue("New Graph");
		
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonLogicCoverage = form.getInputByValue("Logic Coverage");
		final HtmlSubmitInput buttonMinimalMUMCUTCoverage = form.getInputByValue("Minimal-MUMCUT Coverage");
		
		final HtmlTextArea textAreaEdges = form.getTextAreaByName("edges");
		textAreaEdges.setText("1 2\n");
		assertEquals("1 2\n", textAreaEdges.getText());
		
		    
		final HtmlTextInput textFieldEndNodes = form.getInputByName("endNode");
		textFieldEndNodes.setValueAttribute("2");
		assertEquals("2", textFieldEndNodes.getValueAttribute());
		
		final HtmlTextInput textFieldInitialNodes = form.getInputByName("initialNode");
		textFieldInitialNodes.setValueAttribute("1");
		assertEquals("1", textFieldInitialNodes.getValueAttribute());
		    
		try {
			page = buttonNodes.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}

		assertEquals("1 2\n", page.getFormByName("graphCoverageForm").getTextAreaByName("edges").getText());
		final HtmlTable table = page.getHtmlElementById("tableResult");
		System.out.println(table.getCellAt(0, 0).asText());
		assertNotNull(((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText());
		assertEquals( true, (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
		
		try {
			page = buttonDataFlowCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		assertEquals("dataFlowCoverageForm", page.getFormByName("dataFlowCoverageForm").getNameAttribute());
		
	    /*	  
	    textFieldInitialNodes = pageNodes.getFormByName("graphCoverageForm").getInputByName("initialNode");
	    assertEquals("1", textFieldInitialNodes.getValueAttribute());
	    
	    for (final HtmlTableRow row : table.getRows()) {
	        System.out.println("Found row");
	        for (final HtmlTableCell cell : row.getCells()) {
	            System.out.println("   Found cell: " + cell.asText());
	        }
	    }*/
	}
	@Test
	public void test1(){
		//initialize
		final WebClient webClient = new WebClient();
		WebRequest request = null;
		try {
		    request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/GraphCoverage"), HttpMethod.POST);
		} catch (MalformedURLException e) {
		e.printStackTrace();
		}
				 
		HtmlPage page = null;
		try {
		    page = webClient.getPage(request);
		} catch (FailingHttpStatusCodeException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
				
		HtmlForm form = page.getFormByName("graphCoverageForm");
				 
		final HtmlSubmitInput buttonNodes = form.getInputByValue("Nodes");
				
		final HtmlSubmitInput buttonEdges = form.getInputByValue("Edges");
				
		final HtmlSubmitInput buttonEdgePairs = form.getInputByValue("Edge-Pair");
				
		final HtmlSubmitInput buttonSimplePaths = form.getInputByValue("Simple Paths");
				
		final HtmlSubmitInput buttonPrimePaths = form.getInputByValue("Prime Paths");
					
		final HtmlSubmitInput buttonNodeCoverage = form.getInputByValue("Node Coverage");
				
		final HtmlSubmitInput buttonEdgeCoverage = form.getInputByValue("Edge Coverage");
				
		final HtmlSubmitInput buttonEdgePairCoverage = form.getInputByValue("Edge-Pair Coverage");
				
		final HtmlSubmitInput buttonPrimePathCoverage = form.getInputByValue("Prime Path Coverage");
				
		final HtmlSubmitInput buttonNewGraph = form.getInputByValue("New Graph");
				
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonLogicCoverage = form.getInputByValue("Logic Coverage");
		final HtmlSubmitInput buttonMinimalMUMCUTCoverage = form.getInputByValue("Minimal-MUMCUT Coverage");
				
		final HtmlTextArea textAreaEdges = form.getTextAreaByName("edges");
		final HtmlTextInput textFieldEndNodes = form.getInputByName("endNode");
		final HtmlTextInput textFieldInitialNodes = form.getInputByName("initialNode");
				
		final HtmlTable table = null;
		
		//enterGraph
		textAreaEdges.setText("1 2\n");	
		textFieldEndNodes.setValueAttribute("2");
		textFieldInitialNodes.setValueAttribute("1");
		
		//updateGraph
		textAreaEdges.setText("1 2\n1 3\n");	
		textFieldEndNodes.setValueAttribute("2 3");
		textFieldInitialNodes.setValueAttribute("1");
		
		//newGraph
		try {
			page = buttonNewGraph.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//dataFlow
		try {
			page = buttonDataFlowCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//logic
		try {
			page = buttonLogicCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//minimalMUMCUT
		try {
			page = buttonMinimalMUMCUTCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//edge
		try {
			page = buttonEdges.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//node
		try {
			page = buttonNodes.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//edgePair
		try {
			page = buttonEdgePairs.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//simplePath
		try {
			page = buttonSimplePaths.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//primePath
		try {
			page = buttonPrimePaths.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//edgeCoverage
		try {
			page = buttonEdgeCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//nodeCoverage
		try {
			page = buttonNodeCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//primePathCoverage
		try {
			page = buttonPrimePathCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//edgePairCoverage
		try {
			page = buttonEdgePairCoverage.click();
		} catch (IOException e) {
		e.printStackTrace();
		}
		
		//ConstraintResultNotNull
		assertTrue((((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
	} 

}
