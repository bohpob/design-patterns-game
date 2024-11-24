package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import cz.cvut.fit.niadp.mvcgame.visitor.GameDrawer;
import javafx.scene.canvas.GraphicsContext;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_X;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_Y;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private GraphicsContext graphicsContext;
    private final GameDrawer gameDrawer;

    public GameView(GameModel model) {
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
            graphicsContext.clearRect(0, 0, MAX_X, MAX_Y);
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
