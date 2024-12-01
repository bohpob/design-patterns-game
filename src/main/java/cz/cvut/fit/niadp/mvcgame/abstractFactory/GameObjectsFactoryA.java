package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.builder.BuilderEnemy1;
import cz.cvut.fit.niadp.mvcgame.builder.BuilderEnemy2;
import cz.cvut.fit.niadp.mvcgame.builder.Director;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CannonA;
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
        director = new Director(new BuilderEnemy1());

        int spawnZoneWidth = (MvcGameConfig.MAX_X_ENEMY - MvcGameConfig.MIN_X_ENEMY) / MvcGameConfig.NUM_OF_ENEMIES;

        for (int i = 0; i < MvcGameConfig.NUM_OF_ENEMIES; i++) {
            int x = new Random().nextInt(
                    MvcGameConfig.MIN_X_ENEMY + i * spawnZoneWidth,
                    MvcGameConfig.MIN_X_ENEMY + (i + 1) * spawnZoneWidth);
            int y = new Random().nextInt(MvcGameConfig.MIN_Y_ENEMY, MvcGameConfig.MAX_Y_ENEMY);

            Position position = new Position(x, y);

            int health;

            if (i % 2 == 0) {
                director.setBuilder(new BuilderEnemy1());
                health = 1;
            } else {
                director.setBuilder(new BuilderEnemy2());
                health = 2;
            }

            enemies.add(director.construct(position, health));
        }
        return enemies;
    }

    @Override
    public GameInfoA createGameInfo() {
        return new GameInfoA(new Position(MvcGameConfig.MAX_X - 250, 50), model);
    }
}
