package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public interface IMovingStrategyIterator {

    void next();

    IMovingStrategy getCurrent();
}
