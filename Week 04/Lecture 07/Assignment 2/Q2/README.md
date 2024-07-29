## 💡 Comparison of DI Types

### 👷‍♀️ Constructor-based Dependency Injection

**Advantages:**

1. Immutability: Dependencies are injected when the object is created, making it possible to make fields `final`, which enforces immutability
2. Mandatory Dependencies: Makes it clear which dependencies are required, as they must be provided at object creation
3. Testability: Easier to write unit tests because dependencies are explicitly provided through the constructor
4. Dependency Injection (DI) Best Practices: Encourages good design practices by promoting clear dependency declarations.

**Disadvantages:**

1. Boilerplate Code: Can result in boilerplate code, especially if there are many dependencies.

---

### 👩‍🏫 Field-based Dependency Injection

**Advantages:**

1. Simplicity: Requires less boilerplate code compared to constructor-based DI. Dependencies are directly annotated in the fields
2. Readability: Keeps code concise and can be easier to read.

**Disadvantages:**

1. Immutability: Cannot make dependencies `final`, as they are injected after object creation
2. Hidden Dependencies: Makes it less clear which dependencies are required, as they are not part of the constructor
3. Testability: More difficult to write unit tests because dependencies are not explicitly provided and may require reflection to set fields during testing.

---

### 👩‍🏫 Setter-based Dependency Injection

**Advantages:**

1. Optional Dependencies: Allows optional dependencies to be injected without requiring them at object creation
2. Flexibility: Provides flexibility to change dependencies after object creation if needed.

**Disadvantages:**

1. Immutability: Cannot make dependencies `final`, as they can be set or changed after object creation
2. Hidden Dependencies: Dependencies are not immediately visible, as they are not part of the constructor
3. Testability: Similar to field-based DI, can be harder to write unit tests because dependencies are not provided explicitly.

---

### ❔ Which One is Better?

The recommended approach is **constructor dependency injection.** The reasons are:

1. Immutability: Constructor-based DI supports immutability by allowing dependencies to be set only once at object creation
2. Mandatory Dependencies: Clearly indicates which dependencies are required for the class to function
3. Testability: Easiest to test as dependencies are explicitly provided through the constructor
4. Best Practices: Aligns with good object-oriented design principles by promoting clear dependency declarations.

While constructor-based DI is generally recommended, field-based and setter-based DI can still be useful in specific scenarios, such as when dealing with optional dependencies or when simplicity is prioritized over other concerns.