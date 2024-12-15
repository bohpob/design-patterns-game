package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;

public class CollisionA extends AbsCollision {

    private final String resource;

    public CollisionA(Position initPosition, String resource) {
        super(initPosition);
        this.resource = resource;
    }

    @Override
    public String getResource() {
        return resource;
    }
}
