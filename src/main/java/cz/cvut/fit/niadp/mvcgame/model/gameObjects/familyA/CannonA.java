package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;

import java.util.ArrayList;
import java.util.List;

public class CannonA extends AbsCannon {

    private final IGameObjectsFactory gameObjectsFactory;
    private double angle;
    private int power;
    private final List<AbsMissile> shootingBatch;

    public CannonA(Position position, IGameObjectsFactory gameObjectsFactory) {
        this.position = position;
        this.gameObjectsFactory = gameObjectsFactory;
        angle = MvcGameConfig.INIT_ANGLE;
        power = MvcGameConfig.INIT_POWER;
        shootingMode = SINGLE_SHOOTING_MODE;
        shootingBatch = new ArrayList<>();
    }

    @Override
    public void moveUp() {
        move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
    }

    @Override
    public void moveDown() {
        move(new Vector(0, MvcGameConfig.MOVE_STEP));
    }

    @Override
    public List<AbsMissile> shoot() {
        shootingBatch.clear();
        shootingMode.shoot(this);
        return shootingBatch;
    }

    @Override
    public void primitiveShoot() {
        shootingBatch.add(gameObjectsFactory.createMissile(angle, power));
    }

    @Override
    public void aimUp() {
        angle -= MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void aimDown() {
        angle += MvcGameConfig.ANGLE_STEP;
    }

    @Override
    public void powerUp() {
        power = Math.min(MvcGameConfig.MAX_POWER, power + MvcGameConfig.POWER_STEP);
    }

    @Override
    public void powerDown() {
        power = Math.max(MvcGameConfig.MIN_POWER, power - MvcGameConfig.POWER_STEP);
    }

    @Override
    public void toggleShootingMode() {
        if (shootingMode instanceof SingleShootingMode) {
            shootingMode = DOUBLE_SHOOTING_MODE;
        } else if (shootingMode instanceof DoubleShootingMode) {
            shootingMode = SINGLE_SHOOTING_MODE;
        } else {

        }
    }
}
