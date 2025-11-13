package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// File manager to save orders as receipts in receipts.txt
public class FileManager {
    private String fileName = "receipts.txt";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
    int counter = 1;
    // Method saves the orders to a receipt
    public void printReceipt(Order order) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(String.format("                 Ichiraku Ramen\n                123 Ramen Street\n"));

            for (MenuItem item: order.getOrderList()) {
                if (item instanceof Ramen) {
                    bufferedWriter.write(String.format("%s", item));
                } else if (item instanceof Appetizer) {
                    bufferedWriter.write(String.format("1x %-40s $%.2f\n", item, item.getPrice()));
                } else if (item instanceof Drink) {
                    bufferedWriter.write(String.format("1x %-40s $%.2f\n", item, item.getPrice()));
                }
            }

            bufferedWriter.write(String.format("\n\nTotal: $%.2f\n\nHave a great day!", order.getOrderTotal().doubleValue()));

        } catch (IOException e) {
            System.out.println("Issue writing to file: " + e.getMessage());
        }
        counter ++;
    }
}
