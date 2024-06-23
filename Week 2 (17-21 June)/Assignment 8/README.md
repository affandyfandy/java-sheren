## 💡 Q1: serialVersionUID

In Java, `serialVersionUID` is a unique identifier for a class that is used during the serialization and deserialization process. It is a long value that serves as a version control in a `Serializable` class. This identifier is used to ensure that a serialized object can be deserialized correctly by matching the `serialVersionUID` of the serialized object with the `serialVersionUID` of the class that is being deserialized.

### ❔ Why Use serialVersionUID

- **Version Control**: It helps in maintaining the version of the class. If the `serialVersionUID` does not match, the JVM will throw an `InvalidClassException` indicating that the class used to serialize the object and the class used to deserialize the object are not compatible
- **Class Evolution**: It allows a class to evolve while maintaining compatibility with versions of the class that have been previously serialized.

### ✔️ Defining serialVersionUID

We can explicitly define `serialVersionUID` in our class by declaring a `static final long` field. For example:

```java
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String name;

    // Constructor, getters, setters, etc.
}
```

In this example, `Employee` class has a `serialVersionUID` of `1L`. This ensures that even if we modify the class later (e.g., adding new fields), as long as we do not change the `serialVersionUID`, objects serialized with the previous version of the class can still be deserialized with the new version.

If we do not explicitly define a `serialVersionUID`, the Java compiler will generate one automatically based on various aspects of the class. This can lead to unexpected `InvalidClassException` if the class structure changes in any way that affects the generated UID.

### 📌 When to Change serialVersionUID

We should change the `serialVersionUID` when there is a significant, non-backward-compatible change to the class, such as:

- Removing fields
- Changing field types
- Changing class hierarchy

[Example - Serialized](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%208/Serialize.java)

[Example - Deserialized](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%208/Deserialize.java)

---

## 💡 Q2: Serialization and Deserialization in Write List of Object (Employee)

**The question:**

Illustrate Serialization and Deserialization in write list of object (employee) to file and read file to convert to object and using serialVersionUID.

**The codes:**

[Question 2 - Serialize Employee](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%208/SerializeEmployee.java)

[Question 2 - Deserialize Employee](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%208/DeserializeEmployee.java)

**Explanation:**

First, we define a class `Employee` with a `serialVersionUID` and create a class to serialize a list of `EMployee` objects to a file.

```
/* Illustrate Serialization and Deserialization in write list of object (employee) to
file and read file to convert to object and using serialVersionUID */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Unique identifier
    
    private int id;
    private String name;
    private String department;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', department='" + department + "'}";
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

public class SerializeEmployee {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Reine Sara", "Engineering"));
        employees.add(new Employee(2, "Raymond Dave", "IT"));
        employees.add(new Employee(3, "Luis Fernandez", "Sales"));

        try (FileOutputStream fileOut = new FileOutputStream("employees.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(employees);
            System.out.println("Serialized data is saved in employees.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}

```

- **Serializable Interface**: The `Employee` class implements the `Serializable` interface, which is a marker interface that tells the Java runtime that objects of this class can be serialized
- **serialVersionUID**: The `serialVersionUID` is a unique identifier for the class version. This ensures that during deserialization, the sender and receiver of the serialized object have loaded classes for that object that are compatible with respect to serialization
- **FileOutputStream**: Creates a file output stream to write to the file `employees.ser`
- **ObjectOutputStream**: Wraps the `FileOutputStream` and provides methods to serialize objects
- **writeObject()**: Serializes the list of `Employee` objects and writes it to the specified file.

The second one is that we create a class to deserialize the list of `Employee` objects from the file.

```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class DeserializeEmployee {
    public static void main(String[] args) {
        List<Employee> employees = null;

        try (FileInputStream fileIn = new FileInputStream("employees.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            employees = (List<Employee>) in.readObject();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
        }

        System.out.println("Deserialized Employees...");
        if (employees != null) {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }
}

/* Output:
 * Deserialized Employees...
 * Employee{id=1, name='Reine Sara', department='Engineering'}
 * Employee{id=2, name='Raymond Dave', department='IT'}
 * Employee{id=3, name='Luis Fernandez', department='Sales'}
 */
```

- **FileInputStream**: Creates a file input stream to read from the file `employees.ser`
- **ObjectInputStream**: Wraps the `FileInputStream` and provides methods to deserialize objects
- **readObject()**: Reads the serialized list of `Employee` objects from the specified file and casts it to a `List<Employee>`.