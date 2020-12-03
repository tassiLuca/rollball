package rollball.input;

import rollball.model.World;

/**
 * COMMAND PATTERN. 
 */
public interface Command {

    /**
     * Update the world consistently with 
     * the received inputs.
     * @param world
     *          the world game
     */
    void execute(World world);

}
