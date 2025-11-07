package com.pluralsight;
import java.math.*;
import java.util.*;
import java.util.stream.*;

/* This class creates the ramen item for the menu which gives the user many different options to
customize their ramen as well as being able to select the type of ramen they would like
List of ramens: (Tonkotsu, Red Garlic Tonkotsu, Black Garlic Tonkotsu, Miso Tonkotsu)
List of toppings: (Extra noodles, Chashu, Soft boiled egg, Whole fried garlic, Bamboo shoots)
 */

public class Ramen {

    // Instantiate class variables
    String ramenType;
    BigDecimal ramenPrice;
    BigDecimal total;
    List<String> toppingsList;
    BigDecimal toppingsPrice;

    // Constructor takes a string parameter for the type of ramen and then based on the
    // type of ramen sets the price
    public Ramen(String ramenType) {
        this.ramenType = ramenType;
        toppingsList = new ArrayList<>();
        total = BigDecimal.ZERO;
        toppingsPrice = BigDecimal.ZERO;

        if(ramenType.toLowerCase().trim().contains("tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(12);
        }

        if(ramenType.toLowerCase().trim().contains("red garlic tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(14);
        }

        if(ramenType.toLowerCase().trim().contains("black garlic tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(16);
        }

        if(ramenType.toLowerCase().trim().contains("miso tonkotsu")) {
            ramenPrice = BigDecimal.valueOf(12);
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
        for(Integer t: multipleToppings) {
            switch(t) {
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

    // Gets the value of the ramen with the added toppings if any
    public BigDecimal getPrice() {
        return total.add(toppingsPrice).add(ramenPrice);
    }
}
