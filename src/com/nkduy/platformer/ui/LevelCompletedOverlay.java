package com.nkduy.platformer.ui;

import com.nkduy.platformer.main.Game;
import com.nkduy.platformer.states.Playing;
import com.nkduy.platformer.util.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static com.nkduy.platformer.util.Constants.UI.URMButtons.*;

public class LevelCompletedOverlay {

    Playing playing;
    UrmButton menu, next;
    BufferedImage img;
    int bgX, bgY, bgW, bgH;

    public LevelCompletedOverlay(Playing playing) {
        this.playing = playing;

        initImg();
        initButtons();
    }

    private void initButtons() {
        int menuX = (int) (330 * Game.SCALE);
        int nextX = (int) (445 * Game.SCALE);
        int y = (int) (195 * Game.SCALE);
        next = new UrmButton(nextX, y, URM_SIZE, URM_SIZE, 0);
        menu = new UrmButton(menuX, y, URM_SIZE, URM_SIZE, 2);
    }

    private void initImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.COMPLETED_IMG);
        bgW = (int) (img.getWidth() * Game.SCALE);
        bgH = (int) (img.getHeight() * Game.SCALE);
        bgX = Game.GAME_WIDTH / 2 - bgW / 2;
        bgY = (int) (75 * Game.SCALE);
    }

    public void draw(Graphics g) {
        g.drawImage(img, bgX, bgY, bgW, bgH, null);
        next.draw(g);
        menu.draw(g);
    }

    public void update() {
        //
    }

    public void mouseMoved(MouseEvent e) {
        //
    }

    public void mouseReleased(MouseEvent e) {
        //
    }

    public void mousePressed(MouseEvent e) {
        //
    }
}
