package com.platformer.game;

import com.badlogic.gdx.Game;
import com.platformer.game.level.LevelOne;

public class Platformer extends Game {

    @Override
    public void create() {
        setScreen(new LevelOne());
    }
}
