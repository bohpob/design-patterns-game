package cz.cvut.fit.niadp.mvcgame.model;

public class Position {

    private int dimX = 0;
    private int dimY = 0;

    public Position() {
    }

    public Position(int posX, int posY) {
        this.dimX = posX;
        this.dimY = posY;
    }

    public int getX() {
        return dimX;
    }

    public int getY() {
        return dimY;
    }

    public void setX(int x) {
        this.dimX = x;
    }

    public void setY(int y) {
        this.dimY = y;
    }

    public void add(Vector vector) {
        setX(getX() + vector.getDX());
        setY(getY() + vector.getDY());
    }

    public double distanceTo(Position other) {
        int deltaX = other.getX() - this.dimX;
        int deltaY = other.getY() - this.dimY;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
