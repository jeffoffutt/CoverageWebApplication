package coverage.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataFlowCoverageTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final WebClient webClient = new WebClient();
		WebRequest request = null;
		 try {
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/DFGraphCoverage" ), HttpMethod.POST);
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
		
		HtmlForm form = page.getFormByName("dataFlowCoverageForm");
		assertEquals("dataFlowCoverageForm", form.getNameAttribute());
		 
		final HtmlSubmitInput buttonDuPairs = form.getInputByValue("DU Pairs");
		assertEquals("action", buttonDuPairs.getNameAttribute());
		
		final HtmlSubmitInput buttonDuPaths = form.getInputByValue("DU Paths");
		
		final HtmlSubmitInput buttonAllDefCoverage = form.getInputByValue("All Def Coverage");
		
		final HtmlSubmitInput buttonAllUseCoverage = form.getInputByValue("All Use Coverage");
		
		final HtmlSubmitInput buttonAllDuPathCoverage = form.getInputByValue("All DU Path Coverage");
		
		final HtmlSubmitInput buttonNewGraph = form.getInputByValue("New Graph");
		
		final HtmlSubmitInput buttonNewDuInfo = form.getInputByValue("New DU Info");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
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
		
		final HtmlTextArea textAreaDefs = form.getTextAreaByName("defs");
		textAreaDefs.setText("x 1\n");
		assertEquals("x 1\n", textAreaDefs.getText());
		
		final HtmlTextArea textAreaUses = form.getTextAreaByName("uses");
		textAreaUses.setText("x 2\n");
		assertEquals("x 2\n", textAreaUses.getText());
		
		try {
			page = buttonNewDuInfo.click();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			page = buttonDuPairs.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}

		assertEquals("1 2\n", page.getFormByName("dataFlowCoverageForm").getTextAreaByName("edges").getText());
		final HtmlTable table = page.getHtmlElementById("tableResult");
		System.out.println(table.getCellAt(0, 0).asText());
		assertNotNull(((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText());
		assertEquals( true, (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
				
		try {
			page = buttonGraphCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		assertEquals("graphCoverageForm", page.getFormByName("graphCoverageForm").getNameAttribute());
		
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
	public void testMappings(){
		//initialize
		final WebClient webClient = new WebClient();
		WebRequest request = null;
		 try {
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/DFGraphCoverage" ), HttpMethod.POST);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
		
		HtmlForm form = page.getFormByName("dataFlowCoverageForm");
		 
		final HtmlSubmitInput buttonDuPairs = form.getInputByValue("DU Pairs");
		
		final HtmlSubmitInput buttonDuPaths = form.getInputByValue("DU Paths");
		
		final HtmlSubmitInput buttonAllDefCoverage = form.getInputByValue("All Def Coverage");
		
		final HtmlSubmitInput buttonAllUseCoverage = form.getInputByValue("All Use Coverage");
		
		final HtmlSubmitInput buttonAllDuPathCoverage = form.getInputByValue("All DU Path Coverage");
		
		final HtmlSubmitInput buttonNewGraph = form.getInputByValue("New Graph");
		final HtmlSubmitInput buttonNewDuInfo = form.getInputByValue("New DU Info");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
		final HtmlSubmitInput buttonLogicCoverage = form.getInputByValue("Logic Coverage");
		final HtmlSubmitInput buttonMinimalMUMCUTCoverage = form.getInputByValue("Minimal-MUMCUT Coverage");
		final HtmlTextArea textAreaEdges = form.getTextAreaByName("edges");
		final HtmlTextInput textFieldEndNodes = form.getInputByName("endNode");
		final HtmlTextInput textFieldInitialNodes = form.getInputByName("initialNode");
		final HtmlTextArea textAreaDefs = form.getTextAreaByName("defs");
		final HtmlTextArea textAreaUses = form.getTextAreaByName("uses");
		
		//enterGraph
		textAreaEdges.setText("1 2\n");	
		textFieldEndNodes.setValueAttribute("2");
		textFieldInitialNodes.setValueAttribute("1");
		
		//updateGraph
		textAreaEdges.setText("1 2\n1 3\n");	
		textFieldEndNodes.setValueAttribute("2 3");
		textFieldInitialNodes.setValueAttribute("1");
		
		//newDataFlow
		try {
			page = buttonNewDuInfo.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//duPair
		try {
			page = buttonDuPairs.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//duPath
		try {
			page = buttonDuPaths.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//allDefCoverage
		try {
			page = buttonAllDefCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//updateDataFlow
		textAreaDefs.setText("x 1\n");
		textAreaUses.setText("x 2\nx 3\n");
		
		//enterDataFlow
		textAreaDefs.setText("x 1\n");
		textAreaUses.setText("x 2\n");
		
		//allDuPathCoverage
		try {
			page = buttonAllDuPathCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//allUseCoverage
		try {
			page = buttonAllUseCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//newGraph
		try {
			page = buttonNewGraph.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//graphCoverage
		try {
			page = buttonGraphCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//minimalMUMCUTCoverage
		try {
			page = buttonMinimalMUMCUTCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//logicCoverage
		try {
			page = buttonLogicCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//ConstraintResultNotNull
		assertTrue((((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
	}

}
