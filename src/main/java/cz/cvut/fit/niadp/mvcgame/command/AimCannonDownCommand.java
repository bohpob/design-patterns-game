package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class AimCannonDownCommand extends AbstractGameCommand {

    public AimCannonDownCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.aimCannonDown();
    }
}
