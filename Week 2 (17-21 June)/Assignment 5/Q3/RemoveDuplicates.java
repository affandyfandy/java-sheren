package Q3;

/* Remove lines which is duplicated data by 1 key field
Input: file data (.txt) and position key field for txt
Output: write to new file with no duplication by key field */

import java.io.*;
import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        String inputFile = "Q3/txt/input.txt"; // Path of input file
        String outputFile = "Q3/txt/output.txt"; // Path of output file
        String keyField = "0"; // Key field index
        String fileType = "txt";

        try {
            if (fileType.equalsIgnoreCase("txt")) {
                int keyFieldIndex = Integer.parseInt(keyField); // Convert key field index to an integer
                removeDuplicatesFromTXT(inputFile, outputFile, keyFieldIndex);
            } else {
                System.out.println("Invalid file type. Supported type is 'txt'.");
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void removeDuplicatesFromTXT(String inputFile, String outputFile, int keyFieldIndex) throws IOException {
        BufferedWriter writer;
        // Use a BufferedReader to read from the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            writer = new BufferedWriter(new FileWriter(outputFile)); // Create a BufferedWriter to write to the output file
            Set<String> seenKeys = new HashSet<>(); // Create a set to store unique keys
            String line;
            while ((line = reader.readLine()) != null) {
                // To split the line into columns using whitespace as delimiter
                String[] columns = line.split("\\s+");
                // Get the key value from the specified key field index
                String key = columns[keyFieldIndex];
                if (seenKeys.add(key)) {
                    // If the key is unique, write the line to the output file
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
        writer.close();
    }
}
