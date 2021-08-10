package com.platformer.game.config;

import com.badlogic.gdx.math.Vector2;

public class GameConfig {

    public static final float WIDTH = 1000f; //pixels
    public static final float HEIGHT = 320f; //pixels

    public static final float HUD_WIDTH = 480f; //world units
    public static final float HUD_HEIGHT = 800f; //world units

    public static final float WORLD_WIDTH = 10f; //world units
    public static final float WORLD_HEIGHT = 3f; //world units

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f; //world units
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f; //world units

    public static final Vector2 PLAYER = new Vector2(24 * 1.5f * GameConfig.SCALING_FACTOR, 49 * 1.5f * GameConfig.SCALING_FACTOR);

    private static final float TILE_SIZE = 32f;
    public static final float SCALING_FACTOR = 1f / TILE_SIZE;

    public static final float MAX_SPEED_PLAYER = 5f;
    public static final float APPLIED_FORCE_TO_PLAYER = 12f;
    public static final float PLAYER_JUMP_FORCE = 350f;
}
