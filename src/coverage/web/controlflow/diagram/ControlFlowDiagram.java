package coverage.web.controlflow.diagram;

import java.util.ArrayList;
import java.util.List;

import com.drgarbage.visualgraphic.model.ModelElement;
import com.drgarbage.visualgraphic.model.VertexBase;

public class ControlFlowDiagram extends ModelElement
{
    private static final long serialVersionUID = 1;

    /**
     * ID for the name property value (used for by the corresponding property
     * descriptor).
     */
    public static final String NAME_PROP = "name";

    /** Property ID to use when a child is added to this diagram. */
    public static final String CHILD_ADDED_PROP = "ControlFlowGraphDiagram.ChildAdded";

    /** Property ID to use when a child is removed from this diagram. */
    public static final String CHILD_REMOVED_PROP = "ControlFlowGraphDiagram.ChildRemoved";

    /** List of vertices */
    private List<VertexBase> vertices = new ArrayList<VertexBase>();

    /**
     * Diagram name.
     */
    private String name = null;

    /**
     * Counter to generate unique id of this diagram.
     */
    private int uniqueIdCounter = 1;

    /**
     * Constructor.
     */
    public ControlFlowDiagram()
    {
        super();
        this.setId(0);
    }

    /**
     * Add a vertex to this diagram.
     * 
     * @param s,
     *            a non-null vertex instance
     * @return true, if the vertex has been added, false otherwise
     */
    public boolean addChild(VertexBase s)
    {
        s.setId(getUniqueIdCounter());
        boolean add = vertices.add(s);
        if (s != null && add)
        {
            firePropertyChange(CHILD_ADDED_PROP, null, s);
            return true;
        }
        return false;
    }

    /**
     * Returns unique id of this diagram for its children.
     * 
     * @return id
     */
    private int getUniqueIdCounter()
    {
        uniqueIdCounter++;
        return uniqueIdCounter;
    }

    /**
     * Return a List of vertices in this diagram. The returned List should not be
     * modified.
     */
    public List<VertexBase> getChildren()
    {
        return vertices;
    }

    /**
     * Remove a vertex from this diagram.
     * 
     * @param s,
     *            a non-null vertex instance;
     * @return true, if the vertex was removed, false otherwise
     */
    public boolean removeChild(VertexBase s)
    {
        if (s != null && vertices.remove(s))
        {
            firePropertyChange(CHILD_REMOVED_PROP, null, s);
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.visualgraphic.model.ModelElement#setPropertyValue(java.lang.
     * Object, java.lang.Object)
     */
    public void setPropertyValue(Object propertyId, Object value)
    {
        if (NAME_PROP.equals(propertyId))
        {
            name = value.toString();
        }

        super.setPropertyValue(propertyId, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.drgarbage.visualgraphic.model.ModelElement#getPropertyValue(java.lang.
     * Object)
     */
    public Object getPropertyValue(Object propertyId)
    {
        if (NAME_PROP.equals(propertyId))
        {
            return name;
        }

        return super.getPropertyValue(propertyId);
    }

}
