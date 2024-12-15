package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsWall;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public class WallA extends AbsWall {

    public WallA(Position position, int scoreValue) {
        super(position, scoreValue);
    }

    public WallA(WallA wall) {
        super(wall);
    }

    @Override
    public String getResource() {
        return MvcGameResources.BOUND_RESOURCE;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitWall(this);
    }

    @Override
    public AbsWall clone() {
        return new WallA(this);
    }
}
