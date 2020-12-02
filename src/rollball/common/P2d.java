package rollball.common;

/**
 * A 2-dimensional point.
 */
public final class P2d {

    /**
     * The point abscissa.
     */
    private final double x;
    /**
     * The point ordinate.
     */
    private final double y;

    /**
     * @param x
     *          the point abscissa
     * @param y
     *          the point ordinate
     */
    public P2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param p
     * @return a new V2d
     */
    public V2d sub(final P2d p) {
        return new V2d(this.x - p.getX(), this.y - p.getY());
    }

    /**
     * 
     * @param v
     * @return a new P2d
     */
    public P2d sum(final V2d v) {
        return new P2d(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return this.y;
    }

}
