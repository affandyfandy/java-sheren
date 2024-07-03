/* Remove lines which is duplicated data by key field
a) Input: file data (.csv or .txt) and position key field for txt or key field name for csv
b) Output: write to new file with no duplication by key field
Clue: Using Stream with IO */

// REVISION: We can combine multiple fields to form a unique key for each line by specifying both a start and end index

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveDuplicatesTxt {
    public static void main(String[] args) throws IOException {
        String inputFile = "Q3/txt/input.txt"; // Path of input file
        String outputFile = "Q3/txt/output.txt"; // Path of output file
        int keyFieldStartIndex = 0; // Start index of key field
        int keyFieldEndIndex = 2; // End index of key field
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

    /**
     * Removes duplicate lines from a TXT file based on a key field.
     * The key field is specified by a range of column indices.
     *
     * @param inputFile the path to the input file
     * @param outputFile the path to the output file
     * @param keyFieldStartIndex the start index of the key field (inclusive)
     * @param keyFieldEndIndex the end index of the key field (inclusive)
     * @throws IOException if an I/O error occurs
     */

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
                    // Parameter handling to check checks if the keyFieldStartIndex and keyFieldEndIndex are within valid bounds before forming the key
                    if (keyFieldEndIndex >= 0 & keyFieldEndIndex < columns.length && keyFieldStartIndex <= keyFieldEndIndex) {
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
