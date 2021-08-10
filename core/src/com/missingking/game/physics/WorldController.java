package com.missingking.game.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class WorldController {

    private static World world;

    private WorldController() {

    }

    public static void createWorld() {
        world = new World(new Vector2(0f, -9.8f), false);
    }

    public static World getWorld() {
        return world;
    }
}
