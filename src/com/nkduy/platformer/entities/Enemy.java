package com.nkduy.platformer.entities;

import com.nkduy.platformer.main.Game;

import static com.nkduy.platformer.util.Constants.EnemyConstants.*;
import static com.nkduy.platformer.util.HelpMethods.*;

public abstract class Enemy extends Entity {

    int animIndex, enemyState, enemyType;
    int animTick, animSpeed = 25;
    boolean firstUpdate = true;
    boolean inAir;
    float fallSpeed;
    float gravity = 0.04f * Game.SCALE;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;

        initHitbox(x, y, width, height);
    }

    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(enemyType, enemyState)) {
                animIndex = 0;
            }
        }
    }

    public void update(int[][] lvlData) {
        updateMove(lvlData);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData) {
        if (firstUpdate) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }
        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, fallSpeed);
            }
        } else {
            //
        }
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
