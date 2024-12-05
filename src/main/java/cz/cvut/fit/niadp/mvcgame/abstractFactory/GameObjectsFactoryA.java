package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.builder.BuilderEnemy;
import cz.cvut.fit.niadp.mvcgame.builder.BuilderEnemy1;
import cz.cvut.fit.niadp.mvcgame.builder.BuilderEnemy2;
import cz.cvut.fit.niadp.mvcgame.builder.Director;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCollision;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CollisionA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.GameInfoA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.MissileA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private final IGameModel model;
    private Director director;

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
    public List<AbsEnemy> createEnemies() {
        List<AbsEnemy> enemies = new ArrayList<>();
        Random random = new Random();
        director = new Director(new BuilderEnemy1());

        int spawnZoneWidth = (MvcGameConfig.MAX_X_ENEMY - MvcGameConfig.MIN_X_ENEMY) / MvcGameConfig.NUM_OF_ENEMIES;

        for (int i = 0; i < MvcGameConfig.NUM_OF_ENEMIES; i++) {
            Position position = generateRandomPosition(random, i, spawnZoneWidth);

            director.setBuilder(selectBuilder(i));
            enemies.add(director.construct(position));
        }
        return enemies;
    }

    private Position generateRandomPosition(Random random, int index, int spawnZoneWidth) {
        int x = random.nextInt(
                MvcGameConfig.MIN_X_ENEMY + index * spawnZoneWidth,
                MvcGameConfig.MIN_X_ENEMY + (index + 1) * spawnZoneWidth
        );
        int y = random.nextInt(MvcGameConfig.MIN_Y_ENEMY, MvcGameConfig.MAX_Y_ENEMY);
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
    public AbsCollision createCollision(AbsCollision.CollisionType type, Position position) {
        return new CollisionA(position, type);
    }
}
