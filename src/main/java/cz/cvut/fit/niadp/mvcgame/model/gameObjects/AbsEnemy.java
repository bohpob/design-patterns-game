package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsEnemy extends GameObject {

    public enum Type {
        ENEMY_1,
        ENEMY_2
    }

    protected Type type;

    public AbsEnemy(Position position, Type type) {
        this.position = position;
        this.type = type;
    }

    public abstract int getHealth();

    public Type getType() {
        return type;
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitEnemy(this);
    }
}
