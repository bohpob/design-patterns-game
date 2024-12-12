package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.adapter.MissileResourceAdapter;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;

public class MissileA extends AbsMissile {

    private final IMovingStrategy movingStrategy;
    private final MissileResourceAdapter adapter;

    public MissileA(Position initPosition, double initAngle, int initVelocity, IMovingStrategy movingStrategy) {
        super(initPosition, initAngle, initVelocity);
        this.position = initPosition;
        this.movingStrategy = movingStrategy;
        adapter = new MissileResourceAdapter(movingStrategy);
    }

    @Override
    public void move() {
        movingStrategy.updatePosition(this);
    }

    @Override
    public String getResource() {
        return adapter.getResource();
    }
}
