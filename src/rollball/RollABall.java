package rollball;

import rollball.core.GameEngine;

public final class RollABall {

    private RollABall() { }

    public static void main(final String...args) throws InterruptedException {
        final GameEngine engine = new GameEngine();
        Thread.sleep(1000);
        engine.mainLoop();
    }

}
