package cz.cvut.fit.niadp.mvcgame.model;

import static cz.cvut.fit.niadp.mvcgame.config.MvcGameConfig.MOVE_STEP;

public class Cannon extends GameObject {
    // int power
    // float angle

    public Cannon(Position position) {
        this.position = position;
    }

    public void moveUp() {
        move(new Vector(0, -1 * MOVE_STEP));
    }

    public void moveDown() {
        move(new Vector(0, MOVE_STEP));
    }
}
