package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;

public class Enemy1A extends AbsEnemy {

    public Enemy1A(Position position) {
        super(position);
    }

    @Override
    public String getResource() {
        return MvcGameResources.ENEMY_1_RESOURCE;
    }

    @Override
    public String getDeathResource() {
        return MvcGameResources.COLLISION_RESOURCE;
    }
}
