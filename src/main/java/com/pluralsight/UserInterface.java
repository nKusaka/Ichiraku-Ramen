package com.pluralsight;

import java.util.*;

public class UserInterface {

    // Variables that will be used throughout the class
    static Scanner read = new Scanner(System.in);
    static String userInput = "";
    private List<MenuItem> items;
    private List<Order> orders;

    // Method creates the welcome screen for the user
    public void welcomeScreen() {

        while (!userInput.equals("2")) {
            System.out.printf("""
                    ============================================
                    
                              🍜Welcome to Ichiraku!!🍜
                    
                                1. New Order
                                2. Exit
                    
                    ============================================\n""");

            userInput = read.nextLine();

            while (!userInput.equals("1") && !userInput.equals("2")) {
                System.out.printf("Invalid input try again");
                userInput = read.nextLine();
            }
            if (userInput.equals("1")) {
                orders = new ArrayList<>();
                orders.add(new Order());
                items = new ArrayList<>();
                loadingTime();
                displayMenu();
            }
        }
    }

    // Method creates the menu screen for the user and moves to the appropriate screen after
    // taking in users input
    private void displayMenu() {

        boolean receiptPrinted = false;
        userInput = "";
        while (!userInput.equals("5") && !userInput.equals("4")) {
            System.out.printf("""
                    ===============================================
                            Enter One Of The Numbers Below
                                  1. Order Ramen
                                  2. Order Appetizers
                                  3. Order Drinks
                                  4. Get Receipt
                                  5. Cancel Order
                    ===============================================\n""");

            userInput = read.nextLine();

            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5")) {
                System.out.printf("Please enter a valid number");
                userInput = read.nextLine();
            }

            switch (userInput) {
                case "1":
                    loadingTime();
                    orderRamen();
                    break;
                case "2":
                    loadingTime();
                    orderAppetizer();
                    break;
                case "3":
                    loadingTime();
                    orderDrink();
                    break;
                case "4":
                    loadingTime();
                    getReceipt();
                    receiptPrinted = true;
                    break;
                default:
                    break;
            }
        }
            if (!items.isEmpty() && !receiptPrinted) {
                loadingTime();
                getReceipt();
            }
        items.clear();
    }

    // Method creates a Ramen object to be passed into the order list later on
    // Users can customize their ramen by type, bowl size, and toppings if they would like
    private void orderRamen() {
        System.out.printf("""
                ================================================
                        Select A Type Of Ramen
                        1. Tonkotsu Ramen                $12.00
                        2. Red Garlic Tonkotsu Ramen     $14.00
                        3. Black Garlic Tonkotsu Ramen   $16.00
                        4. Miso Tonkotsu Ramen           $12.00
                ================================================\n""");

        userInput = read.nextLine();

        while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")) {
            System.out.printf("Please enter a valid number");
            userInput = read.nextLine();
        }

        switch (userInput) {
            case "1":
                items.add(new Ramen("Tonkotsu Ramen"));
                System.out.println("1 Tonkotsu Ramen added to your order");
                break;
            case "2":
                items.add(new Ramen("Red Garlic Tonkotsu Ramen"));
                System.out.println("1 Red Garlic Tonkotsu Ramen added to your order");
                break;
            case "3":
                items.add(new Ramen("Black Garlic Tonkotsu Ramen"));
                System.out.println("1. Black Garlic Tonkotsu Ramen added to your order");
                break;
            case "4":
                items.add(new Ramen("Miso Ramen"));
                System.out.println("1 Miso Ramen added to your order");
                break;
            default:
                break;
        }

        System.out.printf("""
                ========================================
                            Select A Bowl Size
                                1. Small        +$2.00
                                2. Medium       +$3.00
                                3. Large        +$4.00
                =========================================\n""");

        userInput = read.nextLine();
        while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3")) {
            System.out.printf("Please enter a valid number");
            userInput = read.nextLine();
        }

        if (items.get(items.size() - 1) instanceof Ramen) {
            switch (userInput) {
                case "1":
                    ((Ramen) items.get(0)).addRamenSize(1);
                    System.out.println("You selected Small for your bowl size");
                    break;
                case "2":
                    ((Ramen) items.get(0)).addRamenSize(2);
                    System.out.println("You selected Medium for your bowl size");
                    break;
                case "3":
                    ((Ramen) items.get(0)).addRamenSize(3);
                    System.out.println("You selected Large for your bowl size");
                    break;
                default:
                    break;
            }
        }

        while (!userInput.equals("6")) {
            System.out.printf("""
                    ==============================================
                               Select Toppings
                               1. Extra Noodles            +$4.00
                               2. Extra Chashu 2 Pieces    +$1.50
                               3. Extra Soft Boiled Egg    +$1.00
                               4. Whole Fried Garlic       +$2.50
                               5. Bamboo Shoots            +$3.00
                               6. Exit Toppings List
                    ==============================================\n""");

            userInput = read.nextLine();
            while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4") && !userInput.equals("5") && !userInput.equals("6")) {
                System.out.printf("Please enter a valid number");
                userInput = read.nextLine();
            }

            if (items.get(items.size() - 1) instanceof Ramen) {
                switch (userInput) {
                    case "1":
                        ((Ramen) items.get(items.size() - 1)).addToppings(1);
                        System.out.println("Extra noodles added");
                        break;
                    case "2":
                        ((Ramen) items.get(items.size() - 1)).addToppings(2);
                        System.out.println("Extra Chashu 2 pieces added");
                        break;
                    case "3":
                        ((Ramen) items.get(items.size() - 1)).addToppings(3);
                        System.out.println("Extra soft boiled egg added");
                        break;
                    case "4":
                        ((Ramen) items.get(items.size() - 1)).addToppings(4);
                        System.out.println("Extra whole fried garlic added");
                        break;
                    case "5":
                        ((Ramen) items.get(items.size() - 1)).addToppings(5);
                        System.out.println("Extra bamboo shoots added");
                        break;
                }
            }
        }
        orders.get(orders.size() - 1).addMenuItem(items.get(items.size() - 1));
        System.out.println("Your ramen has been added to your order");
    }

    private void orderAppetizer() {

    }

    private void orderDrink() {

    }

    private void getReceipt() {
        System.out.printf("""
                =========================================
                            Printing Receipt\n""");

        loadingTime();

        System.out.printf("""
                Total: $%.2f
                
                Have a great day!
                ==========================================\n""", orders.get(orders.size() - 1).getOrderTotal().doubleValue());
    }

    private void loadingTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error with loading time");
        }
    }

}
