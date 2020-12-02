package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

/**
 * Represents the game elements.
 */
public interface GameObject {

    /**
     * @param pos
     *          the new object position
     */
    void setPos(P2d pos);

    /**
     * @return
     *          the current object position
     */
    P2d getCurrentPos();

    /**
     * @return 
     *          the current velocity
     */
    V2d getCurrentVel();

    /**
     * @param vel
     *          the new object velocity
     */
    void setVel(V2d vel);

    /**
     * Update the object position consistently with
     * the amount of time passed from the last update.
     * @param dt
     *          the amount of time in milliseconds passed
     */
    void updateState(int dt);

}
