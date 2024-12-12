package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

import java.util.List;

public interface IGameObjectsFactory {

    AbsCannon createCannon();

    AbsMissile createMissile(double initAngle, int initVelocity);

    List<AbsEnemy> createEnemies();

    AbsGameInfo createGameInfo();

    AbsCollision createCollision(String resource, Position position);
}
