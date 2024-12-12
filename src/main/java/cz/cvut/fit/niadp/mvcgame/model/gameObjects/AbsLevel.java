package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.model.Position;
import cz.cvut.fit.niadp.mvcgame.visitor.IVisitor;

public abstract class AbsLevel extends GameObject {

    private int level;
    private final String text;
    private String displayText;

    public AbsLevel(String text, int level, Position position) {
        this.position = position;
        this.level = level;
        this.text = text;
        newLevel();
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
}
