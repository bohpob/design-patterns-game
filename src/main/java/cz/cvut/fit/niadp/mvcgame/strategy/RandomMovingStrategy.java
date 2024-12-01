package cz.cvut.fit.niadp.mvcgame.strategy;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Vector;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;

import java.util.Random;

public class RandomMovingStrategy implements IMovingStrategy {

    private final Random random;

    public RandomMovingStrategy() {
        this.random = new Random();
    }

    @Override
    public void updatePosition(AbsMissile missile) {
        missile.move(
                new Vector(
                        random.nextInt(-MvcGameConfig.MOVE_STEP, 2 * MvcGameConfig.MOVE_STEP),
                        random.nextInt(-MvcGameConfig.MOVE_STEP, MvcGameConfig.MOVE_STEP)
                )
        );
    }

    @Override
    public String getName() {
        return "Random moving";
    }
}
