package cz.cvut.fit.niadp.mvcgame.controller;

import cz.cvut.fit.niadp.mvcgame.model.GameModel;

import java.util.List;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameKeys;

public class GameController {

    private final GameModel model;

    public GameController(GameModel model) {
        this.model = model;
    }

    public void processPressedKeys(List<String> pressedKeysCodes) {
        for (String code : pressedKeysCodes) {
            switch (code) {
                case MvcGameKeys.UP_KEY:
                    model.moveCannonUp();
                    break;
                case MvcGameKeys.DOWN_KEY:
                    model.moveCannonDown();
                    break;
                case MvcGameKeys.SHOOT_KEY:
                    model.cannonShoot();
                    break;
                case MvcGameKeys.AIM_UP_KEY:
                    model.aimCannonUp();
                    break;
                case MvcGameKeys.AIM_DOWN_KEY:
                    model.aimCannonDown();
                    break;
                case MvcGameKeys.POWER_UP_KEY:
                    model.cannonPowerUp();
                    break;
                case MvcGameKeys.POWER_DOWN_KEY:
                    model.cannonPowerDown();
                    break;
                case MvcGameKeys.TOGGLE_MOVING_STRATEGY_KEY:
                    model.toggleMovingStrategy();
                    break;
                case MvcGameKeys.TOGGLE_SHOOTING_MODE_KEY:
                    model.toggleShootingMode();
                case MvcGameKeys.EXIT_KEY:
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
