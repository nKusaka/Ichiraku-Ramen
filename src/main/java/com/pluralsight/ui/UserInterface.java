package com.pluralsight.ui;

import com.pluralsight.models.Order;
import com.pluralsight.util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
            //orderAppetizer();
        });
        drinkButton.addActionListener(e -> {
            this.getContentPane().removeAll();
            //orderDrink();
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

        JButton tonkotsu = new JButton("Tonkotsu Ramen");
        JButton redGarlic = new JButton("Red Garlic Tonkotsu Ramen");
        JButton blackGarlic = new JButton("Black Garlic Tonkotsu Ramen");
        JButton miso = new JButton("Miso Ramen");

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

            this.getContentPane().removeAll();
            getToppings();
        });

        redGarlic.addActionListener(e->
        {
            this.getContentPane().removeAll();
            getToppings();
        });

        blackGarlic.addActionListener(e->
        {
            this.getContentPane().removeAll();
            getToppings();
        });

        miso.addActionListener(e ->
        {
            this.getContentPane().removeAll();
            getToppings();
        });
    }

    private void getToppings() {

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