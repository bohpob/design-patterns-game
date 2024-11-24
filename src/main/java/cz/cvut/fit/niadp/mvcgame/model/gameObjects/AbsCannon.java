package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsCannon extends GameObject {

    protected IGameObjectsFactory gameObjectsFactory;

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract AbsMissile shoot();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitCannon(this);
    }
}
