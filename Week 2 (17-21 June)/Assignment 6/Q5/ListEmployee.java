package Q5;

/* Input: list of employees (ex: Employee (ID, name, age, salary,..))
a) Sort name in alphabetical, ascending using streams
b) Find employee has max salary using streams
c) Check any employee names match with specific keywords or not
 */

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int employeeID;
    private String name;
    private int age;
    private double salary;

    public Employee(int employeeID, String name, int age, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.age = age;
        this.salary = salary;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}

public class ListEmployee {
    public static void main(String[] args) {
        List <Employee> employees = Arrays.asList(
            new Employee(1, "Lisa", 21, 3000000),
            new Employee(2, "Luna", 23, 5000000),
            new Employee(3, "Ray", 27, 2500000),
            new Employee(4, "Bila", 25, 4500000)
        );

        // Sort name in alphabetical ascending using streams
        List <Employee> sortedEmployees = employees.stream()
                                                .sorted(Comparator.comparing(Employee::getName))
                                                .collect(Collectors.toList());
        
        sortedEmployees.forEach(employee -> 
            System.out.println("Sorted Employee: " + employee));
        
        // Find employee who has max salary using streams
        employees.stream()
        .max(Comparator.comparing(Employee::getSalary))
        .ifPresent(employee -> 
            System.out.println("Employee with max salary: " + employee));
        
        // Check any employee names match with specific keywords or not
        String keyword = "Ray";
        boolean match = employees.stream()
                                .anyMatch(employee -> employee.getName().equals(keyword));

        System.out.println("Is there any employee match with '" + keyword + "'? " + match); 
    }     
}

/* Output:
 * Sorted Employee: Employee{employeeID=4, name='Bila', age=25, salary=4500000.0}
 * Sorted Employee: Employee{employeeID=1, name='Lisa', age=21, salary=3000000.0}
 * Sorted Employee: Employee{employeeID=2, name='Luna', age=23, salary=5000000.0}
 * Sorted Employee: Employee{employeeID=3, name='Ray', age=27, salary=2500000.0}
 * Employee with max salary: Employee{employeeID=2, name='Luna', age=23, salary=5000000.0}
 * Is there any employee match with 'Ray'? true
 */
