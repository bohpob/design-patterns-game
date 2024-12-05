package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.Enemy2A;

public class BuilderEnemy2 implements BuilderEnemy {

    private Enemy2A enemy;

    @Override
    public void reset() {
        enemy = new Enemy2A(new Position(0, 0));
    }

    @Override
    public void setHealth() {
        int health = 1;
        enemy.setHealth(health);
    }

    @Override
    public void setPosition(Position position) {
        enemy.setPosition(position);
    }

    @Override
    public void setScoreValue() {
        int scoreValue = 20;
        enemy.setScoreValue(scoreValue);
    }

    @Override
    public AbsEnemy build() {
        return enemy;
    }
}
