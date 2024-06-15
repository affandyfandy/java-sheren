// SINGLETON

package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.exceptions.CsvValidationException;

import model.Employee;
import utils.DateUtils;
import utils.FileUtils;

public class AppManager {
    private static AppManager instance;
    private List<Employee> employees;
    private final Scanner scanner;
    private boolean useOpenCSV;

    private AppManager() {
        employees = new ArrayList<>();
        scanner = new Scanner(System.in);
        useOpenCSV = false;
    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void start() {
        boolean running = true;
        printHeader();
        while(running) {
            showMenu();
            System.out.println("Select:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 0:
                    System.out.println("\nYour choice was 0");
                    System.out.println("Preparing to exit...");
                    System.out.println("Exiting... \n");
                    break;
                case 1:
                    importData();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    ensureDataLoaded();
                    filterEmployees();
                    break;
                case 4:
                    ensureDataLoaded();
                    List<Employee> filteredEmployees = filterEmployees();
                    exportFilteredEmployees(filteredEmployees);
                    break;
            }
        }
    }

    private void printHeader() {
        System.out.println("Welcome to Employee Management System! \n");
    }

    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("0 - Break");
        System.out.println("1 - Select file and import data");
        System.out.println("2 - Add new employee");
        System.out.println("3 - Filter data");
        System.out.println("4 - Filter and export to csv file");
    }

    private void importData() {
        System.out.println("Choose a CSV reading method:");
        System.out.println("1 - Manual");
        System.out.println("2 - OpenCSV");
        System.out.println("Select an option:");

        int methodChoice = scanner.nextInt();
        scanner.nextLine();

        switch (methodChoice) {
            case 1:
                useOpenCSV = false;
                break;
            case 2:
                useOpenCSV = true;
                break;
        }

        System.out.println("Enter file path:");
        String file = scanner.nextLine();

        try {
            if(useOpenCSV) {
                employees = FileUtils.readCsvOpenCsv(file);
            } else {
                employees = FileUtils.readCsvManual(file);
            }
            System.out.println("Data imported successfully.");
            System.out.println("Imported Employees:");
        } catch (CsvValidationException | IOException e) {
            System.out.println("Error importing data:" + e.getMessage());
        }

        printEmployees(employees);
    }

    private void printEmployees(List<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private void addEmployee() {
        System.out.println("Enter Employee ID:");
        String id = scanner.nextLine();

        System.out.println("Enter Employee Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Date of Birth (d/M/yyyy):");
        String dobInput = scanner.nextLine();
        LocalDate dob = DateUtils.parseDate(dobInput);

        System.out.println("Enter Address:");
        String address = scanner.nextLine();

        System.out.println("Enter Department:");
        String department = scanner.nextLine();

        Employee newEmployee = new Employee(id, name, dob, address, department);
        employees.add(newEmployee);

        System.out.println("Enter file path to save the employee:");
        String filePath = scanner.nextLine();

        try {
            FileUtils.writeCsvManual(filePath, newEmployee);
            System.out.println("Employee added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void ensureDataLoaded() {
        if (employees.isEmpty()) {
            System.out.println("Importing data from ImportData.csv...");
            try {
                employees = FileUtils.readCsvManual("data/ImportData.csv");
                System.out.println("Data imported successfully. \n");
            } catch (Exception e) {
                System.out.println("Error importing data: " + e.getMessage());
            }
        }
    }

    private List<Employee> filterEmployees() {
        System.out.println("Filter employees by:");
        System.out.println("1 - ID");
        System.out.println("2 - Name");
        System.out.println("3 - Date of Birth");
        System.out.println("4 - Department");
        System.out.println("5 - All Employees");

        int filterChoice = scanner.nextInt();
        scanner.nextLine();

        List<Employee> filteredEmployees = new ArrayList<>();
        
        switch (filterChoice) {
            case 1:
                System.out.println("Enter ID pattern:");
                String idPattern = scanner.nextLine();
                for (Employee employee : employees) {
                    if (employee.getId().contains(idPattern)) {
                        filteredEmployees.add(employee);
                    }
                }
                if(filteredEmployees.isEmpty()) {
                    System.out.println("No employees found with ID pattern matching.");
                }
                break;
            case 2:
                System.out.println("Enter name pattern:");
                String namePattern = scanner.nextLine();
                for (Employee employee : employees) {
                    if (employee.getName().toLowerCase().contains(namePattern)) {
                        filteredEmployees.add(employee);
                    }
                }
                if(filteredEmployees.isEmpty()) {
                    System.out.println("No employees found with name pattern matching.");
                }
                break;
            case 3:
                System.out.println("Enter year of birth:");
                int yearOfBirth = scanner.nextInt();
                scanner.nextLine(); 
                for (Employee employee : employees) {
                    if (employee.getDateOfBirth().getYear() == yearOfBirth) {
                        filteredEmployees.add(employee);
                    }
                }
                if(filteredEmployees.isEmpty()) {
                    System.out.println("No employees found with date of birth matching.");
                }
                break;
            case 4:
                System.out.println("Enter department:");
                String department = scanner.nextLine();
                for (Employee employee : employees) {
                    if (employee.getDepartment().equalsIgnoreCase(department)) {
                        filteredEmployees.add(employee);
                    }
                }
                if(filteredEmployees.isEmpty()) {
                    System.out.println("No employees found with department matching.");
                }
                break;
            case 5:
                filteredEmployees.addAll(employees);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    
        printEmployees(filteredEmployees);
        return filteredEmployees;
    }

    private void exportFilteredEmployees(List<Employee> filteredEmployees) {
        if (filteredEmployees.isEmpty()) {
            System.out.println("No employees to export. \n");
            return;
        }
        System.out.println("Enter file path to export the filtered employees:");
        String filePath = scanner.nextLine();

        Collections.sort(filteredEmployees, new Comparator<Employee>() {
            @Override
            public int compare(Employee emp1, Employee emp2) {
                return emp1.getDateOfBirth().compareTo(emp2.getDateOfBirth());
            }
        });

        try {
            FileUtils.writeCsvManual(filePath, filteredEmployees);
            System.out.println("Filtered employees exported successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
