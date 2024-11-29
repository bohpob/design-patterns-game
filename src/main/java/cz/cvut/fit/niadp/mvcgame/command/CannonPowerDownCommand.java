package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class CannonPowerDownCommand extends AbstractGameCommand {

    public CannonPowerDownCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonPowerDown();
    }
}
