package com.nkduy.platformer;

public class Game {

    Window window;
    GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel();
        window = new Window(gamePanel);
        gamePanel.requestFocus();
    }
}
