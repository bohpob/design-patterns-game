package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class CannonShootCommand extends AbstractGameCommand {

    public CannonShootCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.cannonShoot();
    }
}
