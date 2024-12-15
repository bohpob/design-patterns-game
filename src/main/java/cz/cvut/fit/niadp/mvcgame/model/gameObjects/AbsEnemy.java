package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.prototype.Prototype;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject implements IObjectResource, Prototype {

    protected int scoreValue;
    protected int health;

    public AbsEnemy(Position position) {
        this.position = position;
    }

    public AbsEnemy(AbsEnemy enemy) {
        this(enemy.getPosition());
        this.scoreValue = enemy.getScoreValue();
        this.health = enemy.getHealth();
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

    private int getHealth() {
        return health;
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

    @Override
    public abstract AbsEnemy clone();
}
