package rollball.model.events;

import rollball.common.P2d;

public class HitBorderEvent implements WorldEvent {

    /**
     * Where the collision occurred.
     */
    private final P2d where;

    /**
     * 
     * @param where
     *          the position where the collision occurred
     */
    public HitBorderEvent(final P2d where) {
        this.where = where;
    }

    /**
     * @return where the collision occurred
     */
    public P2d getWhere() {
        return this.where;
    }

}
