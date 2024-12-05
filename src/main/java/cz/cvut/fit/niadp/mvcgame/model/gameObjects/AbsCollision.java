package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsCollision extends LifetimeLimitedGameObject {

    public enum CollisionType {
        COLLISION_1(MvcGameResources.COLLISION_RESOURCE),
        COLLISION_2(MvcGameResources.COLLISION_WITH_BLOOD_RESOURCE);

        private final String resource;

        CollisionType(String resource) {
            this.resource = resource;
        }

        public String getResource() {
            return resource;
        }
    }

    private final CollisionType collisionType;

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

    public String getResource() {
        return collisionType.getResource();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }
}
