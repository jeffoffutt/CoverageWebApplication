package coverage.test;

import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;

public class TempTest {
	@Test
    public void test(){
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
	        textAreaEdges.setText("1 2\n");
	        textFieldEndNodes.setValueAttribute("2");
	        textFieldInitialNodes.setValueAttribute("1");
	        try {
	        page = buttonNodes.click();
	        } catch (IOException e) {
	        e.printStackTrace();
	        }
	        assertTrue (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null);   
	}
}

