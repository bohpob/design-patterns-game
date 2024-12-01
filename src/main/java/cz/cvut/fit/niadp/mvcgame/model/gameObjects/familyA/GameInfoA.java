package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsGameInfo;

public class GameInfoA extends AbsGameInfo {

    IGameModel model;

    public GameInfoA(Position position, IGameModel model) {
        super(position);
        this.model = model;
    }

    @Override
    public String getText() {
        return "Strategy : " + model.getMovingStrategy().getName() + "\n" +
                "Mode : " + model.getCannon().getShootingMode().getName() + "\n" +
                "Power : " + model.getCannon().getPower() + "\n";
    }
}
