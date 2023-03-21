package com.nkduy.platformer.main;

import com.nkduy.platformer.inputs.Keyboard;
import com.nkduy.platformer.inputs.Mouse;

import javax.swing.*;
import java.awt.*;

import static com.nkduy.platformer.main.Game.GAME_WIDTH;
import static com.nkduy.platformer.main.Game.GAME_HEIGHT;

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
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Panel size: " + GAME_WIDTH + "x" + GAME_HEIGHT);
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
