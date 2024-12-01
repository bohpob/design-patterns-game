package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy2A extends AbsEnemy {

    public Enemy2A(Position position) {
        super(position, EnemyType.ENEMY_2);
    }
}
