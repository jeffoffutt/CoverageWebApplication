package coverage.web.controlflow.diagram;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.drgarbage.bytecode.instructions.BranchInstruction;
import com.drgarbage.bytecode.instructions.LookupSwitchInstruction;
import com.drgarbage.bytecode.instructions.LookupSwitchInstruction.MatchOffsetEntry;
import com.drgarbage.bytecode.instructions.TableSwitchInstruction;
import com.drgarbage.algorithms.BasicBlockGraphVisitor;
import com.drgarbage.algorithms.HierarchicalLayout;
import com.drgarbage.asm.ClassReader;
import com.drgarbage.asm.visitor.FilteringCodeVisitor;
import com.drgarbage.asm.visitor.MethodFilteringVisitor;
import com.drgarbage.bytecode.ByteCodeConstants;
import com.drgarbage.bytecode.LineNumberTableEntry;
import com.drgarbage.bytecode.instructions.AbstractInstruction;
import com.drgarbage.controlflowgraph.ControlFlowGraphException;
import com.drgarbage.controlflowgraph.ControlFlowGraphUtils;
import com.drgarbage.controlflowgraph.intf.GraphExtentionFactory;
import com.drgarbage.controlflowgraph.intf.GraphUtils;
import com.drgarbage.controlflowgraph.intf.IBasicBlock;
import com.drgarbage.controlflowgraph.intf.IDirectedGraphExt;
import com.drgarbage.controlflowgraph.intf.IEdgeExt;
import com.drgarbage.controlflowgraph.intf.IEdgeListExt;
import com.drgarbage.controlflowgraph.intf.INodeExt;
import com.drgarbage.controlflowgraph.intf.INodeListExt;
import com.drgarbage.controlflowgraph.intf.INodeType;
import com.drgarbage.javalang.JavaLangUtils;
import com.drgarbage.visualgraphic.model.CommentElement;
import com.drgarbage.visualgraphic.model.Connection;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;
import com.drgarbage.visualgraphic.model.DecisionVertex;
import com.drgarbage.visualgraphic.model.ExitVertex;
import com.drgarbage.visualgraphic.model.GetVertex;
import com.drgarbage.visualgraphic.model.GotoJumpVertex;
import com.drgarbage.visualgraphic.model.InvokeVertex;
import com.drgarbage.visualgraphic.model.RectangularVertex;
import com.drgarbage.visualgraphic.model.ReturnVertex;
import com.drgarbage.visualgraphic.model.StartVertex;
import com.drgarbage.visualgraphic.model.StringLine;
import com.drgarbage.visualgraphic.model.SwitchVertex;
import com.drgarbage.visualgraphic.model.VertexBase;

public class ControlFlowDiagramGraphFactory
{
    public static final String VIRTUAL_BACK_EDGE_TOOLTIP_TEXT = "Virtual back edge";
    public static final String VIRTUAL_START_NODE_TEXT = "START";
    public static final String VIRTUAL_EXIT_NODE_TEXT  = "EXIT";

    public static final String VIRTUAL_START_NODE_TOOLTIP_TEXT = "Virtual start vertex";
    public static final String VIRTUAL_EXIT_NODE_TOOLTIP_TEXT  = "Virtual exit vertex";

    /**
     * Creates source code graph diagram.
     * 
     * @param graph
     * @return diagram
     */
    public static ControlFlowGraphDiagram createSourceCodeGraphDiagram(IDirectedGraphExt graph)
    {

        boolean createSourcecodeBlockLongDescr = true;

        int nodeWeigth = 78;
        int nodeHeight = 36;
        INodeExt node = null;

        /* build Diagram */
        ControlFlowGraphDiagram diagram = new ControlFlowGraphDiagram();

        VertexBase r = null;
        for (int i = 0; i < graph.getNodeList().size(); i++)
        {
            node = graph.getNodeList().getNodeExt(i);

            StringBuffer buf = new StringBuffer();
            if (createSourcecodeBlockLongDescr)
            {
                buf.append(node.getToolTipText());
            }
            else
            {
                buf.append(String.valueOf(node.getByteCodeOffset()));
            }

            String lb = buf.toString();

            /* create visual Node */
            switch (node.getVertexType())
            {
            case INodeType.NODE_TYPE_SIMPLE:
                // r = new BasicBlockVertex();
                r = new RectangularVertex();

                if (createSourcecodeBlockLongDescr)
                {
                    // TODO: define capital letter height = 15 and width = 8
                    StringLine sl = new StringLine(lb);
                    nodeWeigth = 7 * sl.getMaxLineLenght() + 20;
                    nodeHeight = 15 * sl.getNumberOfLines() + 10;
                }

                break;
            case INodeType.NODE_TYPE_IF:
                r = new DecisionVertex();
                if (createSourcecodeBlockLongDescr)
                {
                    // TODO: define capital letter height = 15 and width = 8
                    StringLine sl = new StringLine(lb);
                    nodeWeigth = (7 * sl.getMaxLineLenght()) * 2;
                    nodeHeight = (15 * sl.getNumberOfLines()) * 2;
                }
                break;
            case INodeType.NODE_TYPE_RETURN:
                r = new ReturnVertex();
                if (createSourcecodeBlockLongDescr)
                {
                    // TODO: define capital letter height = 15 and width = 8
                    StringLine sl = new StringLine(lb);
                    nodeWeigth = 7 * sl.getMaxLineLenght() + 20;
                    nodeHeight = 15 * sl.getNumberOfLines() + 10;
                }

                break;
            case INodeType.NODE_TYPE_GOTO_JUMP:
                r = new GotoJumpVertex();
                break;
            case INodeType.NODE_TYPE_SWITCH:
                r = new SwitchVertex();
                if (createSourcecodeBlockLongDescr)
                {
                    StringLine sl = new StringLine(lb);
                    // TODO: define capital letter height = 15 and width = 8
                    nodeWeigth = (7 * sl.getMaxLineLenght()) * 2;
                    nodeHeight = (15 * sl.getNumberOfLines()) + 10;
                }
                break;
            case INodeType.NODE_TYPE_INVOKE:
                r = new InvokeVertex();
                break;
            case INodeType.NODE_TYPE_GET:
                r = new GetVertex();
                break;
            case INodeType.NODE_TYPE_START:
                r = new StartVertex();
                break;
            case INodeType.NODE_TYPE_EXIT:
                r = new ExitVertex();
                break;
            case INodeType.NODE_TYPE_COMMENT:
                r = new CommentElement();
                break;
            default:
                r = new RectangularVertex();
            }

            if (node.getVertexType() == INodeType.NODE_TYPE_START || node.getVertexType() == INodeType.NODE_TYPE_EXIT)
            {
                /* do nothing */
            }
            else
            {
                /*
                 * r.setLabel(" " + node.getByteCodeOffset() + "  " + node.getByteCodeString() +
                 * " ");
                 */
                r.setLabel(lb);
                if (createSourcecodeBlockLongDescr)
                {
                    r.setLongDescrUsed(true);
                    r.setToolTip("Source line: " + node.getByteCodeOffset());
                }
                else
                {
                    r.setToolTip(node.getToolTipText());
                }
                r.setSize(new Dimension(nodeWeigth, nodeHeight));
            }

            node.setData(r);
            diagram.addChild(r);
        }

        /* create connections */
        createConnections(graph.getEdgeList());

        /* create additions */
        createAdditions(graph, diagram);

        /* layout graph */
        IDirectedGraphExt graph2 = LayoutAlgorithmsUtil.generateGraph(diagram);
        new HierarchicalLayout().visit(graph2);

        INodeListExt listNode = graph2.getNodeList();
        VertexBase vb = null;
        for (int i = 0; i < listNode.size(); i++)
        {
            node = listNode.getNodeExt(i);
            vb = (VertexBase) node.getData();
            vb.setLocation(new Point(node.getX(), node.getY()));
        }

        return diagram;
    }

    /**
     * Build sourcecode graph diagram.
     * 
     * @param classPath
     *            , the class path
     * @param packageName
     *            , the name of the package
     * @param className
     *            , the name of the class
     * @param methodName
     *            , the name of the method
     * @param methodSig
     *            , the method signature
     * @throws ControlFlowGraphException
     *             , IOException, InvalidByteCodeException
     */
    public static ControlFlowGraphDiagram buildSourceControlFlowDiagram(String[] classPath, String packageName,
            String className, String methodName, String methodSig, String fileName)
            throws ControlFlowGraphException, IOException
    {
        /* get preferences */
        boolean createStartNode = true;
        boolean createExitNode = true;
        boolean createBackEdge = false;

        /* generate control flow graph */
        IDirectedGraphExt graph = generateSourceCodeGraph(classPath, packageName, className, methodName, methodSig,
                createStartNode, createExitNode, createBackEdge);
        Map<String, Object> attr = graph.getUserObject();
        attr.put(ByteCodeConstants.NAME, className + "." + methodName + methodSig);
        return createSourceCodeGraphDiagram(graph);
    }

    /**
     * Generates a source code graph for the defined method.
     * 
     * @param classPath
     *            class path
     * @param packageName
     *            package name
     * @param className
     *            class name
     * @param methodName
     *            method name
     * @param methodSig
     *            method signature
     * @param createStartVertex
     *            <code>true</code> if the virtual start vertex has to be created,
     *            <code>false</code> otherwise
     * @param createExitvertex
     *            <code>true</code> if the virtual exit vertex has to be created,
     *            <code>false</code> otherwise
     * @param createBackEdge
     *            <code>true</code> if the virtual back edge has to be created,
     *            <code>false</code> otherwise
     * @return the control flow graph
     * @throws ControlFlowGraphException
     *             if the graph could not be created.
     * @throws IOException
     *             if the class file could not be opened.
     */
    public static IDirectedGraphExt generateSourceCodeGraph(String classPath[], String packageName, String className,
            String methodName, String methodSig, boolean createStartVertex, boolean createExitvertex,
            boolean createBackEdge) throws ControlFlowGraphException, IOException
    {
        FilteringCodeVisitor codeVisitor = getInstructionList(classPath, packageName, className, methodName, methodSig);
        return generateSourceCodeGraph(codeVisitor.getInstructions(), codeVisitor.getLineNumberTable(),
                createStartVertex, createExitvertex, createBackEdge);
    }

    /**
     * Generates a control flow graph from an instruction list.
     * 
     * @param instructions
     *            - list of synchronized instructions
     * @return The control flow graph
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    public static IDirectedGraphExt generateControlFlowGraph(List<AbstractInstruction> instructions)
            throws ControlFlowGraphException, IOException
    {
        return generateControlFlowGraph(instructions, null, false, false, false, false);
    }

    /**
     * Generates a control flow graph from an instruction list.
     * 
     * @param instructions
     *            - list of synchronized instructions
     * @param setReferenceToIntsrutionList
     *            - flag if a reference to the original instruction object has to be
     *            set
     * @return The control flow graph
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    public static IDirectedGraphExt generateControlFlowGraph(List<AbstractInstruction> instructions,
            boolean setReferenceToIntsrutionList) throws ControlFlowGraphException, IOException
    {
        return generateControlFlowGraph(instructions, null, false, false, false, setReferenceToIntsrutionList);
    }

    /**
     * Generates a control flow graph from an instruction list.
     * 
     * @param instructions
     *            - list of synchronized instructions
     * @param lineNumberTable
     *            - the line number table
     * @param createStartVertex
     *            - flag if a virtual start node has to be created
     * @param createExitvertex
     *            - flag if a virtual exit node has to be created
     * @param createBackEdge
     *            - flag if a virtual back edge has to be created
     * @param setReferenceToIntsrutionList
     *            - flag if a reference to the original instruction object has to be
     *            set
     * @return The control flow graph
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    public static IDirectedGraphExt generateControlFlowGraph(List<AbstractInstruction> instructions,
            LineNumberTableEntry[] lineNumberTable, boolean createStartVertex, boolean createExitvertex,
            boolean createBackEdge, boolean setReferenceToIntsrutionList) throws ControlFlowGraphException, IOException
    {
        AbstractInstruction currentInstruction = null;
        HashMap<Integer, INodeExt> hashTable = new HashMap<Integer, INodeExt>();

        /* Create new Control Flow Graph */
        IDirectedGraphExt cfg = GraphExtentionFactory.createDirectedGraphExtention();
        INodeListExt nodes = cfg.getNodeList();
        IEdgeListExt edges = cfg.getEdgeList();

        /* add vertices */
        Integer vertex = null;
        int nodeType = -1;
        String instrVerbose = null;
        for (int i = 0; i < instructions.size(); i++)
        {

            currentInstruction = (AbstractInstruction) instructions.get(i);
            vertex = new Integer(currentInstruction.getOffset());

            /* create vertex property Object */
            nodeType = ControlFlowGraphUtils.getInstructionNodeType(currentInstruction.getOpcode());
            instrVerbose = currentInstruction.getOpcodeMnemonic();

            /* add vertex */
            INodeExt node = setReferenceToIntsrutionList ? GraphExtentionFactory.createNodeExtention(currentInstruction)
                    : GraphExtentionFactory.createNodeExtention(null);
            node.setByteCodeOffset(currentInstruction.getOffset());
            node.setByteCodeString(instrVerbose);
            node.setToolTipText(" " + node.getByteCodeOffset() + " - " + node.getByteCodeString() + " ");
            node.setVertexType(nodeType);

            nodes.add(node);
            hashTable.put(vertex, node);
        }

        parseInstructionList(instructions, edges, hashTable);

        INodeExt startNode = null, exitNode = null;
        /* create start Vertex */
        if (createStartVertex)
        {
            startNode = GraphExtentionFactory.createNodeExtention(null);
            startNode.setToolTipText(VIRTUAL_START_NODE_TOOLTIP_TEXT);
            startNode.setVertexType(INodeType.NODE_TYPE_START);
            nodes.add(startNode);

            /* create edge to the first node */
            edges.add(GraphExtentionFactory.createEdgeExtention(startNode, hashTable.get(0)));
        }

        /* create exit Vertex */
        if (createExitvertex)
        {
            exitNode = GraphExtentionFactory.createNodeExtention(null);
            exitNode.setToolTipText(VIRTUAL_EXIT_NODE_TOOLTIP_TEXT);
            exitNode.setVertexType(INodeType.NODE_TYPE_EXIT);
            nodes.add(exitNode);

            /* create edges to all exit vertices */
            for (int i = 0; i < nodes.size(); i++)
            {
                INodeExt n = nodes.getNodeExt(i);
                if (n.getVertexType() == INodeType.NODE_TYPE_RETURN)
                {
                    /* create an edge */
                    edges.add(GraphExtentionFactory.createEdgeExtention(n, exitNode));
                }
            }

        }

        /* create back edge */
        if (createBackEdge)
        {
            if (startNode != null & exitNode != null)
            {
                IEdgeExt edge = GraphExtentionFactory.createEdgeExtention(exitNode, startNode);
                edge.setData(VIRTUAL_BACK_EDGE_TOOLTIP_TEXT);
                edges.add(edge);
            }
            else
            {
                throw new ControlFlowGraphException("Cannot create virtual back edge. Start or Exit node missing.");//$NON-NLS-1$
            }
        }

        /* use user object to store the line number table representation */
        if (lineNumberTable != null)
        {
            String lineNumberTableStr = createLineNumberTableRespresenation(lineNumberTable);
            cfg.getUserObject().put(ByteCodeConstants.LINE_NUMBER_TABLE, lineNumberTableStr);
        }

        return cfg;
    }

    /**
     * Parse instruction list and generates the list of edges for the control flow
     * graph.
     * 
     * @param instructions
     * @param edges
     * @param hashTable
     */
    private static void parseInstructionList(List<AbstractInstruction> instructions, IEdgeListExt edges,
            HashMap<Integer, INodeExt> hashTable)
    {

        AbstractInstruction currentInstruction = null;

        /* get first instruction */
        if (instructions.size() != 0)
        {
            currentInstruction = (AbstractInstruction) instructions.get(0);
        }

        /* start loop */
        for (int i = 1; i < instructions.size(); i++)
        {

            /* handle TableSwitchInstruction */
            if (currentInstruction instanceof TableSwitchInstruction)
            {

                TableSwitchInstruction tableSwitchInstruction = (TableSwitchInstruction) currentInstruction;
                int start = tableSwitchInstruction.getOffset();
                int[] switchOffsets = tableSwitchInstruction.getJumpOffsets();
                int defaultOffset = tableSwitchInstruction.getDefaultOffset();

                /* get node reference */
                Integer startVertex = new Integer(start);
                INodeExt startNode = hashTable.get(startVertex);

                int low = tableSwitchInstruction.getLow();

                StringBuffer buf = new StringBuffer();
                // buf.append(startNode.getByteCodeString());
                for (int off = 0; off < switchOffsets.length; off++)
                {
                    buf.append("      ");
                    buf.append(String.valueOf(low + off));
                    buf.append(" => ");
                    buf.append(switchOffsets[off] + start);
                    buf.append("\n");
                }
                buf.append("      default: ");
                buf.append(defaultOffset + start);

                startNode.setLongDescr(buf.toString());

                /* insert switch arcs */
                Integer switchVertex = null;
                IEdgeExt newEdge;
                // int low = tableSwitchInstruction.getLow();
                for (int j = 0; j < switchOffsets.length; j++)
                {
                    /* switch offsets are relative */
                    switchVertex = new Integer((switchOffsets[j] + start));
                    newEdge = GraphExtentionFactory.createEdgeExtention(startNode, hashTable.get(switchVertex));
                    newEdge.setData(String.valueOf(low + j)); /* set Text label */
                    edges.add(newEdge);
                }

                /* default */
                switchVertex = new Integer((defaultOffset + start));
                newEdge = GraphExtentionFactory.createEdgeExtention(startNode, hashTable.get(switchVertex));
                newEdge.setData("default"); /* set text label */
                edges.add(newEdge);

                if (i < instructions.size())
                {
                    currentInstruction = (AbstractInstruction) instructions.get(i);
                }

                continue;
            }
            /* handle LookupSwitchInstruction */
            else if (currentInstruction instanceof LookupSwitchInstruction)
            {

                LookupSwitchInstruction lookupSwitchInstruction = (LookupSwitchInstruction) currentInstruction;

                int start = lookupSwitchInstruction.getOffset();
                List<MatchOffsetEntry> matchOffsetPairs = lookupSwitchInstruction.getMatchOffsetPairs();
                int defaultOffset = lookupSwitchInstruction.getDefaultOffset();

                /* get node reference */
                Integer startVertex = new Integer(start);
                INodeExt startNode = hashTable.get(startVertex);

                StringBuffer buf = new StringBuffer();
                // buf.append(startNode.getByteCodeString());
                // buf.append("\n");
                for (MatchOffsetEntry e : matchOffsetPairs)
                {
                    buf.append("      ");
                    buf.append(e.getMatch());
                    buf.append(" => ");

                    int val = e.getOffset();
                    buf.append(String.valueOf(val + start));
                    buf.append("\n");

                }
                buf.append("      default: ");
                buf.append(defaultOffset + start);

                startNode.setLongDescr(buf.toString());

                Integer offsetVertex = null;
                IEdgeExt newEdge;
                for (int k = 0; k < matchOffsetPairs.size(); k++)
                {
                    MatchOffsetEntry matchOffsetEntry = (MatchOffsetEntry) matchOffsetPairs.get(k);
                    offsetVertex = new Integer(matchOffsetEntry.getOffset() + start);
                    newEdge = GraphExtentionFactory.createEdgeExtention(startNode, hashTable.get(offsetVertex));
                    newEdge.setData(String.valueOf(matchOffsetEntry.getMatch()));/* set text label */
                    edges.add(newEdge);
                }

                /* default off */
                offsetVertex = new Integer((defaultOffset + start));
                newEdge = GraphExtentionFactory.createEdgeExtention(startNode, hashTable.get(offsetVertex));
                newEdge.setData("default");
                edges.add(newEdge);

                if (i < instructions.size())
                {
                    currentInstruction = (AbstractInstruction) instructions.get(i);
                }

                continue;
            }
            else if (currentInstruction instanceof BranchInstruction)
            {
                /*
                 * handle BranchInstruction if_ne, if_icmpeq ... goto is branch instruction as
                 * well
                 */

                /* create arc */
                Integer start = new Integer(currentInstruction.getOffset());

                /*
                 * The branch target is a relative byte code address. 13 ifle 11; 16 new 37; 19
                 * dup; 20 invokespecial 39; 23 athrow; 24 return;
                 */
                Integer branchIntruction = new Integer((((BranchInstruction) currentInstruction).getBranchOffset()
                        + ((BranchInstruction) currentInstruction).getOffset()));
                IEdgeExt newEdge = GraphExtentionFactory.createEdgeExtention(hashTable.get(start),
                        hashTable.get(branchIntruction));
                edges.add(newEdge);

                /* create second arc only if the instruction not a goto */
                if (!ControlFlowGraphUtils.isJumpInstruction(currentInstruction.getOpcode()))
                {
                    newEdge.setData("true");
                    currentInstruction = (AbstractInstruction) instructions.get(i);
                    newEdge = GraphExtentionFactory.createEdgeExtention(hashTable.get(start),
                            hashTable.get(currentInstruction.getOffset()));
                    newEdge.setData("false");
                    edges.add(newEdge);
                }
                else
                {
                    currentInstruction = (AbstractInstruction) instructions.get(i);
                }

                /* to the begin of the loop */
                continue;
            }

            /* handle return instruction return and athrow */
            if (ControlFlowGraphUtils.isReturn(currentInstruction.getOpcode()))
            {

                /* no arc has to be created */

                currentInstruction = (AbstractInstruction) instructions.get(i);
                /* to the begin of the loop */
                continue;
            }

            /*
             * Default create an arc between two sequent instructions. For example: 16 new
             * 37; 19 dup; 20 invokespecial 39;
             * 
             * Arcs 16 -> 19 19 -> 20
             */
            if (i < instructions.size())
            {
                Integer start = new Integer(currentInstruction.getOffset());
                currentInstruction = (AbstractInstruction) instructions.get(i);

                edges.add(GraphExtentionFactory.createEdgeExtention(hashTable.get(start),
                        hashTable.get(currentInstruction.getOffset())));

                /* to the begin of the loop */
            }

        } /* end for main loop */

        /* check if the last instruction is a goto instruction */

        if (ControlFlowGraphUtils.isJumpInstruction(currentInstruction.getOpcode()))
        {

            /* create arc */
            Integer start = new Integer(currentInstruction.getOffset());

            /*
             * The branch target is a relative byte code address. 10 goto -10;
             */
            Integer branchIntruction = new Integer((((BranchInstruction) currentInstruction).getBranchOffset()
                    + ((BranchInstruction) currentInstruction).getOffset()));
            IEdgeExt newEdge = GraphExtentionFactory.createEdgeExtention(hashTable.get(start),
                    hashTable.get(branchIntruction));
            edges.add(newEdge);

        }

    }

    /**
     * Generates a control flow graph from an instruction list. <br>
     * NOTE: This method is used for compatibility. Used the method
     * {@link #generateControlFlowGraph(List,LineNumberTableEntry[],boolean,boolean,boolean,boolean)
     * generateControlFlowGraph} instead.
     * 
     * @param instructions
     *            - list of synchronized instructions
     * @param lineNumberTable
     *            - the line number table
     * @param createStartVertex
     *            - flag if a virtual start node has to be created
     * @param createExitvertex
     *            - flag if a virtual exit node has to be created
     * @param createBackEdge
     *            - flag if a virtual back edge has to be created
     * @return The control flow graph
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    public static IDirectedGraphExt generateControlFlowGraph(List<AbstractInstruction> instructions,
            LineNumberTableEntry[] lineNumberTable, boolean createStartVertex, boolean createExitvertex,
            boolean createBackEdge) throws ControlFlowGraphException, IOException
    {
        return generateControlFlowGraph(instructions, null, false, false, false, false);
    }

    /**
     * Generates a source code graph for the defined method.
     * 
     * @param instructions
     *            List of instructions
     * @param lineNumberTable
     *            the line number table
     * @param createStartVertex
     *            <code>true</code> if the virtual start vertex has to be created,
     *            <code>false</code> otherwise
     * @param createExitvertex
     *            <code>true</code> if the virtual exit vertex has to be created,
     *            <code>false</code> otherwise
     * @param createBackEdge
     *            <code>true</code> if the virtual back edge has to be created,
     *            <code>false</code> otherwise
     * @return the control flow graph
     * @throws ControlFlowGraphException
     *             if the graph could not be created.
     * @throws IOException
     *             if the class file could not be opened.
     */
    @SuppressWarnings("unchecked")
    public static IDirectedGraphExt generateSourceCodeGraph(List<AbstractInstruction> instructions,
            LineNumberTableEntry[] lineNumberTable, boolean createStartVertex, boolean createExitvertex,
            boolean createBackEdge) throws ControlFlowGraphException, IOException
    {
        if (lineNumberTable == null)
        {
            throw new ControlFlowGraphException("Line number table attribute missing."); //$NON-NLS-1$
        }

        /* generate bytecode graph */
        IDirectedGraphExt cfg = generateControlFlowGraph(instructions, null, false, false, false);

        /* create bytecode node tree structure */
        Map<Integer, INodeExt> treeNodeTable = new TreeMap<Integer, INodeExt>();
        INodeListExt byteCodeNodes = cfg.getNodeList();
        INodeExt byteCodeNode = null, sourceCodeNode = null;
        int line = -1;
        Integer lineNumber = null;
        for (int i = 0; i < byteCodeNodes.size(); i++)
        {
            byteCodeNode = byteCodeNodes.getNodeExt(i);
            line = getSourceCodeLine(lineNumberTable, byteCodeNode.getByteCodeOffset());

            lineNumber = new Integer(line);

            /* assign line number to the byte code node */
            byteCodeNode.setData(lineNumber);

            sourceCodeNode = treeNodeTable.get(lineNumber);
            if (sourceCodeNode == null)
            {
                List<INodeExt> byteCode = new ArrayList<INodeExt>();
                byteCode.add(byteCodeNode);

                /* create new source code node */
                sourceCodeNode = GraphExtentionFactory.createNodeExtention(byteCode);

                /* set list of bytecode instructions */
                sourceCodeNode.setData(byteCode);

                /* set line number */
                sourceCodeNode.setByteCodeOffset(line);

                /* set text */
                /* bug #108 */
                /* sourceCodeNode.setByteCodeString("line"); */

                /* set line number as y coordinate */
                sourceCodeNode.setY(line - 1);

                treeNodeTable.put(lineNumber, sourceCodeNode);
            }
            else
            {
                /* add byte code instruction to existing source code node */
                List<INodeExt> byteCode = (List<INodeExt>) sourceCodeNode.getData();
                byteCode.add(byteCodeNode);
            }
        }

        /* create Source Code Graph */
        IDirectedGraphExt sourceCodeGraph = GraphExtentionFactory.createDirectedGraphExtention();
        INodeListExt nodes = sourceCodeGraph.getNodeList();
        IEdgeListExt edges = sourceCodeGraph.getEdgeList();

        /* initialize source code edges */
        IEdgeExt byteCodeEdge = null;
        Integer source, target;
        IEdgeExt newEdge = null;
        INodeExt sourceNode = null, targetNode = null;
        Map<String, IEdgeExt> treeEdgeTable = new HashMap<String, IEdgeExt>();
        IEdgeListExt byteCodeEdges = cfg.getEdgeList();
        for (int i = 0; i < byteCodeEdges.size(); i++)
        {
            byteCodeEdge = byteCodeEdges.getEdgeExt(i);
            source = (Integer) byteCodeEdge.getSource().getData();
            target = (Integer) byteCodeEdge.getTarget().getData();

            sourceNode = treeNodeTable.get(source);
            targetNode = treeNodeTable.get(target);
            if (sourceNode == targetNode)
            {
                continue;
            }

            /*
             * check if there are a some edges assigned to the node with the line = 1. It is
             * a virtual line set by compiler
             */
            // if(sourceNode.getByteCodeOffset() == 1 || targetNode.getByteCodeOffset() ==
            // 1){
            // continue;
            // }

            /* check if the edge already exists */
            String key = createKey(sourceNode, targetNode);
            if (!treeEdgeTable.containsKey(key))
            {
                newEdge = GraphExtentionFactory.createEdgeExtention(sourceNode, targetNode);
                newEdge.setData(byteCodeEdge.getData());/* copy text label */
                treeEdgeTable.put(key, newEdge);
                edges.add(newEdge);
            }
            else
            {
                IEdgeExt e = treeEdgeTable.get(key);
                Object o1 = byteCodeEdge.getData();
                if (o1 != null)
                {
                    Object o2 = e.getData();
                    if (o2 != null)
                    {
                        String s = o2.toString() + ", " + o1.toString();
                        e.setData(s);
                    }
                    else
                    {
                        e.setData(o1);
                    }
                }
            }
        }

        /* add nodes */
        Iterator<INodeExt> it = treeNodeTable.values().iterator();
        INodeExt node = null;
        while (it.hasNext())
        {
            node = it.next();

            /*
             * check if there is a node with the line = 1. It is a virtual line set by
             * compiler
             */
            // if(node.getByteCodeOffset() == 1){
            // //System.out.println(node);
            // continue;
            // }

            String s = createToolTip(node);
            node.setToolTipText(s);
            node.setByteCodeString(s);

            if (node.getOutgoingEdgeList().size() == 0)
            {
                node.setVertexType(INodeType.NODE_TYPE_RETURN);
                // } else if(node.getOutgoingEdgeList().size() == 2){
                // node.setVertexType(INodeType.NODE_TYPE_IF);
                // }else if(node.getOutgoingEdgeList().size() > 2){
                // node.setVertexType(INodeType.NODE_TYPE_SWITCH);
            }
            else
            {
                node.setVertexType(getNodeType(node));
            }
            nodes.add(node);
        }

        INodeExt startNode = null, exitNode = null;
        /* create start Vertex */
        if (createStartVertex)
        {
            startNode = GraphExtentionFactory.createNodeExtention(null);
            startNode.setToolTipText(VIRTUAL_START_NODE_TOOLTIP_TEXT);
            startNode.setVertexType(INodeType.NODE_TYPE_START);
            nodes.add(startNode);

            /* get line number of the first node */
            line = getSourceCodeLine(lineNumberTable, 0);

            /* create edge to the first node */
            INodeExt first = treeNodeTable.get(line);
            IEdgeExt e = GraphExtentionFactory.createEdgeExtention(startNode, first);
            edges.add(e);

        }

        /* create exit Vertex */
        if (createExitvertex)
        {
            exitNode = GraphExtentionFactory.createNodeExtention(null);
            exitNode.setToolTipText(VIRTUAL_EXIT_NODE_TOOLTIP_TEXT);
            exitNode.setVertexType(INodeType.NODE_TYPE_EXIT);
            nodes.add(exitNode);

            /* create edges to all exit vertices */
            for (int i = 0; i < nodes.size(); i++)
            {
                INodeExt n = nodes.getNodeExt(i);
                if (n.getVertexType() == INodeType.NODE_TYPE_RETURN)
                {
                    /* create an edge */
                    edges.add(GraphExtentionFactory.createEdgeExtention(n, exitNode));
                }
            }

        }

        /* create back edge */
        if (createBackEdge)
        {
            if (startNode != null & exitNode != null)
            {
                IEdgeExt edge = GraphExtentionFactory.createEdgeExtention(exitNode, startNode);
                edge.setData(VIRTUAL_BACK_EDGE_TOOLTIP_TEXT);
                edges.add(edge);
            }
            else
            {
                throw new ControlFlowGraphException("Cannot create virtual back edge. Start or Exit node missing.");//$NON-NLS-1$
            }
        }

        /* use user object to store the line number table representation */
        String lineNumberTableStr = createLineNumberTableRespresenation(lineNumberTable);
        sourceCodeGraph.getUserObject().put(ByteCodeConstants.LINE_NUMBER_TABLE, lineNumberTableStr);

        return sourceCodeGraph;
    }

    /**
     * Create key of the edge.
     * 
     * @param sourceNode
     *            vertex object
     * @param targetNode
     *            vertex object
     * @return key as a string
     * @see INodeExt
     */
    private static String createKey(INodeExt sourceNode, INodeExt targetNode)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(sourceNode.getByteCodeOffset());
        buf.append(targetNode.getByteCodeOffset());
        return buf.toString();
    }

    /**
     * Creates a tooltip string for a source code node.
     * 
     * @param node
     * @return tooltip String
     */
    @SuppressWarnings("unchecked")
    private static String createToolTip(INodeExt node)
    {
        StringBuffer buf = new StringBuffer();
        buf.append(" Line: ");
        buf.append(node.getByteCodeOffset());
        List<INodeExt> data = (List<INodeExt>) node.getData();
        for (INodeExt n : data)
        {
            // Object o = n.getData();
            buf.append("\n");
            buf.append("  ");
            buf.append(n.getByteCodeOffset());
            buf.append(" ");
            buf.append(n.getByteCodeString());
            if (n.getLongDescr() != null)
            {
                buf.append("\n");
                buf.append(n.getLongDescr());
                // buf.append("\n");
            }

            buf.append(" ");
        }
        return buf.toString();
    }

    /**
     * Returns the node type defined in INodeType (@see
     * com.drgarbage.visualgraphic.controlflowgraph.intf.INodeType).
     * 
     * @param node
     * @return type
     */
    @SuppressWarnings("unchecked")
    private static int getNodeType(INodeExt node)
    {
        int type = INodeType.NODE_TYPE_SIMPLE;
        List<INodeExt> data = (List<INodeExt>) node.getData();
        for (INodeExt n : data)
        {
            /*
             * if the number of the outgoing edges < 2 then you will recive an exception
             * during layouting
             */
            if (n.getVertexType() == INodeType.NODE_TYPE_IF && node.getOutgoingEdgeList().size() == 2)
            {
                type = INodeType.NODE_TYPE_IF;
                return type;
            }
            else if (n.getVertexType() == INodeType.NODE_TYPE_SWITCH)
            {
                type = INodeType.NODE_TYPE_SWITCH;
                return type;
            }
            else if (n.getVertexType() == INodeType.NODE_TYPE_RETURN)
            {
                type = INodeType.NODE_TYPE_RETURN;
                return type;
            }
            // else if(n.getVertexType() == INodeType.NODE_TYPE_INVOKE){
            // type = INodeType.NODE_TYPE_INVOKE;
            // }
        }

        return type;
    }

    /**
     * TODO: fix representation
     * 
     * @param lineNumberTable
     * @return string
     */
    private static String createLineNumberTableRespresenation(LineNumberTableEntry[] lineNumberTable)
    {
        if (lineNumberTable == null)
        {
            return null;
        }

        StringBuffer buf = new StringBuffer();
        buf.append(" Line Number Table:\n");
        buf.append("--------------------\n");
        for (LineNumberTableEntry lne : lineNumberTable)
        {
            buf.append("|\t");
            buf.append(lne.getLineNumber());
            buf.append("\t|\t");
            buf.append(lne.getStartPc());
            buf.append("|\n");

        }
        buf.append("--------------------\n");

        return buf.toString();
    }

    /**
     * Gets line number from number table for given bytecode address.
     * 
     * @param lntb
     * @param bycodeindex
     * @return line number
     */
    private static int getSourceCodeLine(LineNumberTableEntry[] lntb, int bycodeindex)
    {
        if (lntb == null)
            return -1;

        for (int i = lntb.length - 1; i >= 0; i--)
        {
            if (lntb[i].getStartPc() <= bycodeindex)
                return lntb[i].getLineNumber();
        }

        return -1;
    }

    /**
     * Returns instruction list.
     * 
     * @param classPath
     * @param packageName
     * @param className
     * @param methodName
     * @param methodSig
     * @return instructions
     * @throws ControlFlowGraphException
     * @throws IOException
     */
    private static FilteringCodeVisitor getInstructionList(String classPath[], String packageName, String className,
            String methodName, String methodSig) throws ControlFlowGraphException, IOException
    {
        InputStream in = JavaLangUtils.findResource(classPath, packageName, className);
        if (in == null)
        {
            String msg = "Class '" + className + "' not found in the CLASSPATH.";
            throw new ControlFlowGraphException(msg);
        }

        if (!(in instanceof BufferedInputStream))
        {
            /* buffer only if necessary */
            in = new BufferedInputStream(in);
        }

        FilteringCodeVisitor codeVisitor = new FilteringCodeVisitor(methodName, methodSig);
        MethodFilteringVisitor classVisitor = new MethodFilteringVisitor(codeVisitor);
        ClassReader cr = new ClassReader(in, classVisitor);
        cr.accept(classVisitor, 0);
        if (codeVisitor.getInstructions() == null)
        {
            throw new ControlFlowGraphException(
                    "ControlFlowGraphGenerator: can't get method info of the " + methodName + methodSig);

        }

        return codeVisitor;
    }

    /**
     * Build sourcecode graph diagram.
     * 
     * @param instruction
     *            list
     */
    public static ControlFlowGraphDiagram buildSourceControlFlowDiagram(List<AbstractInstruction> instructions,
            LineNumberTableEntry[] lineNumberTable) throws ControlFlowGraphException, IOException
    {
        /* get preferences */
        boolean createStartNode = true;
        boolean createExitNode = true;
        boolean createBackEdge = true;

        /* generate control flow graph */
        IDirectedGraphExt graph = generateSourceCodeGraph(instructions, lineNumberTable, createStartNode,
                createExitNode, createBackEdge);
        return createSourceCodeGraphDiagram(graph);
    }

    public static ControlFlowGraphDiagram createControlFlowGraphDiagram(IDirectedGraphExt graph)
    {
        int nodeWeigth = 78;
        int nodeHeight = 36;
        INodeExt node = null;

        /* build Diagram */
        ControlFlowGraphDiagram diagram = new ControlFlowGraphDiagram();

        VertexBase r = null;
        for (int i = 0; i < graph.getNodeList().size(); i++)
        {
            node = graph.getNodeList().getNodeExt(i);

            // * create visual Node */
            switch (node.getVertexType())
            {
            case INodeType.NODE_TYPE_SIMPLE:
                r = new RectangularVertex();
                break;
            case INodeType.NODE_TYPE_IF:
                r = new DecisionVertex();
                break;
            case INodeType.NODE_TYPE_RETURN:
                r = new ReturnVertex();
                break;
            case INodeType.NODE_TYPE_GOTO_JUMP:
                r = new GotoJumpVertex();
                break;
            case INodeType.NODE_TYPE_SWITCH:
                r = new SwitchVertex();
                break;
            case INodeType.NODE_TYPE_INVOKE:
                r = new InvokeVertex();
                break;
            case INodeType.NODE_TYPE_GET:
                r = new GetVertex();
                break;
            case INodeType.NODE_TYPE_START:
                r = new StartVertex();
                break;
            case INodeType.NODE_TYPE_EXIT:
                r = new ExitVertex();
                break;
            case INodeType.NODE_TYPE_COMMENT:
                r = new CommentElement();
                break;
            default:
                r = new RectangularVertex();
            }

            if (node.getVertexType() == INodeType.NODE_TYPE_START || node.getVertexType() == INodeType.NODE_TYPE_EXIT)
            {
                /* do nothing */
            }
            else
            {

                /*
                 * r.setLabel(" " + node.getByteCodeOffset() + "  " + node.getByteCodeString() +
                 * " ");
                 */
                r.setLabel(String.valueOf(node.getByteCodeString()));
                r.setToolTip(node.getToolTipText());

                // r.setLabel(node.getByteCodeString());

            }

            r.setSize(new Dimension(nodeWeigth, nodeHeight));

            // String t = node.getByteCodeString();
            // if(t != null){
            // StringLine strLine = new StringLine(node.getByteCodeString());
            // r.setSize(new Dimension(6 * strLine.getMaxLineLenght(), 18 *
            // strLine.getNumberOfLines()));
            // }
            // else{
            // r.setSize(new Dimension(nodeWeigth, nodeHeight));
            // }

            node.setData(r);

            diagram.addChild(r);
        }

        /* create connections */
        createConnections(graph.getEdgeList());

        /* create additions */
        createAdditions(graph, diagram);

        /* layout graph */
        IDirectedGraphExt graph2 = LayoutAlgorithmsUtil.generateGraph(diagram);
        new HierarchicalLayout().visit(graph2);

        INodeListExt listNode = graph2.getNodeList();
        VertexBase vb = null;
        for (int i = 0; i < listNode.size(); i++)
        {
            node = listNode.getNodeExt(i);
            vb = (VertexBase) node.getData();
            vb.setLocation(new Point(node.getX(), node.getY()));
        }

        return diagram;
    }

    /**
     * Creates diagram connections for the given edge list.
     * 
     * @param edges
     */
    private static void createConnections(IEdgeListExt edges)
    {
        Connection c = null;
        for (int i = 0; i < edges.size(); i++)
        {
            IEdgeExt edge = edges.getEdgeExt(i);

            c = new Connection((VertexBase) edge.getSource().getData(), (VertexBase) edge.getTarget().getData());

            if (edge.getData() != null)
            {
                c.setLabel((String) edge.getData());
            }

            if (edge.getSource().getVertexType() == INodeType.NODE_TYPE_EXIT
                    || edge.getSource().getVertexType() == INodeType.NODE_TYPE_START
                    || edge.getTarget().getVertexType() == INodeType.NODE_TYPE_EXIT)
            {
                c.setLineStyle(Graphics.LINE_DASH);
            }
            else
            {
                c.setLineStyle(Graphics.LINE_SOLID);
            }
        }
    }

    private static void createAdditions(IDirectedGraphExt graph, ControlFlowGraphDiagram diagram)
    {

        Map<String, Object> attr = graph.getUserObject();
        Object name = attr.get(ByteCodeConstants.NAME);
        if (name != null)
        {
            diagram.setPropertyValue(ByteCodeConstants.NAME, (String) name);
        }

        /* create additions */
        boolean copyLineNumberTable = true;

        if (copyLineNumberTable)
        {
            Object o = attr.get(ByteCodeConstants.LINE_NUMBER_TABLE);
            if (o != null && o instanceof String)
            {
                String txt = (String) o;
                VertexBase r = new CommentElement();
                r.setLabel(txt);

                StringLine strLine = new StringLine(txt);
                r.setSize(new Dimension(8 * strLine.getMaxLineLenght() + 10, 12 * strLine.getNumberOfLines() + 20));
                diagram.addChild(r);
            }
        }
    }

    /**
     * Creates the diagrapm
     */
    protected ControlFlowGraphDiagram createDiagram(String[] classPath, String mPackage, String mClassName,
            String mMethodName, String mMethodSignature)
    {
        ControlFlowGraphDiagram controlFlowGraphDiagram = null;
        try
        {
            controlFlowGraphDiagram = ControlFlowDiagramGraphFactory.buildBasicblockGraphDiagram(classPath, mPackage,
                    mClassName, mMethodName, mMethodSignature);
        }
        catch (ControlFlowGraphException e)
        {
            // ControlFlowFactoryPlugin.getDefault().getLog().log(new
            // Status(IStatus.ERROR,ControlFlowFactoryPlugin.PLUGIN_ID, e.getMessage() ,
            // e));
            // Messages.error(e.getMessage() + CoreMessages.ExceptionAdditionalMessage);
            return null;
        }
        catch (IOException e)
        {
            // ControlFlowFactoryPlugin.getDefault().getLog().log(new
            // Status(IStatus.ERROR,ControlFlowFactoryPlugin.PLUGIN_ID, e.getMessage() ,
            // e));
            // Messages.error(e.getMessage() + CoreMessages.ExceptionAdditionalMessage);
            return null;
        }

        return controlFlowGraphDiagram;

    }

    /**
     * Build basic block graph diagram.
     * 
     * @param instruction
     *            list
     * @throws IOException
     * @throws ControlFlowGraphException
     */
    public static ControlFlowGraphDiagram buildBasicblockGraphDiagram(List<AbstractInstruction> instructions)
            throws ControlFlowGraphException, IOException
    {
        /* get preferences */
        boolean createStartNode = true;
        boolean createExitNode = true;
        boolean createBackEdge = false;

        /* generate control flow graph */
        IDirectedGraphExt basicBlockGraph = generateBasicBlockGraph(instructions, null, createStartNode, createExitNode,
                createBackEdge);
        return createBasicBlockDiagram(basicBlockGraph);
    }

    /**
     * Generates a basic block graph graph from an instruction list.
     * 
     * @param instructions
     *            the instruction list
     * @param lineNumberTable
     *            th eline number table
     * @param createStartVertex
     *            <code>true</code> if the virtual start vertex has to be created,
     *            <code>false</code> otherwise
     * @param createExitvertex
     *            <code>true</code> if the virtual exit vertex has to be created,
     *            <code>false</code> otherwise
     * @param createBackEdge
     *            <code>true</code> if the virtual back edge has to be created,
     *            <code>false</code> otherwise
     * @return the control flow graph
     * @throws ControlFlowGraphException
     *             if the graph could not be created.
     * @throws IOException
     *             if the class file could not be opened.
     */
    public static IDirectedGraphExt generateBasicBlockGraph(List<AbstractInstruction> instructions,
            LineNumberTableEntry[] lineNumberTable, boolean createStartVertex, boolean createExitVertex,
            boolean createBackEdge) throws ControlFlowGraphException, IOException
    {
        IDirectedGraphExt graph = generateControlFlowGraph(instructions, null, false, false, false);

        /* find basic blocks */
        GraphUtils.clearGraph(graph);
        BasicBlockGraphVisitor basicBlockVisitor = new BasicBlockGraphVisitor();
        basicBlockVisitor.start(graph);

        IDirectedGraphExt basicBlockGraph = basicBlockVisitor.getBasicBlockGraph();

        int nodeWeigth = 48;
        int nodeHeight = 36;
        INodeExt node = null;
        INodeListExt nodes = basicBlockGraph.getNodeList();
        for (int i = 0; i < nodes.size(); i++)
        {
            node = nodes.getNodeExt(i);
            node.setWidth(nodeWeigth);
            node.setHeight(nodeHeight);
        }

        IEdgeListExt edges = basicBlockGraph.getEdgeList();
        INodeExt startNode = null, exitNode = null;

        /* create exit Vertex */
        if (createExitVertex)
        {
            exitNode = GraphExtentionFactory.createNodeExtention(VIRTUAL_EXIT_NODE_TEXT);
            exitNode.setToolTipText(VIRTUAL_EXIT_NODE_TOOLTIP_TEXT);
            exitNode.setVertexType(INodeType.NODE_TYPE_EXIT);

            /* create edges to all exit vertices */
            for (int i = 0; i < nodes.size(); i++)
            {
                INodeExt n = nodes.getNodeExt(i);
                if (n.getOutgoingEdgeList().size() == 0)
                {
                    /* create an edge */
                    edges.add(GraphExtentionFactory.createEdgeExtention(n, exitNode));
                }
            }
        }

        /* create start Vertex */
        if (createStartVertex)
        {
            startNode = GraphExtentionFactory.createNodeExtention(null);
            startNode.setToolTipText(VIRTUAL_START_NODE_TOOLTIP_TEXT);
            startNode.setVertexType(INodeType.NODE_TYPE_START);

            /* create edge to the first node */
            for (int i = 0; i < nodes.size(); i++)
            {
                INodeExt n = nodes.getNodeExt(i);
                if (n.getIncomingEdgeList().size() == 0)
                {

                    if (n.getData() != null && n.getData().equals("B1"))
                    {
                        /* create an edge */
                        edges.add(GraphExtentionFactory.createEdgeExtention(startNode, n));
                        break;
                    }
                }
            }
        }

        if (createExitVertex && exitNode != null)
        {
            nodes.add(exitNode);
        }

        if (createStartVertex && startNode != null)
        {
            nodes.add(startNode);
        }

        /* create back edge */
        if (createBackEdge)
        {
            if (startNode != null & exitNode != null)
            {
                IEdgeExt edge = GraphExtentionFactory.createEdgeExtention(exitNode, startNode);
                edge.setData(VIRTUAL_BACK_EDGE_TOOLTIP_TEXT);
                edges.add(edge);
            }
            else
            {
                throw new ControlFlowGraphException("Cannot create virtual back edge. Start or Exit node missing.");//$NON-NLS-1$
            }
        }
        /* use user object to store the line number table representation */
        if (lineNumberTable != null)
        {
            String lineNumberTableStr = createLineNumberTableRespresenation(lineNumberTable);
            basicBlockGraph.getUserObject().put(ByteCodeConstants.LINE_NUMBER_TABLE, lineNumberTableStr);
        }

        return basicBlockGraph;
    }

    /**
     * Build basicblock graph diagram.
     * 
     * @param classPath
     *            , the class path
     * @param packageName
     *            , the name of the package
     * @param className
     *            , the name of the class
     * @param methodName
     *            , the name of the method
     * @param methodSig
     *            , the method signature
     * @throws ControlFlowGraphException
     *             , IOException, InvalidByteCodeException
     */
    public static ControlFlowGraphDiagram buildBasicblockGraphDiagram(String[] classPath, String packageName,
            String className, String methodName, String methodSig) throws ControlFlowGraphException, IOException
    {
        /* get preferences */

        boolean createStartNode = true;
        boolean createExitNode = true;
        boolean createBackEdge = false;

        /* generate control flow graph */
        IDirectedGraphExt basicBlockGraph = generateBasicBlockGraph(classPath, packageName, className, methodName,
                methodSig, createStartNode, createExitNode, createBackEdge);
        Map<String, Object> attr = basicBlockGraph.getUserObject();
        attr.put(ByteCodeConstants.NAME, className + "." + methodName + methodSig);
        return createBasicBlockDiagram(basicBlockGraph);
    }

    /**
     * Generates a control flow graph for the defined method.
     * 
     * @param classPath
     *            the class path
     * @param packageName
     *            the package name
     * @param className
     *            the class name
     * @param methodName
     *            th emethod name
     * @param methodSig
     *            the method signature
     * @return the control flow graph
     */
    public static IDirectedGraphExt generateBasicBlockGraph(String classPath[], String packageName, String className,
            String methodName, String methodSig, boolean createStartVertex, boolean createExitvertex,
            boolean createBackEdge)

            throws ControlFlowGraphException, IOException
    {
        FilteringCodeVisitor codeVisitor = getInstructionList(classPath, packageName, className, methodName, methodSig);
        return generateBasicBlockGraph(codeVisitor.getInstructions(), codeVisitor.getLineNumberTable(),
                createStartVertex, createExitvertex, createBackEdge);
    }

    /**
     * Build basic block graph diagram.
     * 
     * @param graph
     * @return diagram
     */
    private static ControlFlowGraphDiagram createBasicBlockDiagram(IDirectedGraphExt basicBlockGraph)
    {

        boolean createBasicBlockLongDescr = true;

        /* build Diagram */
        ControlFlowGraphDiagram diagram = new ControlFlowGraphDiagram();

        VertexBase r = null;
        INodeExt node = null;
        for (int i = 0; i < basicBlockGraph.getNodeList().size(); i++)
        {
            node = basicBlockGraph.getNodeList().getNodeExt(i);

            if (node.getVertexType() == INodeType.NODE_TYPE_START)
            {
                r = new StartVertex();
                r.setSize(new Dimension(node.getWidth(), node.getHeight()));

            }
            else if (node.getVertexType() == INodeType.NODE_TYPE_EXIT)
            {
                r = new ExitVertex();
                r.setSize(new Dimension(node.getWidth(), node.getHeight()));
            }
            else
            {
                if (createBasicBlockLongDescr)
                {
                    StringBuffer buf = new StringBuffer();
                    buf.append(getBasicBlockContext(node));

                    String s = buf.toString();
                    // BasicBlockVertex bbv = new BasicBlockVertex();
                    RectangularVertex bbv = new RectangularVertex();
                    bbv.setLabel(s);
                    bbv.setToolTip("Basic Block: " + node.getData().toString());

                    StringLine sl = new StringLine(s);
                    // TODO: define capital letter hight = 15 and width = 7
                    bbv.setSize(new Dimension(7 * sl.getMaxLineLenght(), (15 * sl.getNumberOfLines()) + 10));

                    r = bbv;
                    r.setLongDescrUsed(true);
                }
                else
                {
                    r = new RectangularVertex();
                    r.setLabel(node.getData().toString());
                    r.setToolTip(getBasicBlockContext(node));
                    r.setSize(new Dimension(node.getWidth(), node.getHeight()));
                }
            }

            r.setLocation(new Point(node.getX(), node.getY()));

            node.setData(r);
            diagram.addChild(r);
        }

        /* create connections */
        createConnections(basicBlockGraph.getEdgeList());

        /* create additions */
        createAdditions(basicBlockGraph, diagram);

        /* layout graph */
        IDirectedGraphExt graph2 = LayoutAlgorithmsUtil.generateGraph(diagram);
        new HierarchicalLayout().visit(graph2);

        INodeListExt listNode = graph2.getNodeList();
        VertexBase vb = null;
        for (int i = 0; i < listNode.size(); i++)
        {
            node = listNode.getNodeExt(i);
            vb = (VertexBase) node.getData();
            vb.setLocation(new Point(node.getX(), node.getY()));
        }

        return diagram;
    }

    private static String getBasicBlockContext(INodeExt node)
    {
        if (node instanceof IBasicBlock)
        {
            StringBuffer buf = new StringBuffer();
            buf.append(" ");
            buf.append(node.getData().toString());
            buf.append(" := {");
            IBasicBlock bb = null;
            INodeListExt basicblockVertices = null;
            bb = (IBasicBlock) node;
            basicblockVertices = bb.getBasicBlockVertices();
            for (int j = 0; j < basicblockVertices.size(); j++)
            {
                INodeExt b = basicblockVertices.getNodeExt(j);
                buf.append("\n  ");
                buf.append(b.getByteCodeOffset());
                buf.append("  ");
                buf.append(b.getByteCodeString());
                if (b.getLongDescr() != null)
                {
                    buf.append("\n");
                    buf.append(b.getLongDescr());
                }
            }
            buf.append("\n }");
            return buf.toString();
        }
        else
        {
            return node.getData().toString();
        }
    }

}
