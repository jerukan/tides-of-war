package io.github.jerukan.util;

/** The number Mason, what do they mean? */

public class Constants {

    public static final String GAME_NAME = "Tides of War";

    public static final int BOARD_WIDTH = 11;
    public static final int BOARD_HEIGHT = 11;

    public static final int TILE_SIZE = 60;

    public static final int PERLIN_SAMPLES = 10; //SHOULDN'T EXIST IF PERLIN NOISE WAS ACTUALLY DONE RIGHT

    public static final float TILE_MENU_OFFSET = TILE_SIZE * 0.2f;

    public static final float UNIT_SIZE_RATIO = 0.7f;   // multiplied by tile size for sprite size

    public static final float UNIT_SIZE = TILE_SIZE * UNIT_SIZE_RATIO;

    public static final float CAMERA_SPEED_MAX = 5; // in pixels for now
    public static final float CAMERA_SPEED_ACCEL = 0.25f;
    public static final float CAMERA_ZERO_THRESHOLD = 0.3f;

    public static final float CAMERA_ZOOM_MAX = 0.3f;
    public static final float CAMERA_ZOOM_MIN = 2f;
    public static final float CAMERA_ZOOM_SPEED_MAX = 0.05f;

    public static final double CHRIS_CONSTANT = 4.2 * Math.pow(10, 2);  // a mysterious number, what could it mean?

    // GAMEPLAY CONSTANTS
    public static final int DEFAULT_START_MONEY = 200;
    public static final int DEFAULT_MONEY_PRODUCTION = 150;

    public static final int DEFAULT_UNIT_CAP = 3;
}