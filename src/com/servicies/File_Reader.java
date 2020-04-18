package com.servicies;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.entities.*;
import com.users.*;

public class File_Reader {
    public static <T> void readFromFile(Class tClass, List<T> list, String fileName) throws IOException {
        try {
            InputStreamReader inputStreamReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String currentLine;
            currentLine = bufferedReader.readLine();
            String[] fields = currentLine.split(",");
            T newObject;

            //if(T ii)

            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println("Read line: " + currentLine);
            }
            //not closed in case of exception
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void readUsers() {

    }

    public static <T> void writeToFile(List<T> objects, Class tClass, String fileName) {

        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter = null;
        try {
            outputStreamWriter = new FileWriter(fileName);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(toCSV(objects, tClass));
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    System.out.println("Could not even close: " + e.getMessage());
                }
            }
        }
    }

    public static <T> String toCSV(List<T> objects, Class tClass) {
        StringBuilder csvData = new StringBuilder();

        ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(tClass.getDeclaredFields()));

        if (tClass.getSuperclass() != null) {
            var list = Arrays.asList(tClass.getSuperclass().getDeclaredFields());
            //fields=new ArrayList<Field>(fields,;
            fields.addAll(list);
        }
        for (var field : fields) {
            csvData.append(field.getName());
            csvData.append(",");
        }
        csvData.setCharAt(csvData.length() - 1, '\n');
        for (var object : objects) {
            //StringBuilder line=new StringBuilder();
            for (var field : fields) {
                field.setAccessible(true);

                try {
                    Object value = field.get(object);
                    csvData.append(value.toString());
                    csvData.append(",");
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                }

            }
            csvData.append("\n");
        }
        return csvData.toString();
    }
}
