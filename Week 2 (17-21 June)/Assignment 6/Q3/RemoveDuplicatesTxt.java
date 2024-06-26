/* Remove lines which is duplicated data by key field
a) Input: file data (.csv or .txt) and position key field for txt or key field name for csv
b) Output: write to new file with no duplication by key field
Clue: Using Stream with IO */

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class RemoveDuplicatesTxt {
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
        // Use Files.lines to read from the input file as a stream of strings
        try (Stream<String> lines = Files.lines(Paths.get(inputFile));
            // Creates a BufferedWriter to write to the output file
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            // Collect lines into a map to ensure uniqueness based on the key field
            Map<String, String> unique = lines.collect(Collectors.toMap(
                line -> {
                    String[] columns = line.split("\\s+");
                    if (columns.length > keyFieldIndex) {
                        return columns[keyFieldIndex];
                    } else {
                        throw new IllegalArgumentException("Invalid key field index: " + keyFieldIndex + " in line: " + line);
                    }
        
                }, 
                line -> line,
                (existing, replacement) -> existing
            ));

            for (String line : unique.values()) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
