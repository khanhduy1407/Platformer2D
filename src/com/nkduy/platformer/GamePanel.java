package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel {

    public GamePanel() {
        addKeyListener(new Keyboard());
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.fillRect(100, 100, 200, 50);
    }
}
