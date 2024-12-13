package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig;
import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.prototype.Prototype;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsLevel extends GameObject implements Prototype {

    protected int level;
    protected final String text;
    protected String displayText;

    public AbsLevel() {
        this.level = 0;
        this.text = "LEVEL";
        this.displayText = this.text;
        this.position = new Position(MvcGameConfig.LEVEL_X, MvcGameConfig.LEVEL_Y);
    }

    public AbsLevel(AbsLevel level) {
        this.level = level.getLevel();
        this.text = level.getText();
        this.displayText = level.getDisplayText();
        this.position = level.getPosition();
    }

    public void newLevel() {
        this.level++;
        displayText = text + " : " + level;
    }

    public int getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void finalBoss() {
        this.level++;
        displayText = "FINAL BOSS";
    }

    public void theEnd() {
        displayText = "THE END.";
    }

    @Override
    public void acceptVisitor(IVisitor visitor) {
        visitor.visitLevel(this);
    }

    @Override
    public abstract AbsLevel clone();
}
