package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsCannon;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsEnemy;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsMissile;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        drawGameObject(cannon, MvcGameResources.CANNON_RESOURCE);
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        drawGameObject(missile, MvcGameResources.MISSILE_RESOURCE);
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        if (enemy.getType() == AbsEnemy.Type.ENEMY_1)
            drawGameObject(enemy, MvcGameResources.ENEMY_1_RESOURCE);
        else
            drawGameObject(enemy, MvcGameResources.ENEMY_2_RESOURCE);
    }
}
