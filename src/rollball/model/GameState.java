package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;
import rollball.model.bbox.RectBoundingBox;
import rollball.model.events.WorldEventListener;
import rollball.model.objects.Ball;
import rollball.model.objects.PickUpObj;

/**
 * Manages the game: it keeps track of the game state
 * meaning both the world and its objects and the play score.
 */
public class GameState {
    /**
     * The game score.
     */
    private int score;
    /**
     * The game world.
     */
    private final World world;

    /**
     * Initialize the world and the score game.
     * TODO Improve the settings management.
     * @param listener
     *          the world event listener
     */
    public GameState(final WorldEventListener listener) {
        this.world = new World(new RectBoundingBox(new P2d(-500, 300), new P2d(500, -300)), listener);
        this.world.setBall(new Ball(new P2d(0, 0), 25, new V2d(150, 0)));
        this.world.addPickUp(new PickUpObj(new P2d(78, -89), 12));
        this.world.addPickUp(new PickUpObj(new P2d(400, 200), 32));
        this.world.addPickUp(new PickUpObj(new P2d(-205, 100), 150));
        this.world.addPickUp(new PickUpObj(new P2d(-250, -250), 20));
        this.world.addPickUp(new PickUpObj(new P2d(150, 0), 100));
    }

    /**
     * Increment the game score.
     */
    public void incScore() {
        this.score++;
    }

    /**
     * Decrement the game score.
     */
    public void decScore() {
        this.score--;
    }

    /**
     * @return the game world
     */
    public World getWorld() {
        return this.world;
    }

    /**
     * @return the game score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Update the world.
     * @param dt
     *          the time passed
     */
    public void update(final int dt) {
        this.world.updateWorld(dt);
    }

}
