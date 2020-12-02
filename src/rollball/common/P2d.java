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
     * Subtract a vector to the point.
     * @param v
     *          The V2d vector to subtract to the point
     * @return a new P2d, the result of subtraction
     */
    public P2d sub(final V2d v) {
        return new P2d(this.x - v.getX(), this.y - v.getY());
    }

    /**
     * Sum a vector to the point.
     * @param v
     *          The V2d vector to sum to the point
     * @return a new P2d, the result of sum
     */
    public P2d sum(final V2d v) {
        return new P2d(this.x + v.getX(), this.y + v.getY());
    }

    /**
     * @return the point abscissa
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the point ordinate
     */
    public double getY() {
        return this.y;
    }

}
