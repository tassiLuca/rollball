package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

/**
 * Catch the common implementations of game object.
 */
public abstract class AbstractGameObject implements GameObject {

    private static final double TIME_CONVERSION_FACTOR = 0.001;
    /**
     * The current object position.
     */
    private P2d pos;
    /**
     * The current object velocity.
     */
    private V2d vel;

    public AbstractGameObject(final P2d pos, final V2d vel) {
        this.pos = pos;
        this.vel = vel;
    }

    @Override
    public P2d getCurrentPos() {
        return this.pos;
    }

    @Override
    public V2d getCurrentVel() {
        return this.vel;
    }

    @Override
    public void updateState(final int dt) {
        this.pos = this.pos.sum(this.vel.mul(dt * TIME_CONVERSION_FACTOR));
    }

}
