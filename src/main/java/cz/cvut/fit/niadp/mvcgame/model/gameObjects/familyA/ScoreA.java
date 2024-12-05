package cz.cvut.fit.niadp.mvcgame.model.gameObjects.familyA;

import cz.cvut.fit.niadp.mvcgame.model.gameObjects.AbsScore;

public class ScoreA extends AbsScore {

    public ScoreA() {
        this.score = 0;
    }

    public ScoreA(int score) {
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }
}
