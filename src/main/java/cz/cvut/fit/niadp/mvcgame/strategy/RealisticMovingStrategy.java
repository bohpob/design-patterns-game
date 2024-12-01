package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

public class RealisticMovingStrategy implements IMovingStrategy {

    @Override
    public void updatePosition(AbsMissile missile) {
        long time = missile.getAge() / MvcGameConfig.MAGIC_TIME_CONST;
        int dX = (int) (missile.getInitVelocity() * time * Math.cos(missile.getInitAngle()));
        int dY = (int) (missile.getInitVelocity() * time * Math.sin(missile.getInitAngle()) + 0.5 * MvcGameConfig.GRAVITY * Math.pow(time, 2));
        missile.move(new Vector(dX, dY));
    }

    @Override
    public String getName() {
        return "Realistic moving";
    }
}
