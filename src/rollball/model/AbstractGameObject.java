package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

/**
 * Catch the common implementations of game object.
 */
public abstract class AbstractGameObject implements GameObject {

    /**
     * Convention factor from milliseconds to seconds.
     */
    private static final double TIME_CONVERSION_FACTOR = 0.001;
    /**
     * The current object position.
     */
    private P2d pos;
    /**
     * The current object velocity.
     */
    private V2d vel;
    /**
     * The game object bounding box.
     */
    private final BoundingBox bBox;

    /**
     * Initialize a new GameObject.
     * @param pos
     *          the game object position
     * @param vel
     *          the game object speed
     * @param bBox
     *          the game object bounding box
     */
    protected AbstractGameObject(final P2d pos, final V2d vel, final BoundingBox bBox) {
        this.pos = pos;
        this.vel = vel;
        this.bBox = bBox;
    }

    /**
     * {@inheritDoc}
     */
    public void flipVelOnX() {
        this.vel = new V2d(-this.vel.getX(), this.vel.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void flipVelOnY() {
        this.vel = new V2d(this.vel.getX(), -this.vel.getY());
    }

    /**
     * {@inheritDoc}
     */
    public void setVel(final V2d vel) {
        this.vel = vel;
    }

    /**
     * {@inheritDoc}
     */
    public V2d getCurrentVel() {
        return this.vel;
    }

    /**
     * {@inheritDoc}
     */
    public void setPos(final P2d pos) {
        this.pos = pos;
    }

    /**
     * {@inheritDoc}
     */
    public void updateState(final int dt) {
        this.pos = this.pos.sum(this.vel.mul(dt * TIME_CONVERSION_FACTOR));
    }

    /**
     * {@inheritDoc}
     */
    public P2d getCurrentPos() {
        return this.pos;
    }

    /**
     * {@inheritDoc}
     */
    public BoundingBox getBoundingBox() {
        return this.bBox;
    }

}
