package rollball.model.events;

import rollball.model.objects.PickUpObj;

public class HitPickableEvent implements WorldEvent {
    /**
     * The colliding pickup object.
     */
    private final PickUpObj pick;

    /**
     * 
     * @param pick
     *          the colliding pickup object
     */
    public HitPickableEvent(final PickUpObj pick) {
        this.pick = pick;
    }

    /**
     * @return the colliding pickup object
     */
    public PickUpObj getCollisionPick() {
        return this.pick;
    }
}
