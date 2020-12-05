package rollball.model.bbox;

import rollball.common.P2d;

/**
 * Represents a bounding box for the game objects. In other 
 * words, what geometrically manages to effectively describe
 * the game objects in order to test if a collision occurred. 
 */
public interface BoundingBox {

    /**
     * Check if the object represented by this bounding box
     * is colliding with an another game object.
     * @param p
     *         the central point of the game object
     * @param radius
     *         the radius of the game object
     * @return true if the current object is colliding
     */
    boolean isCollidingWith(P2d p, double radius);

}
