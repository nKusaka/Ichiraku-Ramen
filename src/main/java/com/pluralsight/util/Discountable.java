package com.pluralsight.util;
import java.math.*;

// Interface allows items to be discountable
public interface Discountable {
    BigDecimal applyDiscount(BigDecimal total);
}
