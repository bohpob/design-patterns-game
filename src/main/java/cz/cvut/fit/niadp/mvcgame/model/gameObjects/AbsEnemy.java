package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {

    public enum EnemyType {
        ENEMY_1,
        ENEMY_2
    }

    protected EnemyType enemyType;
    protected int scoreValue;
    protected int health;

    public AbsEnemy(Position position, EnemyType enemyType) {
        this.position = position;
        this.enemyType = enemyType;
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

    public EnemyType getType() {
        return enemyType;
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

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
