package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends LifetimeLimitedGameObject {

    private final double initAngle;
    private final int initVelocity;
    private final String resource;

    protected AbsMissile(Position initPosition, double initAngle, int initVelocity) {
        super(initPosition);
        this.initAngle = initAngle;
        this.initVelocity = initVelocity;
        this.resource = MvcGameResources.MISSILE_RESOURCE;
    }

    public abstract void move();

    public double getInitAngle() {
        return initAngle;
    }

    public int getInitVelocity() {
        return initVelocity;
    }

    public String getResource() {
        return resource;
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
        return x < MvcGameConfig.MIN_X || x > MvcGameConfig.MAX_X || y < MvcGameConfig.MIN_Y || y > MvcGameConfig.MAX_Y;
    }
}
