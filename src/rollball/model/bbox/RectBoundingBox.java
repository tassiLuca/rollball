package rollball.model.bbox;

import rollball.common.P2d;

/**
 * Specialize a bounding box for rectangular shapes.
 */
public class RectBoundingBox implements BoundingBox {
    /**
     * The upper left corner point.
     */
    private final P2d upperLeftCorner;
    /**
     * The bottom right corner point.
     */
    private final P2d bottomRightCorner;

    /**
     * Initialize a new rectangular bounding box.
     * @param upperLeftCorner
     * @param bottomRightCorner
     */
    public RectBoundingBox(final P2d upperLeftCorner, final P2d bottomRightCorner) {
        this.upperLeftCorner = upperLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }

    /**
     * @return the upper left corner point
     */
    public P2d getUpperLeftCorner() {
        return this.upperLeftCorner;
    }

    /**
     * @return the bottom right corner point
     */
    public P2d getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    /**
     * Superfluous for this game!
     * {@inheritDoc}
     */
    public boolean isCollidingWith(final P2d p, final double radius) {
        // TODO Auto-generated method stub
        return false;
    }

}
