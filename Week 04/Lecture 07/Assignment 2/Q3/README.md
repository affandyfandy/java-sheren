## 💡Circular Dependency Injection

### 👩‍🏫 Definition

A circular dependency occurs when a bean A depends on another bean B, and the bean B depends on bean A as well. Like this:

Bean A → Bean B → Bean A

We also could have more beans implied, like this:

Bean A → Bean B → Bean C → Bean D → Bean E → Bean A

In Spring, when the Spring context loads all the beans, it tries to create beans in the order needed for them to work completely. For example, we have something like:

Bean A → Bean B → Bean C

Spring will create bean C, then create bean B (and inject bean C into it), then create bean A (and inject bean B into it).

But with circular dependency, Spring cannot decide which of the beans should be created first since they depend on one another. In these cases, Spring will raise a `BeanCurrentlyInCreationException` while loading context. It can happen in Spring when using **constructor injection**, If we use other types of injections, we shouldn’t have this problem since the dependencies will be injected when they are needed and not on the context loading.

### 👩‍💻 Example

Before we have understood about circular dependency and the issue with it in Spring. Now, let’s understand this by codes. Let’s define two beans that depend on one another (via constructor injection).

**1️⃣ BeanA**

```java
@Component
public class BeanA {
    private BeanB beanB;

    @Autowired
    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }
}
```

**2️⃣ BeanB**

```java
@Component
public class BeanB {
    private BeanA beanA;

    @Autowired
    public BeanB(BeanA beanA) {
        this.beanA = beanA;
    }
}
```

**3️⃣ AppConfig**

```java
@Configuration
@ComponentScan(basePackages = { "com.example.assignment2q3"})
public class AppConfig {
}
```

**4️⃣ Assignment2q3Application**

```java
public class Assignment2q3Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
	}

}
```

If we try to run this test, we will get an exception: **`BeanCurrentlyInCreationException**: Error creating bean with name ‘beanA’: Requested bean is currently in creation: Is there an unresolvable circular reference?`

### 💻 Solution

**1️⃣ Use @Lazy**

Using `@Lazy` annotation we can tell Spring to initialize one of the beans lazily. The injected bean will only be fully created when it’s first needed and at the time of beans creation, it injects the proxy bean as a dependency.

```java
@Component
public class BeanA {
    private BeanB beanB;

    @Autowired
    public BeanA(@Lazy BeanB beanB) {
        this.beanB = beanB;
    }
}
```

Now, when we run the project and Spring will load the context then it won’t throw `BeanCurrentlyInCreationException`.

**2️⃣ Use Setter/Field Injection**

Instead of going for constructor-based dependency injection in the context of circular dependency, we should go for Setter-based dependency injection as suggested by Spring documentation. Using this way, Spring creates the beans, but the dependencies are not injected until they are needed.

```java
@Component
public class BeanA { 
    BeanA() { 
        System.out.println("BeanA constructor called."); 
    } 
  
    private BeanB beanB; 
  
    @Autowired
    public void setBeanB(BeanB beanB) { 
        System.out.println("Setting property beanB of BeanA instance"); 
        this.beanB = beanB; 
    } 
}
```

```java
@Component
public class BeanB { 
    BeanB() { 
        System.out.println("BeanB constructor called."); 
    } 
  
    private BeanA beanA; 
  
    @Autowired
    public void setBeanA(BeanA beanA) { 
        System.out.println("Setting property beanA of BeanB instance"); 
        this.beanA = beanA; 
    } 
}
```

In setter-based dependency injection, Spring creates a bean by calling the constructor first and then injecting the dependencies with the help of setter methods. So here Spring won’t raise `BeanCurrentlyInCreationException` as Spring will have the required object at the time of dependency injection.