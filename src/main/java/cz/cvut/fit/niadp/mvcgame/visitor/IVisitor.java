package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public interface IVisitor {

    void visitCannon(AbsCannon cannon);

    void visitMissile(AbsMissile missile);

    void visitEnemy(AbsEnemy enemy);

    void visitGameInfo(AbsGameInfo gameInfo);

    void visitCollision(AbsCollision collision);

    void visitLevel(AbsLevel level);
}
