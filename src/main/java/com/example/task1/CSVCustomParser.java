package com.example.task1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVCustomParser {
    public static void main(String[] args) {
        String filePath = "src/main/resources/students1.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] values = parseLine(line);
                ArrayList<Integer> avMean = new ArrayList<Integer>();
                for (String value : values) {
                    System.out.print(value + " ");
                     if(value.matches(".*\\d.*")){
                         int number = Integer.parseInt(value);
                         avMean.add(number) ;
                     }
                    System.out.println();
                }
                System.out.println(avMean.stream()
                        .mapToDouble(d -> d)
                        .average()
                        .orElse(0.0));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String[] parseLine(String line) {
        // Handle commas, quotes, etc.
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

}