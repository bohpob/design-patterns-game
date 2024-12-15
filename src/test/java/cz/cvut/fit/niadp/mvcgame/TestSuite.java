package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.iterator.movingStrategy.MovingStrategyIteratorBasicTest;
import cz.cvut.fit.niadp.mvcgame.iterator.shootingMode.ShootingModeIteratorBasicTest;
import cz.cvut.fit.niadp.mvcgame.model.GameModelBasicTest;
import cz.cvut.fit.niadp.mvcgame.model.GameModelMockedTest;
import cz.cvut.fit.niadp.mvcgame.model.GameModelReflectionTest;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA.GameInfoAMockedTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MovingStrategyIteratorBasicTest.class, ShootingModeIteratorBasicTest.class, GameModelBasicTest.class,
        GameModelReflectionTest.class, GameModelMockedTest.class, GameInfoAMockedTest.class})
public class TestSuite {
}
