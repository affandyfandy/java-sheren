## 💡 Using Setter-Based Dependency Injection and Field Dependency for #2

Previously, we have used constructor injection for Dependency Injection. Now, let’s delve into another ways to do Dependency Injection.

---

### 👩‍💻 Setter-Based Dependency Injection

This DI involves injecting dependencies through public setter methods. To use this, we can:

- Define the bean class. Ensure the class has public setter methods for the dependencies we want to inject
- Update configuration class. We can configure the beans using the setter methods.

1️⃣ Define the bean class with setters

```java
public class Employee {
    private String id;
    private String name;
    private int age;
    private EmployeeWork employeeWork;

    public Employee() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmployeeWork(EmployeeWork employeeWork) {
        this.employeeWork = employeeWork;
    }

    public void working() {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        employeeWork.work();
    }
}
```

2️⃣ Update configuration class

```java
@Configuration
public class AppConfig {
    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setId("A1");
        employee.setName("Sheren");
        employee.setAge(19);
        employee.setEmployeeWork((employeeWork()));
        return employee;
    }   
}
```

In the setter-based DI, the `Employee` class has setter methods to set the values. The configuration class `AppConfig` creates an instance of `Employee` and sets those properties using the setter methods.

---

### 👩‍🏫 Field-Based Dependency Injection

This DI involves injecting dependencies directly into a fields of a class using the `@Autowired` annotation. To use this, we can:

- Define the bean class. We annotate the fields with `@Autowired`
- Update configuration class.

1️⃣ Define the bean class

```java
public class Employee {
    private String id;
    private String name;
    private int age;

    @Autowired
    private EmployeeWork employeeWork;

    public Employee() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void working() {
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        employeeWork.work();
    }
}
```

2️⃣ Update configuration class

```java
@Configuration
public class AppConfig {
    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setId("A1");
        employee.setName("Sheren");
        employee.setAge(19);
        return employee;
    }   
}
```

In this DI, the `Employee` class has the `emmployeeWork` field annotated with the `@Autowired`, indicating that Spring should inject the `EmployeeWork` bean into this field. 

Note that this field-based injection is generally less preferred because it makes the class less testable and harder to understand. Setter or construction injection is preferred.