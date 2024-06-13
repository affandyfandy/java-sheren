## Thread-Safe Singleton

Singleton design pattern ensures a class has only one instance while providing a global point of access to that instance. Ensuring thread safety in a Singleton is crucial, especially in multi-threaded environments (where multiple threads are executed concurrently within a single process).

The Singleton pattern is designed to guarantee a single instance of a class and typically involves 3 key components:

1. Private constructor: to prevent external instantiation (process of creating instance (object) of a class, involves allocating memory for new object and initializing it by calling its constructor) of the class
2. Private static instance: to hold the single instance of the class
3. Public static method: to provide global access to the instance

### Different Ways to Implement Thread-Safe Singleton

1. Eager initialization
    
    In this approach, the Singleton instance is created at the time of class loading. This ensures thread safety but may not be memory-efficient if the Singleton is not used immediately.
    
    Example:
    
    ```java
    public class EagerSingleton {
        // Create the singleton instance
        private static final EagerSingleton instance = new EagerSingleton();
    
        // Private constructor to prevent instantiation
        private EagerSingleton() { }
    
        // Global access point to get the singleton instance
        public static EagerSingleton getInstance() {
            return instance;
        }
        
        public void showMessage() {
            System.out.println("Hi!");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            // Get the singleton instance
            EagerSingleton singleton = EagerSingleton.getInstance();
    
            // Use the singleton instance
            singleton.showMessage(); // output: Hi!
        }
    }
    ```
    
    Explanation:
    
    - The singleton instance is created at class loading time. This means there’s only one point in time where the instance is created
    - The constructor is declared private, preventing external code from creating new instances
    - Since the instance is created only once during class loading and there’s no way to create new instances externally, the implementation avoids race condition that could lead to multiple instances in a multithreaded environment.
    
2. Lazy initialization
    
    In this approach, the Singleton instance is created only when it’s requested for the first time. It uses a synchronized method to ensure thread safety.
    
    Example:
    
    ```java
    public class LazySingleton {
        // Singleton instance, initially null
        private static LazySingleton instance;
    
        // Private constructor to prevent instantiation
        private LazySingleton() { }
    
        // Public synchronized method to get the singleton instance
        public static synchronized LazySingleton getInstance() {
            if (instance == null) {
                // Create the singleton instance
                instance = new LazySingleton();
            }
            return instance;
        }
    
        public void showMessage() {
            System.out.println("Hello, I am a thread-safe, lazy-initialized Singleton!");
        }
    
        public static void main(String[] args) {
            // Get the singleton instance
            LazySingleton singleton = LazySingleton.getInstance();
    
            singleton.showMessage(); // output: Hello, I am a thread-safe, lazy-initialized Singleton!
        }
    }
    ```
    
    This implementation is thread-safe because the use of synchronization. It ensures only one instance of the Singleton is created, even in a multi-threaded environment, and the instance is created only when it is first needed. However, the synchronization overhead can impact performance, especially if the getInstance method is called frequently.
    
3. Double-checked locking
    
    This technique combines lazy initialization and double-checked locking to improve performance by avoiding synchronization once the instance is created.
    
    Example:
    
    ```
    public class DoubleChecked {
        // Use volatile to ensure changes to instance are visible to all threads
        private static volatile DoubleChecked instance;
    
        // Private constructor to prevent instantiation
        private DoubleChecked() { }
    
        // Public method to get the singleton instance
        public static DoubleChecked getInstance() {
            if (instance == null) {
                synchronized (DoubleChecked.class) {
                    if (instance == null) {
                        instance = new DoubleChecked();
                    }
                }
            }
            return instance;
        }
    
        public void showMessage() {
            System.out.println("Hi, this is a thread-safe, lazy-initialized Singleton using double-checked locking!");
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            // Create multiple threads to demonstrate thread safety
            Thread thread1 = new Thread(() -> {
                DoubleChecked singleton1 = DoubleChecked.getInstance();
                singleton1.showMessage();
            });
    
            Thread thread2 = new Thread(() -> {
                DoubleChecked singleton2 = DoubleChecked.getInstance();
                singleton2.showMessage();
            });
    
            thread1.start();
            thread2.start();
        }
    }
    ```
    
    The double-checked locking implementation is a thread-safe way to achieve lazy initialization with minimal synchronization overhead. The use of the volatile keyword and double-checked locking pattern ensures that the Singleton instance is correctly initialized in a multi-threaded environment while maintaining good performance by reducing the need for synchronization after the instance is initialized.
    
4. Bill Pugh singleton
    
    This approach uses a static inner helper class to hold the Singleton instance. The class is not loaded into memory until it is referenced, which ensures lazy initialization. It is to ensure thread safety in a Singleton without requiring explicit synchronization.
    
    Example:
    
    ```java
    public class BillPughSingleton {
        // Private constructor to prevent instantiation
        private BillPughSingleton() { }
    
        // Static inner class - Inner classes are not loaded until they are referenced
        private static class SingletonHelper {
            private static final BillPughSingleton INSTANCE = new BillPughSingleton();
        }
    
        // Global access point to get the singleton instance
        public static BillPughSingleton getInstance() {
            return SingletonHelper.INSTANCE;
        }
    
        public void showMessage() {
            System.out.println("Hi, this is a thread-safe, lazy-initialized Singleton using the Bill Pugh method!");
        }
    
        public static void main(String[] args) {
            // Get the singleton instance
            BillPughSingleton singleton = BillPughSingleton.getInstance();
            
            singleton.showMessage();
        }
    }
    ```
    
    The SingletonHelper inner static class is not loaded into memory until it is referenced, ensuring lazy initialization. The instance is created when the SingletonHelper class is loaded. The class loading mechanism ensures thread safety and lazy initialization without the overhead of synchronization, making it efficient and easy to implement. This approach is suitable for scenarios where we want to delay the creation of the Singleton instance until it is actually needed.