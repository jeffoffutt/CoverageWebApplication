package coverage.web.controlflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.drgarbage.asm.ClassReader;
import com.drgarbage.asm.visitor.FilteringCodeVisitor;
import com.drgarbage.asm.visitor.MethodFilteringVisitor;
import com.drgarbage.controlflowgraph.ControlFlowGraphException;
import com.drgarbage.visualgraphic.model.Connection;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;
import com.drgarbage.visualgraphic.model.VertexBase;

import coverage.graph.utility.HiddenLinkUtility;
import coverage.web.controlflow.diagram.ClassFile;
import coverage.web.controlflow.diagram.ControlFlowDiagramGraphFactory;

public class ControlFlowUtility
{

    public static ControlFlowGraphDiagram exportJavaFile(Path javaFileLocation) throws FileNotFoundException, IOException, ControlFlowGraphException
    {
        File file = javaFileLocation.toFile();

        try (FileInputStream inputStream = new FileInputStream(file))
        {
            ClassFile classDoc = ClassFile.readClass(inputStream);
            // TODO allow user to select the method

            FilteringCodeVisitor methodInstructions = getInstructionList(file.getAbsolutePath(),
                    classDoc.getMethodSections().get(1).getName(), classDoc.getMethodSections().get(1).getDescriptor());

            ControlFlowGraphDiagram diagramFromFile = ControlFlowDiagramGraphFactory
                    .buildBasicblockGraphDiagram(methodInstructions.getInstructions());

            return diagramFromFile;
        }
    }
    
    /***
     * Returns the instructions for a given method in a java .class file
     * 
     * @param filePath  The ".class" absolute path
     * @param methodName  the name of the method
     * @param methodSig the method signature (i.e. a method with a signature like "public static float abs(float a)" the signature would be"(F)F")
     * @return filtering code visitor
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    public static FilteringCodeVisitor getInstructionList(String filePath,
                                                           String methodName, 
                                                           String methodSig) throws ControlFlowGraphException, IOException 
    {
        try(InputStream fileInputStream = new FileInputStream(filePath))
        {
            FilteringCodeVisitor   codeVisitor  = new FilteringCodeVisitor(methodName,  methodSig);     
            MethodFilteringVisitor classVisitor = new MethodFilteringVisitor(codeVisitor);
            ClassReader            classReader  = new ClassReader(fileInputStream, classVisitor);
            
            classReader.accept(classVisitor, 0);
            
            if (codeVisitor.getInstructions() == null) 
            {
                throw new ControlFlowGraphException("ControlFlowGraphGenerator: can't get method info of the " + methodName + methodSig);

            }

            return codeVisitor;
        }       
    }

    public String convertDiagramToLink(ControlFlowGraphDiagram diagram)
    {
        String endNodeString = "";
        String edgesString = "";
        String initialNodeString = "";
        String actionString = "Nodes";
        String algorithm2String = "";
        // initial and end nodes
        for (VertexBase vertex : diagram.getChildren())
        {
            String label = vertex.getLabel();
            if (label.equals("START"))
            {
                initialNodeString += vertex.getId() + " ";
            }
            else if (label.equals("EXIT"))
            {
                endNodeString += vertex.getId() + " ";
            }
        }
        // edges
        for (Connection edge : getEdges(diagram.getChildren()))
        {
            edgesString += String.format("%d %d\n", edge.getSource().getId(), edge.getTarget().getId());
        }

        // https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?edges=1+2%0D%0A2+3%0D%0A2+4%0D%0A3+5%0D%0A4+5%0D%0A&initialNode=1&endNode=5&action=Node%20Coverage
        return HiddenLinkUtility.BuildHiddenLink(edgesString, initialNodeString, endNodeString, actionString,
                algorithm2String);
    }

    private static List<Connection> getEdges(List<VertexBase> vertices)
    {
        List<Connection> connections = new ArrayList<Connection>();
        Iterator<VertexBase> it = vertices.iterator();
        VertexBase vb = null;
        while (it.hasNext())
        {
            vb = it.next();
            /* build connection list */
            List<Connection> targetConnection = vb.getTargetConnections();
            Iterator<Connection> itTargetConnections = targetConnection.iterator();
            while (itTargetConnections.hasNext())
            {
                connections.add(itTargetConnections.next());
            }
        }

        return connections;
    }
}
