package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.IGameModel;
import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;
import javafx.scene.canvas.GraphicsContext;

public class GameView implements IObserver {

    private final IGameModel model;
    private final GameController controller;
    private GraphicsContext graphicsContext;
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
        if (graphicsContext != null) {
            graphicsContext.clearRect(0, 0, MvcGameConfig.MAX_X, MvcGameConfig.MAX_Y);
            model.getGameObjects().forEach(gameObject -> gameObject.acceptVisitor(gameDrawer));
        }
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        gameDrawer.setGraphicsContext(graphicsContext);
        this.render();
    }

    @Override
    public void update() {
        render();
    }
}
