package cz.cvut.fit.niadp.mvcgame.iterator;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public class MovingStrategyIterator implements IMovingStrategyIterator {

    private final List<IMovingStrategy> strategies;
    private int index;

    public MovingStrategyIterator(List<IMovingStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public void next() {
        index++;
        index %= strategies.size();
    }

    @Override
    public IMovingStrategy getCurrent() {
        return strategies.get(index);
    }
}
