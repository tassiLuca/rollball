package rollball.model;

import java.util.HashSet;
import java.util.Set;

public class World {

    /**
     * The game ball.
     */
    private Ball ball;
    /**
     * The set of pickup objects.
     */
    private final Set<GameObject> picks = new HashSet<>();

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
     * Update the state of game objects.
     * @param dt
     */
    public void updateWorld(final int dt) {
        this.picks.stream().forEach(obj -> obj.updateState(dt));
        this.ball.updateState(dt);
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
