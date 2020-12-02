package rollball.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import rollball.common.P2d;
import rollball.common.V2d;
import rollball.graphics.Scene;
import rollball.model.Ball;
import rollball.model.PickUpObj;
import rollball.model.World;

/**
 * Game engine skeleton.
 */
public class GameEngine {

    private static final long PERIOD = 1000;
    private final Logger logger = Logger.getLogger("GameEngine");

    /**
     * The world game.
     */
    private final World world;
    /**
     * The game view.
     */
    private final Scene view;
    
    public GameEngine() {
        this.world = new World();
        this.world.setBall(new Ball(new P2d(-1, -1), new V2d(1, 1)));
        this.world.addPickUp(new PickUpObj(new P2d(0, 1)));
        this.world.addPickUp(new PickUpObj(new P2d(2, 0)));
        this.view = new Scene(this.world, 600, 600);
    }

    /**
     * Implements the game loop pattern.
     */
    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(current);
            lastTime = current;
        }
    }

    /**
     * Take a little nap to synch with the frame rate.
     * @param current
     *          the time when the current iteration started.
     */
    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) { }
        }
    }

    private void render() {
        this.view.render();
    }

    private void updateGame(final int elapsed) {
        this.world.updateWorld(elapsed);
    }

    private void processInput() {
        logger.log(Level.INFO, "...process input...");
    }

}
