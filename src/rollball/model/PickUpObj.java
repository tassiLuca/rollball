package rollball.model;

import rollball.common.P2d;
import rollball.common.V2d;

public class PickUpObj extends AbstractGameObject {

    public PickUpObj(final P2d pos) {
        super(pos, new V2d(0, 0));
    }

}
