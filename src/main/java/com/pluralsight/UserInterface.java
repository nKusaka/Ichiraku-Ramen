package com.pluralsight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class UserInterface extends JFrame implements KeyListener {

    // Variables that will be used throughout the class
    static Scanner read = new Scanner(System.in);
    static String userInput = "";
    private List<MenuItem> items = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd                                  HH:mm:ss");
    static FileManager fileManager = new FileManager();
    JFrame frame = new JFrame();

    // Method creates the welcome screen for the user
    public void welcomeScreen() {

        // Create new button so user can navigate out of welcome screen to menu
        JButton welcomeButton = new JButton();
        welcomeButton.setBounds(0, 0, 1000, 1000);
        welcomeButton.setFocusable(false);
        frame.getContentPane().removeAll();

        // Action listener waits for user to click button to then move to next menu
        welcomeButton.addActionListener(e ->
        {
            frame.getContentPane().removeAll();
            displayMenu();
        });

        // Setting text font and adding button to the jframe
        welcomeButton.setText("""
                <html><center>
                Welcome To Ichiraku Order Now<br>
                一楽ラーメン
                </center></html>
                """);
        welcomeButton.setFont(new Font("MS UI Gothic", Font.PLAIN, 50));
        frame.add(welcomeButton);
        frame.revalidate();
        frame.repaint();
    }

    // Method creates the menu screen for the user and moves to the appropriate screen after
    // taking in users input
    private void displayMenu() {
        // Main panel with BorderLayout
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Title at the top
        JLabel title = new JLabel("<html><center>Ichiraku Ramen<br>一楽ラーメン</center></html>", SwingConstants.CENTER);
        title.setForeground(new Color(212, 175, 55)); // RGB for gold
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(title);
        menuPanel.add(Box.createVerticalStrut(-30));

        // Panel for buttons (side by side)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        JButton ramenButton = new JButton("Ramen");
        JButton appetizerButton = new JButton("Appetizers");
        JButton drinkButton = new JButton("Drinks");
        JButton placeOrderButton = new JButton("Pay");
        JButton cancelButton = new JButton("Cancel Order");

        // Background Panel for Title
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBounds(0,0,1920,300);
        backgroundPanel.setBackground(Color.BLACK);

        // Set button fonts
        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 20);
        ramenButton.setFont(btnFont);
        appetizerButton.setFont(btnFont);
        drinkButton.setFont(btnFont);
        placeOrderButton.setFont(btnFont);
        cancelButton.setFont(new Font("MS UI Gothic", Font.BOLD, 20));

        // Add buttons to button panel
        buttonPanel.add(ramenButton);
        buttonPanel.add(appetizerButton);
        buttonPanel.add(drinkButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(cancelButton);

        // Add button panel to main panel
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        // Display in frame
        frame.getContentPane().removeAll();
        frame.add(menuPanel);
        frame.revalidate();
        frame.repaint();

        ramenButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            orderRamen();
        });
        appetizerButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            orderAppetizer();
        });
        drinkButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            orderDrink();
        });
        cancelButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            if (!orders.isEmpty()) {
                orders.remove(orders.size() - 1);
                welcomeScreen();
            }
        });
    }


    // Method creates a Ramen object to be passed into the order list later on
    // Users can customize their ramen by type, bowl size, and toppings if they would like
    private void orderRamen() {
//
//        userInput = getValidatedInput("""
//                ================================================
//                        Select A Type Of Ramen
//                        1. Tonkotsu Ramen                $12.00
//                        2. Red Garlic Tonkotsu Ramen     $14.00
//                        3. Black Garlic Tonkotsu Ramen   $16.00
//                        4. Miso Tonkotsu Ramen           $12.00
//                ================================================\n""", "1", "2", "3", "4");
//
//        switch (userInput) {
//            case "1":
//                items.add(new Ramen("Tonkotsu Ramen"));
//                System.out.println("1 Tonkotsu Ramen added to your order");
//                break;
//            case "2":
//                items.add(new Ramen("Red Garlic Tonkotsu Ramen"));
//                System.out.println("1 Red Garlic Tonkotsu Ramen added to your order");
//                break;
//            case "3":
//                items.add(new Ramen("Black Garlic Tonkotsu Ramen"));
//                System.out.println("1. Black Garlic Tonkotsu Ramen added to your order");
//                break;
//            case "4":
//                items.add(new Ramen("Miso Ramen"));
//                System.out.println("1 Miso Ramen added to your order");
//                break;
//            default:
//                break;
//        }
//
//        userInput = getValidatedInput("""
//                ========================================
//                            Select A Bowl Size
//                                1. Small        +$2.00
//                                2. Medium       +$3.00
//                                3. Large        +$4.00
//                =========================================\n""", "1", "2", "3");
//
//        if (items.get(items.size() - 1) instanceof Ramen) {
//            switch (userInput) {
//                case "1":
//                    ((Ramen) items.get(items.size() - 1)).addRamenSize(1);
//                    System.out.println("You selected Small for your bowl size");
//                    break;
//                case "2":
//                    ((Ramen) items.get(items.size() - 1)).addRamenSize(2);
//                    System.out.println("You selected Medium for your bowl size");
//                    break;
//                case "3":
//                    ((Ramen) items.get(items.size() - 1)).addRamenSize(3);
//                    System.out.println("You selected Large for your bowl size");
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        while (!userInput.equals("6")) {
//            userInput = getValidatedInput("""
//                    ==============================================
//                               Select Toppings
//                               1. Extra Noodles            +$4.00
//                               2. Extra Chashu 2 Pieces    +$1.50
//                               3. Extra Soft Boiled Egg    +$1.00
//                               4. Whole Fried Garlic       +$2.50
//                               5. Bamboo Shoots            +$3.00
//                               6. Exit Toppings List
//                    ==============================================\n""", "1", "2", "3", "4", "5", "6");
//
//            if (items.get(items.size() - 1) instanceof Ramen) {
//                switch (userInput) {
//                    case "1":
//                        ((Ramen) items.get(items.size() - 1)).addToppings(1);
//                        System.out.println("Extra noodles added");
//                        break;
//                    case "2":
//                        ((Ramen) items.get(items.size() - 1)).addToppings(2);
//                        System.out.println("Extra Chashu 2 pieces added");
//                        break;
//                    case "3":
//                        ((Ramen) items.get(items.size() - 1)).addToppings(3);
//                        System.out.println("Extra soft boiled egg added");
//                        break;
//                    case "4":
//                        ((Ramen) items.get(items.size() - 1)).addToppings(4);
//                        System.out.println("Extra whole fried garlic added");
//                        break;
//                    case "5":
//                        ((Ramen) items.get(items.size() - 1)).addToppings(5);
//                        System.out.println("Extra bamboo shoots added");
//                        break;
//                }
//            }
//        }
//        orders.get(orders.size() - 1).addMenuItem(items.get(items.size() - 1));
//        System.out.println("Your ramen has been added to your order");
    }

    //
//    // Method creates an appetizer object to be passed into the order list later on
//    // Users can order as many appetizers as they would like and finish ordering by pressing 7
    private void orderAppetizer() {
//
//        userInput = "";
//        while (!userInput.equals("7")) {
//            userInput = getValidatedInput("""
//                    ==========================================
//                    Select An Appetizer
//
//                    1. Gyoza 6 Pieces               $8.00
//                    2. Takoyaki 8 Pieces            $10.00
//                    3. Edamame                      $6.00
//                    4. Wakame Salad                 $5.50
//                    5. Squid Karaage                $12.00
//                    6. Chicken Karaage              $7.00
//                    7. Exit Appetizer List
//                    ==========================================\n""", "1", "2", "3", "4", "5", "6", "7");
//
//            switch (userInput) {
//                case "1":
//                    items.add(new Appetizer("gyoza"));
//                    System.out.println("Gyoza has been added to your order");
//                    break;
//                case "2":
//                    items.add(new Appetizer("takoyaki"));
//                    System.out.println("Takoyaki has been added to your order");
//                    break;
//                case "3":
//                    items.add(new Appetizer("edamame"));
//                    System.out.println("Edamame has been added to your order");
//                    break;
//                case "4":
//                    items.add(new Appetizer("wakame salad"));
//                    System.out.println("Wakame Salad has been added to your order");
//                    break;
//                case "5":
//                    items.add(new Appetizer("squid karaage"));
//                    System.out.println("Squid Karaage has been added to your order");
//                    break;
//                case "6":
//                    items.add(new Appetizer("chicken karaage"));
//                    System.out.println("Chicken Karaage has been added to your order");
//                    break;
//                default:
//                    break;
//            }
//            if (!userInput.equals("7")) {
//                orders.get(orders.size() - 1).addMenuItem(items.get(items.size() - 1));
//            }
//        }
    }

    //
//    // Method creates a drink object to be passed into the order list later on
//    // users can order as many drinks as they would like and finish ordering by pressing 6
    private void orderDrink() {
//        while (!userInput.equals("6")) {
//            userInput = getValidatedInput("""
//                    ====================================
//                    Select A Drink
//
//                    1. Sake                     $8.00
//                    2. Green Tea                $2.00
//                    3. Water                    $FREE
//                    4. Coke                     $3.00
//                    5. Sprite                   $3.00
//                    6. Exit Drink List
//                    =====================================
//                    """, "1", "2", "3", "4", "5", "6");
//
//            if (!userInput.equals("6")) {
//                switch (userInput) {
//                    case "1":
//                        items.add(new Drink("sake"));
//                        System.out.println("Sake added to your order");
//                        break;
//                    case "2":
//                        items.add(new Drink("green tea"));
//                        System.out.println("Green Tea added to your order");
//                        break;
//                    case "3":
//                        items.add(new Drink("water"));
//                        System.out.println("Water added to your order");
//                        break;
//                    case "4":
//                        items.add(new Drink("coke"));
//                        System.out.println("Coke added to your order");
//                        break;
//                    case "5":
//                        items.add(new Drink("sprite"));
//                        System.out.println("Sprite added to your order");
//                        break;
//                    default:
//                        break;
//                }
//                orders.get(orders.size() - 1).addMenuItem(items.get(items.size() - 1));
//            }
//        }
    }

    // Method creates the receipt for the user displaying all items they have purchased
    // Outputs the total cost of the items purchased
    private void getReceipt() {

        System.out.printf("""
                =================================================
                            Printing Receipt\n""");

        loadingTime();
        System.out.printf("%s\n", LocalDateTime.now().format(formatter));

        for (MenuItem item : orders.get(orders.size() - 1).getOrderList()) {
            if (item instanceof Ramen) {
                System.out.printf("%s", item);
            } else if (item instanceof Appetizer) {
                System.out.printf("1x %-40s $%.2f\n", item, item.getPrice());
            } else if (item instanceof Drink) {
                System.out.printf("1x %-40s $%.2f\n", item, item.getPrice());
            }
        }
        System.out.printf("""
                \n\nTotal: $%.2f
                
                Have a great day!
                ==================================================\n""", orders.get(orders.size() - 1).getOrderTotal().doubleValue());
    }

    // Method adds a bit of loading time so the user has time to process output
    private void loadingTime() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Error with loading time");
        }
    }

    // Method overloading for loading time to be able to pass in a certain amount of loading time
    private void loadingTime(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            System.out.println("Error with loading time");
        }
    }

    // Initializes the Jframe ui
    public void init() {
        ImageIcon image = new ImageIcon("logo.png");

        frame.setTitle("Ichiraku Ramen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setIconImage(image.getImage());
        frame.setVisible(true);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        welcomeScreen();
    }

    // Override methods for keylistener interface
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
