package rollball.model.events;

public interface WorldEventListener {

    /**
     * Add the event to the command queue ready
     * to be executed.
     * @param ev
     *          the next command to be executed
     */
    void notifyEvent(WorldEvent ev);

}
