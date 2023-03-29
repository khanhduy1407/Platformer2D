package com.nkduy.platformer.entities;

import static com.nkduy.platformer.util.Constants.EnemyConstants.*;

public abstract class Enemy extends Entity {

    int animIndex, enemyState, enemyType;
    int animTick, animSpeed = 25;

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

    public void update() {
        updateAnimationTick();
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }
}
