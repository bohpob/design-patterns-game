package cz.cvut.fit.niadp.mvcgame.iterator.shootingMode;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShootingModeCollection implements IShootingModeCollection {

    private final List<IShootingMode> modes;

    public ShootingModeCollection() {
        this.modes = new ArrayList<>(Arrays.asList(
                new SingleShootingMode(),
                new DoubleShootingMode()
        ));
    }

    @Override
    public IShootingModeIterator createIterator() {
        return new ShootingModeIterator(modes);
    }
}
