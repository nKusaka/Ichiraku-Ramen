package com.pluralsight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserInterface {

    // Variables that will be used throughout the class
    static Scanner read = new Scanner(System.in);
    static String userInput = "";
    private List<MenuItem> items = new ArrayList<>();
    private Order order;
    static FileManager fileManager = new FileManager();

    // Method creates the welcome screen for the user
    public void welcomeScreen() {

        while (!userInput.equals("2")) {

            userInput = getValidatedInput("""
                    ============================================
                    
                              🍜Welcome to Ichiraku!!🍜
                    
                                1. New Order
                                2. Exit
                    
                    ============================================\n""", "1", "2");

            if (userInput.equals("1")) {
                loadingTime();
                displayMenu();
            }
        }
    }

    // Method creates the menu screen for the user and moves to the appropriate screen after
    // taking in users input
    private void displayMenu() {

        order = new Order();
        userInput = "";
        while (!userInput.equals("5") && !userInput.equals("4")) {

            userInput = getValidatedInput("""
                    ===============================================
                            Enter One Of The Numbers Below
                                  1. Order Ramen
                                  2. Order Appetizers
                                  3. Order Drinks
                                  4. Finished Ordering
                                  5. Cancel Order
                    ===============================================\n""", "1", "2", "3", "4", "5");

            switch (userInput) {
                case "1":
                    userInput = "";
                    loadingTime();
                    orderRamen();
                    break;
                case "2":
                    userInput = "";
                    loadingTime();
                    orderAppetizer();
                    break;
                case "3":
                    userInput = "";
                    loadingTime();
                    orderDrink();
                    break;
                case "4":
                    if (order.getOrderList().isEmpty()) {
                        System.out.println("Returning To Home Screen");
                        loadingTime();
                    } else {
                        loadingTime();
                        getReceipt();
                        break;
                    }
                case "5":
                    if (!order.getOrderList().isEmpty()) {
                        order.getOrderList().clear();
                    }
                    break;
                default:
                    break;
            }
        }

        fileManager.printReceipt(order);
        items.clear();
    }

    // Method creates a Ramen object to be passed into the order list later on
    // Users can customize their ramen by type, bowl size, and toppings if they would like
    private void orderRamen() {

        userInput = getValidatedInput("""
                ================================================
                        Select A Type Of Ramen
                        1. Tonkotsu Ramen                $12.00
                        2. Red Garlic Tonkotsu Ramen     $14.00
                        3. Black Garlic Tonkotsu Ramen   $16.00
                        4. Miso Tonkotsu Ramen           $12.00
                ================================================\n""", "1", "2", "3", "4");

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

        userInput = getValidatedInput("""
                ========================================
                            Select A Bowl Size
                                1. Small        +$2.00
                                2. Medium       +$3.00
                                3. Large        +$4.00
                =========================================\n""", "1", "2", "3");

        if (items.get(items.size() - 1) instanceof Ramen) {
            switch (userInput) {
                case "1":
                    ((Ramen) items.get(items.size() - 1)).addRamenSize(1);
                    System.out.println("You selected Small for your bowl size");
                    break;
                case "2":
                    ((Ramen) items.get(items.size() - 1)).addRamenSize(2);
                    System.out.println("You selected Medium for your bowl size");
                    break;
                case "3":
                    ((Ramen) items.get(items.size() - 1)).addRamenSize(3);
                    System.out.println("You selected Large for your bowl size");
                    break;
                default:
                    break;
            }
        }

        while (!userInput.equals("6")) {
            userInput = getValidatedInput("""
                    ==============================================
                               Select Toppings
                               1. Extra Noodles            +$4.00
                               2. Extra Chashu 2 Pieces    +$1.50
                               3. Extra Soft Boiled Egg    +$1.00
                               4. Whole Fried Garlic       +$2.50
                               5. Bamboo Shoots            +$3.00
                               6. Exit Toppings List
                    ==============================================\n""", "1", "2", "3", "4", "5", "6");

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
        order.addMenuItem(items.get(items.size() - 1));
        System.out.println("Your ramen has been added to your order");
    }

    // Method creates an appetizer object to be passed into the order list later on
    // Users can order as many appetizers as they would like and finish ordering by pressing 7
    private void orderAppetizer() {

        userInput = "";
        while (!userInput.equals("7")) {
            userInput = getValidatedInput("""
                    ==========================================
                    Select An Appetizer
                    
                    1. Gyoza 6 Pieces               $8.00
                    2. Takoyaki 8 Pieces            $10.00
                    3. Edamame                      $6.00
                    4. Wakame Salad                 $5.50
                    5. Squid Karaage                $12.00
                    6. Chicken Karaage              $7.00
                    7. Exit Appetizer List
                    ==========================================\n""", "1", "2", "3", "4", "5", "6", "7");

            switch (userInput) {
                case "1":
                    items.add(new Appetizer("gyoza"));
                    System.out.println("Gyoza has been added to your order");
                    break;
                case "2":
                    items.add(new Appetizer("takoyaki"));
                    System.out.println("Takoyaki has been added to your order");
                    break;
                case "3":
                    items.add(new Appetizer("edamame"));
                    System.out.println("Edamame has been added to your order");
                    break;
                case "4":
                    items.add(new Appetizer("wakame salad"));
                    System.out.println("Wakame Salad has been added to your order");
                    break;
                case "5":
                    items.add(new Appetizer("squid karaage"));
                    System.out.println("Squid Karaage has been added to your order");
                    break;
                case "6":
                    items.add(new Appetizer("chicken karaage"));
                    System.out.println("Chicken Karaage has been added to your order");
                    break;
                default:
                    break;
            }
            if (!userInput.equals("7")) {
                order.addMenuItem(items.get(items.size() - 1));
            }
        }
    }

    // Method creates a drink object to be passed into the order list later on
    // users can order as many drinks as they would like and finish ordering by pressing 6
    private void orderDrink() {
        while (!userInput.equals("6")) {
            userInput = getValidatedInput("""
                    ====================================
                    Select A Drink
                    
                    1. Sake                     $8.00
                    2. Green Tea                $2.00
                    3. Water                    $FREE
                    4. Coke                     $3.00
                    5. Sprite                   $3.00
                    6. Exit Drink List
                    =====================================
                    """, "1", "2", "3", "4", "5", "6");

            if (!userInput.equals("6")) {
                switch (userInput) {
                    case "1":
                        items.add(new Drink("sake"));
                        System.out.println("Sake added to your order");
                        break;
                    case "2":
                        items.add(new Drink("green tea"));
                        System.out.println("Green Tea added to your order");
                        break;
                    case "3":
                        items.add(new Drink("water"));
                        System.out.println("Water added to your order");
                        break;
                    case "4":
                        items.add(new Drink("coke"));
                        System.out.println("Coke added to your order");
                        break;
                    case "5":
                        items.add(new Drink("sprite"));
                        System.out.println("Sprite added to your order");
                        break;
                    default:
                        break;
                }
                order.addMenuItem(items.get(items.size() - 1));
            }
        }
    }

    // Method creates the receipt for the user displaying all items they have purchased
    // Outputs the total cost of the items purchased
    private void getReceipt() {

        System.out.printf("""
                ===============================================
                            Printing Receipt\n""");

        loadingTime();

        System.out.printf("""
                            \n\nHave a great day!
                ===============================================\n""", order.getOrderTotal().doubleValue());

        loadingTime();
    }

    // Method adds a bit of loading time so the user has time to process output
    private void loadingTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error with loading time");
        }
    }

    /* Helper method to check if inputs are valid, before a lot of code was being copy pasted
    around to validate input. Asked ChatGPT to see if there was a better way and gave this helper
    method */
    private String getValidatedInput(String prompt, String... validOptions) {
        System.out.printf("%s", prompt);
        String input = read.nextLine();

        while (!Arrays.asList(validOptions).contains(input)) {
            System.out.printf("Please enter a valid number ");
            input = read.nextLine();
        }

        return input;
    }
}
