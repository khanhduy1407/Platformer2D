package com.nkduy.platformer.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.nkduy.platformer.util.Constants.Directions.*;
import static com.nkduy.platformer.util.Constants.PlayerConstants.*;

public class Player extends Entity {

    BufferedImage[][] animations;
    int animTick, animIndex, animSpeed = 15;
    int playerAction = IDLE;
    boolean moving = false;
    boolean left, up, right, down;
    float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);

        loadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animIndex], (int) x, (int) y, 256, 160, null);
    }

    private void updateAnimationTick() {
        animTick++;
        if (animTick >= animSpeed) {
            animTick = 0;
            animIndex++;
            if (animIndex >= GetSpriteAmount(playerAction)) {
                animIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
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
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];

            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
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