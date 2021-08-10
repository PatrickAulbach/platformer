package com.platformer.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimations {

    private Animation<TextureRegion> idleAnimationRight;
    private Animation<TextureRegion> idleAnimationLeft;
    private Animation<TextureRegion> runningAnimationRight;
    private Animation<TextureRegion> runningAnimationLeft;
    private Animation<TextureRegion> jumpingAnimationRight;
    private Animation<TextureRegion> jumpingAnimationLeft;
    private Animation<TextureRegion> attackingAnimationRight;
    private Animation<TextureRegion> attackingAnimationLeft;

    public PlayerAnimations() {
        createAnimations();
    }

    private void createAnimations() {
        //Idle animation
        TextureAtlas idleAtlas  = new TextureAtlas(Gdx.files.internal("Adventurer\\Idle\\AdventurerIdle.atlas"));
        TextureRegion[] idleLeftFrame = new TextureRegion[3];
        for(int i = 0 ; i < 3; i++){
            idleLeftFrame[i] = new TextureRegion(idleAtlas.findRegion("adventurer-idle-2-0" + i + "-1.3"));
            idleLeftFrame[i].flip(true, false);
        }
        this.idleAnimationLeft = new Animation<>(0.3f, idleLeftFrame);

        TextureRegion[] idleRightFrame = new TextureRegion[3];
        for(int i = 0 ; i < 3; i++){
            idleRightFrame[i] = new TextureRegion(idleAtlas.findRegion("adventurer-idle-2-0" + i + "-1.3"));
        }
        this.idleAnimationRight = new Animation<>(0.3f, idleRightFrame);

        //running animation
        TextureAtlas runningAtlas  = new TextureAtlas(Gdx.files.internal("Adventurer\\Running\\AdventurerRunning.atlas"));
        TextureRegion[] walkLeftFrame = new TextureRegion[6];
        for(int i = 0 ; i < 6; i++){
            walkLeftFrame[i] = new TextureRegion(runningAtlas.findRegion("adventurer-run-0" + i + "-1.3"));
            walkLeftFrame[i].flip(true, false);
        }
        this.runningAnimationLeft = new Animation<>(0.1f, walkLeftFrame);

        TextureRegion[] walkRightFrame = new TextureRegion[6];
        for(int i = 0 ; i < 6; i++){
            walkRightFrame[i] = new TextureRegion(runningAtlas.findRegion("adventurer-run-0" + i + "-1.3"));
        }
        this.runningAnimationRight = new Animation<>(0.1f, walkRightFrame);

        //jumping animation. Note: this will be added in a future release
        TextureAtlas jumpingAtlas  = new TextureAtlas(Gdx.files.internal("Adventurer\\Jump\\AdventurerJumping.atlas"));
        TextureRegion[] jumpLeftFrame = new TextureRegion[4];
        for(int i = 0 ; i < 4; i++){
            jumpLeftFrame[i] = new TextureRegion(jumpingAtlas.findRegion("adventurer-jump-0" + i));
            jumpLeftFrame[i].flip(true, false);
        }
        this.jumpingAnimationLeft = new Animation<>(0.2f, jumpLeftFrame);

        TextureRegion[] jumpRightFrame = new TextureRegion[4];
        for(int i = 0 ; i < 4; i++){
            jumpRightFrame[i] = new TextureRegion(jumpingAtlas.findRegion("adventurer-jump-0" + i));
        }
        this.jumpingAnimationRight = new Animation<>(0.2f, jumpRightFrame);

        //Attacking animation
        TextureAtlas attackingAtlas  = new TextureAtlas(Gdx.files.internal("Adventurer\\Attacking\\AdventurerAttacking.atlas"));
        TextureRegion[] attackingLeftFrame = new TextureRegion[5];
        for(int i = 0 ; i < 5; i++){
            attackingLeftFrame[i] = new TextureRegion(attackingAtlas.findRegion("adventurer-attack1-0" + i));
            attackingLeftFrame[i].flip(true, false);
        }
        this.attackingAnimationLeft = new Animation<>(0.1f, attackingLeftFrame);

        TextureRegion[] attackingRightFrame = new TextureRegion[5];
        for(int i = 0 ; i < 5; i++){
            attackingRightFrame[i] = new TextureRegion(attackingAtlas.findRegion("adventurer-attack1-0" + i));
        }
        this.attackingAnimationRight = new Animation<>(0.1f, attackingRightFrame);
    }

    public Animation<TextureRegion> getIdleAnimationRight() {
        return idleAnimationRight;
    }

    public Animation<TextureRegion> getIdleAnimationLeft() {
        return idleAnimationLeft;
    }

    public Animation<TextureRegion> getRunningAnimationRight() {
        return runningAnimationRight;
    }

    public Animation<TextureRegion> getRunningAnimationLeft() {
        return runningAnimationLeft;
    }

    public Animation<TextureRegion> getJumpingAnimationRight() {
        return jumpingAnimationRight;
    }

    public Animation<TextureRegion> getJumpingAnimationLeft() {
        return jumpingAnimationLeft;
    }

    public Animation<TextureRegion> getAttackingAnimationRight() {
        return attackingAnimationRight;
    }

    public Animation<TextureRegion> getAttackingAnimationLeft() {
        return attackingAnimationLeft;
    }
}
