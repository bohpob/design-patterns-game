package cz.cvut.fit.niadp.mvcgame.iterator.shootingMode;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShootingModeIteratorBasicTest {

    private IShootingModeIterator iterator;

    @Before
    public void setUp() {
        List<IShootingMode> modes = new ArrayList<>();
        modes.add(new SingleShootingMode());
        modes.add(new DoubleShootingMode());

        IShootingModeIterator collectionIterator = new ShootingModeCollection().createIterator();
        for (IShootingMode mode : modes) {
            Assert.assertTrue(collectionIterator.getCurrent().getClass().isInstance(mode));
            collectionIterator.next();
        }

        iterator = new ShootingModeIterator(modes);
    }

    @Test
    public void testNext() {
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);

        iterator.next();
        Assert.assertTrue(iterator.getCurrent() instanceof DoubleShootingMode);

        iterator.next();
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);
    }

    @Test
    public void testSet() {
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);

        iterator.set(new SingleShootingMode());
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);

        iterator.set(new DoubleShootingMode());
        Assert.assertTrue(iterator.getCurrent() instanceof DoubleShootingMode);

        iterator.set(new SingleShootingMode());
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);

        iterator.set(new SingleShootingMode());
        Assert.assertTrue(iterator.getCurrent() instanceof SingleShootingMode);
    }
}
