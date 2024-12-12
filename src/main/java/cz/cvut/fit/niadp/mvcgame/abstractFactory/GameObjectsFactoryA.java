package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.builder.*;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsLevel;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsScore;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private final IGameModel model;
    private final Director director;

    public GameObjectsFactoryA(IGameModel model) {
        this.model = model;
        this.director = new Director(new BuilderEnemy1());
    }

    @Override
    public CannonA createCannon() {
        return new CannonA(new Position(MvcGameConfig.CANNON_POS_X, MvcGameConfig.CANNON_POS_Y), this);
    }

    @Override
    public MissileA createMissile(double initAngle, int initVelocity) {
        return new MissileA(
                new Position(model.getCannonPosition().getX(), model.getCannonPosition().getY()),
                initAngle,
                initVelocity,
                model.getMovingStrategy()
        );
    }

    @Override
    public List<AbsEnemy> createEnemies(int level) {
        List<AbsEnemy> enemies = new ArrayList<>();
        Random random = new Random();

        int spawnZoneWidth = (MvcGameConfig.ENEMY_MAX_POS_X - MvcGameConfig.ENEMY_MIN_POS_X) / (MvcGameConfig.START_NUM_OF_ENEMIES + level);

        for (int i = 0; i < MvcGameConfig.START_NUM_OF_ENEMIES + level; i++) {
            Position position = generateRandomPosition(random, i, spawnZoneWidth);

            director.setBuilder(selectBuilder(i));
            enemies.add(director.construct(position));
        }
        return enemies;
    }

    @Override
    public AbsEnemy createEnemyBoss() {
        director.setBuilder(new BuilderEnemyBoss());
        return director.construct(new Position(MvcGameConfig.ENEMY_BOSS_X, MvcGameConfig.ENEMY_BOSS_Y));
    }

    private Position generateRandomPosition(Random random, int index, int spawnZoneWidth) {
        int x = random.nextInt(
                MvcGameConfig.ENEMY_MIN_POS_X + index * spawnZoneWidth,
                MvcGameConfig.ENEMY_MIN_POS_X + (index + 1) * spawnZoneWidth
        );
        int y = random.nextInt(MvcGameConfig.ENEMY_MIN_POS_Y, MvcGameConfig.ENEMY_MAX_POS_Y);
        return new Position(x, y);
    }

    private BuilderEnemy selectBuilder(int index) {
        return (index % 2 == 0) ? new BuilderEnemy1() : new BuilderEnemy2();
    }

    @Override
    public GameInfoA createGameInfo() {
        return new GameInfoA(new Position(MvcGameConfig.MAX_X - 250, 50), model);
    }

    @Override
    public AbsCollision createCollision(String resource, Position position) {
        return new CollisionA(position, resource);
    }

    @Override
    public AbsLevel createLevel() {
        return new LevelA("LEVEL", 0, new Position(MvcGameConfig.LEVEL_X, MvcGameConfig.LEVEL_Y));
    }

    @Override
    public AbsScore createScore() {
        return new ScoreA();
    }
}
