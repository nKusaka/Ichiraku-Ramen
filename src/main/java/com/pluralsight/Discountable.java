package com.pluralsight;
import java.math.*;

public interface Discountable {
    BigDecimal applyDiscount(BigDecimal total);
}
