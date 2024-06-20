/* See assignment 5.5, add employee to HashSet.
How can it recognize that 2 employee has duplicated employee ID? Implement it. */

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;
    private String department;

    // Constructor, getters, and setters

    public Employee(int employeeID, String name, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    // Ensures that two Employee objects are considered equal if they have the same employeeID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID;
    }

    // Generate a hash code based on the employeeID
    @Override
    public int hashCode() {
        return Objects.hash(employeeID);
    }
}

public class DuplicateEmployee {
    public static void main(String[] args) {
        /* Creates a HashSet named employees to store Employee objects. Handles duplicate
        entries by calling equals() and hashCode() methods of the objects it contains */
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(3, "Lisa", "Finance"));
        employees.add(new Employee(1, "Luna", "IT"));
        employees.add(new Employee(2, "Ray", "HR"));
        employees.add(new Employee(1, "Jun", "Marketing"));

        // Convert the set to a sorted list by employeeID
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getEmployeeID))
                .collect(Collectors.toList());

        // Print the sorted list
        sortedEmployees.forEach(employee -> 
                System.out.println("Sorted Employee: " + employee));
    }
}

/*
 * Output:
 * Sorted Employee: Employee{employeeID=1, name='Luna', department='IT'}
 * Sorted Employee: Employee{employeeID=2, name='Ray', department='HR'}
 * Sorted Employee: Employee{employeeID=3, name='Lisa', department='Finance'}
 */



