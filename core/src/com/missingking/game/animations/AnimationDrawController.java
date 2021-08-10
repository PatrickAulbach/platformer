package com.missingking.game.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.missingking.game.characters.Player;
import com.missingking.game.characters.PlayerAnimations;
import com.missingking.game.config.GameConfig;


public class AnimationDrawController {

    private SpriteBatch batch;

    private PlayerAnimations playerAnimations;

    public AnimationDrawController(SpriteBatch batch) {
        this.batch = batch;
        this.playerAnimations = new PlayerAnimations();
    }

    public void playerUpdate(float time) {
        switch (Player.getCurrentState()) {
            case JUMPINGRIGHT:

            case MOVINGRIGHT:
                batch.draw(playerAnimations.getRunningAnimationRight().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;

            case JUMPINGLEFT:

            case MOVINGLEFT:
                batch.draw(playerAnimations.getRunningAnimationLeft().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;

            case IDLERIGHT:
                batch.draw(playerAnimations.getIdleAnimationRight().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;

            case IDLELEFT:
                batch.draw(playerAnimations.getIdleAnimationLeft().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;

            case ATTACKINGRIGHT:
                batch.draw(playerAnimations.getAttackingAnimationRight().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;

            case ATTACKINGLEFT:
                batch.draw(playerAnimations.getAttackingAnimationLeft().getKeyFrame(time, true),
                        Player.getPlayerPosition().x - GameConfig.PLAYER.x / 2,
                        Player.getPlayerPosition().y - GameConfig.PLAYER.y / 2 + 0.05f,
                        GameConfig.PLAYER.x, GameConfig.PLAYER.y);
                break;
        }
    }
}
