package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;

public class GameView implements IObserver {

    private final IGameModel model;
    private final GameController controller;
    private IGameGraphics gameGraphics;
    private final GameDrawer gameDrawer;

    public GameView(IGameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        model.registerObserver(this);
        gameDrawer = new GameDrawer();
    }

    public GameController getController() {
        return controller;
    }

    public void render() {
        if (gameGraphics != null) {
            gameGraphics.clear();
            model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(gameDrawer));
        }
    }

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
        gameDrawer.setGraphicsContext(gameGraphics);
        this.render();
    }

    @Override
    public void update() {
        render();
    }
}
