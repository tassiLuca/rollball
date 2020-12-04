package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

public class PickUpObj extends AbstractGameObject {

    /**
     * The edge length. 
     */
    private final double edge;

    /**
     * Initialize a new pickup object.
     * @param pos
     *          the central position
     * @param edge
     *          the edge length
     */
    public PickUpObj(final P2d pos, final double edge) {
        super(pos, new V2d(0, 0), new CircleBoundingBox(new P2d(pos.getX(), pos.getY()), edge / 2));
        this.edge = edge;
    }

    /**
     * @return the edge length
     */
    public double getEdge() {
        return this.edge;
    }
}
