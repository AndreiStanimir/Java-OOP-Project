package com.servicies;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.entities.*;
import com.users.*;

import java.util.function.Consumer;

public class FileService {
    public static <T> void readFromFile(List<T> list, Class tClass, String fileName) {
        try {
            InputStreamReader inputStreamReader = new java.io.FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String currentLine;
//            currentLine = bufferedReader.readLine();
//            String[] fields = currentLine.split(",");
            T newObject = null;

            if (tClass == Ticket.class) {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] words = currentLine.split(",");
                    Ticket obj = new Ticket(Integer.parseInt(words[0]), LocalDateTime.parse(words[1]), Float.parseFloat(words[2]));
                    list.add((T) obj);
                }
            } else if (tClass == Concert.class) {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] words = currentLine.split(",");
                    Concert obj = new Concert(words[0],
                            Float.parseFloat(words[1]),
                            words[2],
                            LocalDateTime.parse(words[3]),
                            Integer.parseInt(words[4]),
                            Float.parseFloat(words[5]),
                            words[6]);
                    list.add((T) obj);
                }
            } else if (tClass == Movie.class) {
                while ((currentLine = bufferedReader.readLine()) != null) {
                    String[] words = currentLine.split(",");
                    Movie obj = new Movie(words[0],
                            Float.parseFloat(words[1]),
                            words[2],
                            LocalDateTime.parse(words[3]),
                            Integer.parseInt(words[4]),
                            Float.parseFloat(words[5]),
                            words[6]);
                    list.add((T) obj);
                }
            }
//            else if (tClass == Client.class) {
//                while ((currentLine = bufferedReader.readLine()) != null) {
//                    String[] words = currentLine.split(",");
//                    Client obj = new Client(words[0],
//                            words[1],
//                            words[2],
//                            Integer.parseInt(words[3]));
//                    list.add((T) obj);
//                }
                else {
                    throw new Exception("Class write not implemented");
                }
                //not closed in case of exception
                bufferedReader.close();
            } catch(
                    Exception e){
                System.out.println(e);
            }

        }

        public static <T > void readFromFile (HashMap < String, T > hashMap, Class tClass, String fileName){
            try {
                InputStreamReader inputStreamReader = new java.io.FileReader(fileName);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String currentLine;
//            currentLine = bufferedReader.readLine();
//            String[] fields = currentLine.split(",");
                T newObject = null;
                if (tClass == Administrator.class) {
                    while ((currentLine = bufferedReader.readLine()) != null) {
                        String[] words = currentLine.split(",");
                        Administrator obj = new Administrator(words[0],
                                words[1],
                                words[2],
                                Integer.parseInt(words[3]));
                        hashMap.put(words[1] + words[2], (T) new Administrator(words[0], words[1], words[2], Integer.parseInt(words[3])));
                    }
                } else {
                    throw new Exception("Class write not implemented");
                }
                //not closed in case of exception
                bufferedReader.close();
            } catch (
                    Exception e) {
                System.out.println(e);
            }
        }

        public static void readUsers () {

        }

        public static <T > void writeToFile (List < T > objects, Class tClass, String fileName){

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

        public static <T > void writeToFile (T object, Class tClass, String fileName){
            List<T> objects = new ArrayList<>();
            objects.add(object);
            writeToFile(objects, tClass, fileName);
        }

        public static <T > String toCSV(List < T > objects, Class tClass) {
            StringBuilder csvData = new StringBuilder();

            ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(tClass.getDeclaredFields()));

            if (tClass.getSuperclass() != null) {
                var list = Arrays.asList(tClass.getSuperclass().getDeclaredFields());
                //fields=new ArrayList<Field>(fields,;
                fields.addAll(list);
            }
//        for (var field : fields) {
//            csvData.append(field.getName());
//            csvData.append(",");
//        }
//        csvData.setCharAt(csvData.length() - 1, '\n');
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

                csvData.setCharAt(csvData.length() - 1, '\n');
            }
            return csvData.toString();
        }

        public static <T > String toCSV(T obj, Class tClass) {
            var l = new ArrayList<T>();
            l.add(obj);
            return toCSV(l, tClass);
        }

        public static <T > void appendToFile (T object, Class tClass, String fileName){
            OutputStreamWriter outputStreamWriter;
            BufferedWriter bufferedWriter = null;
            try {
                outputStreamWriter = new FileWriter(fileName, true);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                bufferedWriter.write(toCSV(object, tClass));
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
    }
