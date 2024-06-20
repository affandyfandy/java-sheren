## 💡 Try-with-resources

Try-with-resources statement is feature introduced in Java 7 that simplifies the management resources such as input/output streams, database connections, and other resources that need to be closed after their operations are completed. This feature ensures that resources are closed automatically at the end of the statement. It reduces the risk of resource leaks and simplifying code.

### Syntax

```java
try (ResourceType resource = new ResourceType()) {
    // Use the resource
} catch (ExceptionType e) {
    // Handle exception
}
```

**Example:**

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

- BufferedReader is used to read a file
- The BufferedReader is created inside the try-with-resouurces statement
- The BufferedReader is automatically closed at the end of the try block, even if an exception is thrown.

### Multiple Resources

We can also manage multiple resources in a single try-with-resources statement by separating them with semicolons. For example:

```java
try (
    BufferedReader br = new BufferedReader(new FileReader("example.txt"));
    BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))
) {
    String line;
    while ((line = br.readLine()) != null) {
        bw.write(line);
        bw.newLine();
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

In this code, both BufferedReader and BufferedWriter are managed, and both will be closed automatically when the try block exits.

### Advantages

- Automatic Resource Management. The resources are closed automatically, reducing the chance of resource leaks
- Cleaner code. The code is cleaner and easier to read compared to using traditional try-catch-finally blocks
- There is no need to explicitly close resources in a finally block.

### Behind the scenes

Java compiler translates the try-with-resources statement into a try-catch-finally construct. This is a rough equivalent of the try-with-resources example with explicit resource management:

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("example.txt"));
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
```

In conclusion, the try-with-resources statement is a powerful feature in Java that simplifies resource management, ensuring that resources are closed properly and reducing the risk of resource leaks. It leads to more readable and maintainable code, especially when dealing with multiple resources.

## 💡 Throw vs throws

In Java, throw and throws are two keywords used in exception handling but with different purposes.

### ‘throw’ keyword

Used to explicitly throw an exception in the program. It can be used to throw both checked and unchecked exceptions. When we use throw, we create an instance of exception and pass it to the throw statement.

**Example:**

```java
public class ThrowExample {
    public static void main(String[] args) {
        try {
            validateNumber(17);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    static void validateNumber(int number) {
        if (number < 20) {
            throw new IllegalArgumentException("The number must be at least 20.");
        }
        System.out.println("The number is valid.");
    }
}
```

- The validateNumber method if the number is less than 20
- If it is, it throws an ‘IllegalArgumentException’ with a custom message
- The exception is caught in the main method’s try-catch block.

### ‘throws’ keyword

Used in a method signature to declare that the method can throw exceptions of the specified types. This is typically used to indicate which checked exceptions a method might throw, alerting callers of the method to handle/declare those exceptions.

**Example:**

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsExample {
    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (IOException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }

    static void readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}
```

- The readFile method declares that it throws IOException
- This means the method can throw an IOException that needs to be handled by the caller
- In the main method, the readFile method is called inside a try-catch block to handle the IOException.

### Key differences

- Purpose
    - throw is used to actually throw an instance of an exception
    - throws is used in method signatures to declare which exceptions can be thrown by the method.
- Usage context
    - throw is used within the body of a method or a block of code
    - throws is used in the method declaration
- Types of exceptions
    - throw can be used to throw both checked and unchecked exceptions
    - throws is typically used to declare checked exceptions that a method might throw.