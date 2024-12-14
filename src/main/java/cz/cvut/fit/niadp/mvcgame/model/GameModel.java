package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.command.AbstractGameCommand;
import cz.cvut.fit.niadp.mvcgame.command.UndoLastCommand;
import cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy.IMovingStrategyIterator;
import cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy.MovingStrategyCollection;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class GameModel implements IGameModel {

    private AbsLevel level;
    private AbsScore score;
    private final AbsCannon cannon;
    private List<AbsEnemy> enemies;
    private List<AbsWall> walls;
    private final AbsGameInfo gameInfo;
    private final List<AbsCollision> collisions;
    private final List<AbsMissile> missiles;
    private final Set<IObserver> observers;
    private final IGameObjectsFactory gameObjectsFactory;
    private final IMovingStrategyIterator movingStrategyIterator;

    private final Queue<AbstractGameCommand> unexecutedCommands;
    private final Stack<AbstractGameCommand> executedCommands;

    public GameModel() {
        gameObjectsFactory = new GameObjectsFactoryA(this);
        score = gameObjectsFactory.createScore();
        level = gameObjectsFactory.createLevel();
        level.newLevel();
        cannon = gameObjectsFactory.createCannon();
        enemies = gameObjectsFactory.createEnemies(level.getLevel());
        gameInfo = gameObjectsFactory.createGameInfo();
        observers = new HashSet<>();
        missiles = new ArrayList<>();
        collisions = new ArrayList<>();
        walls = new ArrayList<>();
        movingStrategyIterator = new MovingStrategyCollection().createIterator();

        unexecutedCommands = new LinkedBlockingQueue<>();
        executedCommands = new Stack<>();
    }

    @Override
    public void update() {
        moveMissiles();
        executeCommands();
        levelCheck();
    }

    private void levelCheck() {
        if (enemies.isEmpty() && collisions.isEmpty()) {
            missiles.clear();
            collisions.clear();
            if (level.getLevel() < 3) {
                level.newLevel();
                enemies = gameObjectsFactory.createEnemies(level.getLevel());
            } else if (level.getLevel() == 3) {
                level.finalBoss();
                enemies.add(gameObjectsFactory.createEnemyBoss());
            } else {
                level.theEnd();
            }
        }
    }

    private void executeCommands() {
        while (!unexecutedCommands.isEmpty()) {
            AbstractGameCommand command = unexecutedCommands.poll().doExecute();
            if (!(command instanceof UndoLastCommand)) {
                executedCommands.push(command);
            }
        }
    }

    private void moveMissiles() {
        missiles.forEach(AbsMissile::move);
        destroyMissiles();
        destroyEnemies();
        destroyWalls();
        destroyCollisions();
        notifyObservers();
    }

    private void destroyCollisions() {
        collisions.removeIf(AbsCollision::expireCollision);
    }

    private void destroyWalls() {
        List<AbsMissile> missilesToRemove = new ArrayList<>();

        for (AbsWall wall : walls) {
            for (AbsMissile missile : missiles) {
                if (missile.checkHit(wall)) {
                    missilesToRemove.add(missile);
                    score.addScore(wall.getScoreValue());
                    break;
                }
            }
        }

        missiles.removeAll(missilesToRemove);
    }

    public void toggleWalls() {
        if (walls.isEmpty()) {
            walls = gameObjectsFactory.createWalls();
        } else {
            walls.clear();
        }
    }

    private void destroyEnemies() {
        List<AbsEnemy> enemiesToRemove = new ArrayList<>();
        List<AbsMissile> missilesToRemove = new ArrayList<>();

        for (AbsEnemy enemy : enemies) {
            for (AbsMissile missile : missiles) {
                if (missile.checkHit(enemy)) {
                    processHit(enemy, missile, enemiesToRemove, missilesToRemove);
                    break;
                }
            }
        }

        enemies.removeAll(enemiesToRemove);
        missiles.removeAll(missilesToRemove);
    }

    private void processHit(AbsEnemy enemy, AbsMissile missile, List<AbsEnemy> enemiesToRemove, List<AbsMissile> missilesToRemove) {
        missilesToRemove.add(missile);
        enemy.decreaseHealth();
        if (enemy.isDead()) {
            createCollision(enemy);
            score.addScore(enemy.getScoreValue());
            enemiesToRemove.add(enemy);
        }
    }

    private void createCollision(AbsEnemy enemy) {
        collisions.add(gameObjectsFactory.createCollision(enemy.getDeathResource(), enemy.getPosition()));
    }

    private void destroyMissiles() {
        missiles.removeIf(AbsMissile::isOutOfPlayArea);
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
        gameObjects.addAll(walls);
        gameObjects.add(gameInfo);
        gameObjects.add(level);
        return gameObjects;
    }

    @Override
    public IMovingStrategy getMovingStrategy() {
        return movingStrategyIterator.getCurrent();
    }

    @Override
    public AbsCannon getCannon() {
        return cannon;
    }

    @Override
    public int getScore() {
        return score.getScore();
    }

    @Override
    public int getEnemyCount() {
        return enemies.size();
    }

    @Override
    public List<AbsMissile> getMissiles() {
        return missiles;
    }

    @Override
    public void toggleMovingStrategy() {
        movingStrategyIterator.next();
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
        private double cannonAngle;
        private int cannonPower;
        private AbsScore score;
        private AbsLevel level;
        private List<AbsEnemy> enemies;
        private List<AbsWall> walls;
        private IShootingMode shootingMode;
        private IMovingStrategy movingStrategy;
    }

    @Override
    public Object createMemento() {
        Memento gameModelSnapshot = new Memento();
        gameModelSnapshot.cannonPositionX = cannon.getPosition().getX();
        gameModelSnapshot.cannonPositionY = cannon.getPosition().getY();
        gameModelSnapshot.cannonAngle = cannon.getAngle();
        gameModelSnapshot.cannonPower = cannon.getPower();
        gameModelSnapshot.enemies = enemies.stream().map(AbsEnemy::clone).collect(Collectors.toList());
        gameModelSnapshot.shootingMode = cannon.getShootingMode();
        gameModelSnapshot.movingStrategy = movingStrategyIterator.getCurrent();
        gameModelSnapshot.score = score.clone();
        gameModelSnapshot.level = level.clone();
        gameModelSnapshot.walls = walls.stream().map(AbsWall::clone).collect(Collectors.toList());
        return gameModelSnapshot;
    }

    @Override
    public void setMemento(Object memento) {
        Memento gameModelSnapshot = (Memento) memento;
        cannon.getPosition().setX(gameModelSnapshot.cannonPositionX);
        cannon.getPosition().setY(gameModelSnapshot.cannonPositionY);
        cannon.setAngle(gameModelSnapshot.cannonAngle);
        cannon.setPower(gameModelSnapshot.cannonPower);
        cannon.setShootingMode(gameModelSnapshot.shootingMode);
        enemies = gameModelSnapshot.enemies;
        walls = gameModelSnapshot.walls;
        score = gameModelSnapshot.score;
        level = gameModelSnapshot.level;
        movingStrategyIterator.set(gameModelSnapshot.movingStrategy);
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
