package coverage.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.drgarbage.asm.ClassReader;
import com.drgarbage.asm.render.intf.IMethodSection;
import com.drgarbage.asm.visitor.FilteringCodeVisitor;
import com.drgarbage.asm.visitor.MethodFilteringVisitor;
import com.drgarbage.controlflowgraph.ControlFlowGraphException;
import com.drgarbage.visualgraphic.model.Connection;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;
import com.drgarbage.visualgraphic.model.VertexBase;

import coverage.graph.Graph;
import coverage.graph.GraphUtil;
import coverage.graph.InvalidGraphException;
import coverage.graph.Node;
import coverage.graph.Path;
import coverage.graph.utility.HiddenLinkUtility;
import coverage.web.controlflow.diagram.ClassFile;
import coverage.web.controlflow.diagram.ControlFlowDiagramGraphFactory;
import coverage.web.enums.GraphInput;
import coverage.web.enums.OtherTools;
import coverage.web.enums.TestPaths;
import coverage.web.enums.TestRequirements;

/**
 * @author Wuzhi Xu
 * 
 *         Date: Dec 15, 2006
 * 
 *         Modified by Nan Li Date: Feb, 2009
 * 
 *         Modified by Lin Deng, for replacing applet with JavaScript graph
 *         Date: Apr. 2015
 * 
 *         Modified by Lin Deng, for accommodating https Date: Oct. 2015
 * 
 *         Modified by Lin Deng 02/22/2017 Added function for sharing a graph
 *         with URL
 *         
 *         Modified by Jenifer Cochran
 * 
 */
@MultipartConfig
public class GraphCoverage extends HttpServlet
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    static Graph graphToShow = new Graph();
    static List<Path> paths;
    static String edges;
    static String title;
    static String initialNode;
    static String endNode;
    static List<String> methodsList = new ArrayList<String>();
    static Map<String, ControlFlowGraphDiagram> graphMap = new HashMap<>();
    // numbers of infeasible prime paths
    static String infeasiblePrimePaths;
    // number of infeasible edge-pairs
    static String infeasibleEdgePairs;
    // infeasible sub paths
    static String infeasibleSubpathsString;
    static List<Path> infeasibleSubpaths;

    String[] infeasiblePrimePathsString;// store infeasible prime paths
    boolean[] infeasiblePrimePathsSigns;// represent if that prime path can be
                                        // toured directly or with sidetrips

    String[] infeasibleEdgePairsString;// store infeasible prime paths
    boolean[] infeasibleEdgePairsSigns;// represent if that prime path can be
                                       // toured directly or with sidetrips

    String hiddenLink = "https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage?";
    boolean showShareButton = false;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

    private String getHeader()
    {
        return "<html>\n" + "<head>\n" + "<title>Graph Coverage</title>\n" + "</head>\n"
                + "<body bgcolor=\"#DDEEDD\">\n"
                + "<p style=\"text-align:center;font-size:150%;font-weight:bold\">Graph Coverage Web Application</p>\n"
                +
                // add js lib for graph display
                "<script src=\"jquery-min.js\"></script>\n" + "<script src=\"springy.js\"></script>\n"
                + "<script src=\"springyui.js\"></script>\n"
                // js code for retrieving the url
                // and prompt to users to copy
                + "<script>" + "function copyToClipboard(text) {"
                // +"url = window.location.href;"
                // +"text = url + text;"
                + "window.prompt(\"Copy to clipboard: Ctrl+C\", text);" + "}" + "</script>";
    }
    
    public Collection<WebItem> RetrieveWebPageValues(HttpServletRequest request)
    {
        String contentType = request.getContentType();
        
        Collection<WebItem> webItems = new ArrayList<>();
        
        if(contentType != null && contentType.indexOf("multipart/form-data") != -1)
        {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);            
           
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Parse the request
            try
            {
                List<FileItem> items = upload.parseRequest(request);
             // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();

                    if (item.isFormField()) 
                    {
                        String name  = item.getFieldName();
                        String value = item.getString();                        
                        webItems.add(new WebItem(name, value));
                    }
                    else 
                    {
                         ExecuteImportJavaFile(item, request);
                    }
                }
            }
            catch (FileUploadException e)
            {
                e.printStackTrace();
            }
            
        }
        
        return webItems;
    }
    
    private static String GetGraphInputValue(GraphInput input, Collection<WebItem> webItems)
    {
        return WebCoverageUtility.GetWebValueItemOrNull(input.getControlName(), webItems);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String result = this.getHeader();
        
        Collection<WebItem> webItems = RetrieveWebPageValues(request);
        
        String action           = GetGraphInputValue(GraphInput.ActionButton,           webItems);
        String initialNodeStr   = GetGraphInputValue(GraphInput.InitialNodeTextBox,     webItems);
        String edgesStr         = GetGraphInputValue(GraphInput.EdgesTextBox,           webItems);
        String endNodeStr       = GetGraphInputValue(GraphInput.EndNodeTextBox,         webItems);
        String createGraph      = GetGraphInputValue(GraphInput.CreateGraphButton,      webItems);
        String selectedMethod   = GetGraphInputValue(GraphInput.SelectedMethodDropDown, webItems);          
        
        PrintWriter out = response.getWriter();

        // build hidden link
        hiddenLink = HiddenLinkUtility.BuildHiddenLink(edgesStr, 
        											   initialNodeStr,
        											   endNodeStr,
        											   action,
        											   WebCoverageUtility.GetWebValueItemOrNull(GraphInput.Algorithm2ActionButton.getControlName(), webItems));
        
        showShareButton = SetShowShareButtonVisibility(action, 
                                                       WebCoverageUtility.GetWebValueItemOrNull(GraphInput.Algorithm2ActionButton.getControlName(), webItems),
                                                       webItems);

        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
        String algorithm2Action = null;
        if(action == null)
            algorithm2Action = WebCoverageUtility.GetWebValueItemOrNull(GraphInput.Algorithm2ActionButton.getControlName(), webItems);

        if(algorithm2Action != null)
        {
            result += ExecuteAlgorithm2(algorithm2Action, action, webItems);
        }
        else
        {
            if(action == null ||
               action.equalsIgnoreCase(OtherTools.NewGraph.toString()) || 
               action.equalsIgnoreCase("Graph Coverage"))
            {
                if(createGraph != null)
                {
                    result += createGraphFromFile(request, selectedMethod);
                }
                else
                {
                    result += ClearGraph();
                }               
               
            }
            else if(action.equalsIgnoreCase(OtherTools.DataFlowCoverage.toString()))
            {
                response.sendRedirect(OtherTools.DataFlowCoverage.getRedirect());
            }
            else if(action.equalsIgnoreCase(OtherTools.LogicCoverage.toString()))
            {
                response.sendRedirect(OtherTools.LogicCoverage.getRedirect());
            }
            else if(action.equalsIgnoreCase(OtherTools.MinimalMUMCUTCoverage.toString()))
            {
                response.sendRedirect(OtherTools.MinimalMUMCUTCoverage.getRedirect());
            }
            else if(action.equalsIgnoreCase(TestRequirements.PrimePaths.toString()))
            {
                result += ExecutePrimePaths(webItems);
            }
            else if(action.equalsIgnoreCase("Test"))
            {
                result += ExecuteTest(webItems);
            }
            else if(action.equalsIgnoreCase(TestRequirements.SimplePaths.toString()))
            {
                result += ExecuteSimplePaths(webItems);
            }
            else if(action.equalsIgnoreCase(TestRequirements.Nodes.toString()))
            {                
                result += ExecuteNodes(webItems);
            }
            else if(action.equalsIgnoreCase(TestRequirements.Edges.toString()))
            {                
                result += ExecuteEdges(webItems);
            }
            else if(action.equalsIgnoreCase(TestRequirements.EdgePair.toString()))
            {              
                result += ExecuteEdgePair(webItems);
            }
            else if(action.equalsIgnoreCase(TestPaths.NodeCoverage.toString()))
            {
            	result += ExecuteNodeCoverageAlgorithm1(webItems);
            }
            else if(action.equalsIgnoreCase(TestPaths.EdgeCoverage.toString()))
            {                
            	result += ExecuteEdgeCoverageAlgorithm1(webItems);
            }
            else if(action.equalsIgnoreCase(TestPaths.EdgePairCoverage.toString()))
            {                
            	result += ExecuteEdgePairCoverageAlgorithm1(webItems);
            }
            else if(action.equalsIgnoreCase(TestPaths.PrimePathCoverage.toString()))
            {
            	result += ExecutePrimePathCoverageAlgorithm1(webItems);
            } // end else if for minimum test path via set cover
            else if(action.equalsIgnoreCase("Prime Path Coverage using Set Cover"))
            {
                result += ExecutePrimePathCoverageSetCoverAlgorithm1(request);
            } // end else if for minimum test path via prefix graph
            else if(action.equalsIgnoreCase("MinimumTestPathViaPrefixGraph"))
            {
                String warning = validate(webItems);
                title = "MinimumTestPathViaPrefixGraph";
                Graph prefix = GraphUtil.getPrefixGraph(graphToShow.findPrimePaths());
                Graph bipartite = GraphUtil.getBipartiteGraph(prefix, initialNode, endNode);
                paths = bipartite.findMinimumPrimePathCoverageViaPrefixGraph(graphToShow.findPrimePaths());

                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(printRequirements(paths, warning, title));
            } // end else if for test
              // for anything else, send it back to the page
            else
            {
                response.sendRedirect("GraphCoverage");
            }
        }

        result += "<p style=\"font-size:80%;font-family:monospace\">\n" + "Companion software\n"
                + "<br>to <i>Introduction to Software Testing</i>, Ammann and Offutt.\n"
                + "<br>Implementation by Wuzhi Xu, Nan Li, Lin Deng, Scott Brown, and Jenifer Cochran.\n"
                + "<br>&copy; 2007-2017, all rights reserved.\n" + "<br>Last update: 20-July-2017\n</font></p>"
                + "</body>" + "</html>";

        out.println(result);
    }

    private static boolean SetShowShareButtonVisibility(String action, String algorithm2, Collection<WebItem> webItems)
    {
        boolean show = algorithm2 != null;// only display share button when there is
                                          // an action
        
        if(action != null)
        {
            if(!action.equals("New Graph"))
                show = true; // only display share button when there
                             // is an action
        }            
        
        String initialNodeStr = WebCoverageUtility.GetWebValueItemOrNull(GraphInput.InitialNodeTextBox.getControlName(), webItems);
        String endNodeStr     = WebCoverageUtility.GetWebValueItemOrNull(GraphInput.EndNodeTextBox.getControlName(), webItems);
        String edgesStr       = WebCoverageUtility.GetWebValueItemOrNull(GraphInput.EdgesTextBox.getControlName(), webItems);

        if(initialNodeStr != null && endNodeStr != null && edgesStr != null)
        {
            if(initialNodeStr.equals("") && endNodeStr.equals("") && edgesStr.equals("")) // if
                                                                                          // provided
                                                                                          // nothing
            {
                show = false;
            }
        }
        
        return show;       
    }

    private String createGraphFromFile(HttpServletRequest request, String selectedMethod)
    {        
        ControlFlowGraphDiagram diagram = graphMap.get(selectedMethod);
        //If the selected item is not in the map, clear the graph
        if(diagram == null)
        {            
            return ClearGraph();
        }
        
        initialNode = "";
        endNode = "";
        edges = "";
        
        String edgesString = "";
        //initial and end nodes
        for(VertexBase vertex : diagram.getChildren())
        {
            String label = vertex.getLabel();
            if (label.equals("START")) 
            {
                initialNode += vertex.getId() + " ";
            } 
            else if (label.equals("EXIT")) 
            {
                endNode += vertex.getId() + " ";
            }           
        }
        //edges
        for(Connection edge : getEdges(diagram.getChildren()))
        {
            edgesString += String.format("%d %d\n", edge.getSource().getId(), edge.getTarget().getId());
        }      
        
        edges = edgesString;
        
        
        
        return printEdgeForm(edges, initialNode, endNode, methodsList);
    }
    
    protected static List<Connection> getEdges(List<VertexBase> vertices)
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

    private void ExecuteImportJavaFile(FileItem file, HttpServletRequest request)
    {
        String contentType = request.getContentType();
        if(contentType == null || contentType.indexOf("multipart/form-data") == -1 || file.getSize() <= 0)
        {
            return;
        }
            
        try (InputStream inputStream = file.getInputStream()) 
        {
            ClassFile classDoc = ClassFile.readClass(inputStream);
            methodsList.clear();
            graphMap.clear();
            for(IMethodSection method : classDoc.getMethodSections())
            {
                String methodKey = String.format("Name: %s Descriptor: %s", method.getName(), method.getDescriptor());
                methodsList.add(methodKey);
                FilteringCodeVisitor methodInstructions = getInstructionList(file, method.getName(), method.getDescriptor());
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
    private static FilteringCodeVisitor getInstructionList(FileItem file,
                                                           String methodName, 
                                                           String methodSig) throws ControlFlowGraphException, IOException 
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

    private String ExecutePrimePathCoverageSetCoverAlgorithm1(HttpServletRequest request)
    {
    	String result = "";
        title = "Prime Path Coverage using Set Cover";
        List<Path> primePaths = new ArrayList<Path>();
        primePaths = graphToShow.findPrimePaths();
        paths = graphToShow.findMinimumPrimePathCoverageViaSetCover(primePaths);
        // maximum ratio of test requirements over test paths
        int maxRatio = 0;
        // maximum length of test paths
        int maxLength = 0;
        List<Path> splittedPaths = new ArrayList<Path>();
        primePaths = graphToShow.findPrimePaths();
        try
        {
            splittedPaths = graphToShow.splittedPathsFromSuperString(paths.get(0), graphToShow.findTestPath());
            // compute the maximum ratio of test requirements over test
            // paths
            for(int i = 0; i < splittedPaths.size(); i++)
            {
                int tempCount = 0;
                for(int j = 0; j < primePaths.size(); j++)
                {
                    // System.out.println(primePaths.get(j));
                    // System.out.println(splittedPaths.get(i));
                    if(primePaths.get(j).isSubpath(splittedPaths.get(i)))
                    {
                        // if(splittedPaths.get(i).indexOf(primePaths.get(j))
                        // != -1){
                        tempCount++;
                        // System.out.println("tempCount: " +
                        // tempCount);
                    }
                }

                if(maxRatio < tempCount)
                {
                    maxRatio = tempCount;
                }
                if(maxLength < splittedPaths.get(i).size())
                    maxLength = splittedPaths.get(i).size();
            }
        }
        catch(InvalidGraphException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < splittedPaths.size(); i++)
        {
            paths.add(splittedPaths.get(i));
        }
        paths.remove(0);
        result += printEdgeForm(edges, initialNode, endNode, methodsList);
        try
        {
            result += printResult(printPrimePathCoverage(paths, null, title));
        }
        catch(InvalidGraphException e)
        {
            e.printStackTrace();
        }
        
        return result;
    }

    private String ExecutePrimePathCoverageAlgorithm1(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
    	
        String warning = validate(webItems);
        title = TestPaths.PrimePathCoverage.toString();
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);
        }
        else
        {
            // maximum ratio of test requirements over test paths
            int maxRatio = 0;
            // maximum length of test paths
            int maxLength = 0;

            if(infeasibleSubpathsString == null || infeasibleSubpathsString.equals(""))
            {// no infeasible subpaths
                if(infeasiblePrimePaths == null)
                    infeasiblePrimePaths = "";
                try
                {
                    List<Path> primePaths = graphToShow.findPrimePaths();
                    paths = graphToShow.findPrimePathCoverage(infeasiblePrimePaths);

                    // compute the maximum ratio of test requirements
                    // over test paths
                    for(int i = 0; i < paths.size(); i++)
                    {
                        int tempCount = 0;
                        for(int j = 0; j < primePaths.size(); j++)
                        {
                            if(primePaths.get(j).isSubpath(paths.get(i)))
                                tempCount++;
                        }
                        if(maxRatio < tempCount)
                            maxRatio = tempCount;
                        if(maxLength < paths.get(i).size())
                            maxLength = paths.get(i).size();
                    }

                    for(Path p : paths)
                    {
                        p.size();
                    }
                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(printPrimePathCoverage(paths, null, title));
                }
                catch(InvalidGraphException e)
                {
                    warning = printWarning(e);
                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(warning);
                } // end catch
            }
            else
            { // has infeasible subpaths
                if(infeasiblePrimePaths == null)
                    infeasiblePrimePaths = "";
                if(infeasibleSubpaths != null)
                {
                }
                try
                {
                    paths = graphToShow.findPrimePathCoverageWithInfeasibleSubPath(infeasiblePrimePaths,
                            infeasibleSubpaths);
                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(printPrimePathCoverage(paths, null, title));
                }
                catch(InvalidGraphException e)
                {
                    warning = printWarning(e);
                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(warning);
                } // end catch
            }
        } // end else
        
        return result;
    }

    private String ExecuteEdgePairCoverageAlgorithm1(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        String warning = validate(webItems);
        title = TestPaths.EdgePairCoverage.toString();
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);
        }
        else
        {
            if(infeasibleEdgePairs == null)
                infeasibleEdgePairs = "";
            try
            {
                paths = graphToShow.findEdgePairCoverage(infeasibleEdgePairs);
                for(int i = 0; i < paths.size(); i++)
                {
                    paths.get(i).size();
                }
                
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(printEdgePairCoverage(paths, null, title));
            }
            catch(InvalidGraphException e)
            {
                warning = printWarning(e);
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
        }
        
        return result;
    }

    private String ExecuteEdgeCoverageAlgorithm1(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
    	
        String warning = validate(webItems);
        title = TestPaths.EdgeCoverage.toString();
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);
        }
        else
        {
            try
            {
                paths = graphToShow.findEdgeCoverage();
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(printPaths(paths, null, title));
            }
            catch(InvalidGraphException e)
            {
                warning = printWarning(e);
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
        }
        
        return result;
    }

    private String ExecuteNodeCoverageAlgorithm1(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        String warning = validate(webItems);
        title = TestPaths.NodeCoverage.toString();
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);
        }
        else
        {
            try
            {
                paths = graphToShow.findNodeCoverage();
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(printPaths(paths, null, title));
            }
            catch(InvalidGraphException e)
            {
                warning = printWarning(e);
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
        }
        
        return result;
    }

    private String ExecuteEdgePair(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
    	
        String warning = validate(webItems);
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);

        }
        else
        {
            title = TestRequirements.EdgePair.toString();
            paths = graphToShow.findEdgePairs();
            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            if(infeasibleEdgePairs == null)
                infeasibleEdgePairs = "";
            result += printResult(printEdgePairs(paths, warning, title));
        }
        
        return result;
    }

    private String ExecuteEdges(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        String warning = validate(webItems);
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);

        }
        else
        {
            title = TestRequirements.Edges.toString();
            paths = graphToShow.findEdges();
            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(printRequirements(paths, warning, title));
        }
        
        return result;
    }

    private String ExecuteNodes(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
    	
        String warning = validate(webItems);
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);

        }
        else
        {
            title = TestRequirements.Nodes.toString();
            paths = graphToShow.findNodes();
            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(printRequirements(paths, warning, title));
        }
        
        return result;
    }

    private String ExecuteSimplePaths(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        String warning = validate(webItems);
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);

        }
        else
        {
            title = "Simple Paths";
            paths = graphToShow.findSimplePaths();
            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(printRequirements(paths, warning, title));
        }
        
        return result;
    }

    private String ExecuteTest(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        System.out.println("---------------------Test-----------------------------------");
        String warning = validate(webItems);
        title = "Test";
        List<Path> primePaths = new ArrayList<Path>();
        primePaths = graphToShow.findPrimePaths();
        Graph prefix = GraphUtil.getPrefixGraph(primePaths);
        Graph bipartite = GraphUtil.getBipartiteGraphWithST(prefix, initialNode, endNode);
        try
        {
            // paths = g.fordFulkerson(null, null);
            paths = bipartite.fordFulkerson(null, null);
        }
        catch(InvalidGraphException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        result += printEdgeForm(edges, initialNode, endNode, methodsList);
        result += printResult(printPaths(paths, warning, title));   
        
        return result;
    }

    private String ExecutePrimePaths(Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        String warning = validate(webItems);
        if(warning != null)
        {
            // if any of inputs contain invalid characters
            // clear it
            // otherwise, keep it
            checkNotNull();
            if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                initialNode = "";
            if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                endNode = "";

            result += printEdgeForm(edges, initialNode, endNode, methodsList);
            result += printResult(warning);
        }
        else
        {
            title = TestRequirements.PrimePaths.toString();
            paths = graphToShow.findPrimePaths();
            result += printEdgeForm(edges, initialNode, endNode, methodsList);

            if(infeasiblePrimePaths == null)
                infeasiblePrimePaths = "";

            result += printResult(printPrimePaths(paths, warning, title));
        }
        
        return result;
    }

    private String ClearGraph()
    {
        graphToShow = new Graph();
        edges = null;
        initialNode = null;
        endNode = null;
        title = null;
        paths = null;
        infeasiblePrimePaths = "";
        infeasibleEdgePairs = "";
        
        
        return printEdgeForm("", "", "", methodsList);
    }

    private String ExecuteAlgorithm2(String algorithm2Action, String action, Collection<WebItem> webItems) throws IOException
    {
    	String result = "";
        if(action == null && algorithm2Action.equalsIgnoreCase(TestPaths.EdgeCoverage.toString()))
        {
            String warning = validate(webItems);
            title = TestPaths.EdgeCoverage.toString();
            if(warning != null)
            {
                // if any of inputs contain invalid characters
                // clear it
                // otherwise, keep it
                checkNotNull();
                if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                    initialNode = "";
                if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                    endNode = "";

                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
            else
            {
                List<Path> edgePaths = graphToShow.findEdges();

                Graph prefix = GraphUtil.getPrefixGraph(edgePaths);
                Graph bipartite = GraphUtil.getBipartiteGraph(prefix, initialNode, endNode);
                try
                {
                    paths = bipartite.findMinimumPrimePathCoverageViaPrefixGraphOptimized(edgePaths);
                }
                catch(InvalidGraphException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // maximum ratio of test requirements over test paths
                int maxRatio = 0;
                // maximum length of test paths
                int maxLength = 0;
                List<Path> splittedPaths = new ArrayList<Path>();
                try
                {
                    splittedPaths = graphToShow.splittedPathsFromSuperString(paths.get(0), graphToShow.findTestPath());
                    // compute the maximum ratio of test requirements over test
                    // paths
                    for(int i = 0; i < splittedPaths.size(); i++)
                    {
                        int tempCount = 0;
                        for(int j = 0; j < edgePaths.size(); j++)
                        {
                            if(edgePaths.get(j).isSubpath(splittedPaths.get(i)))
                                tempCount++;
                        }
                        if(maxRatio < tempCount)
                            maxRatio = tempCount;
                        if(maxLength < splittedPaths.get(i).size())
                            maxLength = splittedPaths.get(i).size();
                    }

                }
                catch(InvalidGraphException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();                    
                }
                for(Path p : splittedPaths)
                {
                    paths.add(p);
                }

                for(int i = 1; i < paths.size(); i++)
                {
                    paths.get(i).size();
                }

                paths.remove(0);

                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(printPaths(paths, null, title));
            }            
        }
        else if(action == null && algorithm2Action.equalsIgnoreCase(TestPaths.EdgePairCoverage.toString()))
        {
            String warning = validate(webItems);
            title = String.format("%s using the prefix graph algorithm", TestPaths.EdgePairCoverage.toString());
            if(warning != null)
            {
                // if any of inputs contain invalid characters
                // clear it
                // otherwise, keep it
                checkNotNull();
                if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                    initialNode = "";
                if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                    endNode = "";

                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
            else
            {
                if(infeasibleEdgePairs == null)
                    infeasibleEdgePairs = "";
                try
                {
                    // paths = g.findEdgePairCoverage(infeasibleEdgePairs);

                    List<Path> edgePairs = graphToShow.findEdgePairs();

                    Graph prefix = GraphUtil.getPrefixGraph(edgePairs);
                    Graph bipartite = GraphUtil.getBipartiteGraph(prefix, initialNode, endNode);
                    try
                    {
                        paths = bipartite.findMinimumPrimePathCoverageViaPrefixGraphOptimized(edgePairs);
                    }
                    catch(InvalidGraphException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    // maximum ratio of test requirements over test paths
                    int maxRatio = 0;
                    // maximum length of test paths
                    int maxLength = 0;
                    List<Path> splittedPaths = new ArrayList<Path>();
                    try
                    {
                        splittedPaths = graphToShow.splittedPathsFromSuperString(paths.get(0), graphToShow.findTestPath());
                        // compute the maximum ratio of test requirements over
                        // test paths
                        for(int i = 0; i < splittedPaths.size(); i++)
                        {
                            int tempCount = 0;
                            for(int j = 0; j < edgePairs.size(); j++)
                            {
                                if(edgePairs.get(j).isSubpath(splittedPaths.get(i)))
                                    tempCount++;
                            }
                            if(maxRatio < tempCount)
                                maxRatio = tempCount;
                            if(maxLength < splittedPaths.get(i).size())
                                maxLength = splittedPaths.get(i).size();
                        }

                    }
                    catch(InvalidGraphException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    for(Path p : splittedPaths)
                    {
                        paths.add(p);
                    }

                    for(int i = 1; i < paths.size(); i++)
                    {
                        paths.get(i).size();
                    }

                    paths.remove(0);

                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(printEdgePairCoverage(paths, null, title));
                }
                catch(InvalidGraphException e)
                {
                    warning = printWarning(e);
                    result += printEdgeForm(edges, initialNode, endNode, methodsList);
                    result += printResult(warning);
                }
            }
        }
        else if(action == null && algorithm2Action.equalsIgnoreCase(TestPaths.PrimePathCoverage.toString()))
        {
            String warning = validate(webItems);
            if(warning != null)
            {
                // if any of inputs contain invalid characters
                // clear it
                // otherwise, keep it
                checkNotNull();
                if(!Pattern.matches(GraphUtil.nodePat, initialNode.trim()))
                    initialNode = "";
                if(!Pattern.matches(GraphUtil.nodePat, endNode.trim()))
                    endNode = "";

                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                result += printResult(warning);
            }
            else
            {
                title = String.format("%s using the prefix graph algorithm", TestPaths.PrimePathCoverage.toString());
                List<Path> primePaths = new ArrayList<Path>();
                primePaths = graphToShow.findPrimePaths();

                long start = System.nanoTime();
                Graph prefix = GraphUtil.getPrefixGraph(primePaths);
                Graph bipartite = GraphUtil.getBipartiteGraph(prefix, initialNode, endNode);
                try
                {
                    paths = bipartite.findMinimumPrimePathCoverageViaPrefixGraphOptimized(graphToShow.findPrimePaths());
                }
                catch(InvalidGraphException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                // maximum ratio of test requirements over test paths
                int maxRatio = 0;
                // maximum length of test paths
                int maxLength = 0;
                List<Path> splittedPaths = new ArrayList<Path>();
                try
                {
                    splittedPaths = graphToShow.splittedPathsFromSuperString(paths.get(0), graphToShow.findTestPath());
                    // compute the maximum ratio of test requirements over
                    // test paths
                    for(int i = 0; i < splittedPaths.size(); i++)
                    {
                        int tempCount = 0;
                        for(int j = 0; j < primePaths.size(); j++)
                        {
                            if(primePaths.get(j).isSubpath(splittedPaths.get(i)))
                                tempCount++;
                        }
                        if(maxRatio < tempCount)
                            maxRatio = tempCount;
                        if(maxLength < splittedPaths.get(i).size())
                            maxLength = splittedPaths.get(i).size();
                    }
                    // System.out.println("Maximum Ratio: " + maxRatio);
                    // System.out.println("Maximum Length: " + maxLength);
                }
                catch(InvalidGraphException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                for(Path p : splittedPaths)
                {
                    paths.add(p);
                }
                /*
                 * try { for(Path p:g.findTestPath()){ paths.add(p); } } catch
                 * (InvalidGraphException e) { // TODO Auto-generated catch
                 * block e.printStackTrace(); }
                 */
                long end = System.nanoTime();
                for(int i = 1; i < paths.size(); i++)
                {
                    paths.get(i).size();
                }

                paths.remove(0);
                result += printEdgeForm(edges, initialNode, endNode, methodsList);
                try
                {
                    result += printResult(printPrimePathCoverage(paths, null, title));
                }
                catch(InvalidGraphException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } // end else if for test
        
        return result;
    }

    // to avoid 500 error with NPE
    // for links like : ?action=Nodes
    private void checkNotNull()
    {
        if(edges == null)
            edges = "";
        if(initialNode == null)
            initialNode = "";
        if(endNode == null)
            endNode = "";
    }

    /**
     * get the parameters from the request and create a graph with edges, an
     * initial node, and final nodes
     * 
     * @param request
     * @throws InvalidInputException
     */
    private void createGraph(Collection<WebItem> webItems) throws InvalidInputException
    {
        initialNode = GetGraphInputValue(GraphInput.InitialNodeTextBox, webItems);
        edges       = GetGraphInputValue(GraphInput.EdgesTextBox,       webItems);
        endNode     = GetGraphInputValue(GraphInput.EndNodeTextBox,     webItems);

        graphToShow = GraphUtil.readGraph(edges, initialNode, endNode);
    }

    /**
     * 
     * @param e
     * @return a warning message the graph is invalid
     */
    private String printWarning(Exception e)
    {
        String warning = "" + "    <font face=\"Garamond\">The input graph is invalid because:<br>\n"
                + "    <font color=red>\n" + "    <b>" + e.getMessage()
                + "<br> Cannot generate a set of test paths to satisfy the coverage</b>\n" + "    </font>\n"
                + "    </font>\n" + "    <br>\n";

        return warning;
    }

    
    
    /**
     * 
     * @param request
     * @return a warning message
     * @throws IOException
     */
    private String validate(Collection<WebItem> webItems) throws IOException
    {
        // return a warning if anything wrong with createGraph()
        String warning = null;
        try
        {
            createGraph(webItems);
        }
        catch(InvalidInputException e)
        {
            warning = printWarning(e);
            return warning;
        }
        // return a warning if anything wrong with
        // coverage.graph.Graph.validate()
        try
        {
            graphToShow.validate();
        }
        catch(InvalidGraphException e)
        {
            warning = printWarning(e);
        }
        
        // get the information of infeasible prime paths
        if(WebCoverageUtility.GetWebValueItemOrNull("infeasiblePrimePaths", webItems) != null)
            infeasiblePrimePaths = WebCoverageUtility.GetWebValueItemOrNull("infeasiblePrimePaths", webItems);
        // get the information of infeasible edge-pairs
        if(WebCoverageUtility.GetWebValueItemOrNull("infeasibleEdgePairs", webItems) != null)
            infeasibleEdgePairs = WebCoverageUtility.GetWebValueItemOrNull("infeasibleEdgePairs", webItems);
        if(WebCoverageUtility.GetWebValueItemOrNull("infeasibleSubpaths", webItems) != null)
        {
            infeasibleSubpathsString = WebCoverageUtility.GetWebValueItemOrNull("infeasibleSubpaths", webItems);
            try
            {
                infeasibleSubpaths = GraphUtil.readInfeasibleSubpaths(infeasibleSubpathsString);
            }
            catch(InvalidInputException e)
            {
                warning = printWarning(e);
                e.printStackTrace();
            }
        }
        else
            infeasibleSubpathsString = "";

        return warning;
    }

    /**
     * 
     * @param paths
     * @param warning
     * @param title
     * @return a String including all test paths for a specific coverage
     *         criterion
     */
    private String printPaths(List<Path> paths, String warning, String title)
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            result += "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";
        for(int i = 0; i < paths.size(); i++)
            result += paths.get(i).toString() + "<br>\n";

        return result;
    }

    /**
     * 
     * @param paths
     * @param warning
     * @param title
     * @return a String including requirements of a specific coverage criterion
     */
    private String printRequirements(List<Path> paths, String warning, String title)
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            result += "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        for(int i = 0; i < paths.size(); i++)
            result += paths.get(i).toString() + "<br>\n";

        return result;
    }

    /**
     * 
     * @param testPath
     * @param primePath
     * @param primesPathList
     *            which could be the sidetrips
     * @return true if an prime path can be toured by a specific test path with
     *         sidetrips
     */
    private boolean aTestRequirementTouredWithSidetrips(Path testPath, Path testRequirement, List<Path> primePathsList)
    {
        // initiate two paths to null
        Path firstPartTempPath = null, secondPartTempPath = null;
        // iterate each node of the specified test requirement path
        for(int i = 0; i < testRequirement.size(); i++)
        {
            // iterate other paths of the paths list
            for(int k = 0; k < primePathsList.size(); k++)
            {
                Path tempPrimePath = primePathsList.get(k);
                // check whether the current node of this prime path is the same
                // as the initial and final node of another path as well
                if(testRequirement.get(i).equals(tempPrimePath.get(0))
                        && testRequirement.get(i).equals(tempPrimePath.getEnd())
                        && !testRequirement.equals(tempPrimePath))
                {
                    // final path: firstPartTempPath extends tempPrimePath and
                    // also extends secondPartTempPath
                    secondPartTempPath = testRequirement.subPath(i, testRequirement.size());
                    if(i == 0)
                    {
                        firstPartTempPath = testRequirement.subPath(0, i);
                        firstPartTempPath.extendPath(tempPrimePath.subPath(1, tempPrimePath.size()));
                        firstPartTempPath.extendPath(secondPartTempPath);
                    }
                    else if(i > 0)
                    {
                        firstPartTempPath = testRequirement.subPath(0, i);
                        firstPartTempPath.extendPath(tempPrimePath.subPath(0, tempPrimePath.size() - 1));
                        firstPartTempPath.extendPath(secondPartTempPath);
                    }
                } // end if
                  // return true if the prime path can be toured by the test
                  // path with sidetrips
                if(testPath != null && testRequirement != null && primePathsList != null && firstPartTempPath != null)
                    if(firstPartTempPath.isSubpath(testPath))
                        return true;
            } // end for loop with variable k
        } // end for loop with variable i
        return false;
    }

    /**
     * @return a string printing prime path coverage
     */
    private String printPrimePathCoverage(List<Path> paths, String warning, String title) throws InvalidGraphException
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            // result += "There are <b>" + paths.size() + "</b>" + title +
            // "<br>\n";
            result += "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";
        // initialize the boolean array for infeasible prime paths
        infeasiblePrimePathsSigns = new boolean[graphToShow.findPrimePaths().size()];

        if(infeasiblePrimePaths == null)
            infeasiblePrimePaths = "";
        // list test paths and test requirements in a table
        // and indicate which test requirements are toured by test paths
        // directly
        List<Path> path1 = graphToShow.findPrimePaths();
        if(!infeasiblePrimePaths.equals("") && !infeasiblePrimePaths.equals(" ") && !infeasiblePrimePaths.equals(null))
        {
            infeasiblePrimePathsString = infeasiblePrimePaths.trim().split(",");
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths directly</td></tr>\n";
            boolean flag;// a sign for whether put a comma for another test
                         // requirement
            boolean sign;// a sign for whether a prime path has been marked as
                         // an infeasible prime path
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag = false;
                // check each prime path
                for(int j = 0; j < path1.size(); j++)
                {
                    sign = false;
                    // get the numbers of infeasible prime paths
                    // and if they match the prime path, mark these prime paths
                    // as infeasible prime paths
                    for(int k = 0; k < infeasiblePrimePathsString.length; k++)
                    {
                        Integer tempInt = new Integer(infeasiblePrimePathsString[k]) - 1;
                        if(tempInt.intValue() == j)
                            sign = true;
                    }
                    // write the corresponding test requirements
                    if(path1.get(j).isSubpath(paths.get(i)) && sign == false)
                    {
                        if(flag == true)
                            result += ", ";
                        result += path1.get(j);
                        flag = true;
                        // mark a prime path as an infeasible prime path
                        infeasiblePrimePathsSigns[j] = true;
                    }
                } // end for loop with variable j
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";

            // list test paths and test requirements in a table
            // and indicate which test requirements are toured by test paths
            // with sidetrip
            // path1 = g.findPrimePathsWithSidetrips(infeasiblePrimePaths);
            List<Path> path2 = new ArrayList<Path>();
            // set all prime paths to path2
            path2 = graphToShow.findPrimePaths();
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths with sidetrips</td></tr>\n";
            boolean flag1;// a sign for whether put a comma for another test
                          // requirement
            boolean sign1;// a sign for whether a prime path has been marked as
                          // an infeasible prime path
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag1 = false;
                // count the number and see if there exists any prime path is
                // toured by a test path with sidetrips
                int counterForSidetrips = 0;
                // check each prime path
                for(int j = 0; j < path2.size(); j++)
                {
                    sign1 = false;
                    // check if this prime path is infeasible
                    for(int k = 0; k < infeasiblePrimePathsString.length; k++)
                    {
                        Integer tempInt = new Integer(infeasiblePrimePathsString[k]) - 1;
                        if(tempInt.intValue() == j)
                            sign1 = true;
                    }

                    if(aTestRequirementTouredWithSidetrips(paths.get(i), path2.get(j), path2) && sign1 == true)
                    {
                        if(flag1 == true)
                            result += ", ";
                        result += path2.get(j);
                        infeasiblePrimePathsSigns[j] = true;
                        flag1 = true;
                        counterForSidetrips++;
                    }
                } // end for loop with variable j
                  // for a test path, if no prime path is toured by it with
                  // sidetrips, return null
                if(counterForSidetrips == 0)
                    result += "None";
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";
            // show the infeasible prime paths
            result += "Infeasible prime paths are:</br>\n";

            // list all infeasible prime paths
            int counter = 0;// a counter to record the number of infeasible
                            // prime paths
            for(int j = 0; j < path2.size(); j++)
            {
                if(infeasiblePrimePathsSigns[j] == false)
                {
                    result += path2.get(j).toString() + "<br>\n";
                    counter++;
                }
            }
            if(counter == 0)
                result += "<b>None</b>";

        } // end if
        else if(infeasiblePrimePaths.equals("") || infeasiblePrimePaths.equals(" "))
        {
            result += "<table border = 1>\n";
            // list test paths and test requirements
            // indicate which test requirements are toured by which test paths
            // directly
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths directly</td></tr>\n";
            boolean flag;// a sign for whether put a comma for another test
                         // requirement
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag = false;// a sign for whether put a comma for another test
                             // requirement
                // check each prime path
                for(int j = 0; j < path1.size(); j++)
                {
                    // write the corresponding test requirements
                    if(path1.get(j).isSubpath(paths.get(i)))
                    {
                        if(flag == true)
                            result += ", ";
                        result += path1.get(j);
                        flag = true;
                        // mark a prime path as an infeasible prime path
                        infeasiblePrimePathsSigns[j] = true;
                    }
                } // end for loop with variable j
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";

            // list test paths and test requirements in a table
            // and indicate which test requirements are toured by which test
            // paths with sidetrips
            // set all prime paths with sidetrips to path1
            // path1 = g.findPrimePathsWithSidetrips(infeasiblePrimePaths);
            List<Path> path2 = new ArrayList<Path>();
            // set all prime paths to path2
            path2 = graphToShow.findPrimePaths();
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths with sidetrips</td></tr>\n";
            boolean flag1;// a sign for whether put a comma for another test
                          // requirement
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag1 = false;
                // count the number and see if there exists any prime path is
                // toured by a test path with sidetrips
                int counterForSidetrips = 0;
                // if no prime paths with sidetrips, return null
                // else return all prime paths that are toured by the
                // corresponding test paths with sidetrips
                if(path1.size() == 0)
                    result += "None";
                else if(path1.size() > 0)
                {
                    for(int j = 0; j < path2.size(); j++)
                    {
                        /*
                         * for(int k = 0; k < path1.size();k++) { //if a prime
                         * path can be toured with sidetrip, cannot be toured
                         * directly, and there exists a test path tours this
                         * prime path with sidetrip
                         * if(path2.get(j).sidetrip(path1.get(k)) &&
                         * infeasiblePrimePathsSigns[j] == false &&
                         * path1.get(k).sidetrip(paths.get(i))){ //separate
                         * different prime paths with commas if(flag1 == true)
                         * result += ", "; result += path1.get(j);
                         * infeasiblePrimePathsSigns[j] = true; flag1 = true;
                         * counterForSidetrips++; } }
                         */
                        if(aTestRequirementTouredWithSidetrips(paths.get(i), path2.get(j), path2))
                        {
                            if(flag1 == true)
                                result += ", ";
                            result += path2.get(j);
                            infeasiblePrimePathsSigns[j] = true;
                            flag1 = true;
                            counterForSidetrips++;
                        }
                    } // end the for loop with variable j
                }
                // for a test path, if no prime path is toured by it with
                // sidetrips, return null
                if(counterForSidetrips == 0)
                    result += "None";
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";
            // show the infeasible prime paths
            result += "Infeasible prime paths are:</br>\n";

            // list all infeasible prime paths
            int counter = 0;// a counter to record the number of infeasible
                            // prime paths
            for(int j = 0; j < path2.size(); j++)
            {
                if(infeasiblePrimePathsSigns[j] == false)
                {
                    result += path2.get(j).toString() + "<br>\n";
                    counter++;
                }
            }
            if(counter == 0)
                result += "<b>None</b></br>\n";
        }
        result += "</br>\n";
        result += "List any infeasible sub paths in the box below. Enter sub paths<br>\n";
        result += "as strings of nodes, separated by commas.<br>\n";
        result += "Sub paths you mark as infeasible will <b>not</b> be used<br>\n";
        result += "in any test paths.<br>\n";
        result += "Example: 3,4,7,1,2,3,4,7,1<br>\n";
        result += "<textarea rows=\"5\" name=\"infeasibleSubpaths\" cols=\"25\">\n" + infeasibleSubpathsString
                + "</textarea><br>\n";
        result += "</form>\n";
        return result;
    }

    /*
     * Effect: return a string printing prime path coverage
     */
    private String printEdgePairCoverage(List<Path> paths, String warning, String title) throws InvalidGraphException
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            // result += "There are <b>" + paths.size() + "</b>" + title +
            // "<br>\n";
            result += "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " test paths are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " test path is needed for " + title + "<br>\n";

        if(infeasibleEdgePairs == null)
            infeasibleEdgePairs = "";
        // list test paths and test requirements in a table
        // and indicate which test requirement are toured by test paths
        // List<Path> path1 = g.findEdgePairs(infeasibleEdgePairs);
        /*
         * result += "<table border = 1>"; result +=
         * "<tr><td>Test Paths</td><td>Test Requirements</td></tr>"; boolean
         * flag;//a sign for whether put a comma for another test requirement
         * for(int i = 0;i < paths.size();i++){ result += "<tr><td>" +
         * paths.get(i).toString()+"</td>"; result += "<td>"; flag = false;
         * for(int j = 0; j < path1.size();j++){
         * if(path1.get(j).isSubpath(paths.get(i))){ if(flag == true) result +=
         * ", "; result += path1.get(j); flag = true; } }//end for loop with
         * variable j result += "</td>"; }//end for loop with variable i result
         * += "</table>";
         * 
         * return result;
         */
        // initialize the boolean array for infeasible prime paths
        infeasibleEdgePairsSigns = new boolean[graphToShow.findEdgePairs().size()];
        // list test paths and test requirements in a table
        // and indicate which test requirements are toured by test paths
        // directly
        List<Path> path1 = graphToShow.findEdgePairs();
        if(!infeasibleEdgePairs.equals("") && !infeasibleEdgePairs.equals(" ") && !infeasibleEdgePairs.equals(null))
        {
            infeasibleEdgePairsString = infeasibleEdgePairs.trim().split(",");
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths directly</td></tr>\n";
            boolean flag;// a sign for whether put a comma for another test
                         // requirement
            boolean sign;// a sign for whether a prime path has been marked as
                         // an infeasible prime path
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag = false;
                // check each prime path
                for(int j = 0; j < path1.size(); j++)
                {
                    sign = false;
                    // get the numbers of infeasible prime paths
                    // and if they match the prime path, mark these prime paths
                    // as infeasible prime paths
                    for(int k = 0; k < infeasibleEdgePairsString.length; k++)
                    {
                        Integer tempInt = new Integer(infeasibleEdgePairsString[k]) - 1;
                        if(tempInt.intValue() == j)
                            sign = true;
                    }
                    // write the corresponding test requirements
                    if(path1.get(j).isSubpath(paths.get(i)) && sign == false)
                    {
                        if(flag == true)
                            result += ", ";
                        result += path1.get(j);
                        flag = true;
                        // mark a prime path as an infeasible prime path
                        infeasibleEdgePairsSigns[j] = true;
                    }
                } // end for loop with variable j
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";

            // list test paths and test requirements in a table
            // and indicate which test requirements are toured by test paths
            // with sidetrip
            // path1 = g.findPrimePathsWithSidetrips(infeasiblePrimePaths);
            List<Path> path2 = new ArrayList<Path>();
            // set all prime paths to path2
            path2 = graphToShow.findEdgePairs();
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths with sidetrips</td></tr>\n";
            boolean flag1;// a sign for whether put a comma for another test
                          // requirement
            boolean sign1;// a sign for whether a prime path has been marked as
                          // an infeasible prime path
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag1 = false;
                // count the number and see if there exists any prime path is
                // toured by a test path with sidetrips
                int counterForSidetrips = 0;
                // check each prime path
                for(int j = 0; j < path2.size(); j++)
                {
                    sign1 = false;
                    // check if this prime path is infeasible
                    for(int k = 0; k < infeasibleEdgePairsString.length; k++)
                    {
                        Integer tempInt = new Integer(infeasibleEdgePairsString[k]) - 1;
                        if(tempInt.intValue() == j)
                            sign1 = true;
                    }

                    if(aTestRequirementTouredWithSidetrips(paths.get(i), path2.get(j), path2) && sign1 == true)
                    {
                        if(flag1 == true)
                            result += ", ";
                        result += path2.get(j);
                        infeasibleEdgePairsSigns[j] = true;
                        flag1 = true;
                        counterForSidetrips++;
                    }
                } // end for loop with variable j
                  // for a test path, if no prime path is toured by it with
                  // sidetrips, return null
                if(counterForSidetrips == 0)
                    result += "None";
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";
            // show the infeasible prime paths
            result += "Infeasible Edge-Pairs are:</br>\n";

            // list all infeasible prime paths
            int counter = 0;// a counter to record the number of infeasible
                            // prime paths
            for(int j = 0; j < path2.size(); j++)
            {
                if(infeasibleEdgePairsSigns[j] == false)
                {
                    result += path2.get(j).toString() + "<br>\n";
                    counter++;
                }
            }
            if(counter == 0)
                result += "<b>None</b>";

        } // end if
        else if(infeasibleEdgePairs.equals("") || infeasibleEdgePairs.equals(" "))
        {
            result += "<table border = 1>\n";
            // list test paths and test requirements
            // indicate which test requirements are toured by which test paths
            // directly
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths directly</td></tr>\n";
            boolean flag;// a sign for whether put a comma for another test
                         // requirement
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag = false;// a sign for whether put a comma for another test
                             // requirement
                // check each prime path
                for(int j = 0; j < path1.size(); j++)
                {
                    // write the corresponding test requirements
                    if(path1.get(j).isSubpath(paths.get(i)))
                    {
                        if(flag == true)
                            result += ", ";
                        result += path1.get(j);
                        flag = true;
                        // mark a prime path as an infeasible prime path
                        infeasibleEdgePairsSigns[j] = true;
                    }
                } // end for loop with variable j
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";

            // list test paths and test requirements in a table
            // and indicate which test requirements are toured by which test
            // paths with sidetrips
            // set all prime paths with sidetrips to path1
            // path1 = g.findPrimePathsWithSidetrips(infeasiblePrimePaths);
            List<Path> path2 = new ArrayList<Path>();
            // set all prime paths to path2
            path2 = graphToShow.findEdgePairs();
            result += "<table border = 1>\n";
            result += "<tr><td>Test Paths</td><td>Test Requirements that are toured by test paths with sidetrips</td></tr>\n";
            boolean flag1;// a sign for whether put a comma for another test
                          // requirement
            for(int i = 0; i < paths.size(); i++)
            {
                result += "<tr><td>" + paths.get(i).toString() + "</td>";
                result += "<td>";
                flag1 = false;
                // count the number and see if there exists any prime path is
                // toured by a test path with sidetrips
                int counterForSidetrips = 0;
                // if no prime paths with sidetrips, return null
                // else return all prime paths that are toured by the
                // corresponding test paths with sidetrips
                if(path1.size() == 0)
                    result += "None";
                else if(path1.size() > 0)
                {
                    for(int j = 0; j < path2.size(); j++)
                    {

                        if(aTestRequirementTouredWithSidetrips(paths.get(i), path2.get(j), path2))
                        {
                            if(flag1 == true)
                                result += ", ";
                            result += path2.get(j);
                            infeasibleEdgePairsSigns[j] = true;
                            flag1 = true;
                            counterForSidetrips++;
                        }
                    } // end the for loop with variable j
                }
                // for a test path, if no prime path is toured by it with
                // sidetrips, return null
                if(counterForSidetrips == 0)
                    result += "None";
                result += "</td>";
            } // end for loop with variable i
            result += "</table>";
            // show the infeasible prime paths
            result += "Infeasible Edge-Pairs are:</br>\n";

            // list all infeasible prime paths
            int counter = 0;// a counter to record the number of infeasible
                            // prime paths
            for(int j = 0; j < path2.size(); j++)
            {
                if(infeasibleEdgePairsSigns[j] == false)
                {
                    result += path2.get(j).toString() + "<br>\n";
                    counter++;
                }
            }
            if(counter == 0)
                result += "<b>None</b>";
        }
        return result;
    }

    /*
     * Effects: return a string printing prime paths
     */
    private String printPrimePaths(List<Path> paths, String warning, String title)
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            result += "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        // mark the infeasible prime paths
        for(int i = 0; i < paths.size(); i++)
        {
            // get the number of prime paths and put them in a String array
            String[] tempString = new String[100];
            // if(!primePaths.equals(""))
            tempString = infeasiblePrimePaths.trim().split(",");
            // put a "X" in front of each infeasible prime path
            for(int j = 0; j < tempString.length; j++)
            {
                if(!tempString[j].equals("") && !tempString[j].equals(null) && !tempString[j].equals(" "))
                {
                    Integer tempInt = new Integer(1000);
                    try
                    {
                        // subtract 1 because user input number starting from 1
                        // rather than 0
                        tempInt = new Integer(tempString[j]) - 1;
                    }
                    catch(Exception e)
                    {
                        warning = "Please input infeasible prime paths with the right format";
                    }
                    if(!tempInt.equals(null) && !tempInt.equals("") && tempInt.intValue() == i)
                        result += "<font color = red>" + "X " + "</font>";
                }

            }
            result += ++i + ". " + paths.get(--i).toString() + "<br>\n";
        }
        // a text box to put infeasible prime paths
        result += "<br>\n";
        result += "List any infeasible prime paths in the box below, using the<br>\n";
        result += "numbers beside the prime paths above. Separate with commas.<br>\n";
        result += "Prime paths you mark as infeasible will <b>not</b> be used<br>\n";
        result += "in any test paths.<br>\n";
        result += "Example: 1,5,9<br>\n";
        result += "<input type=\"text\" name=\"infeasiblePrimePaths\" size=\"30\" value=\"" + infeasiblePrimePaths
                + "\"><br>\n";
        // put the end form here such that infeasible prime paths could be
        // returned back to users
        result += "</form>\n";
        return result;
    }

    /*
     * Effects: return a string printing Edge Pairs
     */
    private String printEdgePairs(List<Path> paths, String warning, String title)
    {
        String result = "";
        // distinguish one path from more than one path
        if(warning == null && paths.size() != 1)
            result += "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else if(warning == null && paths.size() == 1)
            result += "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        else if(warning != null && paths.size() != 1)
            result += warning + "<b>" + paths.size() + "</b>" + " requirements are needed for " + title + "<br>\n";
        else
            result += warning + "<b>" + paths.size() + "</b>" + " requirement is needed for " + title + "<br>\n";
        // mark the infeasible edge pairs
        for(int i = 0; i < paths.size(); i++)
        {
            // get the numbers of edge-pairs and put them in a String array
            String[] tempString = new String[100];
            // separate the numbers by commas
            tempString = infeasibleEdgePairs.trim().split(",");
            // put a "X" in front of each infeasible edge-pairs
            for(int j = 0; j < tempString.length; j++)
            {
                if(!tempString[j].equals("") && !tempString[j].equals(null) && !tempString[j].equals(" "))
                {
                    Integer tempInt = new Integer(1000);
                    try
                    {
                        tempInt = new Integer(tempString[j]) - 1;
                    }
                    catch(Exception e)
                    {
                        warning = "Please input infeasible Edge-Pairs with the right format";
                    }
                    // if the number of an infeasible path is the same as that
                    // of the edge-pair, put a "X" in front of the path
                    if(!tempInt.equals(null) && !tempInt.equals("") && tempInt.intValue() == i)
                        result += "<font color = red>" + "X " + "</font>";
                }

            }
            result += ++i + ". " + paths.get(--i).toString() + "<br>\n";
        }
        // a text box to put infeasible Edge-Pairs
        result += "<br>\n";
        result += "List any infeasible edge-pairs in the box below, using the<br>\n";
        result += "numbers beside the edge-pairs above. Separate with commas.<br>\n";
        result += "Edge-pairs you mark as infeasible will <b>not</b> be used<br>\n";
        result += "in any test paths.<br>\n";
        result += "Example: 1,5,9<br>\n";
        result += "<input type=\"text\" name=\"infeasibleEdgePairs\" size=\"50\" value=\"" + infeasibleEdgePairs
                + "\"><br>\n";
        // put the end form here such that infeasible prime paths could be
        // returned back to users
        result += "</form>\n";
        return result;
    }

    /**
     * 
     * @param msg
     * @return a String consisting of two tables. One table represents coverage
     *         paths and the other represents the applet
     */
    private String printResult(String msg)
    {
        // get all edges
        List<Path> edgePaths = graphToShow.findEdges();
        // System.out.println(edgePaths);
        // process edges to generate a graph
        Iterator nodeItr = graphToShow.getNodeIterator();
        String nodeStr = "";
        String edgeStr = "";
        // produce strings used for JS
        while(nodeItr.hasNext())
        {
            // System.out.println(nodeItr.next());
            Node node = (Node) nodeItr.next();
            nodeStr += "var node" + node + " = graph.newNode({label: '" + node;
            if(graphToShow.isInitialNode(node)) // initial to be brown
            {
                nodeStr += "', color: '#FFCC00', font: 'italic bolder 16px Verdana, sans-serif'});\n";
            }
            else if(graphToShow.isEndingNode(node)) // end to be blue
            {
                nodeStr += "', color: '#CC00FF', font: 'italic bolder 16px Verdana, sans-serif'});\n";
            }
            else // normal node t obe black
            {
                nodeStr += "'});\n";
            }
        }

        for(Path edge : edgePaths)
        {
            // System.out.println(edge);
            edgeStr += "graph.newEdge( node" + edge.get(0) + ", node" + edge.get(1) + ");\n";

        }

        String result = "<table id = \"tableResult\" border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n"
                + "  <tr><td width=\"50%\"  valign=\"top\">" + msg + "</td>\n"
                + "    <td width=\"50%\" valign=\"top\">";
        // +"<font face=\"Garamond\">Node color: <font color=gray>Initial
        // Node</font>,\n"
        // +"<font color=black>Ending Node</font>, \n"
        // +"<font color=blue>Passed Node</font>, \n"
        // +"<font color=red>Unpassed Node</font></font><br> \n"

        if(graphToShow.sizeOfNodes() > 0)
        {
            result += "<script>" + "var graph = new Springy.Graph();\n" + nodeStr + edgeStr;

            // function

            result += " jQuery(function(){" + "var springy = window.springy = jQuery('#springydemo').springy({"
                    + "graph: graph," + "nodeSelected: function(node){"
                    + "console.log('Node selected: ' + JSON.stringify(node.data));}" + "   });" + "  });"
                    + "  </script>\n";

            result += "<div>\n";
            result += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                    + "Node color: <font color=\"#FFCC00\">Initial Node</font>, <font color=\"#CC00FF\">Final Node</font><br>\n";
            result += "<canvas id=\"springydemo\" width=\"500\" height=\"400\" />\n";
            result += "</div>\n" + "</td></tr>\n";
        }

        result += "</table>\n";

        return result;
    }
    

    // Effects: return a html page of Graph Coverage Computation Web Application
    private String printEdgeForm(String edges, String initialNode, String endNode, List<String> methods)
    {
                            //Title
        String form = "" + "<form name = \"graphCoverageForm\" method=\"post\" action=\"GraphCoverage\"  enctype=\"multipart/form-data\" >\n"
                         + "<div style=\"text-align:center; font-weight:bold; font-size:125%\">Graph Information</div>\n"
                         // Import java file button
                         + "<div style=\"text-align:center;\" name = \"javaImportSection\">"
                                 +"<td align=right width=\"15%\" >Upload Graph from Java file:</td>\n"
                                 + "<input type=\"file\" value=\"Import Graph from Java .class File\" name=\"importJavaFileAction\" enc id=\"file\"/>"
                                 + "<input value=\"Read File\" type=\"submit\" name=\"importJavaFile\" id=\"upload\"  />"
                                 
                                 //Method selector section
                                 +"<p " + SetVisibilityOfMethodDropDown(methods) +"> "
                                         //Method drop down list
                                         + "<select name =\""+ GraphInput.SelectedMethodDropDown.getControlName() + "\">"
                                            +"<option selected disabled>Choose Method Here</option>"
                                            + CreateMethodOptions(methods)                                             
                                          + "</select>"
                                          //Build Graph button
                                          + "<input value=\"Build Graph\" type=\"submit\" name=\"" 
                                                        + GraphInput.CreateGraphButton.getControlName() + "\" id=\"upload\"  />"
                                + "</p>"
                         + "</div>\n"
                         + "<table id = \"tableForm\" border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\">\n"
                         + "<tr>\n" + "  <td width=\"33%\">\n" + "    <table border=\"0\">\n" + "      <tr>\n" + "        <td>\n"
                         + "          Please enter your <font color=\"green\"><b>graph edges</b></font> in the text box below. \n"
                         + "        	Put each edge in one line. Enter edges as pairs of nodes, separated by spaces.(e.g.: 1 3)\n"
                         + "        </td>\n" + "      </tr>\n" + "      <tr align=\"center\">\n"
                         + "        <td> <textarea rows=\"5\" name=\""+ GraphInput.EdgesTextBox.getControlName() + "\" cols=\"25\">\n" + edges + "</textarea></td>\n"
                         + "      </tr>\n" + "		</table>\n" + "  </td>\n" + "  <td width=\"33%\" valign=\"top\">\n"
                         + "	   <table border=\"0\">\n" + "      <tr><td>\n"
                         + "        Enter <font color=\"green\"><b>initial nodes</b></font> below (can be more than one), separated by spaces. If the text box  \n"
                         + "        below is empty, the first node in the left box will be the initial node.\n"
                         + "      </td></tr>\n" + "      <tr align = center>\n" + "        <td>\n"
                         + "          <p> &nbsp;</p><input type=\"text\" name=\""+ GraphInput.InitialNodeTextBox.getControlName() + "\" size=\"5\" value=\"" + initialNode
                         + "\">\n" + "        </td>\n" + "      </tr>\n" + "		</table>\n" + "  </td>\n"
                         + "  <td width=\"34%\" valign=\"top\">\n" + "		<table border=0>\n"
                         + "      <tr><td>Enter <font color=\"green\"><b>final nodes</b></font> below (can be more than one), \n"
                         + "        separated by spaces.\n" + "      </td></tr>\n" + "      <tr align = center>\n"
                         + "        <td>\n"
                         + "          <p> &nbsp;</p><input type=\"text\" name=\""+ GraphInput.EndNodeTextBox.getControlName() + "\" size=\"30\" value=\"" + endNode
                         + "\">\n" + "        </td>\n" + "      </tr>\n" + "		</table>\n" + "  </td>\n" + "</tr>\n"
                         + "</table>\n" + "<table width=\"100%\">\n" + "<tr><td></tr> <tr><td></tr>\n"
                         + "<tr><td></tr> <tr><td></tr>\n" + "<tr>\n"
                         + "  <td align=right width=\"15%\" ><b>Test Requirements:</b></td>\n"
                         + "	 <td width=\"85%\" colspan=\"3\">\n"
                         + "    <input type=\"submit\" value=\"Nodes\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + " 	&nbsp;<input type=\"submit\" value=\"Edges\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "		&nbsp;<input type=\"submit\" value=\"Edge-Pair\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "		&nbsp;<input type=\"submit\" value=\"Simple Paths\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "    &nbsp;<input type=\"submit\" value=\"Prime Paths\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         // +" &nbsp;<input type=\"submit\" value=\"Test\"
                         // name=\"action\">\n"
                         + "  </td>\n" + "</tr>\n" + "<tr><td></tr> <tr><td></tr>\n" + "<tr>\n"
                         + "  <td align=right width = \"15%\"><b>Test Paths:</b></td>\n"
                         + "	 <td width=\"85%\" colspan=\"3\"> Algorithm 1: Slower, more test paths, shorter test paths"
                         + "									&nbsp; <input type=\"submit\" value=\"Node Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "									&nbsp;<input type=\"submit\" value=\"Edge Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "      							&nbsp;<input type=\"submit\" value=\"Edge-Pair Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n "
                         + " 	   								&nbsp;<input type=\"submit\" value=\"Prime Path Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "   </td>\n" + "</tr>\n" + "<tr>\n" + "  <td align=right width = \"15%\"></td>\n"
                         + "	 <td width=\"85%\" colspan=\"3\"> Algorithm 2: Faster, fewer test paths, longer test paths"
                         + "                                    &nbsp; &nbsp; <input type=\"submit\" value=\"Edge Coverage\" name=\""+ GraphInput.Algorithm2ActionButton.getControlName() + "\"> \n"
                         + "                                    &nbsp; <input type=\"submit\" value=\"Edge-Pair Coverage\" name=\""+ GraphInput.Algorithm2ActionButton.getControlName() + "\"> \n"
                         + "                                    &nbsp; <input type=\"submit\" value=\"Prime Path Coverage\" name=\""+ GraphInput.Algorithm2ActionButton.getControlName() + "\"> \n"
                         + "   </td>\n" + "</tr>\n" + "<tr>\n" + "  <td align=right width = \"15%\"></td>\n"
                         + "	 <td width=\"85%\" colspan=\"3\"> Algorithm 1 is our original, not particularly clever, algorithm to find test paths from graph coverage test requirements. In our 2012 ICST\n"
                         + "    paper, \"<em>Better Algorithms to Minimize the Cost of Test Paths</em>,\" we described an algorithm that combines test requirements to produce fewer, \n"
                         + "    but longer test paths (algorithm 2). Users can evaluate the tradeoffs between more but shorter test paths and fewer but longer  \n"
                         + "    test paths and choose the appropriate algorithm.\n" + "   </td>\n" + "</tr>\n"
                         // +"&nbsp;<input type=\"submit\"
                         // value=\"MinimumTestPathViaPrefixGraph\" name=\"action\">\n"
                         // +" &nbsp;<input type=\"submit\" value=\"Prime Path Coverage
                         // using Set Cover\" name=\"action\">\n"
                         // +" </td></tr>\n"
                         + "<tr><td></tr> <tr><td></tr>\n" + "<tr>\n"
                         + "  <td align=right width = \"15%\" ><b>Other Tools:</b></td>\n"
                         + "  <td aligh=\"center\" width=\"85%\" colspan=\"3\">\n"
                         + "	  <input type=\"submit\" value=\"New Graph\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "		&nbsp;<input type=\"submit\" value=\"Data Flow Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "		&nbsp;<input type=\"submit\" value=\"Logic Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n"
                         + "		&nbsp;<input type=\"submit\" value=\"Minimal-MUMCUT Coverage\" name=\""+ GraphInput.ActionButton.getControlName() + "\">\n" + "  </td>\n"
                         + "</tr>\n" + "<tr><td></tr> <tr><td></tr>\n";
        // only display the share button when an action has been submitted
        // i.e. a graph is displayed
        // otherwise, hide the button
        if(showShareButton)
        {
            form = form + "<tr>\n";
        }
        else
        {
            form = form + "<tr style=\"visibility:collapse;\">\n";
        }

        form = form + "  <td align=right width = \"15%\" ><b>Share Graph:</b></td>\n"
                + "  <td aligh=\"center\" width=\"85%\" >\n" + "	  &nbsp;<img onclick=\"javascript:copyToClipboard('"
                + hiddenLink + "')\" src=\"share.png\" style=\"width:70px;height:20px;\"/>" + "  </td>\n" + "</tr>\n"
                + "</table>\n"
        // leave this form out and put it in the printPrimePaths()
        // need to leave it out for enabling infeasible paths
        // +" </form>\n"
        ;

        return form;
    }

    private String SetVisibilityOfMethodDropDown(List<String> methods)
    {
        if(methods.isEmpty())
        {
            return " style=\"visibility:collapse;\" ";
        }
        return " style=\"visibility:visible;\" ";
    }

    private String CreateMethodOptions(List<String> methods)
    {
        StringBuilder options = new StringBuilder();
        for(String method : methods)
        {
            options.append(String.format("<option value=\"%s\">%s</option>", method, method));
        }
        return options.toString();
    }
}
