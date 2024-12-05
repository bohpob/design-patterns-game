package cz.cvut.fit.niadp.mvcgame.model;

public class Vector {

    private final int dX;
    private final int dY;

    public Vector(int dX, int dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public int getDX() {
        return dX;
    }

    public int getDY() {
        return dY;
    }
}
