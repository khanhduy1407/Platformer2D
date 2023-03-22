package com.nkduy.platformer.entities;

import com.nkduy.platformer.util.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.nkduy.platformer.util.Constants.Directions.*;
import static com.nkduy.platformer.util.Constants.PlayerConstants.*;

public class Player extends Entity {

    BufferedImage[][] animations;
    int animTick, animIndex, animSpeed = 25;
    int playerAction = IDLE;
    boolean moving = false, attacking = false;
    boolean left, up, right, down;
    float playerSpeed = 2.0f;
    int[][] lvlData;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);

        loadAnimations();
    }

    public void update() {
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int) x, (int) y, 96, 48, null);
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {
        int startAnim = playerAction;

        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACK_1;
        }

        if (startAnim != playerAction) {
            resetAnimTick();
        }
    }

    private void resetAnimTick() {
        animTick = 0;
        animIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if (left && !right) { // A
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) { // D
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) { // W
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) { // S
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
