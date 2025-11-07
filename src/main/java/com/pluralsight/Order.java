package com.pluralsight;
import java.util.*;

/* This class creates the order for the user using array lists to contain each type of item
Ramen,Appetizer,Drink
Overloaded methods are included just in case the user has come with a party of more than 1 person
 */
public class Order {

    // Instantiate class variables
    private List<Ramen> ramenList;
    private List<Appetizer> appetizersList;
    private List<Drink> drinksList;

    // Constructor initializes each array list
    public Order() {
        ramenList = new ArrayList<>();
        appetizersList = new ArrayList<>();
        drinksList = new ArrayList<>();
    }

    /* Add methods that do not use a list type append the type(ramen,appetizer,drink) to the end of each
    of their respective lists
     */
    public void addRamen(Ramen ramen) {
        ramenList.add(ramen);
    }

    public void addAppetizers(Appetizer appetizer) {
        appetizersList.add(appetizer);
    }

    public void addDrinks(Drink drink) {
        drinksList.add(drink);
    }

    /* These list add methods use the built-in method addAll to add bulk orders to
    each order list
     */
    public void addRamen(List<Ramen> ramenList) {
        this.ramenList.addAll(ramenList);
    }

    public void addAppetizers(List<Appetizer> appetizers) {
        this.appetizersList.addAll(appetizers);
    }

    public void addDrinks(List<Drink> drinks) {
        this.drinksList.addAll(drinks);
    }
}
