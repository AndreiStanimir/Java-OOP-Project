package com.servicies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {

    public static void addLogMessage(String message, String inputLogFile) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(inputLogFile, true))) {
            bufferedWriter.write(message + "," + formatter.format(date));
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

    public static void addLogMessage(String message) {
        addLogMessage(message, "logs.txt");
    }

}
