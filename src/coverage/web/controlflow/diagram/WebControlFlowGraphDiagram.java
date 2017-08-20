package coverage.web.controlflow.diagram;

import java.util.ArrayList;
import java.util.List;

import com.drgarbage.bytecode.LineNumberTableEntry;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;

public final class WebControlFlowGraphDiagram
{
    private final ArrayList<LineNumberTableEntry> lineNumberTable;
    private final String methodDescription;
    private final ControlFlowGraphDiagram diagram;

    public WebControlFlowGraphDiagram(ControlFlowGraphDiagram controlFlowGraphDiagram, LineNumberTableEntry[] lineNumberTable, String methodDescription)
    {
        this.lineNumberTable = new ArrayList<LineNumberTableEntry>();
        for(LineNumberTableEntry entry : lineNumberTable)
        {
            this.lineNumberTable.add(entry);
        }        
        
        this.methodDescription = methodDescription;
        this.diagram = controlFlowGraphDiagram;
    }
    
    
    public List<LineNumberTableEntry> getLineNumberTable()
    {
        return new ArrayList<LineNumberTableEntry>(this.lineNumberTable);
    }


    public String getMethodDescription()
    {
        return methodDescription;
    }


    public ControlFlowGraphDiagram getDiagram()
    {
        return diagram;
    }

}
