## 🗒️ Comparison of Restful API Best Practice with Assignment 2

REST APIs are one of the most common kinds of web interfaces available today. They allow various clients including browser apps to communicate with services via the REST API. Therefore, it's very important to design REST APIs properly so that we won't run into problems down the road. We have to take into account security, performance, and ease of use for API consumers.

---

### 1️⃣ Accept and respond with JSON

Based on the article, REST APIs should accept JSON for request payload and also send responses to JSON. JSON is the standard for transferring data. My codes already applied this practice.

- `@RestController` annotation makes the controller returns JSON responses if the request headers accept JSON
- `@PostMapping` and `@PutMapping` methods in my codes use `@RequestBody` to consume JSON payloads. This indicates that the methods are designed to accept JSON
- My codes use`ResponseEntity` to return responses, which can be configured to return JSON. This aligns well with best practices.

---

### **2️⃣ Use nouns instead of verbs in endpoint paths**

Based on the article, we shouldn't use verbs in our endpoint paths. Instead, we should use the nouns which represent the entity that the endpoint that we're retrieving or manipulating as the pathname. This is because our HTTP request method already has the verb. Having verbs in our API endpoint paths isn’t useful and it makes it unnecessarily long since it doesn’t convey any new information.

- The `@RequestMapping("/api/v1/employee")` is already indicated a noun which is `employee` used to represent the entity that the endpoint we’re retrieving
- However, there are two endpoints in my codes that didn’t used noun. Those are `"/upload-csv"` and `/by-department`. For the others, I already used noun in endpoint paths.

---

### 3️⃣ **Use logical nesting on endpoints**

When designing endpoints, it makes sense to group those that contain associated information. That is, if one object can contain another object, we should design the endpoint to reflect that.

- In my codes, I only grouped CRUD operations under the common base path which is`/api/v1/employee`.

---

### 4️⃣ **Handle errors gracefully and return standard error codes**

To eliminate confusion for API users when an error occurs, we should handle errors gracefully and return HTTP response codes that indicate what kind of error occurred. This gives maintainers of the API enough information to understand the problem that’s occurred.

In my codes, I already applied this practice.

- **Get all employees**:
    - `ResponseEntity.noContent().build();` returns `204 No Content` if the employee list is empty
    - `ResponseEntity.ok(listEmployee)` returns `200 OK` with the list of employees.
- **Get employee by id**:
    - `ResponseEntity.notFound().build()` returns `404 Not Found` if the employee is not found
    - `ResponseEntity.ok(employeeOpt.get())` returns `200 OK` with the employee details if found.
- **Create new employee**:
    - `ResponseEntity.badRequest().build()` returns `400 Bad Request` if an employee with the same ID already exists
    - `ResponseEntity.ok(employeeRepository.save(employee))` returns `200 OK` with the saved employee.
- **Update employee by id**:
    - `ResponseEntity.notFound().build()` returns `404 Not Found` if the employee is not found
    - `ResponseEntity.ok(updatedEmployee)` returns `200 OK` with the updated employee.
- **Delete employee by id**:
    - `ResponseEntity.notFound().build()` returns `404 Not Found` if the employee is not found
    - `ResponseEntity.ok().build()` returns `200 OK` if the employee is successfully deleted.
- **Upload CSV File**:
    - `ResponseEntity.status(500).build()` returns `500 Internal Server Error` if an `IOException` occurs
    - `ResponseEntity.ok(savedEmployees)` returns `200 OK` with the list of saved employees.
- **Get Employees by Department**:
    - `ResponseEntity.noContent().build()` returns `204 No Content` if no employees are found in the specified department
    - `ResponseEntity.ok(employees)` returns `200 OK` with the list of employees.

---

### 5️⃣ **Allow filtering, sorting, and pagination**

The databases behind a REST API can get very large. Sometimes, there's so much data that it shouldn’t be returned all at once because it’s way too slow or will bring down our systems. Therefore, we need ways to filter items.

We also need ways to paginate data so that we only return a few results at a time. We don't want to tie up resources for too long by trying to get all the requested data at once.

Filtering and pagination both increase performance by reducing the usage of server resources. As more data accumulates in the database, the more important these features become.

- In my codes, I already applied filtering practice. I have an endpoint to filter employees by department
- For sorting and pagination, I haven’t applied it yet in my codes.

---

### 6️⃣ **Maintain good security practices**

Most communication between client and server should be private since we often send and receive private information. Therefore, using SSL/TLS for security is a must.

Other than that, people shouldn't be able to access more information that they requested. For example, a normal user shouldn't be able to access information of another user. They also shouldn't be able to access data of admins.

We need to add role checks either for a single role, or have more granular roles for each user. If we choose to group users into a few roles, then the roles should have the permissions that cover all they need and no more.

- In my codes, I haven’t applied SSL/TLS and add role checks.

---

### 7️⃣ **Cache data to improve performance**

We can add caching to return data from the local memory cache instead of querying the database to get the data every time we want to retrieve some data that users request. The good thing about caching is that users can get data faster. However, the data that users get may be outdated. This may also lead to issues when debugging in production environments when something goes wrong as we keep seeing old data.

- In my codes, I haven’t implemented cache data to improve performance.

---

### 8️⃣ **Versioning our APIs**

We should have different versions of API if we're making any changes to them that may break clients. 

We can gradually phase out old endpoints instead of forcing everyone to move to the new API at the same time. The v1 endpoint can stay active for people who don’t want to change, while the v2 can serve those who are ready to upgrade. This is especially important if our API is public. We should version them so that we won't break third party apps that use our APIs.

Versioning is usually done with /v1/, /v2/, etc. added at the start of the API path.

- In my codes, I have implemented API versioning which is API v1.
    
    `@RequestMapping("/api/v1/employee")`
    
    This way, we can version them so that we won't break current API.