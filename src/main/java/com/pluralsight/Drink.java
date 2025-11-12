package com.pluralsight;

import java.math.*;

public class Drink extends MenuItem {

    // Instantiate class variables
    private BigDecimal price;

    // Constructor takes in a name for the drink and then depending on the name sets the price for the drink
    public Drink(String drinkType) {
        super(drinkType);

        if (drinkType.trim().equalsIgnoreCase("sake")) {
            price = BigDecimal.valueOf(8);
        } else if (drinkType.trim().equalsIgnoreCase("green tea")) {
            price = BigDecimal.valueOf(2);
        } else if (drinkType.trim().equalsIgnoreCase("water")) {
            price = BigDecimal.ZERO;
        }else {
            price = BigDecimal.valueOf(3);
        }
    }

    // Getter for the price of the drink
    public BigDecimal getPrice() {
        return price;
    }

    // toString to help output the drinks for the receipt
    @Override
    public String toString() {
        return name;
    }
}
