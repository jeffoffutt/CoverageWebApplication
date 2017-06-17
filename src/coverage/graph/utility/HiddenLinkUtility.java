package coverage.graph.utility;

public class HiddenLinkUtility
{
    /**
     * Private constructor: this is so clients do not
     * instantiate this class, but rather call it with
     * a static context
     */
    private HiddenLinkUtility()
    {
        
    }
    
    public static final String GRAPH_COVERAGE_LINK = "https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?";
    
    /**
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=1+2%0D%0A2+3%0D%0A1+3%0D%0A3+4%0D%0A3+5%0D%0A&
     * initialNode=1&
     * endNode=5&
     * action=Nodes
     * 
     * 
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=1+2%0D%0A2+3%0D%0A1+3%0D%0A3+4%0D%0A3+5%0D%0A&
     * initialNode=1&
     * endNode=5&
     * algorithm2=Edge-Pair%20Coverage
     * 
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=1+2%0D%0A2+3%0D%0A1+3%0D%0A3+4%0D%0A3+5%0D%0A&
     * initialNode=1+2+3&
     * endNode=5+4&
     * algorithm2=Edge-Pair%20Coverage
     * 
     *no edges
     *https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     *edges=&
     *initialNode=1+2+3&
     *endNode=5+4&
     *action=Nodes
     * 
     * empty
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=&
     * initialNode=&
     * endNode=&
     * action=Edges
     * 
     * @param edgesString
     * @param initialNodeString
     * @param endNodeString
     * @param actionString
     * @param algorithm2String
     * @return
     */
    public static String BuildHiddenLink(String edgesString, 
                                         String initialNodeString,
                                         String endNodeString,
                                         String actionString,
                                         String algorithm2String)
    {
        String edgeParameters             = BuildEdgesLinkString(edgesString);
        String initialNodeParameter       = BuildInitialNodeLinkString(initialNodeString);
        String endNodeParameter           = BuildEndNodeLinkString(endNodeString);
        String actionOrAlgorithmParameter = actionString != null ? BuildActionLinkString(actionString) 
                                                                 : algorithm2String != null ? BuildAlgorithmLinkString(algorithm2String) 
                                                                                            : "";
                                                                 
        String hiddenLink = GRAPH_COVERAGE_LINK + edgeParameters + initialNodeParameter + endNodeParameter + actionOrAlgorithmParameter;
        
        // if the last one is & or ?
        // trim it out
        if(hiddenLink.charAt(hiddenLink.length()-1)=='&')
        {
            hiddenLink = hiddenLink.substring(0, hiddenLink.length()-1);
        }
        
        return hiddenLink;               
    }
    
    
    private static String BuildAlgorithmLinkString(String algorithm2String)
    {
        String algorithm2ActionStr = algorithm2String.trim();
        algorithm2ActionStr = algorithm2String.replaceAll("\\s+", "%20");
        return "algorithm2=" + algorithm2ActionStr;
    }
    
    
    private static String BuildActionLinkString(String actionString)
    {
       // process the whitespace in action
        String actionStr = new String(actionString);
        actionStr = actionStr.trim();
        actionStr = actionStr.replaceAll("\\s+", "%20");
        return "action=" + actionStr;
    }
    
    
    private static String BuildEndNodeLinkString(String endNodeString)
    {
        String endNodeLink = "";
        if (endNodeString!=null)
        {
            // if more than one
            // add +
            endNodeString = endNodeString.trim();
            endNodeString = endNodeString.replaceAll("\\s+","+");
            endNodeLink = "endNode="+ endNodeString + "&";
        }
        return endNodeLink;
    }
    
    
    private static String BuildInitialNodeLinkString(String initalNodeString)
    {
        String initialNodeLink = "";
        if (initalNodeString!=null)
        {
            // if more than one
            // add +
            initalNodeString = initalNodeString.trim();
            initalNodeString = initalNodeString.replaceAll("\\s+","+");
            
            initialNodeLink = "initialNode="+ initalNodeString + "&";
        }
        
        return initialNodeLink;
    }
      
    
    private static String BuildEdgesLinkString(String edgesString)
    {
        // process edgesStr
        // edges=1+2%0D%0A1+3%0D%0A2+4%0D%0A3+4%0D%0A3+5%0D%0A4+5%0D%0A
        String edgesLink = "";
        
        if(edgesString != null)
        {
         // split edges
            String[] lines = edgesString.split("\\r?\\n");     
        
            for(String str : lines)
            {
                // little verification??                
                // replace white space to +
                str = str.trim();
                str = str.replaceAll("\\s+","+");
                edgesLink = edgesLink + str +"%0D%0A";
            }
            edgesLink = "edges=" + edgesLink + "&";
        }
        
        return edgesLink;
    }
}
