package com.nkduy.platformer;

public class Game implements Runnable {

    Window window;
    GamePanel gamePanel;

    Thread gameThread;

    final int FPS_SET = 120;

    public Game() {
        gamePanel = new GamePanel();
        window = new Window(gamePanel);

        gamePanel.requestFocus();

        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
            }
        }
    }
}
