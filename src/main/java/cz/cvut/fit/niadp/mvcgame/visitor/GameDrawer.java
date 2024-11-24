package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.CANNON_RESOURCE;
import static cz.cvut.fit.niadp.mvcgame.config.MvcGameResources.MISSILE_RESOURCE;

public class GameDrawer implements IVisitor {

    private GraphicsContext graphicsContext;

    public void setGraphicsContext(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    private void drawGameObject(GameObject gameObject, String resource) {
        graphicsContext.drawImage(new Image(resource), gameObject.getPosition().getX(), gameObject.getPosition().getY());
    }

    @Override
    public void visitCannon(AbsCannon cannon) {
        drawGameObject(cannon, CANNON_RESOURCE);
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        drawGameObject(missile, MISSILE_RESOURCE);
    }
}
