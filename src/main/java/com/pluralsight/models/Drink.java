package com.pluralsight.models;

import java.math.*;

public class Drink extends MenuItem {

    // Instantiate class variables
    private String size;

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

    // Add size of drinks
    public void addSize(int sizeChoice) {
        switch (sizeChoice) {
            case 1:
                size = "small";
                price = price.add(BigDecimal.valueOf(1));
                break;
            case 2:
                size = "medium";
                price = price.add(BigDecimal.valueOf(2));
                break;
            case 3:
                size = "large";
                price = price.add(BigDecimal.valueOf(3));
                break;
            case 4:
                size = "small";
                break;
            default:
                break;
        }
    }

    // Get size of drink
    public String getSize() {
        return size;
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
