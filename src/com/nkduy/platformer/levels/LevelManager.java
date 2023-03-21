package com.nkduy.platformer.levels;

import com.nkduy.platformer.main.Game;
import com.nkduy.platformer.util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    Game game;
    BufferedImage levelSprite;

    public LevelManager(Game game) {
        this.game = game;
        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
    }

    public void draw(Graphics g) {
        g.drawImage(levelSprite, 0, 0, null);
    }

    public void update() {
        //
    }
}
