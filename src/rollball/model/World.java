package rollball.model;

import java.util.HashSet;
import java.util.Set;

import rollball.common.P2d;

public class World {

    /**
     * The game ball.
     */
    private Ball ball;
    /**
     * The set of pickup objects.
     */
    private final Set<GameObject> picks = new HashSet<>();
    private RectBoundingBox worldBox;

    public World(final RectBoundingBox box) {
        this.worldBox = box;
    }

    /**
     * Set the world ball.
     * @param ball
     */
    public void setBall(final Ball ball) {
        this.ball = ball;
    }

    /**
     * @return the game ball
     */
    public Ball getBall() {
        return this.ball;
    }

    /**
     * Add a new pickUpObject to the picks set.
     * @param obj
     *          the pickup object
     */
    public void addPickUp(final PickUpObj obj) {
        this.picks.add(obj);
    }

    private void checkBoundaries() {
        final P2d ballPos = this.ball.getCurrentPos();
        final double ballRadius = this.ball.getRadius();
        final double upperLimit = this.worldBox.getUpperLeftCorner().getY();
        final double bottomLimit = this.worldBox.getBottomRightCorner().getY();
        final double leftLimit = this.worldBox.getUpperLeftCorner().getX();
        final double rightLimit = this.worldBox.getBottomRightCorner().getX();
        //
        if (ballPos.getY() + ballRadius >= upperLimit) {
            this.ball.setPos(new P2d(ballPos.getX(), upperLimit - ballRadius));
            this.ball.flipVelOnY();
        } else if (ballPos.getY() - ballRadius <= bottomLimit) {
            this.ball.setPos(new P2d(ballPos.getX(), bottomLimit + ballRadius));
            this.ball.flipVelOnY();
        }
        //
        if (ballPos.getX() + ballRadius >= rightLimit) {
            this.ball.setPos(new P2d(rightLimit - ballRadius, ballPos.getY()));
            this.ball.flipVelOnX();
        } else if (ballPos.getX() - ballRadius <= leftLimit) {
            this.ball.setPos(new P2d(rightLimit + ballRadius, ballPos.getY()));
            this.ball.flipVelOnX();
        }
    }

    /**
     * Update the state of game objects.
     * @param dt
     */
    public void updateWorld(final int dt) {
        this.picks.stream().forEach(obj -> obj.updateState(dt));
        this.ball.updateState(dt);
        checkBoundaries();
    }

    /**
     * @return a set containing all game objects
     */
    public Set<GameObject> getSceneObjects() {
        final Set<GameObject> objects = new HashSet<>(picks);
        objects.add(ball);
        return objects;
    }

}
