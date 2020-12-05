package rollball.model;

import java.util.HashSet;
import java.util.Set;

import rollball.common.P2d;
import rollball.model.bbox.RectBoundingBox;
import rollball.model.events.HitBorderEvent;
import rollball.model.events.HitPickableEvent;
import rollball.model.events.WorldEventListener;
import rollball.model.objects.Ball;
import rollball.model.objects.GameObject;
import rollball.model.objects.PickUpObj;

public class World {

    /**
     * The game ball.
     */
    private Ball ball;
    /**
     * The set of pickup objects.
     */
    private final Set<PickUpObj> picks = new HashSet<>();
    /**
     * The world bounding box.
     */
    private final RectBoundingBox worldBox;
    /**
     * The world event listener.
     */
    private final WorldEventListener eventListener;

    /**
     * Initialize a new word.
     * @param box
     *          the world rectangular bounding box
     * @param listener
     *          the world event listener
     */
    public World(final RectBoundingBox box, final WorldEventListener listener) {
        this.worldBox = box;
        this.eventListener = listener;
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

    /**
     * Remove the pickup object.
     * @param obj
     *          the pickup to remove
     */
    public void removePickUp(final PickUpObj obj) {
        this.picks.remove(obj);
    }

    /** 
     * Check if the ball is touching the world boundaries
     * and, if so, flips the direction of the ball.
     */
    private void checkBoundaries() {
        final P2d ballPos = this.ball.getCurrentPos();
        final double ballRadius = this.ball.getRadius();
        final double upperLimit = this.worldBox.getUpperLeftCorner().getY();
        final double bottomLimit = this.worldBox.getBottomRightCorner().getY();
        final double leftLimit = this.worldBox.getUpperLeftCorner().getX();
        final double rightLimit = this.worldBox.getBottomRightCorner().getX();
        // Upper and bottom boundaries
        if (ballPos.getY() + ballRadius > upperLimit) {
            this.ball.setPos(new P2d(ballPos.getX(), upperLimit - ballRadius));
            this.ball.flipVelOnY();
            this.eventListener.notifyEvent(new HitBorderEvent(new P2d(ballPos.getX(), upperLimit)));
        } else if (ballPos.getY() - ballRadius < bottomLimit) {
            this.ball.setPos(new P2d(ballPos.getX(), bottomLimit + ballRadius));
            this.ball.flipVelOnY();
            this.eventListener.notifyEvent(new HitBorderEvent(new P2d(ballPos.getX(), bottomLimit)));
        }
        // Left and right boundaries
        if (ballPos.getX() + ballRadius > rightLimit) {
            this.ball.setPos(new P2d(rightLimit - ballRadius, ballPos.getY()));
            this.ball.flipVelOnX();
            this.eventListener.notifyEvent(new HitBorderEvent(new P2d(rightLimit, ballPos.getY())));
        } else if (ballPos.getX() - ballRadius < leftLimit) {
            this.ball.setPos(new P2d(leftLimit + ballRadius, ballPos.getY()));
            this.ball.flipVelOnX();
            this.eventListener.notifyEvent(new HitBorderEvent(new P2d(leftLimit, ballPos.getY())));
        }
    }

    /**
     * Check if the ball is touching a pickup object.
     * In this case delete it.
     */
    private void checkCollisions() {
        final P2d ballPos = this.ball.getCurrentPos();
        final double ballRadius = this.ball.getRadius();
        PickUpObj found = null;
        for (final PickUpObj obj : this.picks) {
            if (obj.getBoundingBox().isCollidingWith(ballPos, ballRadius)) {
                found = obj;
                break;
            }
        }
        if (found != null) {
            this.eventListener.notifyEvent(new HitPickableEvent(found));
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
        checkCollisions();
    }

    /**
     * @return a set containing all game objects
     */
    public Set<GameObject> getSceneObjects() {
        final Set<GameObject> objects = new HashSet<>(picks);
        objects.add(ball);
        return objects;
    }

    /**
     * 
     * @return the world bounding bo
     */
    public RectBoundingBox getBoundingBox() {
        return this.worldBox;
    }

}
