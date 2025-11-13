package com.pluralsight.models;
import java.math.*;
import java.util.*;

/* This class creates the order for the user using array lists to contain each type of item
Ramen,Appetizer,Drink
Overloaded methods are included just in case the user has come with a party of more than 1 person
 */
public class Order {

    // Instantiate class variables
    private List<MenuItem> orderList;

    // Constructor initializes each array list
    public Order() {
        orderList = new ArrayList<>();
    }

    // Method adds a menu item to the orderList arraylist
    public void addMenuItem(MenuItem menuItem) {
        orderList.add(menuItem);
    }

    // Method calculates the total cost of the order and returns it
    public BigDecimal getOrderTotal() {
        BigDecimal orderTotal = BigDecimal.ZERO;

        for (MenuItem mI : orderList) {
            orderTotal = orderTotal.add(mI.getPrice());
        }

        return orderTotal;
    }

    // Getters and Setters
    public List<MenuItem> getOrderList() {
        return orderList;
    }


}
