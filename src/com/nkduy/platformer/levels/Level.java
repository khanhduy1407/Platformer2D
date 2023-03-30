package com.nkduy.platformer.levels;

import com.nkduy.platformer.entities.Crabby;
import com.nkduy.platformer.main.Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static com.nkduy.platformer.util.HelpMethods.*;

public class Level {

    BufferedImage img;
    int[][] lvlData;
    ArrayList<Crabby> crabs;
    int lvlTilesWide;
    int maxTilesOffset;
    int maxLvlOffsetX;

    public Level(BufferedImage img) {
        this.img = img;

        createLevelData();
        createEnemies();
        calcLvlOffsets();
    }

    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE;
    }

    private void createEnemies() {
        crabs = GetCrabs(img);
    }

    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    public int[][] getLevelData() {
        return lvlData;
    }

    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    public ArrayList<Crabby> getCrabs() {
        return crabs;
    }
}
