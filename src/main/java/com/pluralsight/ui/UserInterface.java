package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.models.MenuItem;
import com.pluralsight.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class UserInterface extends JFrame implements KeyListener {
    private JButton welcomeButton;
    private Order order;
    private List<MenuItem> items = new ArrayList<>();
    static FileManager fileManager = new FileManager();

    public UserInterface() {
        ImageIcon logo = new ImageIcon("logo.png");
        order = new Order();

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
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add Title to display and set color and alignment
        // add the title to the menupanel
        JLabel title = new JLabel("<html><center>Ichiraku Ramen<br>一楽ラーメン</center></html>", SwingConstants.CENTER);
        title.setForeground(new Color(212, 175, 55)); // RGB for gold
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(title);
        menuPanel.add(Box.createVerticalStrut(-30));

        // New panel for buttons and new buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 50));
        buttonPanel.setPreferredSize(new Dimension(200,200));
        JButton ramenButton = new JButton("Ramen");
        JButton appetizerButton = new JButton("Appetizers");
        JButton drinkButton = new JButton("Drinks");
        JButton placeOrderButton = new JButton("Pay");
        JButton cancelButton = new JButton("Cancel Order");

        // Set button fonts
        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 20);
        ramenButton.setFont(btnFont);
        appetizerButton.setFont(btnFont);
        drinkButton.setFont(btnFont);
        placeOrderButton.setFont(btnFont);
        cancelButton.setFont(btnFont);

        // Add buttons to button panel
        buttonPanel.add(ramenButton);
        buttonPanel.add(appetizerButton);
        buttonPanel.add(drinkButton);
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(cancelButton);

        // Add button panel to main panel
        menuPanel.add(buttonPanel, BorderLayout.CENTER);

        // Display in frame
        this.getContentPane().removeAll();
        this.add(menuPanel);
        this.revalidate();
        this.repaint();

        ramenButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            menuPanel.remove(1);
            orderRamen();
        });
        appetizerButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            orderAppetizer();
        });
        drinkButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            orderDrink();
        });

        placeOrderButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            pay();
        });
        cancelButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            if (!order.getOrderList().isEmpty()) {
                order.getOrderList().clear();
            }
            welcomeScreen();
        });
    }

    // Lets the user order ramen types
    public void orderRamen() {

        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Type Of Ramen");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton tonkotsu = new JButton("<html><center>Tonkotsu Ramen<br>$12.00");
        JButton redGarlic = new JButton("<html><center>Red Garlic Tonkotsu Ramen<br>$14.00");
        JButton blackGarlic = new JButton("<html><center>Black Garlic Tonkotsu Ramen<br>$16.00");
        JButton miso = new JButton("<html><center>Miso Ramen<br>$12.00");

        tonkotsu.setFont(btnFont);
        redGarlic.setFont(btnFont);
        blackGarlic.setFont(btnFont);
        miso.setFont(btnFont);

        buttonPanel.add(tonkotsu);
        buttonPanel.add(redGarlic);
        buttonPanel.add(blackGarlic);
        buttonPanel.add(miso);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        tonkotsu.addActionListener(e ->
        {
            items.add(new Ramen("Tonkotsu Ramen"));
            this.getContentPane().removeAll();
            getRamenSize();
        });

        redGarlic.addActionListener(e->
        {
            items.add(new Ramen("Red Garlic Tonkotsu Ramen"));
            this.getContentPane().removeAll();
            getRamenSize();
        });

        blackGarlic.addActionListener(e->
        {
            items.add(new Ramen("Black Garlic Tonkotsu Ramen"));
            this.getContentPane().removeAll();
            getRamenSize();
        });

        miso.addActionListener(e ->
        {
            items.add(new Ramen("Miso Ramen"));
            this.getContentPane().removeAll();
            getRamenSize();
        });
    }

    // Gets the size of the ramen from the user
    private void getRamenSize() {
        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Size of Ramen");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton small = new JButton("<html><center>Small<br>$2.00");
        JButton medium = new JButton("<html><center>Medium<br>$3.00");
        JButton large = new JButton("<html><center>Large<br>$4.00");

        small.setFont(btnFont);
        medium.setFont(btnFont);
        large.setFont(btnFont);

        buttonPanel.add(small);
        buttonPanel.add(medium);
        buttonPanel.add(large);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        small.addActionListener(e ->
        {
            ((Ramen) items.get(items.size() - 1)).addRamenSize(1);
            this.getContentPane().removeAll();
            getToppings();
        });

         medium.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addRamenSize(2);
            this.getContentPane().removeAll();
            getToppings();
        });

        large.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addRamenSize(3);
            this.getContentPane().removeAll();
            getToppings();
        });
    }

    // Gets the type of toppings from the user
    private void getToppings() {
        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Toppings");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton noodles = new JButton("<html><center>Extra Noodles<br>$1.50");
        JButton chashu = new JButton("<html><center>Extra Chashu 2 Pieces<br>$2.00");
        JButton egg = new JButton("<html><center>Extra Soft Boiled Egg<br>$1.00");
        JButton garlic = new JButton("<html><center>Whole Fried Garlic(premium)<br>$4.00");
        JButton bamboo = new JButton("<html><center>Bamboo Shoots(premium)<br>$4.00");
        JButton wagyu = new JButton("<html><center>Wagyu Beef(premium)<br>$4.00");
        JButton cancel = new JButton("<html><center>Leave Toppings List");

        noodles.setFont(btnFont);
        chashu.setFont(btnFont);
        egg.setFont(btnFont);
        garlic.setFont(btnFont);
        bamboo.setFont(btnFont);
        wagyu.setFont(btnFont);
        cancel.setFont(btnFont);

        buttonPanel.add(noodles);
        buttonPanel.add(chashu);
        buttonPanel.add(egg);
        buttonPanel.add(garlic);
        buttonPanel.add(bamboo);
        buttonPanel.add(wagyu);
        buttonPanel.add(cancel);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        noodles.addActionListener(e ->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(1);
            this.getContentPane().removeAll();
            getToppings();
        });

        chashu.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(2);
            this.getContentPane().removeAll();
            getToppings();
        });

        egg.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(3);
            this.getContentPane().removeAll();
            getToppings();
        });

        garlic.addActionListener(e ->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(4);
            this.getContentPane().removeAll();
            getToppings();
        });

        bamboo.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(5);
            this.getContentPane().removeAll();
            getToppings();
        });

        wagyu.addActionListener(e->
        {
            ((Ramen) items.get(items.size() - 1)).addToppings(6);
            this.getContentPane().removeAll();
            getToppings();
        });

        cancel.addActionListener(e->
        {
            order.addMenuItem(items.get(items.size()-1));
            displayOptions();
        });
    }

    // Gets appetizers menu for the user
    private void orderAppetizer() {
        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Appetizers");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton gyoza = new JButton("<html><center>Gyoza 6 Pieces<br>$8.00");
        JButton takoyaki = new JButton("<html><center>Takoyaki 8 Pieces<br>$10.00");
        JButton edamame = new JButton("<html><center>Edamame<br>$6.00");
        JButton wakame = new JButton("<html><center>Wakame Salad<br>$5.50");
        JButton squid = new JButton("<html><center>Squid Karaage<br>$12.00");
        JButton chicken = new JButton("<html><center>Chicken Karaage<br>$7.00");
        JButton cancel = new JButton("<html><center>Leave Appetizer List");

        gyoza.setFont(btnFont);
        takoyaki.setFont(btnFont);
        edamame.setFont(btnFont);
        wakame.setFont(btnFont);
        squid.setFont(btnFont);
        chicken.setFont(btnFont);
        cancel.setFont(btnFont);

        buttonPanel.add(gyoza);
        buttonPanel.add(takoyaki);
        buttonPanel.add(edamame);
        buttonPanel.add(wakame);
        buttonPanel.add(squid);
        buttonPanel.add(chicken);
        buttonPanel.add(cancel);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        gyoza.addActionListener(e ->
        {
            items.add(new Appetizer("gyoza"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        takoyaki.addActionListener(e->
        {
            items.add(new Appetizer("takoyaki"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        edamame.addActionListener(e->
        {
            items.add(new Appetizer("edamame"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        wakame.addActionListener(e ->
        {
            items.add(new Appetizer("wakame salad"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        squid.addActionListener(e->
        {
            items.add(new Appetizer("squid karaage"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        chicken.addActionListener(e->
        {
            items.add(new Appetizer("chicken karaage"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderAppetizer();
        });

        cancel.addActionListener(e->
        {
            displayOptions();
        });
    }

    // Gets the drinks menu for the user
    private void orderDrink() {
        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Drinks");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton sake = new JButton("<html><center>Sake<br>$8.00");
        JButton greenTea = new JButton("<html><center>Green Tea<br>$2.00");
        JButton water = new JButton("<html><center>Water<br>$0.00");
        JButton coke = new JButton("<html><center>Coke<br>$3.00");
        JButton sprite = new JButton("<html><center>Sprite<br>$3.00");
        JButton cancel = new JButton("<html><center>Leave Drinks List");

        sake.setFont(btnFont);
        greenTea.setFont(btnFont);
        water.setFont(btnFont);
        coke.setFont(btnFont);
        sprite.setFont(btnFont);
        cancel.setFont(btnFont);

        buttonPanel.add(sake);
        buttonPanel.add(greenTea);
        buttonPanel.add(water);
        buttonPanel.add(coke);
        buttonPanel.add(sprite);
        buttonPanel.add(cancel);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        sake.addActionListener(e ->
        {
            items.add(new Drink("sake"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            getDrinkSize();
        });

        greenTea.addActionListener(e->
        {
            items.add(new Drink("green tea"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            getDrinkSize();
        });

        water.addActionListener(e->
        {
            items.add(new Drink("water"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            orderDrink();
        });

        coke.addActionListener(e ->
        {
            items.add(new Drink("coke"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            getDrinkSize();
        });

        sprite.addActionListener(e->
        {
            items.add(new Drink("sprite"));
            order.addMenuItem(items.get(items.size() - 1));
            this.getContentPane().removeAll();
            getDrinkSize();
        });

        cancel.addActionListener(e->
        {
            displayOptions();
        });
    }

    private void getDrinkSize() {
        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Select Size of Drink");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton small = new JButton("<html><center>Small<br>$1.00");
        JButton medium = new JButton("<html><center>Medium<br>$2.00");
        JButton large = new JButton("<html><center>Large<br>$3.00");

        small.setFont(btnFont);
        medium.setFont(btnFont);
        large.setFont(btnFont);

        buttonPanel.add(small);
        buttonPanel.add(medium);
        buttonPanel.add(large);

        // add panels
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        small.addActionListener(e ->
        {
            ((Drink) items.get(items.size() - 1)).addSize(1);
            this.getContentPane().removeAll();
            orderDrink();
        });

        medium.addActionListener(e->
        {
            ((Drink) items.get(items.size() - 1)).addSize(2);
            this.getContentPane().removeAll();
            orderDrink();
        });

        large.addActionListener(e->
        {
            ((Drink) items.get(items.size() - 1)).addSize(3);
            this.getContentPane().removeAll();
            orderDrink();
        });
    }

    // Shows the users order and asks them to confirm
    private void pay() {
        StringBuilder everything = new StringBuilder();
        LocalDate today = LocalDate.now();
        StringBuilder ramenName = new StringBuilder();
        StringBuilder toppingName = new StringBuilder();
        int counter = 0;
        StringBuilder drinkName = new StringBuilder();

        System.out.printf("====================================================\n");

        for (MenuItem item : order.getOrderList()) {
            if (item instanceof Ramen) {
                ramenName.append(String.format("%-43s $%.2f\n", item + " (" + ((Ramen) item).getSize() + ")", ((Ramen) item).getBasePrice()));
                everything.append(ramenName);

                if (((Ramen) item).getToppingsList().isEmpty()) {
                    everything.append(" -No Toppings Added\n");
                } else {
                    for (String topping : ((Ramen) item).getToppingsList()) {
                        toppingName.append(String.format(" - %-40s $%.2f\n", topping, ((Ramen) item).getToppingPrice(counter)));
                        everything.append(toppingName);
                        counter++;
                        toppingName.setLength(0);
                    }
                }

            } else if (item instanceof Appetizer) {
                everything.append(String.format("1x %-40s $%.2f\n", item, item.getPrice()));
            } else if (item instanceof Drink) {
                drinkName.append(String.format("1x %-40s $%.2f\n", item + " (" + ((Drink) item).getSize() + ")", ((Drink) item).getPrice()));
                everything.append(drinkName);
            }
            counter = 0;
            ramenName.setLength(0);
            toppingName.setLength(0);
        }
        if (today.getDayOfWeek() == DayOfWeek.FRIDAY) {
            everything.append(String.format(("\n\nFriday Discount Total: " + order.getOrderTotal().doubleValue())));
        } else {
            everything.append(String.format(("\n\nTotal: " + order.getOrderTotal().doubleValue())));
        }

        // main panel everything is on
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // redraw title panel
        JLabel title = new JLabel("Your Order");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // create and add buttons to panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 0, 0));

        Font btnFont = new Font("MS UI Gothic", Font.BOLD, 40);

        JButton confirm = new JButton("<html><center>Confirm");
        JButton cancel = new JButton("<html><center>Cancel Order");

        confirm.setFont(btnFont);
        cancel.setFont(btnFont);

        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        // create and add label for order
        JLabel orderLabel = new JLabel(String.format(String.valueOf(everything)));
        orderLabel.setForeground(Color.BLACK);
        orderLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 50));
        orderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // add panels
        mainPanel.add(orderLabel, BorderLayout.CENTER);
        mainPanel.add(titlePanel,BorderLayout.NORTH);
        mainPanel.add(Box.createVerticalStrut(10));   // spacing
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        this.getContentPane().removeAll();
        this.add(mainPanel);
        this.revalidate();
        this.repaint();

        confirm.addActionListener(e ->
        {
            fileManager.printReceipt(order);
            items.clear();
            welcomeScreen();
        });

        cancel.addActionListener(e ->
        {
            items.clear();
            welcomeScreen();
        });

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