package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;

    protected AbsMissile(Position initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
    }

    public abstract void move();

    public double getInitAngle() {
        return initAngle;
    }

    public int getInitVelocity() {
        return initVelocity;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }

    public boolean checkHit(AbsEnemy enemy) {
        double distance = position.distanceTo(enemy.position);
        return distance <= MvcGameConfig.COLLISION_RADIUS;
    }

    public boolean isOutOfPlayArea() {
        int x = position.getX();
        int y = position.getY();
        return x < 0 || x > MvcGameConfig.MAX_X || y < 0 || y > MvcGameConfig.MAX_Y;
    }
}
