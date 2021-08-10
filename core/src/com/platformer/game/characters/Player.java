package com.platformer.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.platformer.game.animations.AnimationDrawController;
import com.platformer.game.config.GameConfig;
import com.platformer.game.enums.State;
import com.platformer.game.physics.WorldController;

public class Player {

    private final static Vector2 PLAYER = new Vector2(24 * 1.5f * GameConfig.SCALING_FACTOR, 49 * 1.5f * GameConfig.SCALING_FACTOR);

    private static Body player;

    private static State currentState;
    private static State previousState;

    private static int health;

    private static AnimationDrawController animationDraw;

    private static boolean isGrounded;

    private Player() {

    }

    public static void initPlayer(Vector2 position, SpriteBatch batch) {
        if (player != null) {
            return;
        }

        animationDraw = new AnimationDrawController(batch);
        currentState = State.IDLERIGHT;
        previousState = State.MOVINGRIGHT;
        health = 100;
        createBody(position);
    }

    public static void updatePlayer(float time) {
        animationDraw.playerUpdate(time);
    }

    private static void createBody(Vector2 position) {

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(position.x, position.y);
        player = WorldController.getWorld().createBody(bodyDef);

        shape.setAsBox(PLAYER.x / 2f - 0.05f, PLAYER.y / 2f - 0.05f);
        fixtureDef.shape = shape;
        player.createFixture(fixtureDef);
        player.setUserData("Player");

        shape.setAsBox(PLAYER.x / 2f - 0.2f, 0.2f, new Vector2(0, -PLAYER.y / 2f), 0);
        fixtureDef.shape = shape;
        fixtureDef.isSensor=true;
        fixtureDef.friction=50;
        player.createFixture(fixtureDef).setUserData("grounded");
    }

    public static void handleInput() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && isGrounded) {
            player.applyForceToCenter(0, GameConfig.PLAYER_JUMP_FORCE, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && player.getLinearVelocity().x < GameConfig.MAX_SPEED_PLAYER) {
            previousState = currentState;
            currentState = State.MOVINGRIGHT;
            player.applyForceToCenter(GameConfig.APPLIED_FORCE_TO_PLAYER, 0, true);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.getLinearVelocity().x > -GameConfig.MAX_SPEED_PLAYER) {
            previousState = currentState;
            currentState = State.MOVINGLEFT;
            player.applyForceToCenter(-GameConfig.APPLIED_FORCE_TO_PLAYER, 0, true);
        }

        if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE) &&
                !Gdx.input.isKeyPressed(Input.Keys.D) &&
                !Gdx.input.isKeyPressed(Input.Keys.A) &&
                !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                previousState == State.MOVINGLEFT ||
                previousState == State.ATTACKINGLEFT ||
                previousState == State.JUMPINGLEFT) {
            currentState = State.IDLELEFT;
        }

        if (!Gdx.input.isKeyJustPressed(Input.Keys.SPACE) &&
                !Gdx.input.isKeyPressed(Input.Keys.D) &&
                !Gdx.input.isKeyPressed(Input.Keys.A) &&
                !Gdx.input.isKeyPressed(Input.Keys.LEFT) &&
                !Gdx.input.isKeyPressed(Input.Keys.RIGHT) &&
                previousState == State.MOVINGRIGHT ||
                previousState == State.ATTACKINGRIGHT ||
                previousState == State.JUMPINGRIGHT) {
            currentState = State.IDLERIGHT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            currentState = State.ATTACKINGLEFT;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            currentState = State.ATTACKINGRIGHT;
        }
    }

    public void computeDamage(String enemy) {
        if (enemy.toLowerCase().equals("skeleton") && health > 10) {
            health -= 10;
        } else if (enemy.toLowerCase().equals("skeleton") && health <= 10) {
            health = 0;
            currentState = State.DEAD;
        }
    }

    public static Vector2 getPlayerPosition() {
        return player.getPosition();
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static State getPreviousState() {
        return previousState;
    }

    public static Body getPlayer() {
        return player;
    }

    public static void setIsGrounded(boolean isOnGround) {
        isGrounded = isOnGround;
    }
}
