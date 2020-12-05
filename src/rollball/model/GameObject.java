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
     * Set the game object position.
     * @param pos
     *          the game object position
     */
    void setPos(P2d pos);

    /**
     * @return a V2d representing the current velocity
     */
    V2d getCurrentVel();

    /**
     * Set the game object velocity.
     * @param vel
     *          the game object velocity
     */
    void setVel(V2d vel);

    /**
     * Flip horizontally the velocity of the game object.
     */
    void flipVelOnX();

    /**
     * Flip vertically the velocity of the game object.
     */
    void flipVelOnY();

    /**
     * @return the game object bounding box.
     */
    BoundingBox getBoundingBox();

    /**
     * Update the object position consistently with
     * the amount of time passed from the last update.
     * @param dt
     *          the amount of time in milliseconds passed
     */
    void updateState(int dt);

}
