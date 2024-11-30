package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class EnemyA extends AbsEnemy {

    private final int health;

    public EnemyA(Position position, Type type) {
        super(position, type);
        health = (type == Type.ENEMY_1) ? 1 : 2;
    }

    @Override
    public int getHealth() {
        return health;
    }
}
