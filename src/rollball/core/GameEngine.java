package rollball.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import rollball.common.P2d;
import rollball.common.V2d;
import rollball.graphics.Scene;
import rollball.input.Command;
import rollball.input.Controller;
import rollball.model.Ball;
import rollball.model.PickUpObj;
import rollball.model.RectBoundingBox;
import rollball.model.World;

/**
 * [CONTROLLER]
 * Game engine skeleton.
 */
public class GameEngine implements Controller {

    private static final long PERIOD = 50;
    /**
     * The world game.
     */
    private final World world;
    /**
     * The game view.
     */
    private final Scene view;
    /**
     * The queue of commands to be processed.
     * [NOTE] BlockingQueue implementations are thread-safe.
     */
    private final BlockingQueue<Command> cmdQueue;

    public GameEngine() {
        this.cmdQueue = new ArrayBlockingQueue<>(100);
        this.world = new World(new RectBoundingBox(new P2d(-500, 300), new P2d(500, -300)));
        this.world.setBall(new Ball(new P2d(0, 0), 25, new V2d(150, 87)));
        this.world.addPickUp(new PickUpObj(new P2d(78, -89), 12));
        this.world.addPickUp(new PickUpObj(new P2d(400, 200), 32));
        this.world.addPickUp(new PickUpObj(new P2d(-205, 100), 150));
        this.world.addPickUp(new PickUpObj(new P2d(-250, -250), 20));
        this.world.addPickUp(new PickUpObj(new P2d(150, 0), 100));
        this.view = new Scene(this.world);
        this.view.setInputController(this);
    }

    /**
     * Implements the GAME LOOP pattern: control loop ruling the 
     * execution of a game. Decouple the progression of game time 
     * from user input and processor speed. 
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

    /**
     * View rendering.
     */
    private void render() {
        this.view.render();
    }

    /**
     * Updates the world consistently with the amount of 
     * time passed from the last update.
     * @param elapsed
     *          the amount of time elapsed from the last update
     */
    private void updateGame(final int elapsed) {
        this.world.updateWorld(elapsed);
    }

    private void processInput() {
        final Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(world);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void notifyCommand(final Command cmd) {
        cmdQueue.add(cmd);
    }

}
