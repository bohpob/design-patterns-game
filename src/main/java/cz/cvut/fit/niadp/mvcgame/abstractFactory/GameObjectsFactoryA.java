package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.MissileA;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.CANNON_POS_X;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.CANNON_POS_Y;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private final GameModel model;

    public GameObjectsFactoryA(GameModel gameModel) {
        this.model = gameModel;
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(CANNON_POS_X, CANNON_POS_Y), this);
    }

    @Override
    public MissileA createMissile() {
        return new MissileA(new Position(model.getCannonPosition().getX(), model.getCannonPosition().getY()));
    }
}
