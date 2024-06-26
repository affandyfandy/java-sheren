// Remove duplicated items for any object and any duplicated fields

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;

    public Employee(int employeeID, String name) {
        this.employeeID = employeeID;
        this.name = name;
    }

    // Getter and setter
    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                '}';
    }
}

public class RemoveDuplicatesGeneric {
    // Allows method to work with any type of T and key type K
    public static <T, K> List<T> removeDuplicates(List<T> items, Function<? super T, ? extends K> keyExtractor) {
        return items.stream()
                        .collect(Collectors.toMap( // Collects item into a map
                            keyExtractor, // Provides key for each item
                            item -> item, // Uses item itself as value in the map
                            (existing, replacement) -> existing, // If duplicate key is found, keeps the existing item and discards the new one
                            LinkedHashMap::new))  // Use LinkedHashMap to maintain insertion order
                        .values() // Gets collection of unique items from the map
                        .stream() // COnvert collection back to stream
                        .collect(Collectors.toList()); // Collect to list
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Lisa"),
            new Employee(2, "Luna"),
            new Employee(3, "Ray"),
            new Employee(4, "Bila"),
            new Employee(5, "Ray")
        );

        // Remove duplicates by employeeID
        List<Employee> uniqueId = removeDuplicates(employees, Employee::getEmployeeID); // Ensure uniqueness based on employeeID
        System.out.println("Unique employees by employeeID: ");
        uniqueId.forEach(System.out::println);

        // Remove duplicates by name
        List<Employee> uniqueName = removeDuplicates(employees, Employee::getName); // Ensure uniqueness based on name
        System.out.println("\nUnique employees by name:");
        uniqueName.forEach(System.out::println);
    }
}

/* Output:
 * Unique employees by employeeID: 
 * Employee{employeeID=1, name='Lisa'}
 * Employee{employeeID=2, name='Luna'}
 * Employee{employeeID=3, name='Ray'}
 * Employee{employeeID=4, name='Bila'}
 * Employee{employeeID=5, name='Ray'}
 * 
 * Unique employees by name:
 * Employee{employeeID=1, name='Lisa'}
 * Employee{employeeID=2, name='Luna'}
 * Employee{employeeID=3, name='Ray'}
 * Employee{employeeID=4, name='Bila'}
 */