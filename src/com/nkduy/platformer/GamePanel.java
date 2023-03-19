package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    Mouse mouse;
    int xDelta = 0, yDelta = 0;

    public GamePanel() {
        mouse = new Mouse();

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

    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.fillRect(100 + xDelta, 100 + yDelta, 200, 50);
    }
}
