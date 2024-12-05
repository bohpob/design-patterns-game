package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.iterator.shootingMode.IShootingModeIterator;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {

    protected final IShootingModeIterator shootingModeIterator;
    private final String resource;

    public AbsCannon(IShootingModeIterator shootingModeIterator) {
        this.shootingModeIterator = shootingModeIterator;
        this.resource = MvcGameResources.CANNON_RESOURCE;
    }

    public String getResource() {
        return resource;
    }

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract List<AbsMissile> shoot();

    public abstract void primitiveShoot();

    public abstract void aimUp();

    public abstract void aimDown();

    public abstract void powerUp();

    public abstract void powerDown();

    public abstract void toggleShootingMode();

    public abstract IShootingMode getShootingMode();

    public abstract void setShootingMode(IShootingMode shootingMode);

    public abstract int getPower();

    public abstract void setPower(int power);

    public abstract double getAngle();

    public abstract void setAngle(double angle);

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
