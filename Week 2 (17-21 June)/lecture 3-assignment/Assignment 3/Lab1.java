
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Lab1 {
    public static void main(String[] args) {
        String inputFile = "Assignment 3/test.txt";
        String outputFile = "Assignment 3/test2.txt";

        // Use try-with-resources to ensure both resources are closed properly
        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                bw.write(line);
                bw.newLine();
            }
        } catch (FileNotFoundException e) {
            // Handle if the file is not found
            System.err.println("File not found: " + inputFile);
        } catch (IOException e) {
            // Handle any other IOExceptions
            System.err.println("An IOException occured: " + e.getMessage());
        }
    }
}
