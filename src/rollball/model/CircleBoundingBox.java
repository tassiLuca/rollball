package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

public class CircleBoundingBox implements BoundingBox {

    private final P2d center;
    private final double radius;

    /**
     * @param center
     * @param radius
     */
    public CircleBoundingBox(P2d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean isCollidingWith(final P2d p, final double radius) {
        return new V2d(p, center).module() <= radius + this.radius;
    }

}
