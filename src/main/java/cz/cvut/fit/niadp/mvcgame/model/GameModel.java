package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_X;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MOVE_STEP;

public class GameModel implements IObservable {

    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    private final Set<IObserver> observers;
    private final IGameObjectsFactory gameObjectsFactory;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        cannon = gameObjectsFactory.createCannon();
        observers = new HashSet<>();
        missiles = new ArrayList<>();
    }

    public void update() {
        moveMissiles();
    }

    private void moveMissiles() {
        missiles.forEach(missile -> missile.move(new Vector(MOVE_STEP, 0)));
        destroyMissiles();
        notifyObservers();
    }

    private void destroyMissiles() {
        missiles.removeAll(
                missiles.stream().filter(missile -> missile.getPosition().getX() > MAX_X).toList()
        );
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

    public void cannonShoot() {
        missiles.add(cannon.shoot());
        notifyObservers();
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
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
