package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public interface IGameObjectsFactory {

    AbsCannon createCannon();

    AbsMissile createMissile();

    // createEnemies() ...
}
