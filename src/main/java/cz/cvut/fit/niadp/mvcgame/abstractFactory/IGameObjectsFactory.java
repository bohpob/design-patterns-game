package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.List;

public interface IGameObjectsFactory {

    AbsCannon createCannon();

    AbsMissile createMissile(double initAngle, int initVelocity);

    List<AbsEnemy> createEnemies();
}
