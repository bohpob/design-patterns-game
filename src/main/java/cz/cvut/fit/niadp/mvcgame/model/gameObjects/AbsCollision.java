package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsCollision extends LifetimeLimitedGameObject {

    public enum CollisionType {
        COLLISION_1,
        COLLISION_2
    }

    protected CollisionType collisionType;

    public AbsCollision(Position initPosition, CollisionType collisionType) {
        super(initPosition);
        this.collisionType = collisionType;
    }

    public CollisionType getType() {
        return collisionType;
    }

    public boolean expireCollision() {
        return MvcGameConfig.COLLISION_LIFETIME <= getAge();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }
}
