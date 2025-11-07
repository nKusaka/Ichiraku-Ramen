package com.pluralsight;

import java.math.*;
import java.time.*;

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

    @Override
    public BigDecimal applyDiscount(BigDecimal total) {
        return total.multiply(BigDecimal.valueOf(0.5));
    }

    public BigDecimal getPrice() {
        LocalDate today = LocalDate.now();
        if (today.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return applyDiscount(price);
        } else {
            return price;
        }
    }
}
