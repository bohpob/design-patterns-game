package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;

import java.util.List;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys.*;

public class GameController {

    private final GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for (String code : pressedKeysCodes) {
            switch (code) {
                case UP_KEY:
                    model.moveCannonUp();
                    break;
                case DOWN_KEY:
                    model.moveCannonDown();
                    break;
                case SHOOT_KEY:
                    model.cannonShoot();
                    break;
                case EXIT_KEY:
                    System.exit(0);
                    break;
                default:
                    //nothing
            }
        }
        model.update();
        pressedKeysCodes.clear();
    }
}
