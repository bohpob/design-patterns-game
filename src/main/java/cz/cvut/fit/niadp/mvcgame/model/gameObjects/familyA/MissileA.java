package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {

    private IMovingStrategy movingStrategy;

    public MissileA(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initVelocity);
        this.position = initPosition;
        this.movingStrategy = movingStrategy;
    }

    @Override
    public void move() {
        movingStrategy.updatePosition(this);
    }
}
