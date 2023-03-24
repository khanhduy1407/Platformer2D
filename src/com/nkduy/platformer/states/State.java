package com.nkduy.platformer.states;

import com.nkduy.platformer.main.Game;

public class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
