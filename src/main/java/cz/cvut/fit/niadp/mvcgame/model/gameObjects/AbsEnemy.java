package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {

    public enum EnemyType {
        ENEMY_1(MvcGameResources.ENEMY_1_RESOURCE),
        ENEMY_2(MvcGameResources.ENEMY_2_RESOURCE);

        private final String resource;

        EnemyType(String resource) {
            this.resource = resource;
        }

        public String getResource() {
            return resource;
        }
    }

    private final EnemyType enemyType;
    private int scoreValue;
    private int health;

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

    public String getResource() {
        return enemyType.getResource();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
