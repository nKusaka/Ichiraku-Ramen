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
    private String fileName = "receipts.csv";
    private boolean header = false;
    private int counter = 1;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

    // Method saves the orders to a receipt
    public void saveToFile(List<Order> orders) {
        File file = new File(fileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            // Write header only if file is new
            if (!header) {
                bufferedWriter.write("order #|order total|date|time\n");
                header = true;
            }

            for (Order order: orders) {
                if (orders.indexOf(order) >= counter - 1) {
                    bufferedWriter.write(counter + "|" + orders.get(counter - 1).toCSV() + "|" +
                            LocalDateTime.now().format(formatter) + "\n");
                    counter++;
                }
            }
        } catch (IOException e) {
            System.out.println("Issue writing to file: " + e.getMessage());
        }
    }
}
