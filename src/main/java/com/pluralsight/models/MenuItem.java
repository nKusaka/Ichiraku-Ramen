package com.pluralsight.models;
import java.math.*;

// This abstract class is used as a template for all menu items. It gives them a name and a price
public abstract class MenuItem {

    // Instantiate class variables
    protected String name;
    protected BigDecimal price;

    // Constructor for abstract class
    public MenuItem(String name) {
        this.name = name;
        price = BigDecimal.ZERO;
    }

    // Abstract method where all classes that implement must have a getPrice method
    public abstract BigDecimal getPrice();
}
