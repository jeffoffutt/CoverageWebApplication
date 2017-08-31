package coverage.web;

import java.util.ArrayList;
import java.util.List;

import coverage.web.controlflow.diagram.WebControlFlowGraphDiagram;

public final class GraphCoverageInput
{
    private final String edges;
    private final String initialNode;
    private final String endNode;
    private final List<String> methods;
    private final String selectedMethod;
    private final String hiddenLink;
    private final boolean showShareButton;
    private final WebControlFlowGraphDiagram webControlFlowDiagram;
                                           
    public GraphCoverageInput(String                     edges, 
                              String                     initialNode, 
                              String                     endNode, 
                              List<String>               methods, 
                              String                     selectedMethod,
                              String                     hiddenLink,
                              boolean                    showShareButton,
                              WebControlFlowGraphDiagram selectedDiagram)
    {
        this.edges                 = edges;
        this.initialNode           = initialNode;
        this.endNode               = endNode;
        this.methods               = new ArrayList<String>(methods);
        this.selectedMethod        = selectedMethod;
        this.hiddenLink            = hiddenLink;
        this.showShareButton       = showShareButton;
        this.webControlFlowDiagram = selectedDiagram;
    }
    
    public List<String> getMethods()
    {
        return new ArrayList<String>(this.methods);
    }
    
    public String getEdges()
    {
        return this.edges;
    }
    
    public String getInitialNode()
    {
        return this.initialNode;
    }
    
    public String getEndNode()
    {
        return this.endNode;
    }
    
    public String getSelectedMethod()
    {
        return this.selectedMethod;
    }
    
    public String getHiddenLink()
    {
        return this.hiddenLink;
    }
    
    public boolean getShowShareButton()
    {
        return this.showShareButton;
    }

    public WebControlFlowGraphDiagram getDiagram()
    {
        return this.webControlFlowDiagram;
    }
}
