## 💡 **Basic Spring CRUD Example**

### 1️⃣ Init spring boot project

![Init spring boot project](img/initproject.png)

### 2️⃣ Add maven dependencies

Add maven dependencies in `pom.xml`.

![Add maven dependencies](img/maven.png)

### 3️⃣ Init database

Create database called `lecture_5` in phpMyAdmin.

![Init database](img/db.png)

### 4️⃣ Configure properties

Configure properties in `application.properties`.

![Configure properties](img/conf.png)

### 5️⃣ Create model

[Create model codes](img/maven.png)

### 6️⃣ Create jpa repository

[Create jpa repository codes](img/maven.png)

### 7️⃣ Create controller

[Create controller codes](img/maven.png)

**@GetMapping to get all the contacts**

![Get all contacts](img/getallcont.png)

**@GetMapping(value = “/{id}”) to get contact detail by id**

![Get contact detail by id](img/getbyid.png)

**@PostMapping to create new contact**

![Create new contact](img/createcont.png)

**@PutMapping(value = “/{id}”) to update contact by id**

![Update contact by id](img/updatecont.png)

**@DeleteMapping(value = “/contact/{id}”) to delete contact by id**

![Delete contact by id](img/deletecont.png)

### 8️⃣ Import postman collection

After importing [`Findo-lecture-5.postman_collection.json`](https://github.com/NguyenVanTrieu/spring-crud/blob/main/image%2FFindo-lecture-5.postman_collection.json) in postman, we already can do CRUD operation in here.

**Get all contact**

![Get all contacts](img/getallcont1.png)

**Get contact detail by id**

![Get contact detail by id](img/getbyid1.png)

**Create new contact**

![Create new contact](img/createcont1.png)

The new contact is already added.

![Result new contact](img/createresult.png)

**Update contact by id**

![Update contact by id](img/updatecont1.png)

**Delete contact by id**

![Delete contact by id](img/deletecont1.png)