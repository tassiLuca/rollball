package rollball.core;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import rollball.graphics.Scene;
import rollball.input.Command;
import rollball.input.Controller;
import rollball.model.GameState;
import rollball.model.events.HitBorderEvent;
import rollball.model.events.HitPickableEvent;
import rollball.model.events.WorldEvent;
import rollball.model.events.WorldEventListener;

/**
 * [CONTROLLER]
 * Game engine skeleton.
 */
public final class GameEngine implements Controller, WorldEventListener {

    /**
     * The game frame rate.
     */
    private static final long PERIOD = 50;
    /**
     * The world game state.
     */
    private final GameState gameState;
    /**
     * The game view.
     */
    private final Scene view;
    /**
     * The queue of commands to be processed.
     * [NOTE] BlockingQueue implementations are thread-safe.
     */
    private final BlockingQueue<Command> cmdQueue;
    /**
     * The queue of events to be processed.
     */
    private final BlockingQueue<WorldEvent> eventsQueue;

    public GameEngine() {
        // Say to the gameState that I am the world event listener!
        this.gameState = new GameState(this);
        this.cmdQueue = new ArrayBlockingQueue<>(100);
        this.eventsQueue = new ArrayBlockingQueue<>(100);
        this.view = new Scene(this.gameState);
        this.view.setInputController(this);
    }

    /**
     * Extract, if present, a command in the command 
     * queue and executes it.
     */
    private void processInput() {
        final Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(this.gameState.getWorld());
        }
    }

    /**
     * Updates the world consistently with the amount of 
     * time passed from the last update.
     * @param elapsed
     *          the amount of time elapsed from the last update
     */
    private void updateGame(final int elapsed) {
        this.gameState.update(elapsed);
        checkEvents();
    }

    /**
     * View rendering.
     */
    private void render() {
        this.view.render();
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

    private void checkEvents() {
        this.eventsQueue.stream().forEach(ev -> {
            if (ev instanceof HitPickableEvent) {
                final HitPickableEvent pickEv = (HitPickableEvent) ev;
                this.gameState.getWorld().removePickUp(pickEv.getCollisionPick());
                this.gameState.incScore();
            } else if (ev instanceof HitBorderEvent) {
                this.gameState.decScore();
            }
        });
        this.eventsQueue.clear();
    }

    /**
     * {@inheritDoc}
     */
    public void notifyCommand(final Command cmd) {
        cmdQueue.add(cmd);
    }

    /**
     * {@inheritDoc}
     */
    public void notifyEvent(final WorldEvent ev) {
        eventsQueue.add(ev);
    }

}
