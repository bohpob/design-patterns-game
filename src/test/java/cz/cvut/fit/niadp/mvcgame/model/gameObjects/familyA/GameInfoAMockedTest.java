package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.state.IShootingMode;
import cz.cvut.fit.niadp.mvcgame.state.SingleShootingMode;
import cz.cvut.fit.niadp.mvcgame.strategy.IMovingStrategy;
import cz.cvut.fit.niadp.mvcgame.strategy.SimpleMovingStrategy;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

public class GameInfoAMockedTest {

    @Mocked
    private GameModel mockedGameModel;

    @Mocked
    private CannonA mockedCannon;

    @Mocked
    private SimpleMovingStrategy mockedMovingStrategy;

    private void generalMocks() {
        new MockUp<GameModel>() {
            @Mock
            public int getScore() {
                return 100;
            }

            @Mock
            public int getEnemyCount() {
                return 5;
            }

            @Mock
            public AbsCannon getCannon() {
                return mockedCannon;
            }

            @Mock
            public IMovingStrategy getMovingStrategy() {
                return mockedMovingStrategy;
            }
        };

        new MockUp<CannonA>() {
            @Mock
            public int getPower() {
                return 15;
            }

            @Mock
            public double getAngle() {
                return 0.349066111111111111;
            }

            @Mock
            public IShootingMode getShootingMode() {
                return new SingleShootingMode();
            }
        };

        new MockUp<IShootingMode>() {
            public String getName() {
                return "Single shooting";
            }
        };

        new MockUp<SimpleMovingStrategy>() {
            @Mock
            public String getName() {
                return "Simple moving";
            }
        };
    }

    @Test
    public void testGetText() {
        generalMocks();
        GameInfoA gameInfo = new GameInfoA(new Position(MvcGameConfig.GAME_INFO_X, MvcGameConfig.GAME_INFO_Y), mockedGameModel);
        String text = gameInfo.getText();

        String expectedText = """
                Score : 100
                Power : 15
                Enemy count : 5
                Angle : 0.349066
                Mode : Single shooting
                Strategy : Simple moving
                """;

        Assert.assertEquals(expectedText, text);
    }
}
