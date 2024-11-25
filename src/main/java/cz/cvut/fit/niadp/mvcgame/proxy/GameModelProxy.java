package cz.cvut.fit.niadp.mvcgame.proxy;

import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.List;

public class GameModelProxy implements IGameModel {

    private final GameModel subject;

    public GameModelProxy(GameModel model) {
        subject = model;
    }

    @Override
    public void registerObserver(IObserver obs) {
        subject.registerObserver(obs);
    }

    @Override
    public void unregisterObserver(IObserver obs) {
        subject.unregisterObserver(obs);
    }

    @Override
    public void notifyObservers() {
        subject.notifyObservers();
    }

    @Override
    public void update() {
        subject.update();
    }

    @Override
    public Position getCannonPosition() {
        return subject.getCannonPosition();
    }

    @Override
    public void moveCannonUp() {
        subject.moveCannonUp();
    }

    @Override
    public void moveCannonDown() {
        subject.moveCannonDown();
    }

    @Override
    public void aimCannonUp() {
        subject.aimCannonUp();
    }

    @Override
    public void aimCannonDown() {
        subject.aimCannonDown();
    }

    @Override
    public void cannonPowerUp() {
        subject.cannonPowerUp();
    }

    @Override
    public void cannonPowerDown() {
        subject.cannonPowerDown();
    }

    @Override
    public void cannonShoot() {
        subject.cannonShoot();
    }

    @Override
    public List<GameObject> getGameObjects() {
        return subject.getGameObjects();
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return subject.getMovingStrategy();
    }

    @Override
    public void toggleMovingStrategy() {
        subject.toggleMovingStrategy();
    }

    @Override
    public void toggleShootingMode() {
        subject.toggleShootingMode();
    }

    @Override
    public Object createMemento() {
        return subject.createMemento();
    }

    @Override
    public void setMemento(Object memento) {
        subject.setMemento(memento);
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        subject.registerCommand(command);
    }

    @Override
    public void undoLastCommand() {
        subject.undoLastCommand();
    }
}
