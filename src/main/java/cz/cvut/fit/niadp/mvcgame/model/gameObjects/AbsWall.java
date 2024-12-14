package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.prototype.Prototype;

public abstract class AbsWall extends GameObject implements IObjectResource, Prototype {

    protected int scoreValue;

    public AbsWall(Position position, int scoreValue) {
        this.position = position;
        this.scoreValue = scoreValue;
    }

    public AbsWall(AbsWall wall) {
        this(wall.getPosition(), wall.getScoreValue());
    }

    public int getScoreValue() {
        return scoreValue;
    }

    @Override
    public abstract AbsWall clone();
}
