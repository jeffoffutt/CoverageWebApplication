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
    
    /***
     * The base url for the graph coverage location
     */
    public static final String GRAPH_COVERAGE_LINK = "https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?";
    
    /**
     * Creates the link that can share the graph to another
     * user.  Here are some examples:
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
     * no edges
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=&
     * initialNode=1+2+3&
     * endNode=5+4&
     * action=Nodes
     * 
     * empty
     * https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?
     * edges=&
     * initialNode=&
     * endNode=&
     * action=Edges
     * 
     * @param edgesString Edges that are in the patter of letters/numbers + space + letters/number + space + letters/numbers + next line (repeat for each edge)
     * the first letters/numbers is the source node, the next is the destination node, the last is the label for the edge
     * @param initialNodeString Nodes that are separated by spaces
     * @param endNodeString Nodes that are separated by spaces
     * @param actionString the action string (value/input from the button)
     * @param algorithm2String the algorithm2 string (value/input from the button)
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
    
    /***
     * Builds the algorithm/action portion of the string
     * @param algorithm2String algorithm 2 string
     * @return the section of the algorithm2 value in the link
     */
    private static String BuildAlgorithmLinkString(String algorithm2String)
    {
        String algorithm2ActionStr = algorithm2String.trim();
        algorithm2ActionStr = algorithm2String.replaceAll("\\s+", "%20");
        return "algorithm2=" + algorithm2ActionStr;
    }
    
    /***
     * Builds the algorithm/action portion of the string
     * @param actionString action string (button value)
     * @return the section of the action value in the link
     */
    private static String BuildActionLinkString(String actionString)
    {
       // process the whitespace in action
        String actionStr = new String(actionString);
        actionStr = actionStr.trim();
        actionStr = actionStr.replaceAll("\\s+", "%20");
        return "action=" + actionStr;
    }
    
    /***
     * Builds the endNode value of the hidden link
     * @param endNodeString end nodes
     * @return the end node values in the link
     */
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
    
    /***
     * Builds the initial Node value of the hidden link
     * @param initialNodeString initial nodes
     * @return the initial node values in the link
     */
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
      
    /***
     * Edges portion of the string
     * @param edgesString the edges pulled from the text box value
     * @return the edges in a link form
     */
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
