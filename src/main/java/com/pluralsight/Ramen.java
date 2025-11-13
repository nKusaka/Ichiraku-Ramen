package com.pluralsight;
import java.math.*;
import java.time.*;
import java.util.*;

/* This class creates the ramen item for the menu which gives the user many different options to
customize their ramen as well as being able to select the type of ramen they would like
List of ramens: (Tonkotsu, Red Garlic Tonkotsu, Black Garlic Tonkotsu, Miso Tonkotsu)
List of toppings: (Extra noodles, Chashu, Soft boiled egg, Whole fried garlic, Bamboo shoots)
 */

public class Ramen extends MenuItem implements Discountable {

    // Instantiate class variables
    private BigDecimal ramenPrice;
    private List<String> toppingsList;
    private BigDecimal toppingsPrice;
    private String size;

    // Constructor takes a string parameter for the type of ramen and then based on the
    // type of ramen sets the price
    public Ramen(String ramenType) {
        super(ramenType);
        toppingsList = new ArrayList<>();
        toppingsPrice = BigDecimal.ZERO;
        ramenPrice = BigDecimal.ZERO;
        size = "small";

        if (ramenType.toLowerCase().trim().contains("tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(12);
        }

        if (ramenType.toLowerCase().trim().contains("red garlic tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(14);
        }

        if (ramenType.toLowerCase().trim().contains("black garlic tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(16);
        }

        if (ramenType.toLowerCase().trim().contains("miso tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(12);
        }
    }

    // Method selects the size of the ramen bowl and calculates the price
    public void addRamenSize(Integer sizeChoice) {
        switch (sizeChoice) {
            case 1:
                size = "small";
                ramenPrice = ramenPrice.add(BigDecimal.valueOf(2));
                break;
            case 2:
                size = "medium";
                ramenPrice = ramenPrice.add(BigDecimal.valueOf(3));
                break;
            case 3:
                size = "large";
                ramenPrice = ramenPrice.add(BigDecimal.valueOf(4));
                break;
            default:
                break;
        }
    }

    // Method adds toppings to the ramen, uses a for loop to check if the users topping is in the list of toppings
    // if the users topping is in the list it will be added to the list of toppings going into the ramen
    public void addToppings(int topping) {
        switch (topping) {
            case 1:
                toppingsList.add("extra noodles");
                toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(4));
                break;
            case 2:
                toppingsList.add("chashu 2 pieces");
                toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(1.50));
                break;
            case 3:
                toppingsList.add("soft boiled egg");
                toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(1));
                break;
            case 4:
                toppingsList.add("whole fried garlic");
                toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(2.50));
                break;
            case 5:
                toppingsList.add("bamboo shoots");
                toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(3));
                break;
            default:
                break;
        }
    }

    // Overloaded method to add multiple toppings to the list at the same time instead of 1 by 1
    public void addToppings(List<Integer> multipleToppings) {
        for (Integer t : multipleToppings) {
            switch (t) {
                case 1:
                    toppingsList.add("extra noodles");
                    toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(4));
                    break;
                case 2:
                    toppingsList.add("chashu 2 pieces");
                    toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(1.50));
                    break;
                case 3:
                    toppingsList.add("soft boiled egg");
                    toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(1));
                    break;
                case 4:
                    toppingsList.add("whole fried garlic");
                    toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(2.50));
                    break;
                case 5:
                    toppingsList.add("bamboo shoots");
                    toppingsPrice = toppingsPrice.add(BigDecimal.valueOf(3));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.5));
    }

    // Gets the value of the ramen with the added toppings if any and also applies a 50% discount if it is Friday
    public BigDecimal getPrice() {

        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return applyDiscount(price.add(toppingsPrice).add(ramenPrice));
        } else {
            return price.add(toppingsPrice).add(ramenPrice);
        }
    }

    // Gets the value of just the ramen type and bowl price
    public BigDecimal getBasePrice() {
        return ramenPrice;
    }

    // Get the value of each topping
    public BigDecimal getToppingPrice(int counter) {
        String topping;
        for (int i = counter; i < getToppingsList().size(); i++) {
            switch (toppingsList.get(i)) {
                case "extra noodles":
                    return BigDecimal.valueOf(4);
                case "chashu 2 pieces":
                    return BigDecimal.valueOf(1.50);
                case "soft boiled egg":
                    return BigDecimal.valueOf(1);
                case "whole fried garlic":
                    return BigDecimal.valueOf(2.50);
                case "bamboo shoots":
                    return BigDecimal.valueOf(3);
            }
        }
        return BigDecimal.valueOf(0);
    }

    // Getters and Setters
    public List<String> getToppingsList() {
        return toppingsList;
    }

    public void setToppingsList(List<String> toppingsList) {
        this.toppingsList = toppingsList;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Overrided toString to output the type of ramen with bowl size and toppings
    @Override
    public String toString() {
        return name;
    }
}
