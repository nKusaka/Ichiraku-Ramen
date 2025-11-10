package com.pluralsight;

import java.util.*;

public class UserInterface {

    // Variables that will be used throughout the class
    static Scanner read = new Scanner(System.in);
    private Order userOrder = new Order();
    static String userInput;

    // Method creates the welcome screen for the user
    public void welcomeScreen() {
        System.out.printf("""
                ============================================
                
                          🍜Welcome to Ichiraku!!🍜
                
                ============================================\n""");
        loadingTime();
        displayMenu();
    }

    // Method creates the menu screen for the user and moves to the appropriate screen after
    // taking in users input
    private void displayMenu() {
        System.out.printf("""
                ===============================================
                Enter One Of The Numbers Below
                1. Order Ramen
                2. Order Appetizers
                3. Order Drinks
                4. Get Receipt
                5. Exit
                ===============================================\n""");

        userInput = read.nextLine();

        while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5")) {
            System.out.printf("Please enter a valid number");
            userInput = read.nextLine();
        }
    }

        private void init () {

        }

        private void loadingTime () {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error with loading time");
            }
        }

    }
