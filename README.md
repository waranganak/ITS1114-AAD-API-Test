# ITS1114 AAD API Test

A Spring Boot RESTful API project built for the **ITS 1114 - Advanced Application Development** module at IJSE.

## Tech Stack

| Technology         | Version |
| ------------------ | ------- |
| Java               | 17      |
| Spring Boot        | 4.0.6   |
| Spring Data JPA    | —       |
| MySQL              | 8.x     |
| Lombok             | —       |
| Maven              | —       |

## Getting Started

### Prerequisites

- **JDK 21** or later
- **MySQL 8.x** running on `localhost:3306`
- **Maven** (or use the included `mvnw` wrapper)

### Database Setup

The application will auto-create the database `aad_first_project` if it doesn't exist.  
Update credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/aad_first_project?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
```

### Run the Application

```bash
# Using Maven wrapper
./mvnw spring-boot:run

# Or using Maven directly
mvn spring-boot:run
```

The server starts at **http://localhost:8080**

---

## API Endpoints & Example JSON

> **Base URL:** `http://localhost:8080/v1/api`

---

### 🏓 Health Check (Ping)

#### `GET /v1/api/ping`

**Response:**
```
pong
```

---

### 👤 Customer API

Base path: `/v1/api/customers`

#### `POST /v1/api/customers` — Save Customer

**Request Body:**
```json
{
  "id": 0,
  "name": "Kaumini Warangana",
  "phone": "0703073428",
  "address": "123, Main Street, Colombo"
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Kaumini Warangana",
  "phone": "0703073428",
  "address": "123, Main Street, Colombo"
}
```

---

### 🏢 Department API

Base path: `/v1/api/departments`

#### `POST /v1/api/departments` — Save Department

**Request Body:**
```json
{
  "departmentId": 0,
  "departmentName": "Computer Science",
  "departmentLocation": "Building A, Floor 2"
}
```

**Response:**
```
"Department save successfully"
```

---

#### `GET /v1/api/departments/all` — Get All Departments

**Response:**
```json
[
  {
    "departmentId": 1,
    "departmentName": "Computer Science",
    "departmentLocation": "Building A, Floor 2"
  },
  {
    "departmentId": 2,
    "departmentName": "Information Technology",
    "departmentLocation": "Building B, Floor 1"
  }
]
```

---

#### `GET /v1/api/departments/department-details/{id}` — Get Department by ID

**Example:** `GET /v1/api/departments/department-details/1`

**Response:**
```json
{
  "departmentId": 1,
  "departmentName": "Computer Science",
  "departmentLocation": "Building A, Floor 2"
}
```

---

#### `PUT /v1/api/departments/update` — Update Department

**Request Body:**
```json
{
  "departmentId": 1,
  "departmentName": "Computer Science & Engineering",
  "departmentLocation": "Building A, Floor 3"
}
```

**Response:**
```json
{
  "departmentId": 1,
  "departmentName": "Computer Science & Engineering",
  "departmentLocation": "Building A, Floor 3"
}
```

---

### 🛒 Order API

Base path: `/v1/api/orders`

#### `POST /v1/api/orders` — Save Order

**Request Body (OrderSaveDTO):**
```json
{
  "orderDate": "2026-06-01",
  "price": 2500.50,
  "customerId": 1
}
```

**Response (OrderDTO):**
```json
{
  "id": 1,
  "orderDate": "2026-06-01",
  "price": 2500.50,
  "customerId": 1
}
```

---

### 🏫 School API

Base path: `/v1/api/school`

#### `POST /v1/api/school` — Save School

**Request Body:**
```json
{
  "id": 0,
  "location": "Galle Road, Colombo 03",
  "name": "Royal College"
}
```

**Response:**
```json
{
  "id": 1,
  "location": "Galle Road, Colombo 03",
  "name": "Royal College"
}
```

---

#### `GET /v1/api/school/all` — Get All Schools

**Response:**
```json
[
  {
    "id": 1,
    "location": "Galle Road, Colombo 03",
    "name": "Royal College"
  },
  {
    "id": 2,
    "location": "Reid Avenue, Colombo 07",
    "name": "Ananda College"
  }
]
```

---

#### `GET /v1/api/school/school-details/{id}` — Get School by ID

**Example:** `GET /v1/api/school/school-details/1`

**Response:**
```json
{
  "id": 1,
  "location": "Galle Road, Colombo 03",
  "name": "Royal College"
}
```

---

#### `PUT /v1/api/school/update` — Update School

**Request Body:**
```json
{
  "id": 1,
  "location": "Galle Road, Colombo 04",
  "name": "Royal College Colombo"
}
```

**Response:**
```json
{
  "id": 1,
  "location": "Galle Road, Colombo 04",
  "name": "Royal College Colombo"
}
```

---

### 🎓 Student API

Base path: `/v1/api/students`

#### `POST /v1/api/students` — Save Student

**Request Body:**
```json
{
  "id": 0,
  "fName": "Kamal",
  "lName": "Perera",
  "dob": "2000-05-15",
  "address": "45, Temple Road, Kandy",
  "schoolId": 1
}
```

**Response:**
```json
{
  "id": 1,
  "fName": "Kamal",
  "lName": "Perera",
  "dob": "2000-05-15",
  "address": "45, Temple Road, Kandy",
  "schoolId": 1
}
```

---

#### `GET /v1/api/students/all` — Get All Students

**Response:**
```json
[
  {
    "id": 1,
    "fName": "Kamal",
    "lName": "Perera",
    "dob": "2000-05-15",
    "address": "45, Temple Road, Kandy",
    "schoolId": 1
  },
  {
    "id": 2,
    "fName": "Nimal",
    "lName": "Silva",
    "dob": "2001-08-22",
    "address": "78, Lake Road, Galle",
    "schoolId": 2
  }
]
```

---

#### `GET /v1/api/students/student-details/{id}` — Get Student by ID

**Example:** `GET /v1/api/students/student-details/1`

**Response:**
```json
{
  "id": 1,
  "fName": "Kamal",
  "lName": "Perera",
  "dob": "2000-05-15",
  "address": "45, Temple Road, Kandy",
  "schoolId": 1
}
```

---

#### `PUT /v1/api/students/update` — Update Student

**Request Body:**
```json
{
  "id": 1,
  "fName": "Kamal",
  "lName": "Perera",
  "dob": "2000-05-15",
  "address": "99, New Road, Colombo",
  "schoolId": 1
}
```

**Response:**
```json
{
  "id": 1,
  "fName": "Kamal",
  "lName": "Perera",
  "dob": "2000-05-15",
  "address": "99, New Road, Colombo",
  "schoolId": 1
}
```

---

### 👥 User API

Base path: `/v1/api/users`

> **UserStatus enum values:** `ACTIVE`, `INACTIVE`, `DELETED`

#### `POST /v1/api/users` — Save User

**Request Body:**
```json
{
  "id": 0,
  "firstName": "Sunil",
  "lastName": "Fernando",
  "dob": "1995-03-10T00:00:00.000+00:00",
  "status": "ACTIVE"
}
```

**Response:**
```json
{
  "id": 1,
  "firstName": "Sunil",
  "lastName": "Fernando",
  "dob": "1995-03-10T00:00:00.000+00:00",
  "status": "ACTIVE"
}
```

---

#### `GET /v1/api/users` — Get All Users

**Response:**
```json
[
  {
    "id": 1,
    "firstName": "Sunil",
    "lastName": "Fernando",
    "dob": "1995-03-10T00:00:00.000+00:00",
    "status": "ACTIVE"
  },
  {
    "id": 2,
    "firstName": "Amal",
    "lastName": "Jayawardena",
    "dob": "1998-07-25T00:00:00.000+00:00",
    "status": "INACTIVE"
  }
]
```

---

#### `GET /v1/api/users/user-details/{id}` — Get User by ID

**Example:** `GET /v1/api/users/user-details/1`

**Response:**
```json
{
  "id": 1,
  "firstName": "Sunil",
  "lastName": "Fernando",
  "dob": "1995-03-10T00:00:00.000+00:00",
  "status": "ACTIVE"
}
```

---

#### `PUT /v1/api/users/update` — Update User

**Request Body:**
```json
{
  "id": 1,
  "firstName": "Sunil",
  "lastName": "Fernando",
  "dob": "1995-03-10T00:00:00.000+00:00",
  "status": "ACTIVE"
}
```

**Response:**
```json
{
  "id": 1,
  "firstName": "Sunil",
  "lastName": "Fernando",
  "dob": "1995-03-10T00:00:00.000+00:00",
  "status": "ACTIVE"
}
```

---

#### `PATCH /v1/api/users/update-status` — Update User Status

**Request Body:**
```json
{
  "id": 1,
  "firstName": null,
  "lastName": null,
  "dob": null,
  "status": "INACTIVE"
}
```

**Response:**
```
"User status update successfully"
```

---

#### `DELETE /v1/api/users/delete/{id}` — Delete User

**Example:** `DELETE /v1/api/users/delete/1`

**Response:**
```
"User delete successfully"
```

---

## API Summary Table

| Method   | Endpoint                                    | Description            | Request Body   |
| -------- | ------------------------------------------- | ---------------------- | -------------- |
| `GET`    | `/v1/api/ping`                              | Health check           | —              |
| `POST`   | `/v1/api/customers`                         | Save customer          | CustomerDTO    |
| `POST`   | `/v1/api/departments`                       | Save department        | DepartmentDTO  |
| `GET`    | `/v1/api/departments/all`                   | Get all departments    | —              |
| `GET`    | `/v1/api/departments/department-details/{id}` | Get department by ID | —              |
| `PUT`    | `/v1/api/departments/update`                | Update department      | DepartmentDTO  |
| `POST`   | `/v1/api/orders`                            | Save order             | OrderSaveDTO   |
| `POST`   | `/v1/api/school`                            | Save school            | SchoolDTO      |
| `GET`    | `/v1/api/school/all`                        | Get all schools        | —              |
| `GET`    | `/v1/api/school/school-details/{id}`        | Get school by ID       | —              |
| `PUT`    | `/v1/api/school/update`                     | Update school          | SchoolDTO      |
| `POST`   | `/v1/api/students`                          | Save student           | StudentDTO     |
| `GET`    | `/v1/api/students/all`                      | Get all students       | —              |
| `GET`    | `/v1/api/students/student-details/{id}`     | Get student by ID      | —              |
| `PUT`    | `/v1/api/students/update`                   | Update student         | StudentDTO     |
| `POST`   | `/v1/api/users`                             | Save user              | UsersDTO       |
| `GET`    | `/v1/api/users`                             | Get all users          | —              |
| `GET`    | `/v1/api/users/user-details/{id}`           | Get user by ID         | —              |
| `PUT`    | `/v1/api/users/update`                      | Update user            | UsersDTO       |
| `PATCH`  | `/v1/api/users/update-status`               | Update user status     | UsersDTO       |
| `DELETE` | `/v1/api/users/delete/{id}`                 | Delete user            | —              |

---

## Project Structure

```
src/main/java/lk/ijse/AAD/
├── AadApplication.java          # Main Spring Boot Application
├── controller/
│   ├── Test.java                # Ping / Health check
│   ├── CustomerController.java  # Customer endpoints
│   ├── DepartmentController.java# Department endpoints
│   ├── OrderController.java     # Order endpoints
│   ├── SchoolController.java    # School endpoints
│   ├── StudentController.java   # Student endpoints
│   └── UserController.java      # User endpoints
├── dto/
│   ├── CustomerDTO.java
│   ├── DepartmentDTO.java
│   ├── OrderDTO.java
│   ├── OrderSaveDTO.java
│   ├── SchoolDTO.java
│   ├── StudentDTO.java
│   └── UsersDTO.java
├── entity/
│   ├── Customer.java
│   ├── Department.java
│   ├── Order.java
│   ├── School.java
│   ├── Student.java
│   ├── UserDepartment.java
│   └── Users.java
├── enumaration/
│   └── UserStatus.java          # ACTIVE, INACTIVE, DELETED
├── repository/
│   └── ...                      # Spring Data JPA repositories
└── service/
    └── ...                      # Service layer
```

---

## License

This project is for educational purposes as part of .
# ITS1114-AAD-API-Test
