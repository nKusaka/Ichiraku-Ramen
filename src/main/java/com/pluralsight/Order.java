package com.pluralsight;

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

    // Method will get the first item in the list and return it
    public MenuItem getFirst() {
        return orderList.get(0);
    }

    // Method will get the last item in the list and return it
    public MenuItem getLast() {
        return orderList.get(orderList.size() - 1);
    }

    // Method adds multiple items from a list of menu items to the orderList
    public void addMenuItem(List<MenuItem> menuItems) {
        orderList.addAll(menuItems);
    }

    public BigDecimal getOrderTotal(BigDecimal tip) {
        BigDecimal orderTotal = BigDecimal.ZERO;

        for (MenuItem mI : orderList) {
            orderTotal = orderTotal.add(mI.getPrice());
        }

        return orderTotal.multiply(BigDecimal.valueOf(1.07).add(tip));
    }
}
