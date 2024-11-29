package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class ToggleShootingModeCommand extends AbstractGameCommand {

    public ToggleShootingModeCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.toggleShootingMode();
    }
}
