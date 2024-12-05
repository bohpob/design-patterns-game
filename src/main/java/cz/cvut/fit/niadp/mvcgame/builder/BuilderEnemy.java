package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public interface BuilderEnemy {

    void reset();

    void setPosition(Position position);

    void setHealth();

    void setScoreValue();

    AbsEnemy build();
}
