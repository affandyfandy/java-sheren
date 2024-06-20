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