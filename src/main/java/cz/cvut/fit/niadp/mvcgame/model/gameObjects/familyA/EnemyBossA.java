package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class EnemyBossA extends AbsEnemy {

    public EnemyBossA(Position position) {
        super(position);
    }

    public EnemyBossA(EnemyBossA enemy) {
        super(enemy);
    }

    @Override
    public String getDeathResource() {
        return MvcGameResources.COLLISION_ENEMY_BOSS_RESOURCE;
    }

    @Override
    public String getResource() {
        return MvcGameResources.ENEMY_BOSS_RESOURCE;
    }

    @Override
    public AbsEnemy clone() {
        return new EnemyBossA(this);
    }
}
