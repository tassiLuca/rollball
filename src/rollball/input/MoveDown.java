package rollball.input;

import rollball.common.V2d;
import rollball.model.Ball;
import rollball.model.World;

public class MoveDown implements Command {

    /**
     * {@inheritDoc}
     */
    public void execute(final World world) {
        final Ball ball = world.getBall();
        final double speed = ball.getCurrentVel().module();
        ball.setVel(new V2d(0, -1).mul(speed));
    }

}
