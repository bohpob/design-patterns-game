package cz.cvut.fit.niadp.mvcgame.model.gameObjects;

import cz.cvut.fit.niadp.mvcgame.prototype.Prototype;

public abstract class AbsScore implements Prototype {

    protected int score;

    public AbsScore() {
        this.score = 0;
    }

    public AbsScore(AbsScore score) {
        this.score = score.score;
    }

    public abstract int getScore();

    public abstract void addScore(int score);

    @Override
    public abstract AbsScore clone();
}
