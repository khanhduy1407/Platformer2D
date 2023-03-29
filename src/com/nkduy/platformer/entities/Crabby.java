package com.nkduy.platformer.entities;

import static com.nkduy.platformer.util.Constants.EnemyConstants.*;

public class Crabby extends Enemy {

    public Crabby(float x, float y) {
        super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY);
    }
}
