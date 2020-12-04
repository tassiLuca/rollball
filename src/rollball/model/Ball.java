package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

public class Ball extends AbstractGameObject {

    private final double radius;

    public Ball(final P2d pos, final double radius, final V2d vel) {
        super(pos, vel, new CircleBoundingBox(new P2d(pos.getX(), pos.getY()), radius));
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

}
