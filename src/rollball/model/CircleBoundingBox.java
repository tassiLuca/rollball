package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

/**
 * Specialize a bounding box for circle shapes.
 */
public class CircleBoundingBox implements BoundingBox {
    /**
     * The circle center point.
     */
    private final P2d center;
    /**
     * The circle radius.
     */
    private final double radius;

    /**
     * Initialize a new CircleBoundingBox.
     * @param center
     *          the circle center point
     * @param radius
     *          the circle radius
     */
    public CircleBoundingBox(final P2d center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCollidingWith(final P2d p, final double radius) {
        return new V2d(p, center).module() <= radius + this.radius;
    }

}
