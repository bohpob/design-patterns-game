package cz.cvut.fit.niadp.mvcgame.adapter;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

public class MovingStrategyToMissileResourceService {

    private final String resource;

    public MovingStrategyToMissileResourceService(IMovingStrategy strategy) {
        if (strategy instanceof SimpleMovingStrategy) {
            resource = MvcGameResources.MISSILE_RESOURCE_1;
        } else if (strategy instanceof RealisticMovingStrategy) {
            resource = MvcGameResources.MISSILE_RESOURCE_2;
        } else {
            resource = MvcGameResources.MISSILE_RESOURCE_3;
        }
    }

    public String strategyToResource() {
        return resource;
    }
}
