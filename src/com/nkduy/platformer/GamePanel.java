package com.nkduy.platformer;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    Mouse mouse;
    Game game;

    public GamePanel(Game game) {
        mouse = new Mouse(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new Keyboard(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(960, 480); // w: 960/32 = 30 images; h: 480/32 = 15 images
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {
        //
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
