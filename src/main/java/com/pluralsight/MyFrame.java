package com.pluralsight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// This class creates the frame for the ui
public class MyFrame extends JFrame implements KeyListener {
    private JButton welcomeButton;


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

    // Welcome frame
    public void welcomeScreen() {
        welcomeButton = new JButton();
        welcomeButton.setFocusable(false);
        this.getContentPane().removeAll();

        welcomeButton.addActionListener(e ->
        {
            this.getContentPane().removeAll();
            displayOptions();
        });

        welcomeButton.setText("""
                <html><center>
                Welcome To Ichiraku Order Now<br>
                一楽ラーメン
                </center></html>
                """);
        welcomeButton.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        this.add(welcomeButton);
        this.revalidate();
        this.repaint();

    }



    private void displayOptions() {

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
