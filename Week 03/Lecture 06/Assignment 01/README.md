## 💡 Normalization in Database

### 💭 Definition

Database normalization is a database design principle for organizing data in an organized and consistent way. It helps us avoid redundancy and maintain the integrity of the database. It is also used to eliminate undesirable characteristics like Insertion, Update, and Deletion Anomalies. Normalization divides the larger table into smaller and links them using relationships. The normal form is used to reduce redundancy from the database table.

---

### ❔ Why Do We Need Normalization?

The main reason for normalizing the relations is removing these anomalies. Failure to eliminate anomalies leads to data redundancy and can cause data integrity and other problems as the database grows. Normalization consists of a series of guidelines that helps to guide us in creating a good database structure.

**Data modification anomalies can be categorized into three types:**

- **Insertion Anomaly**: Refers to when one cannot insert a new tuple into a relationship due to lack of data
- **Deletion Anomaly:** Refers to the situation where the deletion of data results in the unintended loss of some other important data
- **Update Anomaly:** When an update of a single data value requires multiple rows of data to be updated.

---

### 👩‍🏫 Types of Normal Forms

- 1NF, 2NF, and 3NF are the first three types of database normalization. They stand for **first normal form**, **second normal form**, and **third normal form**, respectively.
- There are also 4NF (fourth normal form), and 5NF (fifth normal form). There is even 6NF (sixth normal form), but the commonest normal form we will see out there is 3NF (third normal form).
- All the types of database normalization are cumulative. It means each one builds on top of those beneath it. So all the concepts in 1NF also carry over to 2NF, and so on.

---

### 1️⃣ The First Normal Form - 1NF

A table is in 1NF if:

- A single cell must not hold more than one value (atomicity)
- There must be a primary key for identification
- Each column must have only one value for each row in the table
- The order of rows does not matter.

**Unnormalized table example:**

| EmployeeID | EmployeeName | Projects |
| --- | --- | --- |
| 1 | Renata May | Project A, Project B |
| 2 | June Li | Project C |
| 3 | Sera Livia | Project A, Project C |

In this table, `Projects` column contains multiple values (Project A, Project B), which violates 1NF.

**1NF table:**
To convert that table into 1NF, we split the `Projects` column into individual rows:

| EmployeeID | EmployeeName | Project |
| --- | --- | --- |
| 1 | Renata May | Project A |
| 1 | Renata May | Project B |
| 2 | June Li | Project C |
| 3 | Sera Livia | Project A |
| 3 | Sera Livia | Project C |

This table is already 1NF table as a single cell only holds one value.

---

### 2️⃣ The Second Normal Form - 2NF

The 1NF only eliminates repeating groups, not redundancy. That is why there is 2NF. A table is said to be in 2NF if it meets the following criteria:

- It’s already in 1NF
- Has no partial dependency. That is, all non-key attributes are fully dependent on a primary key.

**1NF table:**

| EmployeeID | EmployeeName | Project |
| --- | --- | --- |
| 1 | Renata May | Project A |
| 1 | Renata May | Project B |
| 2 | June Li | Project C |
| 3 | Sera Livia | Project A |
| 3 | Sera Livia | Project C |

In this table, `EmployeeID` and `Project` together form the composite primary key. However, `EmployeeName` depends only on `EmployeeID`, not on the entire composite key.

**2NF tables:**

To achieve 2NF, we need to separate the data into two tables, which one for employee details and another for project assignments.

1. **Employee table**
    
    
    | EmployeeID | EmployeeName |
    | --- | --- |
    | 1 | Renata May |
    | 2 | June Li |
    | 3 | Sera Livia |
2. **Project assignment table**
    
    
    | EmployeeID | Project |
    | --- | --- |
    | 1 | Project A |
    | 1 | Project B |
    | 2 | Project C |
    | 3 | Project A |
    | 3 | Project C |

---

### 3️⃣ The Third Normal Form - 3NF

When a table is in 2NF, it eliminates repeating groups and redundancy, but it does not eliminate transitive partial dependency. This means a non-prime attribute (an attribute that is not part of the candidate’s key) is dependent on another non-prime attribute. This is what the third normal form (3NF) eliminates.

So, for a table to be in 3NF, it must:

- Be in 2NF
- Have no transitive partial dependency.

**2NF tables:**

1. **Employee table**
    
    
    | EmployeeID | EmployeeName | Department |
    | --- | --- | --- |
    | 1 | Renata May | HR |
    | 2 | June Li | IT |
    | 3 | Sera Livia | HR |
2. **Project assignment table**
    
    
    | EmployeeID | Project |
    | --- | --- |
    | 1 | Project A |
    | 1 | Project B |
    | 2 | Project C |
    | 3 | Project A |
    | 3 | Project C |

Here, `Department` depends on `EmployeeID`, but if there are more details related to the department, this could create a transitive dependency. For example, if `DepartmentHead` was another attribute in the `Employee Table`, it would depend on `Department`, which is not the primary key.

**3NF tables:**

To remove the transitive dependency, we create a separate table for departments.

1. **Employee table**
    
    
    | EmployeeID | EmployeeName |
    | --- | --- |
    | 1 | Renata May |
    | 2 | June Li |
    | 3 | Sera Livia |
2. **Department table**
    
    
    | EmployeeID | Department |
    | --- | --- |
    | 1 | HR |
    | 2 | IT |
    | 3 | HR |
3. **Project assignment table**
    
    
    | EmployeeID | Project |
    | --- | --- |
    | 1 | Project A |
    | 1 | Project B |
    | 2 | Project C |
    | 3 | Project A |
    | 3 | Project C |
4. **Department details table (optional, if more attributes like DepartmentHead are needed)**
    
    
    | Department | DepartmentHead |
    | --- | --- |
    | HR | Kanara Damita |
    | IT | Jake Logan |

By structuring the tables this way, we ensure that each table is in 3NF, eliminating redundant data and improving data integrity.