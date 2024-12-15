package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyBossA;

public class BuilderEnemyBoss implements BuilderEnemy {

    private EnemyBossA enemy;

    @Override
    public void reset() {
        enemy = new EnemyBossA(new Position(0, 0));
    }

    @Override
    public void setPosition(Position position) {
        enemy.setPosition(position);
    }

    @Override
    public void setHealth() {
        int health = 15;
        enemy.setHealth(health);
    }

    @Override
    public void setScoreValue() {
        int scoreValue = 10000;
        enemy.setScoreValue(scoreValue);
    }

    @Override
    public AbsEnemy build() {
        return enemy;
    }
}
