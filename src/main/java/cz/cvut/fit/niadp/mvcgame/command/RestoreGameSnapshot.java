package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class RestoreGameSnapshot extends AbstractGameCommand {

    public RestoreGameSnapshot(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        CareTaker.getInstance().restoreMemento();
    }
}
