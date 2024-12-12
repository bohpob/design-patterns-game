package cz.cvut.fit.niadp.mvcgame.adapter;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.IObjectResource;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileResourceAdapter implements IObjectResource {

    MovingStrategyToMissileResourceService service;

    public MissileResourceAdapter(IMovingStrategy strategy) {
        service = new MovingStrategyToMissileResourceService(strategy);
    }

    @Override
    public String getResource() {
        return service.strategyToResource();
    }
}
