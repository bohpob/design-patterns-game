package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsLevel;

public class LevelA extends AbsLevel {

    public LevelA() {
        super();
    }

    public LevelA(LevelA level) {
        super(level);
    }

    @Override
    public AbsLevel clone() {
        return new LevelA(this);
    }
}
