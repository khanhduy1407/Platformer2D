package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    Mouse mouse;
    float xDelta = 100, yDelta = 100;
    BufferedImage img, subImg;

    public GamePanel() {
        mouse = new Mouse(this);

        importImg();

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
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

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

//        g.drawImage(img.getSubimage(0, 0, 64, 40), 0, 0, 96, 48, null);
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 96, 48, null);
    }
}
