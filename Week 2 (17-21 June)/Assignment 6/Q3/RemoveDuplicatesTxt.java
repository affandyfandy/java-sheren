/* Remove lines which is duplicated data by key field
a) Input: file data (.csv or .txt) and position key field for txt or key field name for csv
b) Output: write to new file with no duplication by key field
Clue: Using Stream with IO */

// REVISION: We can combine multiple fields to form a unique key for each line by specifying both a start and end index

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class RemoveDuplicatesTxt {
    public static void main(String[] args) throws IOException {
        String inputFile = "Q3/txt/input.txt"; // Path of input file
        String outputFile = "Q3/txt/output.txt"; // Path of output file
        int keyFieldStartIndex = 0; 
        int keyFieldEndIndex = 1; 
        String fileType = "txt";

        try {
            if (fileType.equalsIgnoreCase("txt")) {
                removeDuplicatesFromTXT(inputFile, outputFile, keyFieldStartIndex, keyFieldEndIndex);
            } else {
                System.out.println("Invalid file type. Supported type is 'txt'.");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void removeDuplicatesFromTXT(String inputFile, String outputFile, int keyFieldStartIndex, int keyFieldEndIndex) throws IOException {
        // Use Files.lines to read from the input file as a stream of strings
        try (Stream<String> lines = Files.lines(Paths.get(inputFile));
            // Creates a BufferedWriter to write to the output file
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            // Use LinkedHashSet to maintain order and ensure uniqueness based on the key field
            Set<String> seenKeys = new LinkedHashSet<>();
            List<String> unique = lines.filter(
                line -> {
                    String[] columns = line.split("\\s+");
                    if (columns.length > keyFieldEndIndex) {
                        String key = String.join(" ", Arrays.copyOfRange(columns, keyFieldStartIndex, keyFieldEndIndex + 1));
                        return seenKeys.add(key);
                    } else {
                        throw new IllegalArgumentException("Invalid key field range: " + keyFieldStartIndex + "-" + keyFieldEndIndex + " in line: " + line);
                    }
            }).collect(Collectors.toList());
            

            for (String line : unique) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
