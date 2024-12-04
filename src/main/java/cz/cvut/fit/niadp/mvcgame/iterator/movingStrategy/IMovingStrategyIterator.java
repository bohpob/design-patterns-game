package cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public interface IMovingStrategyIterator {

    void next();

    void set(IMovingStrategy strategy);

    IMovingStrategy getCurrent();
}
