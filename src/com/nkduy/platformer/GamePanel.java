package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    Mouse mouse;

    public GamePanel() {
        mouse = new Mouse();

        addKeyListener(new Keyboard());
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.fillRect(100, 100, 200, 50);
    }
}
