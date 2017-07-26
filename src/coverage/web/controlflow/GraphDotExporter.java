package coverage.web.controlflow;

/**
 * http://www.graphviz.org/Download_windows.php
 * @author Jenifer
 *
 */
import java.io.IOException;
import java.text.MessageFormat;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.RGB;

import com.drgarbage.controlflowgraphfactory.ControlFlowFactoryMessages;
import com.drgarbage.dot.DotAttributes;
import com.drgarbage.dot.DotKeywords;
import com.drgarbage.dot.DotLexicalConstants;
import com.drgarbage.dot.DotUtils;
import com.drgarbage.dot.DotValues;
import com.drgarbage.dot.DotValues.nodeShapePolygon;
import com.drgarbage.dot.DotValues.style;
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
import com.drgarbage.visualgraphic.model.SwitchVertex;
import com.drgarbage.visualgraphic.model.VertexBase;

/**
 * Utility for export of graphs in DOT format.
 *
 * Example of a graph in DOT format :
 * 
 * digraph cog_graph { graph [label="..."]; 6 [label="6"]; 7 [label="7"]; 10
 * [label="10"]; 11 [label="11"]; 14 [label="14"]; 18 [label="18"]; 22
 * [label="22"]; 40 [label="40"]; 6 -> 7; 7 -> 10; 10 -> 14; 10 -> 11; 14 -> 18;
 * 11 -> 18; 18 -> 22; 22 -> 40; 40 -> 7; 14 -> 40; }
 * 
 * 
 * @author Sergej Alekseev
 * @version $Revision: 18 $ $Id: GraphDOTExport.java 1523 2012-04-13 14:34:24Z
 *          Sergej Alekseev $
 */
public class GraphDotExporter extends AbstractExporter
{
    public static final RGB DEFAULT_WHITE_COLOR = new RGB(255, 255, 255);
    public static final RGB DEFAULT_BLACK_COLOR = new RGB(0, 0, 0);
    public static final RGB DEFAULT_RED_COLOR   = new RGB(255, 0, 0);
    public static final RGB DEFAULT_GREEN_COLOR = new RGB(0, 255, 0);
    public static final RGB DEFAULT_BLUE_COLOR  = new RGB(0, 0, 255);

    public static final RGB DEFAULT_INSTRUCTION_BGCOLOR      = new RGB(206, 206, 255);
    public static final RGB DEFAULT_BASIC_BLOCK_BGCOLOR      = new RGB(206, 206, 255);
    public static final RGB DEFAULT_ENTRY_END_BGCOLOR        = new RGB(230, 230, 230);
    public static final RGB DEFAULT_DECISION_VERTEX_BGCOLOR  = new RGB(180, 255, 180);
    public static final RGB DEFAULT_RETURN_VERTEX_BGCOLOR    = new RGB(255, 220, 168);
    public static final RGB DEFAULT_INVOKE_VERTEX_BGCOLOR    = new RGB(255, 255, 168);
    public static final RGB DEFAULT_GET_VERTEX_BGCOLOR       = new RGB(255, 140, 140);
    public static final RGB DEFAULT_SWITCH_VERTEX_BGCOLOR    = new RGB(252, 235, 165);
    public static final RGB DEFAULT_GOTO_JUMP_VERTEX_BGCOLOR = new RGB(208, 255, 255);

    public static final RGB DEFAULT_COMMENT_BGCOLOR = ColorConstants.tooltipBackground.getRGB();
    /* preferences constants */
    public static final String GRAPH_COLOR_PREFIX       = "GRAPH_COLOR_";
    public static final String INSTRUCTION_BGCOLOR      = GRAPH_COLOR_PREFIX + "INSTRUCTION_BGCOLOR";
    public static final String BASIC_BLOCK_BGCOLOR      = GRAPH_COLOR_PREFIX + "BASIC_BLOCK_BGCOLOR";
    public static final String ENTRY_END_BGCOLOR        = GRAPH_COLOR_PREFIX + "ENTRY_END_BGCOLOR";
    public static final String DECISION_VERTEX_BGCOLOR  = GRAPH_COLOR_PREFIX + "DECISION_VERTEX_BGCOLOR";
    public static final String RETURN_VERTEX_BGCOLOR    = GRAPH_COLOR_PREFIX + "RETURN_VERTEX_BGCOLOR";
    public static final String INVOKE_VERTEX_BGCOLOR    = GRAPH_COLOR_PREFIX + "INVOKE_VERTEX_BGCOLOR";
    public static final String GET_VERTEX_BGCOLOR       = GRAPH_COLOR_PREFIX + "GET_VERTEX_BGCOLOR";
    public static final String SWITCH_VERTEX_BGCOLOR    = GRAPH_COLOR_PREFIX + "SWITCH_VERTEX_BGCOLOR";
    public static final String GOTO_JUMP_VERTEX_BGCOLOR = GRAPH_COLOR_PREFIX + "GOTO_JUMP_VERTEX_BGCOLOR";

    public static final String COMMENT_BGCOLOR                    = GRAPH_COLOR_PREFIX + "COMMENT_BGCOLOR";
    public static final String GRAPH_PANEL_LOCATION               = "GRAPH_PANEL_LOCATION";
    public static final String GRAPH_PANEL_LOCATION_SEPARATE_VIEW = "GRAPH_PANEL_LOCATION_SEPARATE_VIEW";
    public static final String GRAPH_PANEL_LOCATION_EDITOR        = "GRAPH_PANEL_LOCATION_EDITOR";

    private static void appendAttribute(String attribute, double value, Appendable buf) throws IOException
    {
        buf.append(attribute).append(DotLexicalConstants.EQUALS).append(String.valueOf(value));
    }

    private static void appendAttribute(String attribute, int value, Appendable buf) throws IOException
    {
        buf.append(attribute).append(DotLexicalConstants.EQUALS).append(String.valueOf(value));
    }

    private static void appendCommaSpace(Appendable buf) throws IOException
    {
        buf.append(DotLexicalConstants.COMMA).append(DotLexicalConstants.SPACE);
    }

    /**
     * Nodes and edges can specify a color attribute, with black the default. This
     * is the color used to draw the node�s shape or the edge. A color value can
     * be a huesaturation-brightness triple (three �oating point numbers between 0
     * and 1, separated by commas); one of the colors names listed in Appendix G
     * (borrowed from some version of the X window system); or a red-green-blue
     * (RGB) triple4 (three hexadecimal number between 00 and FF, preceded by the
     * character �#�). Thus, the values "orchid", "0.8396,0.4862,0.8549" and
     * #DA70D6 are three ways to specify the same color.
     * 
     * @return attribute as a string
     * @throws IOException
     */
    private static void appendFillcolor(RGB color, Appendable buf) throws IOException
    {

        buf.append(DotAttributes.fillcolor).append(DotLexicalConstants.EQUALS)
           .append(DotLexicalConstants.QUOTE)                
           .append(DotUtils.toHexColor(color.red, color.green, color.blue))
           .append(DotLexicalConstants.QUOTE);
    }

    private static void appendFixedsize(String bool, Appendable buf) throws IOException
    {
        buf.append(DotAttributes.fixedsize).append(DotLexicalConstants.EQUALS).append(bool);
    }

    private static void appendShape(DotValues.nodeShapePolygon shape, Appendable buf) throws IOException
    {
        buf.append(DotAttributes.shape).append(DotLexicalConstants.EQUALS).append(shape.toString());
    }

    private static void appendStyle(DotValues.style style, Appendable buf) throws IOException
    {
        buf.append(DotAttributes.style).append(DotLexicalConstants.EQUALS).append(style.toString());
    }

    private static void appendStyle(DotValues.style[] style, Appendable buf) throws IOException
    {
        buf.append(DotAttributes.style).append(DotLexicalConstants.EQUALS).append(DotLexicalConstants.QUOTE);

        boolean first = true;
        for (style s : style)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                appendCommaSpace(buf);
            }
            buf.append(s.toString());
        }

        buf.append(DotLexicalConstants.QUOTE);
    }

    private static String toDotStyle(int lineStyle)
    {
        switch (lineStyle)
        {
        case Graphics.LINE_DASH:
            return DotValues.style.dashed.toString();
        case Graphics.LINE_DOT:
            return DotValues.style.dotted.toString();
        case Graphics.LINE_SOLID:
            return DotValues.style.solid.toString();
        default:

            String msg = MessageFormat.format(ControlFlowFactoryMessages.GraphDOTExport_Cannot_transform_line_style_0,
                    new Object[]
                    { Integer.valueOf(lineStyle) });
            return DotValues.style.solid.toString();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#createVertexInfo(
     * com.drgarbage.visualgraphic.model.Connection)
     */
    @Override
    protected void appendEdge(Connection con, Appendable buf) throws IOException
    {
        buf.append(DotLexicalConstants.SPACE)
           .append(String.valueOf(con.getSource().getId()))
           .append(DotLexicalConstants.SPACE)
           .append(DotLexicalConstants.RIGHT_ARROW)
           .append(DotLexicalConstants.SPACE)
           .append(String.valueOf(con.getTarget().getId()))
           .append(DotLexicalConstants.SPACE)
           .append(DotLexicalConstants.LEFT_SQUARE_BRACKET)
           .append(DotAttributes.label)
           .append(DotLexicalConstants.EQUALS);

        DotUtils.appendQuotedLabel(con.getLabel().trim(), buf);

        if (graphSpecification.isExportDecorations())
        {
            buf.append(DotLexicalConstants.COMMA)
               .append(DotLexicalConstants.SPACE)
               .append(DotAttributes.style)
               .append(DotLexicalConstants.EQUALS)
               .append(toDotStyle(con.getLineStyle()));
        }

        buf.append(DotLexicalConstants.SPACE)
           .append(DotLexicalConstants.RIGHT_SQUARE_BRACKET)
           .append(DotLexicalConstants.NEWLINE);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#endCreateGraph(
     * com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram)
     */
    @Override
    protected void appendGraphEnd(ControlFlowGraphDiagram diagram, Appendable buf) throws IOException
    {
        buf.append(DotLexicalConstants.RIGHT_BRACE).append(DotLexicalConstants.NEWLINE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#startCreateGraph(
     * com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram)
     */
    @Override
    protected void appendGraphStart(ControlFlowGraphDiagram diagram, Appendable buf) throws IOException
    {

        buf.append(DotLexicalConstants.SPACE)
           .append(DotLexicalConstants.LEFT_BRACE)
           .append(DotLexicalConstants.NEWLINE)
           .append(DotLexicalConstants.SPACE)
           .append(DotKeywords.graph).append(DotLexicalConstants.SPACE)
           .append(DotLexicalConstants.LEFT_SQUARE_BRACKET).append(DotAttributes.label)
           .append(DotLexicalConstants.EQUALS);
        
        DotUtils.appendQuotedLabel(String.valueOf(diagram.getPropertyValue(ControlFlowGraphDiagram.NAME_PROP)), buf);

        buf.append(DotLexicalConstants.RIGHT_SQUARE_BRACKET)
           .append(DotLexicalConstants.SEMICOLON)
           .append(DotLexicalConstants.NEWLINE);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#createHeader(com.
     * drgarbage.visualgraphic.model.ControlFlowGraphDiagram)
     */
    @Override
    protected void appendHeader(ControlFlowGraphDiagram diagram, Appendable buf) throws IOException
    {
        appendHeaderComment(buf);
        buf.append(DotKeywords.digraph).append(DotLexicalConstants.SPACE);
        DotUtils.appendQuotedLabel(String.valueOf(diagram.getPropertyValue(ControlFlowGraphDiagram.NAME_PROP)), buf);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#convertLabel(java
     * .lang.String)
     */
    @Override
    protected void appendLabel(String label, Appendable out) throws IOException
    {
        DotUtils.appendEscapedLabel(label, out);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#createVertexInfo(
     * com.drgarbage.visualgraphic.model.VertexBase)
     */
    @Override
    protected void appendVertex(VertexBase vb, Appendable buf) throws IOException
    {
        buf.append(DotLexicalConstants.SPACE).append(String.valueOf(vb.getId())).append(DotLexicalConstants.SPACE)
                .append(DotLexicalConstants.LEFT_SQUARE_BRACKET).append(DotAttributes.label)
                .append(DotLexicalConstants.EQUALS);

        DotUtils.appendQuotedLabel(vb.getLabel().trim(), buf);

        if (graphSpecification.isExportDecorations())
        {

            appendCommaSpace(buf);

            if (vb instanceof DecisionVertex)
            {
                appendShape(nodeShapePolygon.diamond, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_DECISION_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof GetVertex)
            {
                appendShape(nodeShapePolygon.parallelogram, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_GET_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof GotoJumpVertex)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_GOTO_JUMP_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof InvokeVertex)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_INVOKE_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof RectangularVertex)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_INSTRUCTION_BGCOLOR, buf);
            }
            else if (vb instanceof ReturnVertex)
            {
                appendShape(nodeShapePolygon.invhouse, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_RETURN_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof StartVertex)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(new style[]
                { style.filled, style.rounded }, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_ENTRY_END_BGCOLOR, buf);
            }
            else if (vb instanceof ExitVertex)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(new style[]
                { style.filled, style.rounded }, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_ENTRY_END_BGCOLOR, buf);
            }
            else if (vb instanceof SwitchVertex)
            {
                appendShape(nodeShapePolygon.hexagon, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_SWITCH_VERTEX_BGCOLOR, buf);
            }
            else if (vb instanceof CommentElement)
            {
                appendShape(nodeShapePolygon.box, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

                appendCommaSpace(buf);
                appendFillcolor(DEFAULT_COMMENT_BGCOLOR, buf);
            }
            else
            {
                appendShape(nodeShapePolygon.ellipse, buf);

                appendCommaSpace(buf);
                appendStyle(style.filled, buf);

            }

        }

        if (graphSpecification.isExportGeometry())
        {

            appendCommaSpace(buf);
            appendFixedsize(DotValues.true_, buf);
            appendCommaSpace(buf);
            appendAttribute(DotAttributes.fontsize, 12, buf);

            appendCommaSpace(buf);
            appendAttribute(DotAttributes.width, vb.getSize().width / (double) 100, buf);
            appendCommaSpace(buf);
            appendAttribute(DotAttributes.height, vb.getSize().height / (double) 100, buf);
        }

        buf.append(DotLexicalConstants.SPACE).append(DotLexicalConstants.RIGHT_SQUARE_BRACKET)
                .append(DotLexicalConstants.NEWLINE);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#getCommentBegin()
     */
    @Override
    protected String getCommentBegin()
    {
        return DotLexicalConstants.COMMENT_BEGIN;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#getCommentEnd()
     */
    @Override
    protected String getCommentEnd()
    {
        return DotLexicalConstants.COMMENT_END;
    }

    @Override
    protected String getCommentFiller()
    {
        return DotLexicalConstants.COMMENT_FILLER;
    }
}