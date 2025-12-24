# Learning Management System (LMS)

## 📌 Project Overview

A robust **Java Web Application** designed to manage educational resources. Built using the **MVC (Model-View-Controller)** architecture, this system facilitates secure user authentication, role-based access control (Student vs. Instructor), and dynamic course management.

The project complies with industry-standard development practices, including server-side validation, secure database connectivity, and modular code organization.

## 🚀 Key Features

### 1. User Authentication & Security

* **Secure Login/Signup:** Users can register as "Student" or "Instructor".

* **Data Validation:** Prevents duplicate email registrations to ensure data integrity.

* **Session Management:** Protects internal pages (Dashboard) from unauthorized access.

### 2. Role-Based Access Control (RBAC)

* **Instructors:** Have exclusive rights to **Create** and **Publish** new courses.

* **Students:** Can **View** and **Browse** the course catalog.

* **Dynamic UI:** The dashboard automatically adjusts its interface (buttons/links) based on the logged-in user's role.

### 3. Innovation: Dynamic Search Engine

* Features a **Live Search Bar** on the dashboard.

* Allows users to filter courses by title or description using optimized SQL `LIKE` queries.

* Provides instant feedback and "Clear Search" functionality.

## 🛠️ Technical Stack

* **Language:** Java (JDK 17+)

* **Framework:** Jakarta EE Servlets & JSP

* **Architecture:** MVC (Model-View-Controller)

* **Database:** MySQL 8.0

* **Server:** Apache Tomcat 10.x

* **Build Tool:** Maven

* **Frontend:** HTML5, CSS3, JSP

## 📂 Project Structure

```text
LMS_Project
├── src/main/java
│   ├── controller   # Servlets (Login, Register, AddCourse, Search)
│   ├── dao          # Data Access Objects (UserDAO, CourseDAO)
│   ├── model        # Java Beans (User, Course)
│   └── util         # DBConnection (Singleton Pattern)
├── src/main/webapp
│   ├── dashboard.jsp    # Main Hub (Role-specific view)
│   ├── login.jsp        # Authentication Entry
│   ├── register.jsp     # User Registration
│   ├── addCourse.jsp    # Course Creation Form
│   └── WEB-INF/web.xml  # Deployment Descriptor
└── pom.xml              # Maven Dependencies
```

## ⚙️ Setup & Installation

### 1. Database Setup

Create a MySQL database named `lms_db` and run the following scripts:

```sql
CREATE DATABASE lms_db;
USE lms_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'Student'
);

CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    instructor_name VARCHAR(100)
);
```

### 2. Application Configuration

1. Open `src/main/java/util/DBConnection.java`.

2. Update the `URL`, `USERNAME`, and `PASSWORD` fields to match your local MySQL configuration.

### 3. Build & Run

1. Open the project in **IntelliJ IDEA Ultimate**.

2. Ensure **Apache Tomcat** is configured in "Run Configurations".

3. Click **Run**. The application will launch at `http://localhost:8080/[ArtifactName]`.

## 🧪 Testing Guide

1. **Registration:** Sign up with a new email (Role: Student).

2. **Duplicate Check:** Try signing up with the same email again to verify the error message.

3. **Instructor Mode:**

    * Manually set a user's role to 'Instructor' in the database (or sign up as one).

    * Verify that the green **"+ Add New Course"** button appears on the dashboard.

4. **Search:** Type a keyword (e.g., "Java") in the dashboard search bar and verify the table filters correctly.

## 📝 License

This project is developed for academic purposes as part of the Java Web Application coursework.