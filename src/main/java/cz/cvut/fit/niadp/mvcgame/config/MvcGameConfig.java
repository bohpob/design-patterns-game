package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final double INIT_ANGLE = 0;
    public static final int INIT_POWER = 10;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final double GRAVITY = 9.81;
    public static final int MAGIC_TIME_CONST = 100;
    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 50;
    public static final int MIN_X_ENEMY = 300;
    public static final int MIN_Y_ENEMY = 200;
    public static final int MAX_X_ENEMY = 1800;
    public static final int MAX_Y_ENEMY = 750;
    public static final int NUM_OF_ENEMIES = 10;
    public static final int COLLISION_RADIUS = 25;
    public static final int COLLISION_LIFETIME = 500;
}
