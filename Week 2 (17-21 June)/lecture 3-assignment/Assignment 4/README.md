## 💡 Deadlock

Deadlock in Java is a part of multithreading. Deadlock is a situation when a thread is waiting for an object lock, that is acquired by another thread and second thread is waiting for an object lock that is acquired by first thread. Since, both threads are waiting for each other to release the lock, the condition is called deadlock.

**Example:**

```java
public class DeadlockExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Thread 1: Holding lock 1 and lock 2...");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2: Holding lock 2...");
                try {
                    Thread.sleep(100); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Thread 2: Holding lock 1 and lock 2...");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

/* Output:
Thread 2: Holding lock 2...
Thread 1: Holding lock 1...
Thread 1: Waiting for lock 2...
Thread 2: Waiting for lock 1... */
```

- Two objects are used as locks (lock1 and lock2)
- Two threads are created (thread1 and thread2)
    - thread1 locks lock1 and then attempts to lock lock2
    - thread2 locks lock2 and then attempts to lock lock1
- Deadlock scenario:
    - thread1 locks lock1 and waits to acquire lock2
    - thread2 locks lock 2 and waits to acquire lock1

**How to prevent deadlock:**

- Avoid nested locks
    
    We must avoid giving locks to multiple threads. This is the main reason for a deadlock condition. It normally happens when you give locks to multiple threads
    
- Avoid unnecessary locks
    
    The locks should be given to the important threads. Giving locks to the unnecessary threads that cause the deadlock condition
    
- Using thread join
    
    Thread.join() guarantees that one thread finishes before starting the another. For example, when one thread is reading from a file, and another is writing to the same file. Therefore, a deadlock cannot arise.
    

## 💡 Noticeable things when using multiple thread

### Advantages

- **Improved performance**
    - **Concurrency:** Allows multiple operations to be performed simultaneously, potentially speeding up the execution of CPU-bound tasks on multi-core processors
    - **Responsiveness:** Enhances the responsiveness of applications by performing long-running operations in the background.
- **Resource utilization**
    - **Parallel processing:** Enables the effective utilization of multi-core processors by running threads in parallel
    - **Non-blocking operations:** Allows for non-blocking I/O operations (Input/Output), improving efficiency in I/O-bound applications.

### Challenges and Issues

- **Synchronization and coordination**
    - **Race conditions:** Occur when two or more threads access shared data and try to change it simultaneously, leading to inconsistent result
    - **Deadlock:** Arise when two or more threads are waiting indefinitely for resources held by each other
- **Complexity**
    - **Debugging:** Multithreaded programs can be challenging to debug due to non-deterministic behavior
    - **Design and maintenance:** Requires careful design to ensure thread safety and proper synchronization, increasing the complexity of code maintenance.

### Best practices

- **Minimize shared state:** Reducing the amount of shared data between threads to lower the risk of synchronization issues
- **Immutable objects:** Using immutable objects where possible to avoid the need for synchronization
- **Avoiding deadlocks:** Carefully designing the order in which looks are acquired and ensuring that all threads follow the same order to prevent deadlocks

## 💡 ReadWriteLock interface

In Java, ReadWriteLock interface is part of the java.util.concurrent.locks package and used to handle scenarios where you have more reads than writes on a shared resource. It allows multiple threads to read the resource concurrently while maintaining exclusive access for writing. This can improve performance in read heavy-applications by reducing contention.

The ReadWriteLock interface has two locks:

- ReadLock → Allows multiple threads to read the resource simultaneously. If no thread has locked the ReadWriteLock for writing then multiple thread can access the read lock
- WriteLock → Allows only one thread to write to the resource, ensuring exclusive access. If no thread is reading or writing, the one thread can access the write lock.

**Example:**

```java
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int sharedResource = 0;

    public int readResource() {
    // Acquring the read lock
        lock.readLock().lock();
        try {
            // Perform read operations on the shared resource
            return sharedResource;
        } finally {
    // Read lock released 
            lock.readLock().unlock();
        }
    }

    public void writeResource(int value) {
    // Acquring the write lock
        lock.writeLock().lock();
        try {
            // Perform write operations on the shared resource
            sharedResource = value;
        } finally {
     // Write lock released
            lock.writeLock().unlock();
        }
    }
}
```