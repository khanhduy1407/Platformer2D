package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    Mouse mouse;
    int xDelta = 100, yDelta = 100;

    public GamePanel() {
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

    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.fillRect(xDelta, yDelta, 200, 50);

        repaint();
    }
}
