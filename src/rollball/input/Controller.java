package rollball.input;

public interface Controller {

    /**
     * Add the command to the command queue ready
     * to be executed.
     * @param cmd
     *          the next command to be executed
     */
    void notifyCommand(Command cmd);

}
