## 💡 Q1: Parallel Stream

In Java 8, it introduced the Stream API that makes it easy to iterate over collections as streams of data. It’s easy to create streams that execute in parallel and make use of multiple processor cores.

### 👩‍🏫 Streams in Java

Stream is simply a wrapper around a data source, allowing us to perform bulk operations on the data in a convenient way. It doesn’t store data or make any changes to the underlying data source. Instead, it adds support for functional-style operations.

By default, any stream operation in Java is processed sequentially unless explicitly specified as parallel. Any stream in Java can easily be transformed from sequential to parallel by adding the *parallel* method to a sequential stream or by creating a stream using the *parallelStream* method of a collection.

### ❔ When to Use Parallel Streams

We need to be very considerate when using parallel streams. It can bring performance benefits in certain use cases. But, it cannot be considered as a magical performance booster. **Sequential streams should still be used as default during development.**

A sequential stream can be converted to a parallel one when we **have actual performance requirements**. By considering those requirements, we should first run a performance measurement and consider parallelism as a possible optimization strategy.

**When we use parallel stream or not:**

✅ A large amount of data and many computations done per element indicate that parallelism could be a good option

✅ A small amount of data, unevenly splitting sources, expensive merge operations, and poor memory locality indicate a potential problem for parallel execution

✅ Arrays are a great data source for parallel execution because they bring the best possible locality and split cheaply and evenly

✅ When performing operations like `sum`, `average`, `max`, `min`, `collect`, etc., that benefit from parallel processing.

**Things to notice:**

✅ Avoid shared mutable state to prevent race conditions and data corruption. Use thread-safe constructs like `ConcurrentHashMap`, or ensure immutability

✅ If the order of processing is important, be cautious with parallel streams. Operations on parallel streams do not guarantee the order of execution.

### 💻 Sum List of Number Using Parallel Stream

One of the methods for calculating the sum of a list of integer is by using the Stream.collect() method.

**Codes example:**

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sum {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        
        // Using parallel stream to sum the numbers
        Integer sum = integers.parallelStream().collect(Collectors.summingInt(Integer::intValue));
        
        System.out.println(sum);
    }
}
```

- The code makes use of Java's Stream API to process the list of integers
- `integers.parallelStream()`: Converts the list to a parallel stream
- `.collect(Collectors.summingInt(Integer::intValue))`: Uses the `Collectors.summingInt` method to sum the integers in the parallel stream.

[Codes](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/Sum.java)

---

## 💡 Q2: Remove All Duplicate Elements Using Stream

**The question:**

Remove all duplicate elements from a list of string using streams.

**The codes:**

[Question 2 - Remove All Duplicate Elements Using Stream](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/RemoveDuplicatesString.java)

```java
// Remove all duplicate elements from a list of string using streams

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicatesString {
    public static void main(String[] args) {
        List<String> color = Arrays.asList("Blue", "Red", "Pink", "Blue", "Yellow", "Red");

        // Get list without duplicates using stream
        List<String> distinctColor = color.stream()
                                            .distinct()
                                            .collect(Collectors.toList());

        System.out.println(distinctColor); // Output: [Blue, Red, Pink, Yellow]
    }
}
```

**Explanation:**

To remove duplicates elements from a list of string, we can use `stream.distinct()` method. The `distinct()` method returns a stream consisting of the distinct elements of the given stream.

---

## 💡 Q3: Remove Lines Which is Duplicated Data By Key Field

**The question:**

Remove lines which is duplicated data by key field
a) Input: file data (.csv or .txt) and position key field for txt or key field name for csv
b) Output: write to new file with no duplication by key field
Clue: Using Stream with IO.

**The codes:**

[Question 3 - Remove Lines Which is Duplicated Data By Key Field](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/RemoveDuplicatesTxt.java)

**Explanation:**
By specifying both a start and end index, we can combine multiple columns to form a unique key for each line. This can be useful when a single column is not enough to uniquely identify a line.

- Single column as key: ID1 is the key
- Three columns as key: ID1 Name1 Data1 is the key.

By specifying the two columns as a key from start index and end index, we will get a result like this:

Input:
```java
ID1 Name1 Data1 Test1
ID1 Name1 Data1 Test2
ID1 Name2 Data1 Test1
ID1 Name1 Data3 Test1
ID2 Name2 Data2 Test3
ID2 Name2 Data2 Test4
ID3 Name3 Data4 Test4
```

Output:
```java
ID1 Name1 Data1 Test1
ID1 Name2 Data1 Test1
ID1 Name1 Data3 Test1
ID2 Name2 Data2 Test3
ID3 Name3 Data4 Test4
```

**Key points:**

- **Streams and Collectors**:
    - The code uses Java Streams to process the file lines in a functional style
    - `Collectors.toMap` is used to ensure that only unique lines are retained based on the key field.
- **Error Handling**:
    - The code checks for invalid key field indexes and throws an exception if the index is out of bounds.
- **Resource Management**:
    - Try-with-resources ensures that the `Stream` and `BufferedWriter` are closed automatically, preventing resource leaks.

---

## 💡 Q4: Count Number of Strings Start with a Specific Letter Using Streams

**The question:**

Count the number of strings in a list that start with a specific letter using streams.
a) Input: ["Red", "Green", "Blue", "Pink", "Brown“] and String = “G”
b) Output: 1.

**The codes:**

[Question 4 - Count Number of Strings Start with a Specific Letter Using Streams](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/CountString.java)

```java
/* Count the number of strings in a list that start with a specific letter using streams.
a) Input: ["Red", "Green", "Blue", "Pink", "Brown“] and String = “G”
b) Output: 1
*/

import java.util.*;

public class CountString {
    public static void main(String[] args) {
        List<String> colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
        String letter = "G";

        long count = colors.stream()
                        .filter(color -> color.startsWith(letter))
                        .count();

        System.out.println(count); // Output: 1
    }
}

```

**Explanation:**

We can count the number of strings in a list that start with a specific letter by:

- Convert the list to a stream
- Filter the stream to include only the strings that start with the specific letter
- Count the number of elements in the filtered stream.

---

## 💡 Q5: Input List of Employees

**The question:**

Input: list of employees (ex: Employee (ID, name, age, salary,..))
a) Sort name in alphabetical, ascending using streams
b) Find employee has max salary using streams
c) Check any employee names match with specific keywords or not.

**The codes:**

[Question 5 - Input List of Employees](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/ListEmployee.java)

**Explanation:**

- Use `Stream.sorted()` to sort the employee’s name
- Use `Stream.max` with a comparator based on the `salary` field. The comparator can be created using `Comparator.comparing(Employee::getSalary)`
- Use `Stream.anyMatch` to check if any employee's name matches the specific keyword.

---

## 💡 Q6: Convert List Employees to Map with ID as key

**The question:**

Convert list employees to map with ID as key.

**The codes:**

[Question 6 - Convert List Employees to Map with ID as key](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%206/ListEmployeeToMap.java)

**Explanation:**

- `employees.stream()`: Converts the list of employees into a stream.
- `.collect(Collectors.toMap(Employee::getEmployeeID, emp -> emp))`: Collects elements of the stream into a `Map<Integer, Employee>`.
    - `Employee::getEmployeeID` is used as the key extractor.
    - `emp -> emp` specifies that each employee object itself is the value in the map.