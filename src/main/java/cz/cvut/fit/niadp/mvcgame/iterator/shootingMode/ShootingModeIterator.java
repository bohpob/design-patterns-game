package cz.cvut.fit.niadp.mvcgame.iterator.shootingMode;

import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

import java.util.List;
import java.util.ListIterator;

public class ShootingModeIterator implements IShootingModeIterator {

    private final List<IShootingMode> modes;
    private ListIterator<IShootingMode> iterator;

    public ShootingModeIterator(List<IShootingMode> modes) {
        this.modes = modes;
        this.iterator = modes.listIterator();
        iterator.next();
    }

    @Override
    public void next() {
        if (!iterator.hasNext()) {
            iterator = modes.listIterator();
        }
        iterator.next();
    }

    @Override
    public void set(IShootingMode shootingMode) {
        for (int i = 0; i < modes.size(); i++) {
            if (getCurrent().getName().equals(shootingMode.getName())) {
                return;
            } else {
                next();
            }
        }
    }

    @Override
    public IShootingMode getCurrent() {
        return modes.get(iterator.previousIndex());
    }
}
