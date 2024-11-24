package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsMissile extends GameObject {

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitMissile(this);
    }
}
