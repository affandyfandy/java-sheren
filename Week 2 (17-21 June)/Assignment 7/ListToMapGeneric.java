// Convert list any object to map with any key field

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;
    private String department;

    public Employee(int employeeID, String name, String department) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
    }

    // Getter and setter
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

public class ListToMapGeneric {
    // Generic method to convert list to map
    public static <T, K> Map<K, T> listToMap(List<T> list, Function<? super T, ? extends K> keyExtractor) {
        return list.stream()
                   .collect(Collectors.toMap(keyExtractor, Function.identity()));
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(3, "Lisa", "Finance"));
        employees.add(new Employee(1, "Luna", "IT"));
        employees.add(new Employee(2, "Ray", "HR"));

        // Convert list to map using employeeID as key
        Map<Integer, Employee> employeeMapById = listToMap(employees, Employee::getEmployeeID);
        System.out.println("Map with employeeID as key:");
        employeeMapById.forEach((id, employee) -> 
                System.out.println("EmployeeID: " + id + ", Employee: " + employee));

        // Convert list to map using name as key
        Map<String, Employee> employeeMapByName = listToMap(employees, Employee::getName);
        System.out.println("\nMap with name as key:");
        employeeMapByName.forEach((name, employee) -> 
                System.out.println("Name: " + name + ", Employee: " + employee));
    }
}

/* Output:
 * Map with employeeID as key:
 * EmployeeID: 1, Employee: Employee{employeeID=1, name='Luna', department='IT'}
 * EmployeeID: 2, Employee: Employee{employeeID=2, name='Ray', department='HR'}
 * EmployeeID: 3, Employee: Employee{employeeID=3, name='Lisa', department='Finance'}
 *
 * Map with name as key:
 * Name: Luna, Employee: Employee{employeeID=1, name='Luna', department='IT'}
 * Name: Ray, Employee: Employee{employeeID=2, name='Ray', department='HR'}
 * Name: Lisa, Employee: Employee{employeeID=3, name='Lisa', department='Finance'}
 */
