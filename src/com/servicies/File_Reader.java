package com.servicies;

import java.io.*;

public class File_Reader {
    public static void readFromFile( String fileName) throws IOException {
        InputStreamReader inputStreamReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String currentLine;
        while ((currentLine = bufferedReader.readLine()) != null) {
            System.out.println("Read line: " + currentLine);
        }
        //not closed in case of exception
        bufferedReader.close();
    }
    public static void readUsers()
    {
        
    }

    public static void writeToFile() {
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter = null;
        try {
            outputStreamWriter = new FileWriter("write.txt");
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("line 1");
            bufferedWriter.newLine();
            bufferedWriter.write("line 2");
            bufferedWriter.newLine();
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
