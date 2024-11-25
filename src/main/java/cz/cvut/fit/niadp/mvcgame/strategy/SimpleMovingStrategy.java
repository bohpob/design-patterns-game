package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class SimpleMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        long time = missile.getAge() / MvcGameConfig.MAGIC_TIME_CONST;
        int dX = (int) (missile.getInitVelocity() * time * Math.cos(missile.getInitAngle()));
        int dY = (int) (missile.getInitVelocity() * time * Math.sin(missile.getInitAngle()));
        missile.move(new Vector(dX, dY));
    }
}
