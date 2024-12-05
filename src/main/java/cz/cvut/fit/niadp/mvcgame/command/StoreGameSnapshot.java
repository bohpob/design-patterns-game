package cz.cvut.fit.niadp.mvcgame.command;

import cz.cvut.fit.niadp.mvcgame.memento.CareTaker;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;

public class StoreGameSnapshot extends AbstractGameCommand {

    public StoreGameSnapshot(IGameModel model) {
        this.model = model;
    }

    @Override
    protected void execute() {
        CareTaker.getInstance().createMemento();
    }
}
