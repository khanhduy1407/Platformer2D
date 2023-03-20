package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static com.nkduy.platformer.util.Constants.PlayerConstants.*;
import static com.nkduy.platformer.util.Constants.Directions.*;

public class GamePanel extends JPanel {

    Mouse mouse;
    float xDelta = 100, yDelta = 100;
    BufferedImage img;
    BufferedImage[][] animations;
    int animTick, animIndex, animSpeed = 15;
    int playerAction = IDLE;
    int playerDir = -1;
    boolean moving = false;

    public GamePanel() {
        mouse = new Mouse(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(960, 480); // w: 960/32 = 30 images; h: 480/32 = 15 images
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
        if (moving) {
            switch (playerDir) {
                case LEFT: xDelta -= 5; break;
                case UP: yDelta -= 5; break;
                case RIGHT: xDelta += 5; break;
                case DOWN: yDelta += 5; break;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();
        setAnimation();
        updatePos();

        g.drawImage(animations[playerAction][animIndex], (int) xDelta, (int) yDelta, 256, 160, null);
    }
}
