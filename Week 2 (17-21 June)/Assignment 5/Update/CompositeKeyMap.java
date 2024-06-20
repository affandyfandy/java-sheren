/*  Similar (9), create Map of employee with composite key (department, employeeID) */

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

class CompositeKey {
    private String department;
    private int employeeID;

    public CompositeKey(String department, int employeeID) {
        this.department = department;
        this.employeeID = employeeID;
    }

    // Getter and setter
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    // To ensure that two keys are considered equal if both department and employeeID match
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return employeeID == that.employeeID && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, employeeID);
    }

    @Override
    public String toString() {
        return "CompositeKey{" +
        "department='" + department + '\''
        + ", employeeID=" + employeeID +
        '}';
    }
}

public class CompositeKeyMap {
    public static void main(String[] args) {
        // Creates a map with CompositeKey as the key and Employee objects as the value 
        Map<CompositeKey, Employee> employeeMap = new HashMap<>();
        employeeMap.put(new CompositeKey("Finance", 3), new Employee(3, "Lisa", "Finance"));
        employeeMap.put(new CompositeKey("IT", 1), new Employee(1, "Luna", "IT"));
        employeeMap.put(new CompositeKey("HR", 2), new Employee(2, "Ray", "HR"));
        employeeMap.put(new CompositeKey("Marketing", 1), new Employee(1, "Jun", "Marketing"));

        // Convert the map to a sorted list by CompositeKey (department then employeeID)
        List<Map.Entry<CompositeKey, Employee>> sortedEmployees = employeeMap.entrySet().stream()
                .sorted(Comparator.comparing((Map.Entry<CompositeKey, Employee> e) -> e.getKey().getDepartment())
                    .thenComparing(e -> e.getKey().getEmployeeID()))
                .collect(Collectors.toList());

        // Print the sorted list
        sortedEmployees.forEach(entry -> 
                System.out.println("CompositeKey: " + entry.getKey() + ", Employee: " + entry.getValue()));
    }
}

/*
 * Output:
 * CompositeKey: CompositeKey{department='Finance', employeeID=3}, Employee: Employee{employeeID=3, name='Lisa', department='Finance'}
 * CompositeKey: CompositeKey{department='HR', employeeID=2}, Employee: Employee{employeeID=2, name='Ray', department='HR'}
 * CompositeKey: CompositeKey{department='IT', employeeID=1}, Employee: Employee{employeeID=1, name='Luna', department='IT'}
 * CompositeKey: CompositeKey{department='Marketing', employeeID=1}, Employee: Employee{employeeID=1, name='Jun', department='Marketing'}
 */



