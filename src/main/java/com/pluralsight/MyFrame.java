package com.pluralsight;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class creates the frame for the ui
public class MyFrame extends JFrame implements KeyListener {

    public MyFrame() {
        ImageIcon logo = new ImageIcon("logo.png");

        this.setTitle("Ichiraku Ramen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
