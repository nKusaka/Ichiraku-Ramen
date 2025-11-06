package com.myutilities;
import java.util.*;
/*
Custom utilities that I will be using throughout the project
 */
public class Utilities {

    // Method to generate the loading bar
    public static void loadingBar(String customMessage) throws Exception {

        // Generate a random number from 1 to 5 using Random java class
        Random randomNumber = new Random();
        int randomNum = randomNumber.nextInt(3) + 1;

        // Simulate the loading bar
        StringBuilder loadingBar = new StringBuilder();
        for(int i = 0; i <= 20; i++) {
            loadingBar.append("▉");
            System.out.print("\rLoading: [" + loadingBar + "]" + (i * 5) + "%");
            Thread.sleep(100L * randomNum);
        }

        System.out.println("\nDONE");
    }

}

