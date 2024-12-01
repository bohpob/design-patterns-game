package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.state.DoubleShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

import java.util.List;

public abstract class AbsCannon extends GameObject {

    protected IShootingMode shootingMode;
    protected static IShootingMode SINGLE_SHOOTING_MODE = new SingleShootingMode();
    protected static IShootingMode DOUBLE_SHOOTING_MODE = new DoubleShootingMode();

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

    public abstract int getPower();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
