package coverage.web.controlflow;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.drgarbage.asm.ClassReader;
import com.drgarbage.asm.render.intf.IMethodSection;
import com.drgarbage.asm.visitor.FilteringCodeVisitor;
import com.drgarbage.asm.visitor.MethodFilteringVisitor;
import com.drgarbage.controlflowgraph.ControlFlowGraphException;
import com.drgarbage.visualgraphic.model.Connection;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;
import com.drgarbage.visualgraphic.model.VertexBase;

import coverage.web.controlflow.diagram.ClassFile;
import coverage.web.controlflow.diagram.ControlFlowDiagramGraphFactory;

public class ControlFlowUtility
{
    
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
    public static FilteringCodeVisitor getInstructionList(FileItem file,
                                                          String   methodName, 
                                                          String   methodSig) throws ControlFlowGraphException, IOException 
    {
           try(InputStream fileInputStream = file.getInputStream())
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
    
    /***
     * Creates a Map where the key is the method name and descriptor (what gets displayed in the method drop down menu)
     * and the value is the ControlFlowGraphDiagram that the method is associated with
     * @param file the java .class file
     * @param request the request to pull the content type
     * @return Map where the key is the method name and descriptor (what gets displayed in the method drop down menu)
     * and the value is the ControlFlowGraphDiagram that the method is associated with
     */
    public static Map<String, ControlFlowGraphDiagram> createControlFlowGraphMap(FileItem file, HttpServletRequest request)
    {
        Map<String, ControlFlowGraphDiagram> graphMap = new HashMap<String, ControlFlowGraphDiagram>();
        String contentType = request.getContentType();
        if(contentType == null || contentType.indexOf("multipart/form-data") == -1 || file.getSize() <= 0)
        {
            return graphMap;
        }
            
        try (InputStream inputStream = file.getInputStream()) 
        {
            ClassFile classDoc = ClassFile.readClass(inputStream);
            for(IMethodSection method : classDoc.getMethodSections())
            {
                String methodKey = String.format("Name: %s Descriptor: %s", method.getName(), method.getDescriptor());
                
                FilteringCodeVisitor methodInstructions = ControlFlowUtility.getInstructionList(file, method.getName(), method.getDescriptor());
                graphMap.put(methodKey, ControlFlowDiagramGraphFactory.buildBasicblockGraphDiagram(methodInstructions.getInstructions()));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ControlFlowGraphException e)
        {
            e.printStackTrace();
        }
        catch(Exception e) //we dont want the web page to crash so in case of any invalid files
        {
            e.printStackTrace();
        }
        
        return graphMap;
    }


    /***
     * Returns a list of connections given the vertices of the control flow graph
     * @param vertices the vertices in the graph
     * @return a list of connections in the graph
     */
    public static List<Connection> getEdges(List<VertexBase> vertices)
    {
        List<Connection> connections = new ArrayList<Connection>();
        Iterator<VertexBase> it = vertices.iterator();
        VertexBase vb = null;
        while(it.hasNext())
        {
            vb = it.next();         
            /* build connection list */
            List<Connection> targetConnection = vb.getTargetConnections();
            Iterator<Connection> itTargetConnections = targetConnection.iterator();
            while(itTargetConnections.hasNext())
            {
                connections.add(itTargetConnections.next());
            }
        }
        
        return connections;
    }
}
