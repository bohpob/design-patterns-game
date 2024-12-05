package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class Director {

    private BuilderEnemy builder;

    public Director(BuilderEnemy builder) {
        this.builder = builder;
    }

    public void setBuilder(BuilderEnemy builder) {
        this.builder = builder;
    }

    public AbsEnemy construct(Position position) {
        builder.reset();
        builder.setPosition(position);
        builder.setHealth();
        builder.setScoreValue();
        return builder.build();
    }
}
