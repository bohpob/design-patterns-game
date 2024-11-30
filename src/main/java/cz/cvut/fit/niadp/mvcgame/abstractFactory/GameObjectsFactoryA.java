package cz.cvut.fit.niadp.mvcgame.abstractFactory;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.CannonA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.EnemyA;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.MissileA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameObjectsFactoryA implements IGameObjectsFactory {

    private final IGameModel model;

    public GameObjectsFactoryA(IGameModel model) {
        this.model = model;
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

        int spawnZoneWidth = (MvcGameConfig.MAX_X_ENEMY - MvcGameConfig.MIN_X_ENEMY) / MvcGameConfig.NUM_OF_ENEMIES;

        for (int i = 0; i < MvcGameConfig.NUM_OF_ENEMIES; i++) {
            AbsEnemy.Type enemyType = (i % 2 == 0) ? AbsEnemy.Type.ENEMY_1 : AbsEnemy.Type.ENEMY_2;

            enemies.add(new EnemyA(new Position(
                    new Random().nextInt(
                            MvcGameConfig.MIN_X_ENEMY + i * spawnZoneWidth,
                            MvcGameConfig.MIN_X_ENEMY + (i + 1) * spawnZoneWidth),
                    new Random().nextInt(MvcGameConfig.MIN_Y_ENEMY, MvcGameConfig.MAX_Y_ENEMY)),
                    enemyType));
        }
        return enemies;
    }
}
