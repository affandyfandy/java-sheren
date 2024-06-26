package Q3;

/* Input: list of any object
a) Sort by any field
b) Find item has max value of any field */

import java.util.*;
import java.util.function.Function;
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

    // Getter and setter methods
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

public class SortedMaxGeneric {
    // Generic method to sort by any field
    public static <T, U extends Comparable<? super U>> List<T> sortByField(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream()
                   .sorted(Comparator.comparing(keyExtractor))
                   .collect(Collectors.toList());
    }

    // Generic method to find the item with the maximum value of any field
    public static <T, U extends Comparable<? super U>> Optional<T> maxByField(List<T> list, Function<? super T, ? extends U> keyExtractor) {
        return list.stream()
                   .max(Comparator.comparing(keyExtractor));
    }

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Lisa", 21, 3000000),
            new Employee(2, "Luna", 23, 5000000),
            new Employee(3, "Ray", 27, 2500000),
            new Employee(4, "Bila", 25, 4500000)
        );

        // Sort by name in alphabetical order
        List<Employee> sortedByName = sortByField(employees, Employee::getName);
        System.out.println("Sorted by name:");
        sortedByName.forEach(employee -> System.out.println(employee));

        // Find employee with maximum salary
        Optional<Employee> maxSalaryEmployee = maxByField(employees, Employee::getSalary);
        maxSalaryEmployee.ifPresent(employee -> System.out.println("Employee with max salary: " + employee));
    }
}

/* Output:
 * Sorted by name:
Employee{employeeID=4, name='Bila', age=25, salary=4500000.0}
Employee{employeeID=1, name='Lisa', age=21, salary=3000000.0}
Employee{employeeID=2, name='Luna', age=23, salary=5000000.0}
Employee{employeeID=3, name='Ray', age=27, salary=2500000.0} 
Employee with max salary: Employee{employeeID=2, name='Luna', age=23, salary=5000000.0}
 */