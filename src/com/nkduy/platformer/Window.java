package com.nkduy.platformer;

import javax.swing.*;

public class Window {

    JFrame jFrame;

    public Window() {
        jFrame = new JFrame();

        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
