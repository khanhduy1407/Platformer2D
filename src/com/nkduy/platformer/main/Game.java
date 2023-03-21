package com.nkduy.platformer.main;

import com.nkduy.platformer.entities.Player;

import java.awt.*;

public class Game implements Runnable {

    Window window;
    GamePanel gamePanel;

    Thread gameThread;

    final int FPS_SET = 120;
    final int UPS_SET = 200;

    Player player;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        window = new Window(gamePanel);

        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            // deltaU will be 1.0 or more WHEN the duration since last update is equal
            // OR more than timePerUpdate
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU > 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF > 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
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

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
