package com.nkduy.platformer.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                System.out.println("Its W");
                break;
            case KeyEvent.VK_A:
                System.out.println("Its A");
                break;
            case KeyEvent.VK_S:
                System.out.println("Its S");
                break;
            case KeyEvent.VK_D:
                System.out.println("Its D");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
