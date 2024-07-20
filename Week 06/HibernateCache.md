## 💾 Hibernate Cache

Hibernate caching is a mechanism to improve the performance of the application by reducing the number of database queries. The types of hibernate cache are first-level cache, second-level cache, and query cache.

---

### 1️⃣ First-level cache

The first-level cache is associated with the `Session` object in Hibernate. It is enabled by default and cannot be turned off. The first-level cache is maintained at the session level and is valid only within the scope of a session.

**❔ How it works**

- When an entity is loaded from the database, it is stored in the first-level cache
- Subsequent requests to load the same entity within the same session will be served from the cache rather than querying the database again
- The cache is cleared when the session is closed.

**Example:**

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

// Load employee with ID 1
Employee employee1 = session.get(Employee.class, 1);
System.out.println(employee1.getName()); // Hits the database

// Load the same employee again
Employee employee2 = session.get(Employee.class, 1);
System.out.println(employee2.getName()); // Uses the cache, no database hit

tx.commit();
session.close();
```

In this example, the first query will hit the database, but the second query for the same employee within the same session will be served from the cache.

---

### 2️⃣ Second-level cache

The second-level cache is optional and is configured at the session factory level. It can cache objects across sessions, making it more useful for applications where the same data is accessed frequently by different sessions.

**❔ How it works**

- Entities, collections, and queries can be cached
- Requires additional configuration to enable and specify the caching provider (e.g., EHCache, Infinispan, etc.)
- Objects in the second-level cache can be shared across multiple sessions.

---

### 3️⃣ Query Cache

The query cache stores the results of query executions and can significantly improve performance by avoiding repeated query execution. However, the query cache is only useful if the data doesn't change frequently or if the changes are acceptable to be slightly outdated for some time.

**❔ How it works**

- The query cache stores the results of queries
- When a cached query is executed, Hibernate checks if the query result is already in the cache. If it is, it returns the cached result. If not, it executes the query and stores the result in the cache
- The query cache works in conjunction with the second-level cache because it caches only identifiers of the entities returned by the query. The actual entity data is retrieved from the second-level cache.

---

### 👩‍🏫 Benefits of using caching

- **Reduced database load**: Fewer queries to the database, leading to reduced load and better performance
- **Improved application performance**: Faster data retrieval by serving data from the cache
- **Scalability**: Helps in scaling the application by reducing the database bottleneck.