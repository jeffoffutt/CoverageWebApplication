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

public class LogicCoverageTest {

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
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/LogicCoverage" ), HttpMethod.POST);
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
		
		HtmlForm form = page.getFormByName("logicCoverageForm");
		assertEquals("logicCoverageForm", form.getNameAttribute());
		 
		final HtmlSubmitInput buttonTruthTable = form.getInputByValue("Truth Table");
		assertEquals("action", buttonTruthTable.getNameAttribute());
		
		final HtmlSubmitInput buttonGacc = form.getInputByValue("GACC");
		
		final HtmlSubmitInput buttonCacc = form.getInputByValue("CACC");
		
		final HtmlSubmitInput buttonRacc = form.getInputByValue("RACC");
		
		final HtmlSubmitInput buttonGicc = form.getInputByValue("GICC");
		
		final HtmlSubmitInput buttonNewExpression = form.getInputByValue("New Expression");
		
		final HtmlSubmitInput buttonRicc = form.getInputByValue("RICC");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonMinimalMUMCUTCoverage = form.getInputByValue("Minimal-MUMCUT Coverage");
		
		final HtmlTextInput textInputExpression = form.getInputByName("expression");
		textInputExpression.setText("a && b");
		assertEquals("a && b", textInputExpression.getText());
		
		try {
			page = buttonTruthTable.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}

		assertEquals("a && b", page.getFormByName("logicCoverageForm").getInputByName("expression").getValueAttribute());
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableException")).getCellAt(0, 0).asText() != null));
		System.out.println(((HtmlTable) page.getHtmlElementById("tableException")).getCellAt(0, 0).asText() );
		
		textInputExpression.setText("a & b");
		assertEquals("a & b", textInputExpression.getText());
		
		try {
			page = buttonGacc.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		assertEquals(true, (((HtmlTable) page.getHtmlElementById("tableException")).getCellAt(0, 0).asText() != null));
		final HtmlTable table = page.getHtmlElementById("tableResult");
		System.out.println(table.getCellAt(0, 0).asText());
		assertEquals( true, (((HtmlTable) page.getHtmlElementById("tableResult")).getCellAt(0, 0).asText() != null));
				
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
			request = new WebRequest( new URL("http://localhost:8080/CoverageWebApplication/coverage/LogicCoverage" ), HttpMethod.POST);
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
		
		HtmlForm form = page.getFormByName("logicCoverageForm");
		assertEquals("logicCoverageForm", form.getNameAttribute());
		 
		final HtmlSubmitInput buttonTruthTable = form.getInputByValue("Truth Table");
		assertEquals("action", buttonTruthTable.getNameAttribute());
		
		final HtmlSubmitInput buttonGacc = form.getInputByValue("GACC");
		
		final HtmlSubmitInput buttonCacc = form.getInputByValue("CACC");
		
		final HtmlSubmitInput buttonRacc = form.getInputByValue("RACC");
		
		final HtmlSubmitInput buttonGicc = form.getInputByValue("GICC");
		
		final HtmlSubmitInput buttonNewExpression = form.getInputByValue("New Expression");
		
		final HtmlSubmitInput buttonRicc = form.getInputByValue("RICC");
		
		final HtmlTextInput textInputExpression = form.getInputByName("expression");
		
		final HtmlSubmitInput buttonGraphCoverage = form.getInputByValue("Graph Coverage");
		final HtmlSubmitInput buttonDataFlowCoverage = form.getInputByValue("Data Flow Coverage");
		final HtmlSubmitInput buttonMinimalMUMCUTCoverage = form.getInputByValue("Minimal-MUMCUT Coverage");
		
		//enterExpression
		textInputExpression.setText("a & b");
		
		//truthTable
		try {
			page = buttonTruthTable.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//gacc
		try {
			page = buttonGacc.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//cacc
		try {
			page = buttonCacc.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//racc
		try {
			page = buttonRacc.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//gicc
		try {
			page = buttonGicc.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//ricc
		try {
			page = buttonRicc.click();
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
		
		//graph
		try {
			page = buttonGraphCoverage.click();
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
		
		//minimalMUMCUT
		try {
			page = buttonMinimalMUMCUTCoverage.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
		//trOrT
		try {
			page = buttonGacc.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//enterWrongeExpression
		textInputExpression.setText("a && b");
		
		//ConstraintExceptionNotNull
		assertTrue((((HtmlTable) page.getHtmlElementById("tableException")).getCellAt(0, 0).asText() != null));
	}

}
