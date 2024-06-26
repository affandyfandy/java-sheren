package Q6;

// Convert list employees to map with ID as key

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
}

public class ListEmployeeToMap {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Lisa", "Finance"));
        employees.add(new Employee(1, "Luna", "IT"));
        employees.add(new Employee(2, "Ray", "HR"));

        // Convert List to Map
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getEmployeeID, emp -> emp));

        // Print the sorted map
        employeeMap.forEach((id, employee) -> 
                System.out.println("EmployeeID: " + id + ", Employee: " + employee));
    }
}

/* Output:
 * EmployeeID: 1, Employee: Employee{employeeID=1, name='Luna', department='IT'}
 * EmployeeID: 2, Employee: Employee{employeeID=2, name='Ray', department='HR'}
 * EmployeeID: 3, Employee: Employee{employeeID=3, name='Lisa', department='Finance'}
 */



