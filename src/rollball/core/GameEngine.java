package rollball.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Game engine skeleton.
 */
public class GameEngine {

    private final long period = 1000;
    private Logger logger = Logger.getLogger("GameEngine");

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
        if (dt < this.period) {
            try {
                Thread.sleep(this.period - dt);
            } catch (InterruptedException e) { }
        }
    }

    private void render() {
        logger.log(Level.INFO, "...render...");
    }

    private void updateGame(final int elapsed) {
        logger.log(Level.INFO, "...update: elapsed " + elapsed);
    }

    private void processInput() {
        logger.log(Level.INFO, "...process input...");
    }

}
