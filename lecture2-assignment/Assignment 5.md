## Thread-Safe Singleton

Singleton design pattern ensures a class has only one instance while providing a global point of access to that instance. Ensuring thread safety in a Singleton is crucial, especially in multi-threaded environments (where multiple threads are executed concurrently within a single process).

The Singleton pattern is designed to guarantee a single instance of a class and typically involves 3 key components:

1. Private constructor: to prevent external instantiation (process of creating instance (object) of a class, involves allocating memory for new object and initializing it by calling its constructor) of the class
2. Private static instance: to hold the single instance of the class
3. Public static method: to provide global access to the instance

### Different Ways to Implement Thread-Safe Singleton

1. Eager initialization
2. Lazy initialization
3. Double-checked locking
4. Bill pugh singleton