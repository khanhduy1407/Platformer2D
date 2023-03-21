package com.nkduy.platformer.inputs;

import com.nkduy.platformer.main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    GamePanel gamePanel;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getGame().getPlayer().setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
