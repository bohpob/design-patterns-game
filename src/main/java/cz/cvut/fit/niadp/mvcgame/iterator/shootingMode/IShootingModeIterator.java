package cz.cvut.fit.niadp.mvcgame.iterator.shootingMode;

import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

public interface IShootingModeIterator {

    void next();

    void set(IShootingMode shootingMode);

    IShootingMode getCurrent();
}
