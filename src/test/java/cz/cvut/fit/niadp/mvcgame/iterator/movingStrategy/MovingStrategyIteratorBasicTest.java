package cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy;

import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MovingStrategyIteratorBasicTest {

    private IMovingStrategyIterator iterator;

    @Before
    public void setUp() {
        List<IMovingStrategy> strategies = new ArrayList<>();
        strategies.add(new SimpleMovingStrategy());
        strategies.add(new RealisticMovingStrategy());
        strategies.add(new RandomMovingStrategy());

        IMovingStrategyIterator collectionIterator = new MovingStrategyCollection().createIterator();
        for (IMovingStrategy strategy : strategies) {
            Assert.assertTrue(collectionIterator.getCurrent().getClass().isInstance(strategy));
            collectionIterator.next();
        }

        iterator = new MovingStrategyIterator(strategies);
    }

    @Test
    public void testNext() {
        Assert.assertTrue(iterator.getCurrent() instanceof SimpleMovingStrategy);

        iterator.next();
        Assert.assertTrue(iterator.getCurrent() instanceof RealisticMovingStrategy);

        iterator.next();
        Assert.assertTrue(iterator.getCurrent() instanceof RandomMovingStrategy);

        iterator.next();
        Assert.assertTrue(iterator.getCurrent() instanceof SimpleMovingStrategy);
    }

    @Test
    public void testSet() {
        Assert.assertTrue(iterator.getCurrent() instanceof SimpleMovingStrategy);

        iterator.set(new SimpleMovingStrategy());
        Assert.assertTrue(iterator.getCurrent() instanceof SimpleMovingStrategy);

        iterator.set(new RandomMovingStrategy());
        Assert.assertTrue(iterator.getCurrent() instanceof RandomMovingStrategy);

        iterator.set(new RealisticMovingStrategy());
        Assert.assertTrue(iterator.getCurrent() instanceof RealisticMovingStrategy);

        iterator.set(new RealisticMovingStrategy());
        Assert.assertTrue(iterator.getCurrent() instanceof RealisticMovingStrategy);

    }
}
