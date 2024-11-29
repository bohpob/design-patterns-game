package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimCannonUpCommand extends AbstractGameCommand {

    public AimCannonUpCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.aimCannonUp();
    }
}
