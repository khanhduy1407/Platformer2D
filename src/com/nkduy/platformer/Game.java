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

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            // If one second have passed since the last fps check, we do a new fps check.
            // Save the newFps check as the lastFps check and repeat.
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
