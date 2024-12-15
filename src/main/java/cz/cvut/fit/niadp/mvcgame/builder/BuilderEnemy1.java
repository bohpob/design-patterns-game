package cz.cvut.fit.niadp.mvcgame.builder;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.Enemy1A;

public class BuilderEnemy1 implements BuilderEnemy {

    private Enemy1A enemy;

    @Override
    public void reset() {
        enemy = new Enemy1A(new Position(0, 0));
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
        int scoreValue = 50;
        enemy.setScoreValue(scoreValue);
    }

    @Override
    public AbsEnemy build() {
        return enemy;
    }
}
