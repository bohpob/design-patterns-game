package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsGameInfo extends GameObject {

    public AbsGameInfo(Position position) {
        this.position = position;
    }

    public abstract String getText();

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitGameInfo(this);
    }
}
