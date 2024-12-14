package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    // Game name
    public static final String GAME_NAME = "The NI-ADP MvcGame";

    // Play area
    public static final int MIN_X = 0;
    public static final int MIN_Y = 0;
    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;

    // Level
    public static final int LEVEL_X = (int) (MAX_X * 0.485);
    public static final int LEVEL_Y = 50;

    // Cannon and missiles
    public static final int CANNON_MIN_POS_Y = MIN_Y;
    public static final int CANNON_MAX_POS_Y = MAX_Y - 200;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final double INIT_ANGLE = 0;
    public static final int INIT_POWER = 10;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 50;

    // Enemy
    public static final int ENEMY_BOSS_X = 900;
    public static final int ENEMY_BOSS_Y = 500;
    public static final int ENEMY_MIN_POS_X = 300;
    public static final int ENEMY_MIN_POS_Y = 200;
    public static final int ENEMY_MAX_POS_X = 1800;
    public static final int ENEMY_MAX_POS_Y = 750;
    // Base number of enemies, increases by the level (level 1 -> 8 enemies, level 2 -> 9 enemies, etc.)
    public static final int START_NUM_OF_ENEMIES = 7;

    // Collision
    public static final int COLLISION_RADIUS = 25;
    public static final int COLLISION_LIFETIME = 500;

    // Moving strategy
    public static final double GRAVITY = 9.81;
    public static final int MAGIC_TIME_CONST = 200;

    // Game info
    public static final int GAME_INFO_X = MvcGameConfig.MAX_X - 320;
    public static final int GAME_INFO_Y = 50;

    // Wall
    public static final int WALL_X = MAX_X - 30;
}
