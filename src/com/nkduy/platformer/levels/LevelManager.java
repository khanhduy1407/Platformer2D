package com.nkduy.platformer.levels;

import com.nkduy.platformer.main.Game;
import com.nkduy.platformer.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    Game game;
    BufferedImage[] levelSprite;

    public LevelManager(Game game) {
        this.game = game;
//        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48]; // w: 12, h: 4 => 12*4 = 48

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 12; i++) {
                int index = j*12 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite[2], 0, 0, null);
    }

    public void update() {
        //
    }
}
