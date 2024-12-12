package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject implements IObjectResource {

    private int scoreValue;
    private int health;

    public AbsEnemy(Position position) {
        this.position = position;
    }

    public void decreaseHealth() {
        health--;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public abstract String getDeathResource();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
