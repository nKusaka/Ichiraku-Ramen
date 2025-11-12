package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// File manager to save orders as receipts in receipts.txt
public class FileManager {
    private String fileName = "receipt.csv";
    private boolean header = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");

    // Method saves the orders to a receipt
    public void saveToFile(List<Order> orders) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            int counter = 1;
            if (!header) {
                bufferedWriter.write("order #|order total|date|time\n");
                header = true;
            }
            for (Order order : orders) {
                    bufferedWriter.write(counter + "|" + order.toCSV() + "|" + LocalDateTime.now().format(formatter) + "\n");
            }

        } catch (IOException e) {
            System.out.println("Issue writing to file");
        }
    }
}
