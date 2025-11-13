package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// File manager to save orders as receipts in receipts.txt
public class FileManager {
    private String fileName = "receipts.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd                                  HH:mm:ss");
    int counter = 1;

    // Method saves the orders to a receipt
    public void printReceipt(Order order) {

        LocalDate today = LocalDate.now();
        StringBuilder ramenName = new StringBuilder();
        StringBuilder toppingName = new StringBuilder();
        int counter = 0;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(String.format("                  Ichiraku Ramen\n                 123 Ramen Street\n\n"));
            bufferedWriter.write(String.format("%s \n", LocalDateTime.now().format(formatter)));
            bufferedWriter.write("====================================================\n");

            for (MenuItem item : order.getOrderList()) {
                if (item instanceof Ramen) {
                    ramenName.append(String.format("%-43s $%.2f\n", item + " (" + ((Ramen) item).getSize() + ")", ((Ramen) item).getBasePrice()));
                    bufferedWriter.write(String.valueOf(ramenName));

                    if (((Ramen) item).getToppingsList().isEmpty()) {
                        bufferedWriter.write(" -No Toppings Added\n");
                    } else {
                        for (String topping : ((Ramen) item).getToppingsList()) {
                            toppingName.append(String.format(" - %-40s $%.2f\n", topping, ((Ramen) item).getToppingPrice(counter)));
                            bufferedWriter.write(String.valueOf(toppingName));
                            counter++;
                            toppingName.setLength(0);
                        }
                    }

                } else if (item instanceof Appetizer) {
                    bufferedWriter.write(String.format("1x %-40s $%.2f\n", item, item.getPrice()));
                } else if (item instanceof Drink) {
                    bufferedWriter.write(String.format("1x %-40s $%.2f\n", item, item.getPrice()));
                }
                counter = 0;
                ramenName.setLength(0);
                toppingName.setLength(0);
            }
            if (today.getDayOfWeek() == DayOfWeek.FRIDAY) {
                bufferedWriter.write(String.format("\n\nFriday Discount Total: $%.2f\n\nHave a great day!\n", order.getOrderTotal().doubleValue()));
            } else {
                bufferedWriter.write(String.format("\n\nTotal: $%.2f\n\nHave a great day!\n", order.getOrderTotal().doubleValue()));
            }

        } catch (IOException e) {
            System.out.println("Issue writing to file: " + e.getMessage());
        }
        counter++;
    }
}
