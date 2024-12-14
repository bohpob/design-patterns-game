package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.iterator.shootingMode.ShootingModeCollection;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;

import java.util.ArrayList;
import java.util.List;

public class CannonA extends AbsCannon {

    private final IGameObjectsFactory gameObjectsFactory;
    private double angle;
    private int power;
    private final List<AbsMissile> shootingBatch;

    public CannonA(Position position, IGameObjectsFactory gameObjectsFactory) {
        super(new ShootingModeCollection().createIterator());
        this.position = position;
        this.gameObjectsFactory = gameObjectsFactory;
        angle = MvcGameConfig.INIT_ANGLE;
        power = MvcGameConfig.INIT_POWER;
        shootingBatch = new ArrayList<>();
    }

    @Override
    public void moveUp() {
        if (position.getY() > MvcGameConfig.CANNON_MIN_POS_Y) {
            move(new Vector(0, -1 * MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public void moveDown() {
        if (position.getY() < MvcGameConfig.CANNON_MAX_POS_Y) {
            move(new Vector(0, MvcGameConfig.MOVE_STEP));
        }
    }

    @Override
    public List<AbsMissile> shoot() {
        shootingBatch.clear();
        shootingModeIterator.getCurrent().shoot(this);
        return shootingBatch;
    }

    @Override
    public void primitiveShoot() {
        shootingBatch.add(gameObjectsFactory.createMissile(angle, power));
    }

    @Override
    public void aimUp() {
        angle -= MvcGameConfig.ANGLE_STEP;
        angle = normalizeAngle(angle);
    }

    @Override
    public void aimDown() {
        angle += MvcGameConfig.ANGLE_STEP;
        angle = normalizeAngle(angle);
    }

    private double normalizeAngle(double angle) {
        if (angle < -Math.PI) {
            angle += 2 * Math.PI;
        } else if (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
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
        shootingModeIterator.next();
    }

    @Override
    public IShootingMode getShootingMode() {
        return shootingModeIterator.getCurrent();
    }

    @Override
    public void setShootingMode(IShootingMode shootingMode) {
        shootingModeIterator.set(shootingMode);
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }

    @Override
    public String getResource() {
        return MvcGameResources.CANNON_RESOURCE;
    }
}
