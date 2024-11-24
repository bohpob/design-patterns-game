package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;

import java.util.HashSet;
import java.util.Set;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.*;

public class GameModel implements IObservable {

    private final Cannon cannon;
    private final Set<IObserver> observers;

    public GameModel() {
        cannon = new Cannon(new Position(CANNON_POS_X, CANNON_POS_Y));
        observers = new HashSet<>();
    }

    public void update() {
        // remove killed enemies
        // move missiles
    }

    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(IObserver::update);
    }
}
