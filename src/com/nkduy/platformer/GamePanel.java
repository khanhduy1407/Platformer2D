package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {

    Mouse mouse;
    float xDelta = 100, yDelta = 100;
    float xDir = 1f, yDir = 1f;
    Color color = new Color(150, 20, 90);
    Random random;

    // temp, just for effect
    ArrayList<MyRect> rects = new ArrayList<>();

    public GamePanel() {
        random = new Random();
        mouse = new Mouse(this);

        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
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

    public void spawnRect(int x, int y) {
        rects.add(new MyRect(x, y));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // temp rects
        for (MyRect rect: rects) {
            rect.updateRect();
            rect.draw(g);
        }

        updateRectangle();
        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);
    }

    private void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRandColor();
        }

        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRandColor();
        }
    }

    private Color getRandColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }

    public class MyRect {
        int x, y, w, h;
        int xDir = 1, yDir = 1;
        Color color;

        public MyRect(int x, int y) {
            this.x = x;
            this.y = y;
            w = random.nextInt(50);
            h = w;
            color = newColor();
        }

        public void updateRect() {
            this.x += xDir;
            this.y += yDir;

            if (xDelta > 400 || xDelta < 0) {
                xDir *= -1;
                color = newColor();
            }
            if (yDelta > 400 || yDelta < 0) {
                yDir *= -1;
                color = newColor();
            }
        }

        private Color newColor() {
            return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }

        public void draw(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, w, h);
        }
    }
}
