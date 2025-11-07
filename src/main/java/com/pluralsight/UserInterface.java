package com.pluralsight;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class UserInterface {

    // Variables that will be used throughout the class
    static Scanner read = new Scanner(System.in);
    private Order userOrder = new Order();
    static JFrame display = new JFrame("Ichiraku Ramen");

    // Method creates the welcome screen for the user
    public void welcomeScreen() {
        displayMenu();
    }

    private void displayMenu() {
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setSize(1000, 1000);
        display.setLocationRelativeTo(null);
        display.setResizable(false);
        display.setVisible(true);

        try {
            Image icon = ImageIO.read(new File("logo.png"));
            Image scaledIcon = icon.getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            display.setIconImage(scaledIcon);
            display.repaint();
            display.revalidate();
            display.getContentPane().setBackground(Color.BLACK);
        } catch (IOException e) {

        }

    }


    private void init() {

    }
}
