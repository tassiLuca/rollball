package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

public class PickUpObj extends AbstractGameObject {

    private final double edge;

    public PickUpObj(final P2d pos, final double edge) {
        super(pos, new V2d(0, 0), new CircleBoundingBox(new P2d(pos.getX(), pos.getY()), edge / 2));
        this.edge = edge;
    }

    public double getEdge() {
        return this.edge;
    }
}
