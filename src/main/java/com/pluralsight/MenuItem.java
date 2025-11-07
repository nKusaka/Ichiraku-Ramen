package com.pluralsight;
import java.math.*;

// This abstract class is used as a template for all menu items. It gives them a name and a price
public abstract class MenuItem {

    protected String name;
    protected BigDecimal price;

    public MenuItem(String name) {
        this.name = name;
        price = BigDecimal.ZERO;
    }

    public abstract BigDecimal getPrice();
}
