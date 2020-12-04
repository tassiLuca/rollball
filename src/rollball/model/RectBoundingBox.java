package rollball.model;

import rollball.common.P2d;

public class RectBoundingBox implements BoundingBox {

    private P2d upperLeftCorner;
    private P2d bottomRightCorner;

    /**
     * @param upperLeftCorner
     * @param bottomRightCorner
     */
    public RectBoundingBox(final P2d upperLeftCorner, final P2d bottomRightCorner) {
        this.upperLeftCorner = upperLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }

    public P2d getUpperLeftCorner() {
        return this.upperLeftCorner;
    }

    public P2d getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    @Override
    public boolean isCollidingWith(final P2d p, final double radius) {
        // TODO Auto-generated method stub
        return false;
    }

}
