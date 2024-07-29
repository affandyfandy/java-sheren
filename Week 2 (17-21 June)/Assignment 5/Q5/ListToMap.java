package Q5;

//Convert List to Map (ex: employee with employeeID as a key and order asc by key)

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

public class ListToMap {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Lisa", "Finance"));
        employees.add(new Employee(1, "Luna", "IT"));
        employees.add(new Employee(2, "Ray", "HR"));

        // Convert List to Map and sort by employeeID
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getEmployeeID, // Key
                        e1 -> e1, // Value
                        (e1, e2) -> e1, // Merge function that specifies what to do in case of duplicate keys 
                        TreeMap::new // Specifies that the resulting map is TreeMap, which maintains natural ordering of keys
                ));

        // Print the sorted map
        employeeMap.forEach((id, employee) -> 
                System.out.println("EmployeeID: " + id + ", Employee: " + employee));
    }
}




