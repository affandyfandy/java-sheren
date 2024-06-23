import java.util.*;

class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter and setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class Manager extends Employee {
    private String department;

    public Manager(int id, String name, String department) {
        super(id, name);
        this.department = department;
    }
    
    // Getter and setter
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

class Worker extends Employee {
    private int hourlyRate;

    public Worker(int id, String name, int hourlyRate) {
        super(id, name);
        this.hourlyRate = hourlyRate;
    }

    // Getter and setter
    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}

class EmployeeProcessor {
    // Method to process a list of employees or its subclasses. Use extends as we only want to get values
    public void processEmployees(List<? extends Employee> employees) {
        for (Employee emp : employees) {
            // Process each employee (Employee, Manager, Worker)
            System.out.println(emp);
        }
    }

    // Method to add an employee to a list (super keyword) when we want to only put values into a structure
    public void addEmployee(Employee emp, List<? super Employee> employeeList) {
        employeeList.add(emp);
        System.out.println(emp + " added to the list.");
    }
}

public class WildcardDemo {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Reine"));
        employees.add(new Manager(2, "Kei", "IT"));
        employees.add(new Worker(3, "Jena", 15));

        EmployeeProcessor processor = new EmployeeProcessor();

        // Process employees (Employee, Manager, Worker)
        System.out.println("Processing employees:");
        processor.processEmployees(employees);
        System.out.println();

        // Adding new employees using super wildcard
        List<Object> newEmployees = new ArrayList<>();
        Employee newEmp1 = new Employee(4, "Rana");
        Employee newEmp2 = new Worker(5, "Peter", 20);

        processor.addEmployee(newEmp1, newEmployees);
        processor.addEmployee(newEmp2, newEmployees);

        // Process new employees in the list
        System.out.println("\nNew employees:");
        for (Object emp : newEmployees) {
            System.out.println(emp);
        }
    }
}

/* Output:
 * Processing employees:
 * Employee{id=1, name='Reine'}
 * Manager{id=2, name='Kei', department='IT'}
 * Worker{id=3, name='Jena', hourlyRate=15}
 * 
 * Employee{id=4, name='Rana'} added to the list.
 * Worker{id=5, name='Peter', hourlyRate=20} added to the list.
 * 
 * New employees:
 * Employee{id=4, name='Rana'}
 * Worker{id=5, name='Peter', hourlyRate=20}
 */
