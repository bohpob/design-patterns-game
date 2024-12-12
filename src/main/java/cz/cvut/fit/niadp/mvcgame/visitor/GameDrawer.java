package cz.cvut.fit.niadp.mvcgame.visitor;

import cz.cvut.fit.niadp.mvcgame.bridge.IGameGraphics;
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
        drawGameObject(cannon, cannon.getResource());
    }

    @Override
    public void visitMissile(AbsMissile missile) {
        drawGameObject(missile, missile.getResource());
    }

    @Override
    public void visitEnemy(AbsEnemy enemy) {
        drawGameObject(enemy, enemy.getResource());
    }

    @Override
    public void visitGameInfo(AbsGameInfo gameInfo) {
        Position leftTop = new Position(gameInfo.getPosition().getX() - 25, gameInfo.getPosition().getY() - 25);
        Position rightBottom = new Position(gameInfo.getPosition().getX() + 235, gameInfo.getPosition().getY() + 135);
        gameGraphics.drawRectangle(leftTop, rightBottom);
        drawText(gameInfo.getText(), gameInfo.getPosition());
    }

    @Override
    public void visitCollision(AbsCollision collision) {
        drawGameObject(collision, collision.getResource());
    }

    @Override
    public void visitLevel(AbsLevel level) {
        drawText(level.getDisplayText(), level.getPosition());
    }
}
