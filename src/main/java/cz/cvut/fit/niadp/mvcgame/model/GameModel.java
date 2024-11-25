package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObservable;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class GameModel implements IObservable {

    private final AbsCannon cannon;
    private final List<AbsMissile> missiles;
    private final Set<IObserver> observers;
    private final IGameObjectsFactory gameObjectsFactory;
    private IMovingStrategy movingStrategy;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        cannon = gameObjectsFactory.createCannon();
        observers = new HashSet<>();
        missiles = new ArrayList<>();
        movingStrategy = new SimpleMovingStrategy();
    }

    public void update() {
        moveMissiles();
    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
        destroyMissiles();
        notifyObservers();
    }

    private void destroyMissiles() {
        missiles.removeAll(
                missiles.stream().filter(missile -> missile.getPosition().getX() > MvcGameConfig.MAX_X).toList()
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
        missiles.addAll(cannon.shoot());
        notifyObservers();
    }

    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }

    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }

    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }

    public void cannonPowerDown() {
        cannon.powerDown();
        notifyObservers();
    }

    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    public List<GameObject> getGameObjects() {
        return Stream.concat(Stream.of(cannon), missiles.stream()).toList();
    }

    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    public void toggleMovingStrategy() {
        if (movingStrategy instanceof SimpleMovingStrategy) {
            movingStrategy = new RealisticMovingStrategy();
        } else if (movingStrategy instanceof RealisticMovingStrategy) {
            movingStrategy = new RandomMovingStrategy();
        } else if (movingStrategy instanceof RandomMovingStrategy) {
            movingStrategy = new SimpleMovingStrategy();
        }
    }

    public void toggleShootingMode() {
        cannon.toggleShootingMode();
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
