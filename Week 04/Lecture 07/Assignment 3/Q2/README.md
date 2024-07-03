## 💡 Create Controller For Request Scope

Q: For request scope, create a controller and test it

### 👩‍🏫 Definition

The request scope creates a bean instance for a single HTTP request. To demonstrate the request scope in a Spring Boot application, we need to create a controller that handles HTTP requests. The request scope means that a new instance of the bean will be created for each HTTP request.

---

### 💻 Step by Step Implementation

**1️⃣ Add Spring Web dependency in `pom.xml` file**

```xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**2️⃣ Create a request-scoped controller**

In here, we create a new class for controller.

```
@RestController
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequestMapping("/api")
public class RequestScopeController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/request-scope")
    public String getRequestScope() {
        String uniqueMessage = "Email sent at: " + java.time.LocalDateTime.now();
        emailService.sendEmail("example@example.com", "Request Scope Test", uniqueMessage);
        return "Request scope: " + uniqueMessage;
    }
}
```

- `@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)` annotation defines the scope of the bean
    - `value = "request"` specifies that the scope of this bean is a request scope
    - `proxyMode = ScopedProxyMode.TARGET_CLASS` The `proxyMode` attribute is necessary because at the moment of the instantiation of the web application context, there is no active request. Spring creates a proxy to be injected as a dependency, and instantiates the target when it is needed in a request.
- The `getRequestScope` method generates a unique message using the current timestamp (`java.time.LocalDateTime.now()`)
- Email Service method calls the `EmailService` to send an email with this unique message. Because the controller is request-scoped, each request results in a new controller instance, which leads to different timestamps in the emails sent for different requests
- By calling the `/api/request-scope` endpoint multiple times (using tools like Postman), we can see different timestamps in the responses and the emails, demonstrating that a new instance of `RequestScopeController` is created for each request.

3️⃣ **Update application.properties**

We need necessary configurations in our [`application.properties`](http://application.properties) file. We need to add this one:

```java
server.port=8080
```

---

### 💻 Test the Controller

**1️⃣ Run the application**

The first step that we need to do is start the Spring Boot application by running the `Assignment3q2Application` class

**2️⃣ Make HTTP requests**

In this step, I use Postman. In Postman, we can create a new request, set the request method to `GET`, and enter the URL `localhost:8080/api/request-scope`. After that, we can click `Send` button and view the response in the Postman interface.

![Output](output.png)

**3️⃣ Observe the output**
Each time we access this endpoint, we should see a different timestamp in the response, confirming that a new instance of the `RequestScopeController` is created for each request. This demonstrates the request scope in action.

Example output:

```
Request scope: Email sent at: 2024-07-04T00:44:04.964945200
```

```
Request scope: Email sent at: 2024-07-04T00:57:48.698239800
```