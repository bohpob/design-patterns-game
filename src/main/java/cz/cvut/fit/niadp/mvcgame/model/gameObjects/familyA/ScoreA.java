package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsScore;

public class ScoreA extends AbsScore {

    public ScoreA() {
        super();
    }

    public ScoreA(ScoreA score) {
        super(score);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public AbsScore clone() {
        return new ScoreA(this);
    }
}
