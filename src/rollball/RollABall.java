package rollball;

import rollball.core.GameEngine;

public final class RollABall {

    private RollABall() { }

    public static void main(final String...args) {
        final GameEngine engine = new GameEngine();
        engine.mainLoop();
    }

}
