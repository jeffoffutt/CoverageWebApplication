package coverage.applet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoundedRangeModel;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import coverage.graph.GraphAdapter;
import coverage.graph.GraphUtil;

import edu.uci.ics.jung.graph.ArchetypeEdge;
import edu.uci.ics.jung.graph.ArchetypeVertex;
import edu.uci.ics.jung.graph.Edge;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.decorators.AbstractEdgeShapeFunction;
import edu.uci.ics.jung.graph.decorators.ConstantDirectionalEdgeValue;
import edu.uci.ics.jung.graph.decorators.ConstantVertexFontFunction;
import edu.uci.ics.jung.graph.decorators.DefaultToolTipFunction;
import edu.uci.ics.jung.graph.decorators.EdgePaintFunction;
import edu.uci.ics.jung.graph.decorators.EdgeShape;
import edu.uci.ics.jung.graph.decorators.EdgeStringer;
import edu.uci.ics.jung.graph.decorators.PickableEdgePaintFunction;
import edu.uci.ics.jung.graph.decorators.VertexPaintFunction;
import edu.uci.ics.jung.graph.decorators.VertexStringer;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseVertex;
import edu.uci.ics.jung.graph.impl.UndirectedSparseEdge;
import edu.uci.ics.jung.visualization.FRLayout;
import edu.uci.ics.jung.visualization.GraphLabelRenderer;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layout;
import edu.uci.ics.jung.visualization.PickedInfo;
import edu.uci.ics.jung.visualization.PluggableRenderer;
import edu.uci.ics.jung.visualization.ShapePickSupport;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.contrib.CircleLayout;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;

/**
 * The applet for graph coverage
 * 
 */
public class GraphApplet extends JApplet {

    /**
     * the graph
     */
    Graph  graph;
    
    coverage.graph.Path[] paths;
    
    String pathName = "Show paths";
    
    coverage.graph.GraphAdapter adapter;

    /**
     * the visual component and renderer for the graph
     */
    VisualizationViewer vv;
    
    PluggableRenderer pr;
    
    /**
     */
    GraphLabelRenderer graphLabelRenderer;
    
    ScalingControl scaler = new CrossoverScalingControl();
    
    /**
     * create an instance of a simple graph with controls to
     * demo the label positioning features
     * 
     */
//    public GraphApplet() {
    
    public void init(){
        
        // create a simple graph for the demo
//        graph = new SparseGraph();
//        Vertex[] v = createVertices(3);
//        createEdges(v);
    	
        createGraph();
        
        //set visualization
        pr = new PluggableRenderer();
       
        Layout layout = new FRLayout(graph);
        vv =  new VisualizationViewer(layout, pr, new Dimension(600,400));
        vv.setPickSupport(new ShapePickSupport());
        pr.setEdgeShapeFunction(new EdgeShape.QuadCurve());
        vv.setBackground(Color.white);
        
        //graphLabelRenderer = pr.getGraphLabelRenderer();
        pr.setVertexStringer(new VertexLabel(adapter.getVertexLabel()));
        pr.setVertexPaintFunction(new VertexPainter(adapter.getVertexType()));
        pr.setVertexFontFunction(new ConstantVertexFontFunction(new Font("TimesRoman", Font.PLAIN, 15)));
        pr.setEdgePaintFunction(new EdgePainter(adapter.getEdgeType()));
        pr.setEdgeStringer(new EdgeLabel(adapter.getEdgeLabel()));
        
        // add my listener for ToolTips 
        vv.setToolTipFunction(new DefaultToolTipFunction());
        
        // create a frame to hold the graph
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
        Container content = getContentPane();
        content.add(panel);
        
        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
        vv.setGraphMouse(graphMouse);
        
        //adding zoom controllers for the graph displaying
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1/1.1f, vv.getCenter());
            }
        });

        JPanel controls = new JPanel();
        
        JPanel zoomPanel = new JPanel(new GridLayout(1,0));
        zoomPanel.setBorder(BorderFactory.createTitledBorder("Scale"));
        zoomPanel.add(plus);
        zoomPanel.add(minus);
        //add a combo box for test paths for different criteria
        //when selecting a different path listed in the combo box, the graph may change on the graph,like the color of the nodes
        if(paths != null)
        {
	        JComboBox pathBox = new JComboBox();
	        pathBox.setMinimumSize(new Dimension(200, 25));
	        for(int i = 0;i < paths.length;i++)
	      	  pathBox.addItem((i+1) + ":" + paths[i].toString());
	        
	        pathBox.addItemListener(new ItemListener() {
	      	  public void itemStateChanged(ItemEvent ie) {
	      		  String pstr = ie.getItem().toString();
	      		  String[] str = pstr.split(":");
		        	  int index = Integer.parseInt(str[0]);
		        	  graph = adapter.getJungGraph(paths[index-1]);
		        	  //repaint the graph if a different path is selected
		        	  vv.repaint();
	      	  }
	        }
	        );
	        
	        JPanel pathPanel = new JPanel(new GridLayout(1, 0));
	        pathPanel.setBorder(BorderFactory.createTitledBorder(pathName));
	        pathPanel.add(pathBox);
	        controls.add(pathPanel);
        }
        
        controls.add(zoomPanel);        
        content.add(controls, BorderLayout.SOUTH);
    }
    
    /**
     * create a graph
     */
    public void createGraph()
    {
   	//get parameters from the webpages
    	String gstr = getParameter("graph");
    	String pstr = getParameter("path");
    	String title = getParameter("title");
    	String variables = getParameter("variables");
    	if(title != null)
    		pathName = title;
    	
    	coverage.graph.Graph tg = null;
    	System.out.println("variables:" + variables);
    	if(variables != null && !variables.trim().equals(""))
    		tg = GraphUtil.inputDFGraph(gstr, variables);
    	else
    		tg = GraphUtil.inputGraph(gstr);
    	
    	System.out.println("graph:edges :" + tg.sizeOfEdges());
    	System.out.println("graph:final nodes :" + tg.sizeOfEndingNode());
    	System.out.println("graph:initial nodes :" + tg.sizeOfInitialNode());
    	System.out.println("graph:nodes :" + tg.sizeOfNodes());
    	
    	if(pstr != null)
    		paths = GraphUtil.inputPath(pstr, tg);
    	
    	adapter = new GraphAdapter(tg);
    	graph = adapter.getJungGraph();
    }
    
    class VertexLabel implements VertexStringer{

    	protected Map<Vertex, String> v2s;
    	private boolean nodeLabel=true;
    	
    	public VertexLabel(Map<Vertex, String> v2s)
    	{
    		this.v2s=v2s;
    	}
    	
		public String getLabel(ArchetypeVertex arg0) 
		{
			if(nodeLabel)
				return v2s.get(arg0);
			else
				return "xu";			
		}

		public boolean isNodeLabel() {
			return nodeLabel;
		}

		public void setNodeLabel(boolean nodeLabel) {
			this.nodeLabel = nodeLabel;
		}
    	
    }
    
    class VertexPainter implements VertexPaintFunction{
    	
    	protected Map<Vertex, Integer> v2i;    	
    	
    	public VertexPainter(Map<Vertex, Integer> v2i)
    	{
    		this.v2i=v2i;
    	}
    	
		public Paint getDrawPaint(Vertex arg0) {
			return Color.black;
		}
		
		public Paint getFillPaint(Vertex arg0) {
			int type=v2i.get(arg0);
			switch(type){
				case GraphAdapter.INITIALNODE:
					return Color.gray;
				case GraphAdapter.ENDINGNODE:
					return Color.black;
				case GraphAdapter.PASSED:
					return Color.blue;
				default:
					return Color.red;
			}
		}
    }
    
    class EdgePainter implements EdgePaintFunction{

    	protected Map<edu.uci.ics.jung.graph.Edge, Integer> je2i;
    	
    	public EdgePainter(Map<edu.uci.ics.jung.graph.Edge, Integer> je2i)
    	{
    		this.je2i=je2i;
    	}
    	
		public Paint getDrawPaint(Edge arg0) {
			int type=je2i.get(arg0);
			if(type==GraphAdapter.PASSED)
				return Color.blue;
			else
				return Color.black;			
		}

		public Paint getFillPaint(Edge arg0) {
			
			return null;
		}
    	
    }//end EdgePainter class
    
    class EdgeLabel implements EdgeStringer{
   	 
   	protected Map<edu.uci.ics.jung.graph.Edge, String> je2s;
   	
   	public EdgeLabel(Map<edu.uci.ics.jung.graph.Edge, String> je2s){
   		this.je2s = je2s;
   	}

		public String getLabel(ArchetypeEdge arg0) {
			
			return je2s.get(arg0);
		}
   	 
    }//end EdgeLabel class  

}//end GraphApplet class
