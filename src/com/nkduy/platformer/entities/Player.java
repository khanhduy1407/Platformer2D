package com.nkduy.platformer.entities;

import static com.nkduy.platformer.util.Constants.PlayerConstants.*;
import static com.nkduy.platformer.util.HelpMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nkduy.platformer.main.Game;
import com.nkduy.platformer.util.LoadSave;

public class Player extends Entity {

    BufferedImage[][] animations;
    int animTick, animIndex, animSpeed = 25;
    int playerAction = IDLE;
    boolean moving = false, attacking = false;
    boolean left, up, right, down, jump;
    float playerSpeed = 1.0f * Game.SCALE;
    int[][] lvlData;
    float xDrawOffset = 21 * Game.SCALE;
    float yDrawOffset = 4 * Game.SCALE;

    // Jumping / Gravity
    float airSpeed = 0f;
    float gravity = 0.04f * Game.SCALE;
    float jumpSpeed = -2.25f * Game.SCALE;
    float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    boolean inAir = false;

    // Status bar UI
    BufferedImage statusBarImg;
    int statusBarWidth = (int) (192 * Game.SCALE);
    int statusBarHeight = (int) (58 * Game.SCALE);
    int statusBarX = (int) (10 * Game.SCALE);
    int statusBarY = (int) (10 * Game.SCALE);

    int healthBarWidth = (int) (150 * Game.SCALE);
    int healthBarHeight = (int) (4 * Game.SCALE);
    int healthBarXStart = (int) (34 * Game.SCALE);
    int healthBarYStart = (int) (14 * Game.SCALE);

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);

        loadAnimations();
        initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));
    }

    public void update() {
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[playerAction][animIndex], (int) (hitbox.x-xDrawOffset)-lvlOffset, (int) (hitbox.y-yDrawOffset), width, height, null);
//        drawHitbox(g, lvlOffset);

        drawUI(g);
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
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

        if (inAir) {
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALLING;
            }
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

        if (jump) {
            jump();
        }
        if (!inAir) {
            if ((!left && !right) || (right && left)) {
                return;
            }
        }

        float xSpeed = 0;

        if (left) { // A
            xSpeed -= playerSpeed;
        }
        if (right) { // D
            xSpeed += playerSpeed;
        }

        if (!inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);

                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }

                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }

        moving = true;
    }

    private void jump() {
        if (inAir) {
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
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

        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData)) {
            inAir = true;
        }
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

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
