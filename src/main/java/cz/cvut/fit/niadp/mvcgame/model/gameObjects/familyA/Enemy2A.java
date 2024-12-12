package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy2A extends AbsEnemy {

    public Enemy2A(Position position) {
        super(position);
    }

    @Override
    public String getResource() {
        return MvcGameResources.ENEMY_2_RESOURCE;
    }

    @Override
    public String getDeathResource() {
        return MvcGameResources.COLLISION_WITH_BLOOD_RESOURCE;
    }
}
