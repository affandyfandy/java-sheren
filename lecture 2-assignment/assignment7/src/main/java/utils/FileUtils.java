package utils;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import model.Employee;

public class FileUtils {
    public static List<Employee> readCsvManual(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.remove(0);
            for (String line : lines) {
                String[] data = line.split(",");
                Employee employee = Employee.dataCSV(data);
                employees.add(employee);
            }
        } catch (IOException e) {
            System.err.println("Error parsing line.");
        }
        return employees;
    }

    public static List<Employee> readCsvOpenCsv(String filePath) throws IOException, CsvValidationException {
        List<Employee> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader (new FileReader(filePath))) {
            reader.readNext();
            String[] lines;
            while ((lines = reader.readNext()) != null) {
                Employee employee = Employee.dataCSV(lines);
                employees.add(employee);
            }
        }
        return employees;
    }

    // To add new employee
    public static void writeCsvManual(String filePath, Employee employee) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(employee.toString()).append("\n");
        }
    }

    // To export filtered data into new csv file
    public static void writeCsvManual(String filePath, List<Employee> employees) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Name,DateOfBirth,Address,Department");
            writer.newLine();
            for (Employee employee : employees) {
                writer.write(employee.toCsvString());
                writer.newLine();
            }
        }
    }
}
