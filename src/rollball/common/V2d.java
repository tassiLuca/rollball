package rollball.common;

/**
 * A 2-dimensional vector.
 */
public final class V2d {
    /**
     * The horizontal component. 
     */
    private final double x;
    /**
     * The vertical component.
     */
    private final double y;

    /**
     * Initialize a new vector.
     * @param x
     *          the horizontal component.
     * @param y
     *          the vertical component.
     */
    public V2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initialize a new vector with two points.
     * @param from
     *          the starting point
     * @param to
     *          the arrival point
     */
    public V2d(final P2d from, final P2d to) {
        this.x = to.getX() - from.getX();
        this.y = to.getY() - from.getY();
    }

    /**
     * @param v
     *          the V2d vector to sum
     * @return a new V2d, result of the vectors sum.
     */
    public V2d sum(final V2d v) {
        return new V2d(this.x + v.x, this.y + v.y);
    }

    /**
     * @return the vector modulus.
     */
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * @return a new V2d, representing the vector normalization.
     */
    public V2d getNormalized() {
        final double module = this.module();
        return new V2d(this.x / module, this.y / module);
    }

    /**
     * Multiply a vector by a scalar.
     * @param fact
     *          the multiplication factor
     * @return a new V2d, result of the vector multiplication.
     */
    public V2d mul(final double fact) {
        return new V2d(this.x * fact, this.y * fact);
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

}
