package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class UndoLastCommand extends AbstractGameCommand {

    public UndoLastCommand(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        model.undoLastCommand();
    }
}
