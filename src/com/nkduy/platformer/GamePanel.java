package com.nkduy.platformer;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        //
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        g.fillRect(100, 100, 200, 50);
    }
}
