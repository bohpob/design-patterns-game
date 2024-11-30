package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public interface IVisitor {

    void visitCannon(AbsCannon cannon);

    void visitMissile(AbsMissile missile);

    void visitEnemy(AbsEnemy enemy);

    // collisions, gameInfo, ...
}
