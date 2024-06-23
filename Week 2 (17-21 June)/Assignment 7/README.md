## 💡 Q1: Remove Duplicated Items for Any Object and Any Duplicated Fields

**The question:**

Remove duplicated items for any object and any duplicated fields.

**The codes:**

[Question 1 - Remove Duplicated Items for Any Object and Any Duplicated Fields]()

```java
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
```

**Explanation:**

**Generic method `removeDuplicates`:**

- The method accepts a `List<T>` and a `Function<? super T, ? extends K>` which extracts the key used to determine duplicates
- `Collectors.toMap` is used to collect elements into a `Map` where the key is extracted using `keyExtractor` and the value is the item itself
- `(existing, replacement) -> existing` is a merge function that retains the existing value if a duplicate key is found
- `values()` method of the map retrieves a collection of unique items
- The collection of unique items is then converted back to a list using `Collectors.toList`.

**Main method:**

- **Remove duplicates by `employeeID`**: Calls `removeDuplicates` with `Employee::getEmployeeID` to ensure uniqueness based on the `employeeID`.
- **Remove duplicates by `name`**: Calls `removeDuplicates` with `Employee::getName` to ensure uniqueness based on the `name`

**Output:**

- Each `Employee` object has a unique `employeeID`, so all entries are retained
- The list contains two employees named "Ray". The first occurrence is retained, and the duplicate is removed.

---

## 💡 Q2: Using Wildcards With Generics

Using wildcards with generics in Java allows us to define flexible generic types that can work with various types in a type-safe manner. Wildcards are denoted by the `?` symbol and are used in contexts where we want to accept a family of related types without specifying the exact type.

There are three main types of wildcards in Java generics: `? extends T`, `? super T`, and `?`.

### 💻 **Demo example:**

Consider a class hierarchy involving `Employee` and its subclasses `Manager` and `Worker`. We will create a generic class `EmployeeProcessor` that can process a list of employees and its subclasses.

```java
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

```

[Question 2 - Using Wildcards With Generics]()

**Explanation:**

1. `EmployeeProcessor` contains two methods:
    - `processEmployees`: Uses `? extends Employee` wildcard to accept a list of `Employee` or any subclass (`Manager`, `Worker`). It iterates through the list and prints each employee
    - `addEmployee`: Uses `? super Employee` wildcard to accept a list that can hold `Employee` or any superclass (`Object` in this case). It adds an employee to the list and prints a confirmation message.
    
2. **Demo Class (Main Method):**
- Demonstrates `processEmployees` method to process and print all employees
- Demonstrates `addEmployee` method to add new employees (`Employee` and `Worker`) to a list of `Object` (using `super` wildcard)

### 🔑 **Key Points**

- **Wildcard Types:** `? extends Employee` allows processing of `Employee` and its subclasses; `? super Employee` allows adding `Employee` and its subclasses to a list
- **Flexibility:** Wildcards provide flexibility in handling generic types, enabling methods like `processEmployees` and `addEmployee` to work with diverse types while maintaining type safety.

---

## 💡 Q3: Input List of Any Object

**The question:**

Input: list of any object
a) Sort by any field
b) Find item has max value of any field

**The codes:**

[Question 3 - Sort and Find Item Has Max Value in List]()

```java
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
```

**Explanation:**

To create generic methods to sort a list of any type of objects by any field and to find the item with the maximum value of any field, we can use Java generics and `Function` interfaces. This allows us to write reusable code that can operate on any type of object and any field.

1. **Generic method to sort by any field**
    - `sortByField` method accepts a list of any type `T` and a key extractor function `Function<? super T, ? extends U>`, where `U` is a type that extends `Comparable`
    - The method uses `stream()`, `sorted()`, and `collect(Collectors.toList())` to sort the list based on the field extracted by the `keyExtractor`.
    
2. **Generic method to find the item with the maximum value of any field**
    - `maxByField` method accepts a list of any type `T` and a key extractor function `Function<? super T, ? extends U>`, where `U` is a type that extends `Comparable`.
    - The method uses `stream()`, `max(Comparator.comparing(keyExtractor))` to find the item with the maximum value for the field extracted by the `keyExtractor`.
    

---

## 💡 Q4: Convert List Any Object to Map with Any Key Field

**The question:**

Convert list any object to map with any key field.

**The codes:**

[Question 4 - Convert List Any Object to Map with Any Key Field]()

```java
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
```

**Explanation:**

To convert a list of any object to a map with any key field using Java generics, we can create a generic method that takes a list and a function to extract the key. This method will use Java Streams to collect the list into a map based on the provided key extractor function.

**Generic method `listToMap`**:

- This method takes a list of type `T` and a key extractor function `Function<? super T, ? extends K>`
- It uses the `Collectors.toMap` collector to convert the list to a map, with keys extracted using the provided key extractor function.

---

## 💡 Q5: Design Class Generic for Paging Data (Any Object)

**The question:**

Design Class generic for paging data (any Object). Demo the use.
Include: Object, size, pageNumber,...

**The codes:**

[Question 4 - Design Class Generic for Paging Data]()

**Explanation:**

To design class generic for paging data and shows the usage, here I created two files. There are `PagedData` class that can be used to paginate any type of data and `PagingDemo` class to show the usage of this paging generic.

1. **`PagedData` class:**
- The class is a generic class that can handle any type of list (`List<T>`)
- It has fields for `items` (the items for the current page), `size` (the size of the page), `pageNumber` (the current page number), `totalItems` (the total number of items in the full list), and `totalPages` (the total number of pages)
- The constructor initializes these fields and calculates the total number of pages using the formula `(totalItems + size - 1) / size`.

1. **`PagingDemo` class:**
- This class shows how to use the `PagedData` class
- In the `main` method, it creates a list of `EmployeeData` objects and defines a page size (`size`)
- It calculates the total number of pages using the formula `(employees.size() + size - 1) / size`
- It loops through each page, gets the paged data using the `getPagedData` method, and prints the employees on that page
- The `getPagedData` method takes a full list, page number, and size, and returns a `PagedData` object with the items for the current page.