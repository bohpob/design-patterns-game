package cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovingStrategyCollection implements IMovingStrategyCollection {

    private final List<IMovingStrategy> strategies;

    public MovingStrategyCollection() {
        this.strategies = new ArrayList<>(Arrays.asList(
                new SimpleMovingStrategy(),
                new RealisticMovingStrategy(),
                new RandomMovingStrategy()
        ));
    }

    @Override
    public IMovingStrategyIterator createIterator() {
        return new MovingStrategyIterator(strategies);
    }
}
