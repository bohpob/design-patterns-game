package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
import cz.cvut.fit.niadp.mvcgame.config.MvcGameResources;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.model.gameObjects.*;

public class GameDrawer implements IVisitor {

    private IGameGraphics gameGraphics;

    public void setGraphicsContext(IGameGraphics gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    private void drawGameObject(GameObject gameObject, String resource) {
        gameGraphics.drawImage(resource, gameObject.getPosition());
    }

    private void drawText(String text, Position position) {
        gameGraphics.drawText(text, position);
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
        if (enemy.getType() == AbsEnemy.EnemyType.ENEMY_1) {
            drawGameObject(enemy, MvcGameResources.ENEMY_1_RESOURCE);
        } else {
            drawGameObject(enemy, MvcGameResources.ENEMY_2_RESOURCE);
        }
    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {
        drawText(gameInfo.getText(), gameInfo.getPosition());
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        if (AbsCollision.CollisionType.COLLISION_1 == collision.getType()) {
            drawGameObject(collision, MvcGameResources.COLLISION_RESOURCE);
        } else {
            drawGameObject(collision, MvcGameResources.COLLISION_WITH_BLOOD_RESOURCE);
        }
    }
}
