/**
 * 
 */
package coverage.graph;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.graph.impl.DirectedSparseEdge;
import edu.uci.ics.jung.graph.impl.SparseGraph;
import edu.uci.ics.jung.graph.impl.SparseVertex;
/**
 * 
 *It is for applet. It is adapter from coverage.graph.Graph to edu.uci.ics.jung.graph.Graph.
 *It is used to visualize the graph based one Jung api package. 
 *Jung API package can be downloaded from <a href=http://jung.sourceforge.net/> jung site</a>
 *
 * @author wuzhi
 * 
 * Modifeid and Corrected by Nan Li 
 * Start Date: March 4, 2009
 * 
 */
public class GraphAdapter {
	
	//constants for node type
	public final static int INITIALNODE = 0;
	public final static int ENDINGNODE = 1;
	
	//constants for edge type
	public final static int PASSED = 2;
	public final static int UNPASSED = 3; //default for node and edge
	
	edu.uci.ics.jung.graph.Graph jgraph;
	coverage.graph.Graph xgraph;
	
	Map<coverage.graph.Node, Vertex> n2v; //coverage node to jung vertex
	
	Map<Vertex, String> v2s; //node label: vertex to string
	Map<Vertex, Integer> v2i; //node type: vertex to integer
	Map<edu.uci.ics.jung.graph.Edge, Integer> je2i; //for edge passing: jung edge to integer
	Map<edu.uci.ics.jung.graph.Edge, String> je2s; //for edge label: jung edge to integer
	Map<coverage.graph.Edge, edu.uci.ics.jung.graph.Edge> e2e; 
	
	public GraphAdapter(coverage.graph.Graph xgraph)
	{
		this.xgraph = xgraph;
	}
	
	/**
	 * It is uesd when a different path is selected and edges' types and vertexts' types need to be changed
	 * @param path that needs to be showed in a graph
	 * @return a graph of the type edu.uci.ics.jung.graph.Graph
	 */

	public edu.uci.ics.jung.graph.Graph getJungGraph(coverage.graph.Path path)
	{
		getJungGraph();
		
		if(path != null)
		{
			getVertexType(path);
			getEdgeType(path);
		}
		
		return jgraph;
	}
	
	/**
	 * create a jung graph without a path
	 * @return
	 */
	public edu.uci.ics.jung.graph.Graph getJungGraph()
	{
		if(jgraph != null)
			return jgraph;
		
    	jgraph = new SparseGraph();
    	n2v = new HashMap<coverage.graph.Node, Vertex>();
    	v2s = new HashMap<Vertex, String>();
    	v2i = new HashMap<Vertex, Integer>();
    	e2e = new HashMap<coverage.graph.Edge, edu.uci.ics.jung.graph.Edge>();
    	//add nodes to the graph
    	Iterator<coverage.graph.Node> nodes = xgraph.getNodeIterator();
    	while(nodes.hasNext())
    	{
    		Vertex v = jgraph.addVertex(new SparseVertex());
    		coverage.graph.Node node = nodes.next();
    		n2v.put(node, v);
    		v2s.put(v, node.toString());
    		if(xgraph.isInitialNode(node))
    			v2i.put(v, INITIALNODE);
    		else if(xgraph.isEndingNode(node))
    			v2i.put(v, ENDINGNODE);
    		else 
    			v2i.put(v, UNPASSED);
    	}
    	//add edges to the graph
    	Iterator<coverage.graph.Edge> edges = xgraph.getEdgeIterator();
    	je2i = new HashMap<edu.uci.ics.jung.graph.Edge, Integer>();
    	je2s = new HashMap<edu.uci.ics.jung.graph.Edge, String>();
    	while(edges.hasNext())
    	{
    		coverage.graph.Edge te = edges.next();
    		Vertex src = n2v.get(te.getSrc());
    		Vertex des = n2v.get(te.getDest());
    		edu.uci.ics.jung.graph.Edge je = new DirectedSparseEdge(src, des);
    		jgraph.addEdge(je);
    		e2e.put(te, je);
    		je2i.put(je, UNPASSED);
    	}
    	
    	
    	//add defs and uses for a data flow graph 
    	if(xgraph instanceof DFGraph )
    	{
    		Iterator<Variable> vars = ((DFGraph)xgraph).getVariableIterator();
 
    		while(vars.hasNext())
    		{
    			Variable v = vars.next();
    			Iterator<coverage.graph.Node> defs = v.getDefIterator();
    			List<coverage.graph.Edge> defsOnEdges = v.getDefsOnEdges();
    			List<coverage.graph.Edge> usesOnEdges = v.getUsesOnEdges();
    			Iterator<coverage.graph.Node> uses = v.getUseIterator();
    			//put defs on nodes in
    			while(defs.hasNext())
    			{
    				coverage.graph.Node d = defs.next();
    				Vertex vx = n2v.get(d);
    				String str = v2s.get(vx) + "def:" + v.getName();
    				System.out.println(str);
    				v2s.put(vx, str);
    			}
    			//The errors are corrected below, use should be used rather than def  			
    			//put uses on nodes in
    			while(uses.hasNext())
    			{
    				coverage.graph.Node u = uses.next();
    				Vertex vx = n2v.get(u);
    				String str = v2s.get(vx) + "use:" + v.getName();
    				System.out.println(str);
    				v2s.put(vx, str);
    			}
    			//put defs on edges in
    			for(int i = 0; i < defsOnEdges.size();i++){
    				coverage.graph.Edge e = defsOnEdges.get(i);
    	    		je2s.put(e2e.get(e), e.toString() + " def:" + v.getName());
    			}
    			//put uses on edges in
    			for(int i = 0; i < usesOnEdges.size();i++){
    				coverage.graph.Edge e = usesOnEdges.get(i);
    			
    	    		je2s.put(e2e.get(e), e.toString() + " uses:" + v.getName());
    			}
    		}//end while loop
    		
    	}//end if
    	
    	return jgraph;
	}
	
	public Map<Vertex, String> getVertexLabel()
	{
		if (v2s == null)
			getJungGraph();
		
		return v2s;
	}

	public Map<Vertex, Integer> getVertexType()
	{
		if(v2i == null)
			getJungGraph();
		
		return v2i;				
	}
	
	public Map<Vertex, Integer> getVertexType(coverage.graph.Path path)
	{
		if(v2i == null)
			getJungGraph();
		
		if(path == null)
			return v2i;
		
		//recover some passed node to unpassed 
		Iterator<coverage.graph.Node> nodes = xgraph.getNodeIterator();
		while(nodes.hasNext())
		{
			coverage.graph.Node node = nodes.next();
			Vertex v = n2v.get(node);
    		if(xgraph.isInitialNode(node))
    			v2i.put(v, INITIALNODE);
    		else if(xgraph.isEndingNode(node))
    			v2i.put(v, ENDINGNODE);
    		else 
    			v2i.put(v, UNPASSED);
		}
		
		//change the nodes in the path to passed
		Iterator<coverage.graph.Node> pNodes = path.getNodeIterator();
		while(pNodes.hasNext())
		{
			coverage.graph.Node node = pNodes.next();
			Vertex v = n2v.get(node);
			v2i.put(v, PASSED);
		}
		
		return v2i;		
	}
	
	public Map<edu.uci.ics.jung.graph.Edge, String> getEdgeLabel()
	{
		if(je2s == null)
			getJungGraph();
		
		return je2s;
	}
	
	public Map<edu.uci.ics.jung.graph.Edge, Integer> getEdgeType()
	{
		if(je2i == null)
			getJungGraph();
		
		return je2i;
	}
	
	public Map<edu.uci.ics.jung.graph.Edge, Integer> getEdgeType(coverage.graph.Path path)
	{
		if(je2i == null)
			getJungGraph();
		
		if(path == null)
			return je2i;
		
		//recovery
		Iterator<?> vedges = jgraph.getEdges().iterator();
		while(vedges.hasNext())
		{
			edu.uci.ics.jung.graph.Edge vedge = (edu.uci.ics.jung.graph.Edge)vedges.next();
			je2i.put(vedge, UNPASSED); //RECOVERY						
		}
		
		//change the edges in the path to passed
		List<coverage.graph.Edge> edges = path.getEdgeList();
		for(int i = 0;i < edges.size();i++)
		{
			Node src = edges.get(i).getSrc();
			Node dest = edges.get(i).getDest();
			Vertex vsrc = n2v.get(src);
			Vertex vdest = n2v.get(dest);
			edu.uci.ics.jung.graph.Edge vedge = vsrc.findEdge(vdest);
			if(vedge != null)
				je2i.put(vedge, PASSED);
		}
		
		return je2i;
	}
	
}
