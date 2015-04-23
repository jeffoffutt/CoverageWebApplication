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

public class MinimalMUMCUTCoverageTest {

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
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/MinimalMUMCUTCoverage" ), HttpMethod.POST);
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
		
		HtmlForm form = page.getFormByName("minimalMUMCUTCoverageForm");
		assertEquals("minimalMUMCUTCoverageForm", form.getNameAttribute());

		final HtmlSubmitInput buttonMUTPH = form.getInputByValue("MUTP Heuristic");
		assertEquals("action", buttonMUTPH.getNameAttribute());
		
		final HtmlSubmitInput buttonFDM = form.getInputByValue("Fault Detection Maximization");
		assertEquals("action", buttonFDM.getNameAttribute());
		
		final HtmlSubmitInput buttonNewExpression = form.getInputByValue("New Expression");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonLogicCoverage = form.getInputByValue("Logic Coverage");
		
		final HtmlTextInput textInputExpression = form.getInputByName("expression");
		final HtmlTextInput textInputInfeasibleLiterals = form.getInputByName("infeasibleLiterals");
		final HtmlTextInput textInputMaxTestSetSize = form.getInputByName("maxTestSetSize");
		
		textInputExpression.setText("a && b");
		assertEquals("a && b", textInputExpression.getText());
		
		try {
			page = buttonMUTPH.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		assertEquals("a && b", page.getFormByName("minimalMUMCUTCoverageForm").getInputByName("expression").getValueAttribute());
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
		System.out.println(((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() );
		
		textInputExpression.setText("a | b");
		assertEquals("a | b", textInputExpression.getText());
		
		textInputInfeasibleLiterals.setText("a = 0");
		try {
			page = buttonMUTPH.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//throws exceptions
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
				
		textInputInfeasibleLiterals.setText("a=1,b=1");
		try {
			page = buttonMUTPH.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableCoverage")).getCellAt(0, 0).asText() != null));
		
		
		textInputInfeasibleLiterals.setText("");
		textInputMaxTestSetSize.setText("1");
		try {
			page = buttonFDM.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableCoverage")).getCellAt(0, 0).asText() != null));
		
		 textInputExpression.setText("a & b"); textInputInfeasibleLiterals.setText("");
	        textInputMaxTestSetSize.setText("-1");
	        try {
	        page = buttonMUTPH.click();
	        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        }
	        
		try {
			page = buttonGraphCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		assertEquals("graphCoverageForm", page.getFormByName("graphCoverageForm").getNameAttribute());
		
	}
	@Test
	public void testMappings(){
		//initialize
		final WebClient webClient = new WebClient();
		WebRequest request = null;
		 try {
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/MinimalMUMCUTCoverage" ), HttpMethod.POST);
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
		
		HtmlForm form = page.getFormByName("minimalMUMCUTCoverageForm");

		final HtmlSubmitInput buttonMUTPH = form.getInputByValue("MUTP Heuristic");
		
		final HtmlSubmitInput buttonFDM = form.getInputByValue("Fault Detection Maximization");
		
		final HtmlSubmitInput buttonNewExpression = form.getInputByValue("New Expression");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonLogicCoverage = form.getInputByValue("Logic Coverage");
		
		final HtmlTextInput textInputExpression = form.getInputByName("expression");
		final HtmlTextInput textInputInfeasibleLiterals = form.getInputByName("infeasibleLiterals");
		final HtmlTextInput textInputMaxTestSetSize = form.getInputByName("maxTestSetSize");
		
		//enterWrongExpression
		textInputExpression.setText("a && b");textInputInfeasibleLiterals.setText("");
		
		//enterExpressionAndWrongLiterals
		textInputExpression.setText("a | b");
		textInputInfeasibleLiterals.setText("a = 0");
		
		//enterExpressionAndLiterals
		textInputExpression.setText("a | b"); 
		textInputMaxTestSetSize.setText("");
		textInputInfeasibleLiterals.setText("a=1,b=1");
		
		//mUTPH
		try {
			page = buttonMUTPH.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//newExpression
		try {
			page = buttonNewExpression.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//fDM
		try {
			page = buttonFDM.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//logic
		try {
			page = buttonLogicCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//dataFlow
		try {
			page = buttonDataFlowCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//graph
		try {
			page = buttonGraphCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//enterWrongInteger
		textInputMaxTestSetSize.setText("-1");
		
		//enterInteger
		textInputMaxTestSetSize.setText("1");
		
		//clearInteger
		textInputMaxTestSetSize.setText("");
		
		//ConstraintExceptionNotNull
		assertTrue((((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
	        
    }

}
