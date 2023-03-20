package com.nkduy.platformer;

public class Game implements Runnable {

    Window window;
    GamePanel gamePanel;

    Thread gameThread;

    final int FPS_SET = 120;
    final int UPS_SET = 200;

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
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;

        while (true) {
            now = System.nanoTime();
            long currentTime = System.nanoTime();

            // deltaU will be 1.0 or more WHEN the duration since last update is equal
            // OR more than timePerUpdate
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if (deltaU > 1) {
                // update();
                updates++;
                deltaU--;
            }

            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;
            }

            // If one second have passed since the last fps check, we do a new fps check.
            // Save the newFps check as the lastFps check and repeat.
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
}
