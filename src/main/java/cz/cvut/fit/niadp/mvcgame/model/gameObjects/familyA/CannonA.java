package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MOVE_STEP;

public class CannonA extends AbsCannon {

    public CannonA(Position position, IGameObjectsFactory gameObjectsFactory) {
        this.position = position;
        this.gameObjectsFactory = gameObjectsFactory;
    }

    public void moveUp() {
        move(new Vector(0, -1 * MOVE_STEP));
    }

    public void moveDown() {
        move(new Vector(0, MOVE_STEP));
    }

    @Override
    public AbsMissile shoot() {
        return gameObjectsFactory.createMissile();
    }
}
