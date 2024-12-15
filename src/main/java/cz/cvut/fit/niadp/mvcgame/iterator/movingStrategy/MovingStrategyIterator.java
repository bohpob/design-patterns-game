package cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;
import java.util.ListIterator;

public class MovingStrategyIterator implements IMovingStrategyIterator {

    private final List<IMovingStrategy> strategies;
    private ListIterator<IMovingStrategy> iterator;

    public MovingStrategyIterator(List<IMovingStrategy> strategies) {
        this.strategies = strategies;
        this.iterator = strategies.listIterator();
        iterator.next();
    }

    @Override
    public void next() {
        if (!iterator.hasNext()) {
            iterator = strategies.listIterator();
        }
        iterator.next();
    }

    @Override
    public void set(IMovingStrategy strategy) {
        for (int i = 0; i < strategies.size(); i++) {
            if (getCurrent().getName().equals(strategy.getName())) {
                return;
            } else {
                next();
            }
        }
    }

    @Override
    public IMovingStrategy getCurrent() {
        return strategies.get(iterator.previousIndex());
    }
}
