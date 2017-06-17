package coverage.test.graph.utility;

import static org.junit.Assert.*;

import java.io.IOException;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import coverage.graph.utility.HiddenLinkUtility;

public class HiddenLinkUtilityTest
{
    @Test
    public void TestEmptyGraph() throws FailingHttpStatusCodeException, IOException
    {  
        
        String edgesString = null;
        String initialNodeString = null;
        String endNodeString = null;
        String actionString = null;
        String algorithm2String= null;
        
        
        String hiddenLink = HiddenLinkUtility.BuildHiddenLink(edgesString, 
                                                              initialNodeString, 
                                                              endNodeString, 
                                                              actionString, 
                                                              algorithm2String);
        
        assertEquals("https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?", hiddenLink);
    
    }
    
    @Test
    public void ActionStringTest() throws FailingHttpStatusCodeException, IOException
    {  
        
        String edgesString = "1 3\r\n3 4\r\n4 5\r\n2 3";
        String initialNodeString = "1 2";
        String endNodeString = "4 5";
        String actionString = "Node Coverage";
        String algorithm2String= null;
        
        
        String hiddenLink = HiddenLinkUtility.BuildHiddenLink(edgesString, 
                                                              initialNodeString, 
                                                              endNodeString, 
                                                              actionString, 
                                                              algorithm2String);
        
        assertEquals("https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?edges=1+3%0D%0A3+4%0D%0A4+5%0D%0A2+3%0D%0A&initialNode=1+2&endNode=4+5&action=Node%20Coverage", 
                     hiddenLink);
    }

    @Test
    public void EmptyEdgesGraph() throws FailingHttpStatusCodeException, IOException
    {  
        
        String edgesString = "";
        String initialNodeString = "1 2 3";
        String endNodeString = "4 5 6";
        String actionString = null;
        String algorithm2String= "Edge Coverage";
        
        
        String hiddenLink = HiddenLinkUtility.BuildHiddenLink(edgesString, 
                                                              initialNodeString, 
                                                              endNodeString, 
                                                              actionString, 
                                                              algorithm2String);
        
        assertEquals("https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?edges=%0D%0A&initialNode=1+2+3&endNode=4+5+6&algorithm2=Edge%20Coverage", 
                     hiddenLink);
    }
    
    @Test
    public void Algorithm2Test() throws FailingHttpStatusCodeException, IOException
    {  
        
        String edgesString = "";
        String initialNodeString = "";
        String endNodeString = "4 5";
        String actionString = null;
        String algorithm2String= "Prime Path Coverage";
        
        
        
        String hiddenLink = HiddenLinkUtility.BuildHiddenLink(edgesString, 
                                                              initialNodeString, 
                                                              endNodeString, 
                                                              actionString, 
                                                              algorithm2String);
        
        assertEquals("https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?edges=%0D%0A&initialNode=&endNode=4+5&algorithm2=Prime%20Path%20Coverage", 
                     hiddenLink);
    }
    
}
