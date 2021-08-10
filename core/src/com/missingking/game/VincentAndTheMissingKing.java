package com.missingking.game;

import com.badlogic.gdx.Game;
import com.missingking.game.level.LevelOne;

public class VincentAndTheMissingKing extends Game {

    @Override
    public void create() {
        setScreen(new LevelOne());
    }
}
