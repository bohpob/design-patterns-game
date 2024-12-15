package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.abstractFactory.GameObjectsFactoryA;
import cz.cvut.fit.niadp.mvcgame.abstractFactory.IGameObjectsFactory;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

public class GameModelMockedTest {

    private static final double INIT_ANGLE = 0;
    private static final int INIT_VELOCITY = 0;
    private static final int CANNON_POS_X = 200;
    private static final int CANNON_POS_Y = 200;

    @Mocked
    private GameModel model;

    @Test
    public void createMissileTest() {
        generalMocks();
        IGameObjectsFactory factory = new GameObjectsFactoryA(model);
        AbsMissile missile = factory.createMissile(INIT_ANGLE, INIT_VELOCITY);
        Assert.assertEquals(CANNON_POS_X, missile.getPosition().getX());
        Assert.assertEquals(CANNON_POS_Y, missile.getPosition().getY());
    }

    private void generalMocks() {
        new MockUp<GameModel>() {
            @Mock
            public Position getCannonPosition() {
                return new Position(CANNON_POS_X, CANNON_POS_Y);
            }
        };
    }
}
