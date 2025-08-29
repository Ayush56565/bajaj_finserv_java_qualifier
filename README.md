# Bajaj Finserv Java Qualifier

This project is a Spring Boot application built as part of the **Bajaj Finserv Health Java Qualifier**. It automates the process of generating a webhook, selecting and solving an SQL problem, and submitting the solution with JWT authentication.

---

## 📌 Problem Statement

On startup, the application should:

1. **Generate a Webhook**
   - Sends a POST request to `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA` with student details.
   - Receives a `webhook` URL and an `accessToken` (JWT).

2. **Select SQL Problem**
   - Based on the last two digits of `regNo`:
     - **Odd** → Question 1 SQL
     - **Even** → Question 2 SQL (provided in the assignment)

3. **Submit SQL Solution**
   - Sends the SQL query as JSON to the `webhook` URL.
   - Uses the `accessToken` in the `Authorization` header.

---

## ⚙️ Tech Stack
- **Java 17**
- **Spring Boot 3.2+**
- **Maven**
- **RestTemplate (HTTP Client)**

---

## 🚀 How It Works
- The workflow runs **automatically on startup** (no REST endpoints required).
- The SQL solution is selected dynamically based on the `regNo`.
- The solution is submitted with proper authentication headers.

---

## 🛠 Setup & Run

### 1. Clone Repository
```bash
git clone https://github.com/<your-username>/java-qualifier.git
cd java-qualifier
```

### 2. Build JAR (skip tests to avoid package mismatch)
```bash
mvn clean package -DskipTests
```

### 3. Run Application
```bash
java -jar target/java-qualifier-1.0.0.jar
```

You should see output like:
```
Generated Webhook URL: https://...
Access Token: eyJhbGciOi...
Final SQL Query Selected: SELECT ...
Submission Response: 200 - {"status":"success"}
```

---

## 📜 SQL Solutions

### Odd RegNo (Question 1)
```sql
-- Placeholder query (replace with actual Q1 statement if provided)
SELECT department, COUNT(*) FROM employees GROUP BY department;
```

### Even RegNo (Question 2)
```sql
SELECT
    e1.EMP_ID,
    e1.FIRST_NAME,
    e1.LAST_NAME,
    d.DEPARTMENT_NAME,
    COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN EMPLOYEE e2
       ON e1.DEPARTMENT = e2.DEPARTMENT
      AND e2.DOB > e1.DOB
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY e1.EMP_ID DESC;
```

---

## 📦 Deliverables
- **GitHub Repo:** [https://github.com/<your-username>/java-qualifier.git](https://github.com/<your-username>/java-qualifier.git)
- **Final JAR (Downloadable):** [https://github.com/<your-username>/java-qualifier/raw/main/java-qualifier-1.0.0.jar](https://github.com/<your-username>/java-qualifier/raw/main/java-qualifier-1.0.0.jar)

---

## ✅ Checklist
- [x] Spring Boot app runs automatically on startup
- [x] Generates webhook and retrieves JWT
- [x] Selects SQL query dynamically
- [x] Submits solution with JWT authentication
- [x] Builds final JAR
- [x] Submission-ready with GitHub repo and raw JAR link

---

## 👨‍💻 Author
**Ayush Sengupta**  
Email: ayush.sengupta2022@vitstudent.ac.in 
RegNo: 22BAI1032

