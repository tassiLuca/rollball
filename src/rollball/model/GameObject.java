package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

/**
 * Represents a game element.
 */
public interface GameObject {

    /**
     * @return a P2d representing the current object position
     */
    P2d getCurrentPos();

    /**
     * @return a V2d representing the current velocity
     */
    V2d getCurrentVel();

    /**
     * Update the object position consistently with
     * the amount of time passed from the last update.
     * @param dt
     *          the amount of time in milliseconds passed
     */
    void updateState(int dt);

}
