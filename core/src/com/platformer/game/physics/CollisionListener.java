package com.platformer.game.physics;

import com.badlogic.gdx.physics.box2d.*;
import com.platformer.game.characters.Player;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture firstFixture = contact.getFixtureA();
        Fixture secondFixture = contact.getFixtureB();

        if (firstFixture.getUserData() != null && firstFixture.getUserData().equals("grounded") ||
                secondFixture.getUserData() != null && secondFixture.getUserData().equals("grounded")) {
            Player.setIsGrounded(true);
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture firstFixture = contact.getFixtureA();
        Fixture secondFixture = contact.getFixtureB();

        if (firstFixture.getUserData() != null && firstFixture.getUserData().equals("grounded") ||
                secondFixture.getUserData() != null && secondFixture.getUserData().equals("grounded")) {
            Player.setIsGrounded(false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
