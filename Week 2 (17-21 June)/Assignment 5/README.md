## 💡 Q1: Comparison

### ✅ ArrayList vs LinkedList

Both ArrayList and LinkedList are implementations of the List interface in Java, but they have different performance characteristics and are suited for different use cases. 

|  | ArrayList | LinkedList |
| --- | --- | --- |
| 1. | This class uses a dynamic array to store the elements in it. With the introduction of generics, this class supports the storage of all types of objects. | This class uses a double linked list to store the elements in it. Similar to the ArrayList, this class also supports the storage of all types of objects. |
| 2. | Manipulating ArrayList takes more time due to the internal implementation. Whenever we remove an element, internally, the array is traversed and the memory bits are shifted. | Manipulating LinkedList takes less time compared to ArrayList because, in a doubly-linked list, there is no concept of shifting the memory bits. The list is traversed and the reference link is changed. |
| 3. | Inefficient memory utilization. | Good memory utilization. |
| 4. | It can be one, two or multi-dimensional. | It can either be single, double or circular LinkedList. |
| 5. | Insertion operation is slow. | Insertion operation is fast. |
| 6. | This class implements a List interface. Therefore, this acts as a list. | This class implements both the List interface and the Deque Interface. Therefore, it can act as a list and a deque. |
| 7. | This class works better when the application demands storing the data and accessing it. | This class works better when the application demands manipulation of the stored data. |
| 8. | Data access and storage is very efficient as it stores the elements according to the indexes. | Data access and storage is slow in LinkedList. |
| 9. | Deletion operation is not very efficient. | Deletion operation is very efficient. |
| 10. | It is used to store only similar types of data. | It is used to store any types of data. |
| 11. | Less memory is used. | More memory is used. |
| 12. | This is known as static memory allocation. | This is known as dynamic memory allocation. |

**When should we use?**

- Use ArrayList when you need fast random access to elements, and insertions/deletions are infrequent or occur mostly at the end of the list
- Use LinkedList when your application involves frequent insertions and deletions, particularly at the beginning or end of the list, and you don’t need fast random access.

### ✅ HashSet vs TreeSet vs LinkedHashSet

HashSet, LinkedHashSet and TreeSet are all implementations of Set interface.

- **HashSet** is a collection of items where every item is unique and it is found in the java.util package
- **Treeset** provides an implementation of the Set interface that uses a tree for storage. Objects are stored in a sorted and ascending order
- **LinkedHashSet** class is a Hashtable and Linked list implementation of the Set interface. It inherits the HashSet class and implements the Set interface. LinkedHashSet class contains unique elements only like HashSet. But, keeping the insertion order in the LinkedHashset has some additional costs, both in terms of extra memory and extra CPU cycles. Therefore, if it is not required to maintain the insertion order, go for the lighter-weight HashSet instead.

**Comparison:**

|  | HashSet | LinkedHashSet | TreeSet |
| --- | --- | --- | --- |
| How they work internally? | HashSet uses HashMap internally to store it’s elements. | LinkedHashSet uses LinkedHashMap internally to store it’s elements. | TreeSet uses TreeMap internally to store it’s elements. |
| Order Of Elements | HashSet doesn’t maintain any order of elements. | LinkedHashSet maintains insertion order of elements. i.e elements are placed as they are inserted. | TreeSet orders the elements according to supplied Comparator. If no comparator is supplied, elements will be placed in their natural ascending order. |
| Null elements | HashSet allows maximum one null element. | LinkedHashSet also allows maximum one null element. | TreeSet doesn’t allow even a single null element. If you try to insert null element into TreeSet, it throws NullPointerException. |
| Performance | HashSet gives better performance than the LinkedHashSet and TreeSet. | The performance of LinkedHashSet is between HashSet and TreeSet. It’s performance is almost similar to HashSet. But slightly in the slower side as it also maintains LinkedList internally to maintain the insertion order of elements. | TreeSet gives less performance than the HashSet and LinkedHashSet as it has to sort the elements after each insertion and removal operations. |
| Synchronized | No | No | No |
| Fail-Fast Iterators or Fail-Safe | Fail-fast in nature. i.e You will get ConcurrentModificationException if they are modified after the creation of Iterator object. | Fail-fast in nature | Fail-fast in nature |
| Memory Occupation | HashSet requires less memory than LinkedHashSet and TreeSet as it uses only HashMap internally to store its elements. | LinkedHashSet requires more memory than HashSet as it also maintains LinkedList along with HashMap to store its elements. | TreeSet also requires more memory than HashSet as it also maintains Comparator to sort the elements along with the TreeMap. |
| When To Use? | Use HashSet if you don’t want to maintain any order of elements. | Use LinkedHashSet if you want to maintain insertion order of elements. | Use TreeSet if you want to sort the elements according to some Comparator. |
|  |  |  |  |

---

## 💡 Q2: Java Program to Retrieve an Element (At a Specified Index)

**The question:**

Write a Java program to retrieve an element (at a specified index) from a given array list.

**The codes:**
[Question 2 - Retrieve Element](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/RetrieveElement.java)

---

## 💡 Q3: Remove Lines Which is Duplicated Data by 1 Key Field

**The question:**

Remove lines which is duplicated data by 1 key field
• Input: file data (.csv or .txt) and position key field for txt or key field name for csv
• Output: write to new file with no duplication by key field

**The codes:**
[Question 3 - Remove Duplicates](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/RemoveDuplicates.java)

---

## 💡 Q5: Convert List to Map

**The question:**

Convert List to Map (ex: employee with employeeID as a key and order asc by key).

**The codes:**
[Question 5 - Convert List to Map](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/ListToMap.java)

---

## [Assignment 5 - Update]

## 💡 Q8: equal() and hashCode() Method

`equals()` and `hashCode()` methods in Java are fundamental methods used for comparing objects and managing collections like `HashMap` and `HashSet.`

### 👩‍🏫 equals() Method

`equals()` method is used to compare two objects for equality. By default, this method in the `Object` class compares the memory addresses of the object (i.e., it performs a reference comparison). However, it can be overridden in a custom class to compare the values of the object's attributes.

**Example:**

```java
public class EqualsExample {
    private String name;
    private int age;

    public EqualsExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EqualsExample person = (EqualsExample) obj;
        return age == person.age && name.equals(person.name);
    }

    public static void main(String[] args) {
        EqualsExample p1 = new EqualsExample("Liam", 19);
        EqualsExample p2 = new EqualsExample("Dwayne", 23);
        EqualsExample p3 = new EqualsExample("Liam", 19);

        System.out.println(p1.equals(p2)); // Output: false
        System.out.println(p1.equals(p3)); // Output: true
    }

    // Getter and setter
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
}
```

[Codes](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/Update/EqualsExample.java)


### 👩‍🏫 hashCode() Method

`hashCode()` method returns an integer hash code value for the object. It is used in hashing-based collections like `HashMap`, `HashSet`, and `Hashtable`. When overriding the `equals()` method, it's important to also override the `hashCode()` method to maintain the contract between them: if two objects are equal according to `equals()`, they must have the same hash code.

**Example:**

```
public class HashCodeExample {
    private String name;
    private int age;

    public HashCodeExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HashCodeExample person = (HashCodeExample) obj;
        return age == person.age && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return 25 * name.hashCode() + age;
    }

    public static void main(String[] args) {
        HashCodeExample p1 = new HashCodeExample("Liam", 19);
        HashCodeExample p2 = new HashCodeExample("Dwayne", 23);
        HashCodeExample p3 = new HashCodeExample("Liam", 19);

        System.out.println(p1.equals(p2)); // Output: false
        System.out.println(p1.equals(p3)); // Output: true
        System.out.println(p1.hashCode() == p2.hashCode()); // Output: false
        System.out.println(p1.hashCode() == p3.hashCode()); // Output: true
    }
}
```

[Codes](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/Update/HashCodeExample.java)

### ❔ Why We Override equals() and hashCode()

- **Consistency in Collections:** When using objects in hashing-based collections, consistent behavior of `equals()` and `hashCode()` is important
- **Contract Maintenance:** If two objects are equal according to `equals()`, they must have the same hash code. This ensures that the objects can be used correctly in hash-based collections
- **Logical Equality:** To provide a meaningful equality check based on the attributes of the objects rather than their memory addresses.

### Summary

✅ **`equals()` method:** Determines whether two objects are equal based on their attributes
✅ **`hashCode()` method:** Generates a hash code for an object, used in hashing-based collections
✅ **Contract:** If `equals()` is overridden, `hashCode()` must also be overridden to maintain consistency
✅ By overriding `equals()` and `hashCode()`, we can ensure that our objects behave correctly when compared and used in collections.

---

## 💡 Q9: Add Employee to HashSet and Recognize Duplicated Employee ID

**The question:**

See assignment 5.5, add employee to HashSet. How can it recognize that 2 employee has duplicated employee ID? Implement it.

**The codes:**
[Question 9 - Add Employee to HashSet and Recognize Duplicate employeeID](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/Update/DuplicateEmployee.java)

**Explanation:**

To recognize 2 employee has duplicated employee ID, we need to use `HashSet` for storing employees. A `HashSet` automatically handles duplicate entries based on the object’s `hashCode()` and `equals()` methods.

---

## 💡 Q10: Create Map of Employee with Composite Key (department, employeeID)

**The question:**

Similar (9), create Map of employee with composite key (department, employeeID).

**The codes:**
[Question 10 - Map Employee with Composite Key](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/Update/CompositeKeyMap.java)

**Explanation:**

To create a map of employees with composite key (department, employeeID), we need to define a class for the composite key. This class will override `equals()` and `hashCode()` methods to ensure that two keys are considered equal if both the department and employeeID match.

---

## 💡 Q11: Issue with Provided Codes

💻 **The provided codes:**

```
public class demo1 {
    List<String> data = new ArrayList();
    data.add("Joe");
    data.add("Helen");
    data.add("Test");
    data.add("Rien");
    data.add("Ruby");
    for (String d : data) {
        if (d.equals("Test")) {
            data.remove(d);
        }
    }
}
```

❔ **The issues:**

The issue with the provided code is that it wants to add elements to the `data` list and iterate over it at the class level (outside of any method or constructor). In addition, modifying a list while iterating over it directly (in this case, it is removing an element) will cause a `ConcurrentModificationException`.

**Key issues:**

- **Syntax error:** The code for adding elements to the list and the for-loop should be inside a method/a constructor. It is not valid syntax at the class level
- Concurrent modification: Removing elements from a list while iterating over it using for-each loop can cause a `ConcurrentModificationException`.

👩‍💻 **The solution:**

To fix the issues, we can:

✅ Move the list initialization and manipulation code into a method

✅ Use an iterator to safely remove elements while iterating over the list.

```java
import java.util.*;

public class demo1 {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("Joe");
        data.add("Helen");
        data.add("Test");
        data.add("Rien");
        data.add("Ruby");

        // Use an iterator to safely remove elements
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            String d = iterator.next();
            if (d.equals("Test")) {
                iterator.remove();
            }
        }

        // Print the list to verify the removal
        for (String d : data) {
            System.out.println(d);
        }
    }
}

/* Output:
 * Joe
 * Helen
 * Rien
 * Ruby
 */
```

[Question 11 - Issues with Provided Codes](https://github.com/affandyfandy/java-sheren/blob/main/Week%202%20(17-21%20June)/Assignment%205/Update/demo1.java)

---

## 💡 Q12: Multiple Threads Access and Modify a Shared Collection Concurrently

Q: What happen multiple threads to access and modify a shared collection concurrently? Note: ConcurrencyModificationException

When multiple threads access and modify a shared collection concurrently, an issue that is `ConcurrentModificationException` can arise.

### ⚠️ Problems with Concurrent Access and Modification

- **ConcurrentModificationException:**
    
    This exception occurs when a thread tries to modify a collection while another thread is iterating over it. The collection’s internal state becomes inconsistent, leading to an exception
    
- **Data inconsistency:**
    
    If multiple threads modify the collection simultaneously without proper synchronization, the collection may end up in an inconsistent state. For example, an element might be added or removed partially or incorrectly
    
- **Race conditions:**
    
    Occur when the outcome of the program depends on the sequence/timing of uncontrollable events, such as thread scheduling. This can lead to unpredictable behavior and hard-to-find bugs.
    

### **👩‍🏫 Example Scenario**

The following example is where multiple threads try to modify a shared `ArrayList`:

```java
import java.util.ArrayList;
import java.util.List;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        List<String> sharedList = new ArrayList<>();
        sharedList.add("test1");
        sharedList.add("test2");
        sharedList.add("test3");

        Runnable task = () -> {
            for (String test : sharedList) {
                System.out.println(test);
                if ("test2".equals(test)) {
                    sharedList.remove(test);
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
```

**Problems that arise in the example:**

- **ConcurrentModificationException**:
    
    When one thread modifies the list while another is iterating over it, the exception is thrown
    
- **Data Inconsistency and Race Conditions**:
    
    The list might be modified in an inconsistent way, leading to unexpected results.
    

### 💻 Solutions

1. **Synchronized Collections**
Use `Collections.synchronizedList` to create a thread-safe version of the list. However, iteration still needs to be synchronized manually to avoid `ConcurrentModificationException`:

```
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationExample {
    public static void main(String[] args) {
        List<String> sharedList = Collections.synchronizedList(new ArrayList<>());
        sharedList.add("test1");
        sharedList.add("test2");
        sharedList.add("test3");

        Runnable task = () -> {
            synchronized (sharedList) {
                Iterator<String> iterator = sharedList.iterator();
                while (iterator.hasNext()) {
                    String test = iterator.next();
                    System.out.println(test);
                    if ("test2".equals(test)) {
                        iterator.remove();
                    }
                }
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}

/* Output:
 * test1
 * test2
 * test3
 * test1
 * test3
 */
```

2. **Concurrent Collections**
    
    Use concurrent collections from the `java.util.concurrent` package, such as `CopyOnWriteArrayList`, `ConcurrentHashMap`, etc. These collections handle concurrency internally and avoid `ConcurrentModificationException:`
    
    ```java
    import java.util.List;
    import java.util.concurrent.CopyOnWriteArrayList;
    
    public class ConcurrentModificationExample {
        public static void main(String[] args) {
            List<String> sharedList = new CopyOnWriteArrayList<>();
            sharedList.add("test1");
            sharedList.add("test2");
            sharedList.add("test3");
    
            Runnable task = () -> {
                for (String test : sharedList) {
                    System.out.println(test);
                    if ("test2".equals(test)) {
                        sharedList.remove(test);
                    }
                }
            };
    
            Thread thread1 = new Thread(task);
            Thread thread2 = new Thread(task);
    
            thread1.start();
            thread2.start();
        }
    }
    
    /* Output:
     * test1
     * test2
     * test3
     * test1
     * test2
     * test3
     */
    ```
    

`CopyOnWriteArrayList` is a thread-safe variant of `ArrayList` where all mutative operations (add, set, and so on) are implemented by making a fresh copy of the underlying array. This approach avoids `ConcurrentModificationException` but can be less efficient for write-heavy scenarios due to the overhead of copying the list.