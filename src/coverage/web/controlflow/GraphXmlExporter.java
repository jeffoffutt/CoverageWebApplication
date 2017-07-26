package coverage.web.controlflow;

import org.eclipse.draw2d.Graphics;

import java.io.IOException;

import com.drgarbage.bytecode.ByteCodeConstants;
import com.drgarbage.visualgraphic.model.Connection;
import com.drgarbage.visualgraphic.model.ControlFlowGraphDiagram;
import com.drgarbage.visualgraphic.model.VertexBase;
import com.drgarbage.xml.XmlLexicalConstants;

/**
 * Utility for export of graphs in GraphXML format.
 *
 * @author Sergej Alekseev
 * @version $Revision: 18 $ $Id: GraphXMLExport.java 1523 2012-04-13 14:34:24Z
 *          Sergej Alekseev $
 */
public class GraphXmlExporter extends AbstractExporter
{

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
        buf.append("  <edge source=\"");
        buf.append(String.valueOf(con.getSource().getId()));
        buf.append("\" target=\"");
        buf.append(String.valueOf(con.getTarget().getId()));
        buf.append("\">\n");

        /* set label */
        buf.append("    <label>");
        appendLabel(con.getLabel().trim(), buf);
        buf.append("</label>\n");

        if (graphSpecification.isExportDecorations())
        {
            buf.append("    <style>\n");
            buf.append("       <line linestyle=\"");
            if (con.getLineStyle() == Graphics.LINE_SOLID)
                buf.append("solid");
            else if (con.getLineStyle() == Graphics.LINE_DASH)
                buf.append("dashed");
            else
                buf.append("unknown");
            buf.append("\" linewidth=\"1.2\" colour=\"black\"/>\n");
            // buf.append(" <fill fillstyle=\"none\"/>\n");
            buf.append("    </style>\n");
        }
        buf.append("  </edge>\n");

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
        buf.append(" </graph>\n");
        buf.append("</GraphXML>\n");
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
        buf.append("<?xml version=\"1.0\"?>\n<!DOCTYPE GraphXML SYSTEM \"GraphXML.dtd\">\n");
        appendHeaderComment(buf);
        buf.append("<GraphXML>\n");
        buf.append(" <graph version=\"1.0\" vendor=\"www.drgarbage.com\" id=\"");
        String name = "null";
        Object o = diagram.getPropertyValue(ByteCodeConstants.NAME);
        if (o != null)
        {
            name = o.toString();
        }

        appendLabel(name, buf);
        buf.append("\">\n");

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.controlflowgraphfactory.export.AbstractExport#createHeader(com.
     * drgarbage.visualgraphic.model.ControlFlowGraphDiagram)
     */
    protected void appendHeader(ControlFlowGraphDiagram diagram, Appendable buf) throws IOException
    {
        /* XML does not allow content before XML declaration <?xml */
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
        String l = label.replace("\n", "\\n").replace("\"", "\\\"");
        out.append(l);
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
        buf.append("   <node name=\"");
        buf.append(String.valueOf(vb.getId()));
        buf.append("\">\n");

        /* set label */
        buf.append("    <label>");
        appendLabel(vb.getLabel().trim(), buf);
        buf.append("</label>");

        if (vb.getToolTip() != null || !vb.getToolTip().equals(""))
        {
            buf.append("\n    <tooltip>");
            String trimed = vb.getToolTip().trim();
            buf.append(trimed);
            buf.append("</tooltip>\n");
        }

        if (graphSpecification.isExportGeometry())
        {
            buf.append("    <position x=\"");
            buf.append(String.valueOf(vb.getLocation().x));
            buf.append("\" y=\"");
            buf.append(String.valueOf(vb.getLocation().y));
            buf.append("\"/>\n");
            buf.append("    <size width=\"");
            buf.append(String.valueOf(vb.getSize().width));
            buf.append("\" height=\"");
            buf.append(String.valueOf(vb.getSize().height));
            buf.append("\"/>\n");
        }
        if (graphSpecification.isExportDecorations())
        {
            buf.append("    <style>\n");
            buf.append("       <line linestyle=\"solid\" linewidth=\"1.2\" colour=\"black\"/>\n");
            // buf.append(" <fill fillstyle=\"solid\" colour=\"unknown\"/>\n");
            buf.append("    </style>\n");
        }
        buf.append("   </node>\n");

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
        return XmlLexicalConstants.COMMENT_BEGIN;
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
        return XmlLexicalConstants.COMMENT_END;
    }

    @Override
    protected String getCommentFiller()
    {
        return XmlLexicalConstants.COMMENT_FILLER;
    }
}