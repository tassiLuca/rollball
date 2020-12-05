package rollball.input;

import rollball.common.V2d;
import rollball.model.World;
import rollball.model.objects.Ball;

public final class MoveRight implements Command {

    /**
     * {@inheritDoc}
     */
    public void execute(final World world) {
        final Ball ball = world.getBall();
        final double speed = ball.getCurrentVel().module();
        ball.setVel(new V2d(1, 0).mul(speed));
    }
}
