package cz.cvut.fit.niadp.mvcgame.view;

import cz.cvut.fit.niadp.mvcgame.controller.GameController;
import cz.cvut.fit.niadp.mvcgame.model.GameModel;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.observer.IObserver;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_X;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MAX_Y;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.CANNON_RESOURCE;

public class GameView implements IObserver {

    private final GameModel model;
    private final GameController controller;
    private GraphicsContext graphicsContext;

    public GameView(GameModel model) {
        this.model = model;
        this.controller = new GameController(model);
        model.registerObserver(this);
    }

    public GameController getController() {
        return controller;
    }

    public void render() {
        if (graphicsContext != null) {
            graphicsContext.clearRect(0, 0, MAX_X, MAX_Y);
            drawCannon();
        }
    }

    private void drawCannon() {
        Position cannonPosition = model.getCannonPosition();
        graphicsContext.drawImage(new Image(CANNON_RESOURCE), cannonPosition.getX(), cannonPosition.getY());
    }

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        this.render();
    }

    @Override
    public void update() {
        render();
    }
}
