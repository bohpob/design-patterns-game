package cz.cvut.fit.niadp.mvcgame.model;

import cz.cvut.fit.niadp.mvcgame.command.MoveCannonDownCommand;
import cz.cvut.fit.niadp.mvcgame.command.MoveCannonUpCommand;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import org.junit.Assert;
import org.junit.Test;

public class GameModelBasicTest {

    @Test
    public void undoLastCommandTest() {
        IGameModel gameModel = new GameModel();

        // MoveCannonUpCommand
        int beginCannonPosition = gameModel.getCannonPosition().getY();
        gameModel.registerCommand(new MoveCannonUpCommand(gameModel));
        gameModel.update();
        Assert.assertEquals(beginCannonPosition - MvcGameConfig.MOVE_STEP, gameModel.getCannonPosition().getY());
        gameModel.undoLastCommand();
        Assert.assertEquals(beginCannonPosition, gameModel.getCannonPosition().getY());

        // MoveCannonDownCommand
        beginCannonPosition = gameModel.getCannonPosition().getY();
        gameModel.registerCommand(new MoveCannonDownCommand(gameModel));
        gameModel.update();
        Assert.assertEquals(beginCannonPosition + MvcGameConfig.MOVE_STEP, gameModel.getCannonPosition().getY());
        gameModel.undoLastCommand();
        Assert.assertEquals(beginCannonPosition, gameModel.getCannonPosition().getY());
    }
}
