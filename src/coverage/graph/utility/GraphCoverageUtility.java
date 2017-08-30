package coverage.graph.utility;

import java.util.Collection;
import java.util.List;

import com.drgarbage.visualgraphic.model.VertexBase;

import coverage.graph.Node;
import coverage.web.GraphCoverageInput;
import coverage.web.WebCoverageUtility;
import coverage.web.WebItem;
import coverage.web.controlflow.diagram.WebControlFlowGraphDiagram;
import coverage.web.enums.GraphInput;

public class GraphCoverageUtility
{
    /**
     * Private constructor: this is so clients do not
     * instantiate this class, but rather call it with
     * a static context
     */
    private GraphCoverageUtility()
    {
        
    }

    /***
     * Returns true if the show button should be visible false otherwise
     * 
     * @param action action value from the button
     * @param algorithm2 the value from the algorithm2 button
     * @param webItems the list of web buttons and their values
     * @return
     */
    public static boolean setShowShareButtonVisibility(String action, String algorithm2, Collection<WebItem> webItems)
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

    /***
     * Finds an edge that has a source and destination node of the nodes passed into the function.
     * @param node  node start (or end)
     * @param node2 node end (or start)
     * @param existingEdges the edges list to search through
     * @return
     */
    public static coverage.graph.Edge findEdge(Node node, Node node2, List<coverage.graph.Edge> existingEdges)
    {
        
        for(coverage.graph.Edge existingEdge : existingEdges)
        {
            //if node equals the edge source and node2 is the edge's destination
            if(node.equals(existingEdge.getSrc()))
            {
                if(node2.equals(existingEdge.getDest()))
                {
                    return existingEdge;
                }
            }  
          //if node equals the edge destination and node2 is the edge's source
            else if(node.equals(existingEdge.getDest()))
            {
                if(node2.equals(existingEdge.getSrc()))
                {
                    return existingEdge;
                }
            }
        }
        //no match was found
        return null;                                                 
    }
    
    /***
     * Builds the line number table for the nodes generated from a java .class file. 
     * It uses the diagram created.
     * @param diagram the diagram to show the line numbers associated with the node identifiers
     * @return the html table string with the line numbers and identifier relationships
     */
    private static String buildLineNumberTable(GraphCoverageInput input)
    {
        if(input.getDiagram() != null)
        {
           
            String htmlTableHeader = "<a href=\"#demo\" class=\"accordion\" data-toggle=\"collapse\">Show Node Description</a>\r\n" + 
                                     "  <div id=\"demo\" class=\"panel\">\r\n" +                                                                              
                                     "<td width=\"50\" >\r\n"                       +  
                                     "<table style=\"width:25%\" border=\"1\">\r\n" + 
                                     "    <tr align=\"left\">\r\n"                   + 
                                     "      <th>Node Identifier</th>\r\n"                 + 
                                     "      <th>Description</th> \r\n"               + 
                                     "    </tr>";
            
            StringBuilder tableContents = new StringBuilder();
            List<VertexBase> nodes = input.getDiagram().getDiagram().getChildren();
            
            if(nodes.size() > 0)
            {
                nodes.remove(nodes.size() - 1); //Remove the last node because it isn't a real node}
            }
            
            for(VertexBase node : input.getDiagram().getDiagram().getChildren())
            {           
            tableContents.append(String.format("   <tr>\r\n"                  + 
                                               "      <td>%d</td>\r\n"        + 
                                               "      <td><p>%s</p></td>\r\n" + 
                                               "    </tr>",
                                               node.getId(),
                                               node.getLabel().replace("\n", "<br />")));
            }
            
            String htmlTableFooter = "  </table>\r\n" + 
                                     "</td> &nbsp; "
                                     + "</div>"
                                     + "<script>\r\n" + 
                                     "var acc = document.getElementsByClassName(\"accordion\");\r\n" + 
                                     "var i;\r\n" + 
                                     "\r\n" + 
                                     "for (i = 0; i < acc.length; i++) {\r\n" + 
                                     "    acc[i].onclick = function(){\r\n" + 
                                     "        this.classList.toggle(\"active\");\r\n" + 
                                     "        var panel = this.nextElementSibling;\r\n" + 
                                     "        if (panel.style.display === \"block\") {\r\n" + 
                                     "            panel.style.display = \"none\";\r\n" + 
                                     "        } else {\r\n" + 
                                     "            panel.style.display = \"block\";\r\n" + 
                                     "        }\r\n" + 
                                     "    }\r\n" + 
                                     "}\r\n" + 
                                     "</script>";
            
            
            return String.format("%s%s%s", htmlTableHeader, tableContents.toString(), htmlTableFooter);
        }
        
        return "\r\n";
    }

    /***
     *  Effects: return a html page of Graph Coverage Computation Web Application
     *  
     *  This builds the text input for the initial node, end node, and edges portion of the webpage as well as all of
     *  the buttons for the coverage types and the file import.
     * @param input graph coverage input to fill out the user inputs as well as the methods to select
     * @return the html page of the graph coverage computation web application
     */
    public static String printEdgeForm(GraphCoverageInput input)
    {
        String edges = input.getEdges();
        String initialNode = input.getInitialNode();
        String endNode    = input.getEndNode();
        if(edges == null)
        {
            edges = "";
        }
        
        if(initialNode == null)
        {
            initialNode = "";
        }
        
        if(endNode == null)
        {
            endNode = "";
        }
                            //Title
        String form = "" + "<form name = \"graphCoverageForm\" method=\"post\" action=\"GraphCoverage\"  enctype=\"multipart/form-data\" >\n"
                         + "<div style=\"text-align:center; font-weight:bold; font-size:125%\">Graph Information</div>\n"
                         // Import java file section
                         + "<div style=\"text-align:center;\" name = \"javaImportSection\">"
                                 +"<td align=right width=\"15%\" >Upload Graph from Java .class file:</td>\n"
                                 //file input
                                 + "<input type=\"file\" value=\"Import Graph from Java .class File\" name=\"importJavaFileAction\" enc id=\"file\"/>"
                                 //Read file button
                                 + " &nbsp; <input value=\"Read File\" type=\"submit\" name=\"importJavaFile\" id=\"upload\"  />"                                 
                                 //Method selector section
                                 +"<p " + GraphCoverageUtility.setVisibilityOfMethodDropDown(input.getMethods()) +"> "
                                         //Method drop down list
                                         + "<select name =\""+ GraphInput.SelectedMethodDropDown.getControlName() + "\">"
                                            +"<option selected disabled>Choose Method Here</option>"
                                            + GraphCoverageUtility.createMethodOptions(input.getMethods(), input.getSelectedMethod())                                             
                                          + "</select>"
                                          //Build Graph button
                                          + "&nbsp;  <input value=\"Build Graph\" type=\"submit\" name=\"" 
                                                        + GraphInput.CreateGraphButton.getControlName() + "\" id=\"upload\"  />"
                                + "</p>" 
                         + "</div>\n"
                         + buildLineNumberTable(input)
                         + "<table id = \"tableForm\" border=\"1\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\"  bgcolor=\"#EEFFEE\">\n"
                         + "<tr>\n" + "  <td width=\"33%\">\n" + "    <table border=\"0\">\n" + "      <tr>\n" + "        <td>\n"
                         + "          Please enter your <font color=\"green\"><b>graph edges</b></font> in the text box below. \n"
                         + "        	Put each edge in one line. Enter edges as pairs of nodes, separated by spaces.(e.g.: 1 3) \nOptional to add a label for the edge (e.g. 1 3 true)\n"
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
        if(input.getShowShareButton())
        {
            form = form + "<tr>\n";
        }
        else
        {
            form = form + "<tr style=\"visibility:collapse;\">\n";
        }
    
        form = form + "  <td align=right width = \"15%\" ><b>Share Graph:</b></td>\n"
                + "  <td aligh=\"center\" width=\"85%\" >\n" + "	  &nbsp;<img onclick=\"javascript:copyToClipboard('"
                + input.getHiddenLink() + "')\" src=\"share.png\" style=\"width:70px;height:20px;\"/>" + "  </td>\n" + "</tr>\n"
                + "</table>\n"
        // leave this form out and put it in the printPrimePaths()
        // need to leave it out for enabling infeasible paths
        // +" </form>\n"
        ;
    
        return form;
    }

    
    private static String getValueOfToggle(GraphCoverageInput input)
    {
        if(input.showNodeDescriptionDiagramTable())
        {
            return " checked=\"on\" ";
        }
        return " checked=\"off\" ";
    }

    private static String getLabelHiddenNodeDescriptionTable(GraphCoverageInput input)
    {       
        return input.getHideTableName();
    }

    /***
     * Hides the methods dropdown if there are no methods to choose from
     * @param methods
     * @return
     */
    private static String setVisibilityOfMethodDropDown(List<String> methods)
    {
        if(methods.isEmpty())
        {
            return " style=\"visibility:collapse;\" ";
        }
        return " style=\"visibility:visible;\" ";
    }

    /***
     * Creates the method drop down values
     * @param methods
     * @param selectedMethod TODO
     * @return
     */
    private static String createMethodOptions(List<String> methods, String selectedMethod)
    {
        StringBuilder options = new StringBuilder();
        for(String method : methods)
        {
            String selected  = "";
            if(method.equals(selectedMethod))
            {
                selected = "selected=\"selected\"";
            }
            options.append(String.format("<option %s value=\"%s\">%s</option>", selected, method, method));
        }
        return options.toString();
    }

    /***
     * The header html for the Graph Coverage webpage
     * @return
     */
    public static String getHeader()
    {
        return "<html>\n" + "<head>\n" + "<title>Graph Coverage</title>\n" + "</head>\n"
                + "<body bgcolor=\"#DDEEDD\">\n"
                + "<p style=\"text-align:center;font-size:150%;font-weight:bold\">Graph Coverage Web Application</p>\n"
                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" 
                 //style for collapsable pane
                +"<style> /* Style the buttons that are used to open and close the accordion panel */\r\n" + 
                "button.accordion {\r\n" + 
                "    background-color: #eee;\r\n" + 
                "    color: #444;\r\n" + 
                "    cursor: pointer;\r\n" + 
                "    padding: 18px;\r\n" + 
                "    width: 100%;\r\n" + 
                "    text-align: left;\r\n" + 
                "    border: none;\r\n" + 
                "    outline: none;\r\n" + 
                "    transition: 0.4s;\r\n" + 
                "}\r\n" + 
                "\r\n" + 
                "/* Add a background color to the button if it is clicked on (add the .active class with JS), and when you move the mouse over it (hover) */\r\n" + 
                "button.accordion.active, button.accordion:hover {\r\n" + 
                "    background-color: #ddd;\r\n" + 
                "}\r\n" + 
                "\r\n" + 
                "/* Style the btn-btn-info collapse. Note: hidden by default */\r\n" + 
                "div.panel {\r\n" + 
                "    padding: 0 18px;\r\n" + 
                "    background-color:#DDEEDD;\r\n" + 
                "    display: none;\r\n" + 
                "}</style>"+
             
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

}
