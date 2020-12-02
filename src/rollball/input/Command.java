package rollball.input;

import rollball.model.World;

/**
 * COMMAND PATTERN. 
 */
public interface Command {

    /**
     * Update the world consistently with 
     * the received inputs.
     */
    public void execute(World world);

}
