package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RandomMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.RealisticMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class GameModel implements IGameModel {

    private final AbsCannon cannon;
    private List<AbsEnemy> enemies;
    private AbsGameInfo gameInfo;
    private List<AbsCollision> collisions;
    private final List<AbsMissile> missiles;
    private final Set<IObserver> observers;
    private final IGameObjectsFactory gameObjectsFactory;
    private IMovingStrategy movingStrategy;

    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        cannon = gameObjectsFactory.createCannon();
        enemies = gameObjectsFactory.createEnemies();
        gameInfo = gameObjectsFactory.createGameInfo();
        observers = new HashSet<>();
        missiles = new ArrayList<>();
        collisions = new ArrayList<>();
        movingStrategy = new SimpleMovingStrategy();

        unexecutedCommands = new LinkedBlockingQueue<>();
        executedCommands = new Stack<>();
    }

    @Override
    public void update() {
        moveMissiles();
        executeCommands();
    }

    private void executeCommands() {
        while (!unexecutedCommands.isEmpty()) {
            executedCommands.push(unexecutedCommands.poll().doExecute());
        }
    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
        destroyMissiles();
        destroyEnemies();
        destroyCollisions();
        notifyObservers();
    }

    private void destroyCollisions() {
        collisions.removeIf(AbsCollision::expireCollision);
    }

    private void destroyEnemies() {
        List<AbsMissile> missilesToRemove = new ArrayList<>();
        List<AbsEnemy> enemiesToRemove = enemies.stream()
                .filter(enemy -> missiles.stream().anyMatch(missile -> {
                    if (missile.checkHit(enemy)) {
                        missilesToRemove.add(missile);
                        enemy.decreaseHealth();
                        createCollision(enemy);
                        return enemy.isDead();
                    }
                    return false;
                })).toList();

        enemies.removeAll(enemiesToRemove);
        missiles.removeAll(missilesToRemove);
    }

    private void createCollision(AbsEnemy enemy) {
        if (enemy.isDead()) {
            if (enemy.getType() == AbsEnemy.EnemyType.ENEMY_1) {
                collisions.add(gameObjectsFactory.createCollision(AbsCollision.CollisionType.COLLISION_1, enemy.getPosition()));
            } else {
                collisions.add(gameObjectsFactory.createCollision(AbsCollision.CollisionType.COLLISION_2, enemy.getPosition()));
            }
        }
    }

    private void destroyMissiles() {
        missiles.removeAll(
                missiles.stream().filter(missile -> missile.getPosition().getX() > MvcGameConfig.MAX_X).toList()
        );
    }

    @Override
    public Position getCannonPosition() {
        return cannon.getPosition();
    }

    @Override
    public void moveCannonUp() {
        cannon.moveUp();
        notifyObservers();
    }

    @Override
    public void moveCannonDown() {
        cannon.moveDown();
        notifyObservers();
    }

    @Override
    public void cannonShoot() {
        missiles.addAll(cannon.shoot());
        notifyObservers();
    }

    @Override
    public void aimCannonUp() {
        cannon.aimUp();
        notifyObservers();
    }

    @Override
    public void aimCannonDown() {
        cannon.aimDown();
        notifyObservers();
    }

    @Override
    public void cannonPowerUp() {
        cannon.powerUp();
        notifyObservers();
    }

    @Override
    public void cannonPowerDown() {
        cannon.powerDown();
        notifyObservers();
    }

    @Override
    public List<GameObject> getGameObjects() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(cannon);
        gameObjects.addAll(enemies);
        gameObjects.addAll(missiles);
        gameObjects.addAll(collisions);
        gameObjects.add(gameInfo);
        return gameObjects;
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return movingStrategy;
    }

    @Override
    public AbsCannon getCannon() {
        return cannon;
    }

    @Override
    public void toggleMovingStrategy() {
        if (movingStrategy instanceof SimpleMovingStrategy) {
            movingStrategy = new RealisticMovingStrategy();
        } else if (movingStrategy instanceof RealisticMovingStrategy) {
            movingStrategy = new RandomMovingStrategy();
        } else if (movingStrategy instanceof RandomMovingStrategy) {
            movingStrategy = new SimpleMovingStrategy();
        } else {

        }
    }

    @Override
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

    private static class Memento {

        private int cannonPositionX;
        private int cannonPositionY;
        private List<AbsEnemy> enemies;
        private AbsGameInfo gameInfo;
        private List<AbsCollision> collisions;
        // game snapshot (gameobjects, score, strategy, cannon state)
    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = cannon.getPosition().getX();
        gameModelSnapshot.cannonPositionY = cannon.getPosition().getY();
        gameModelSnapshot.enemies = new ArrayList<>(enemies);
        gameModelSnapshot.collisions = new ArrayList<>(collisions);
        gameModelSnapshot.gameInfo = gameInfo;
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        enemies = gameModelSnapshot.enemies;
        gameInfo = gameModelSnapshot.gameInfo;
        collisions = gameModelSnapshot.collisions;
    }

    @Override
    public void registerCommand(AbstractGameCommand command) {
        unexecutedCommands.add(command);
    }

    @Override
    public void undoLastCommand() {
        if (!executedCommands.isEmpty()) {
            executedCommands.pop().unExecute();
            notifyObservers();
        }
    }
}
