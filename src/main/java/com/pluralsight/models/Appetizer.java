package com.pluralsight.models;

import com.pluralsight.util.Discountable;

import java.math.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

public class Appetizer extends MenuItem implements Discountable {

    // Constructor takes in the type of appetizer i.e. the name and then depending on the name sets the price of the app
    public Appetizer(String appetizerType) {
        super(appetizerType);

        if (appetizerType.trim().equalsIgnoreCase("gyoza")) {
            price = BigDecimal.valueOf(8);
        }

        if (appetizerType.trim().equalsIgnoreCase("takoyaki")) {
            price = BigDecimal.valueOf(10);
        }

        if (appetizerType.trim().equalsIgnoreCase("edamame")) {
            price = BigDecimal.valueOf(6);
        }

        if (appetizerType.trim().equalsIgnoreCase("wakame salad")) {
            price = BigDecimal.valueOf(5.50);
        }

        if (appetizerType.equalsIgnoreCase("squid karaage")) {
            price = BigDecimal.valueOf(12);
        }

        if (appetizerType.equalsIgnoreCase("chicken karaage")) {
            price = BigDecimal.valueOf(7);
        }
    }

    // Discount is applied at 50% of the appetizers cost
    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.5));
    }

    // Calculates the price of the item and if it is friday discounts the item by 50%
    public BigDecimal getPrice() {
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return applyDiscount(price);
        } else {
            return price;
        }
    }

    // Returns the base price of the item without discount
    public BigDecimal getBasePrice() {
        return price;
    }

    // toString to help output the Appetizer to the receipt later on
    @Override
    public String toString() {
        return Arrays.stream(name.split(" "))
                .filter(word -> !word.isEmpty())
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
