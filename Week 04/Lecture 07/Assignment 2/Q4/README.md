## 💡 Stereotype Annotations

### 1️⃣ @Configuration

The `@Configuration` annotation indicates that the class has `@Bean` definition methods. So Spring container can process the class and generate Spring Beans to be used in the application. Example:

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

---

### 2️⃣ @Bean

The `@Bean` annotation tells Spring that a method annotated with `@Bean` will return an object that should be registered as a bean in the Spring application context. Example:

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

---

### 3️⃣ @ComponentScan

The `@ComponentScan` annotation is used with the `@Configuration` annotation to specify the packages to scan for Spring beans. Example:

```java
@Configuration
@ComponentScan(basePackages = "com.example.myapp")
public class AppConfig {
}
```

---

### 4️⃣ @Component

The `@Component` annotation is used to indicate that a class is a Spring component. Spring will automatically detect these classes through classpath scanning and register the corresponding beans in the application context. Example:

```java
@Component
public class MyComponent {
    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

---

### 5️⃣ @Service

The `@Service` annotation is a specialization of the `@Component` annotation. It is used to indicate that a class is a service, which typically contains business logic. Example:

```java
@Service
public class MyService {
    public void performService() {
        System.out.println("Performing service...");
    }
}
```

---

### 6️⃣ @Repository

The `@Repository` annotation is a specialization of the `@Component` annotation. It is used to indicate that a class is a repository, which will be used to access the database. Example:

```java
@Repository
public class MyRepository {
    public void save() {
        System.out.println("Saving data...");
    }
}
```

---

### 7️⃣ @Autowired

The `@Autowired` annotation is used to inject dependencies automatically by Spring's dependency injection mechanism. Example:

```java
@Component
public class MyComponent {
    private final MyService myService;

    @Autowired
    public MyComponent(MyService myService) {
        this.myService = myService;
    }

    public void execute() {
        myService.performService();
    }
}
```

---

### 8️⃣ @Scope

The `@Scope` annotation is used to define the scope of a bean. The default scope is singleton. Example:

```java

@Component
@Scope("prototype")
public class MyPrototypeBean {
    // This bean will be created anew each time it is requested
}
```

---

### 9️⃣ @Qualifier

The `@Qualifier` annotation is used to specify which bean should be injected when there are multiple beans of the same type. Example:

```java
@Component
public class MyComponent {
    private final MyService myService;

    @Autowired
    public MyComponent(@Qualifier("specificService") MyService myService) {
        this.myService = myService;
    }
}
```

---

### 🔟 @PropertySource, @Value

The `@PropertySource` annotation is used to declare a properties file to be read. The `@Value` annotation is used to inject property values into Spring beans. Example:

```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Value("${my.property}")
    private String myProperty;

    @Bean
    public MyService myService() {
        return new MyService(myProperty);
    }
}
```

---

### 1️⃣1️⃣ @PreDestroy, @PostConstruct

The `@PreDestroy` annotation is used on methods that need to be executed before the bean is destroyed. The `@PostConstruct` annotation is used on methods that need to be executed after dependency injection is done to perform any initialization. Example:

```java
@Component
public class MyComponent {
    
    @PostConstruct
    public void init() {
        System.out.println("Initialization logic...");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleanup logic...");
    }
}
```