package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsCollision extends LifetimeLimitedGameObject implements IObjectResource {

    public AbsCollision(Position initPosition) {
        super(initPosition);
    }

    public boolean expireCollision() {
        return MvcGameConfig.COLLISION_LIFETIME <= getAge();
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCollision(this);
    }
}
