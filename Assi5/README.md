# Employee Registration Web Application (Enhanced)

A JSP + Servlet + JDBC web app for registering employees into a MySQL database.


### UI
- Dark, modern theme with animated gradient blobs
- DM Serif Display + DM Sans fonts for a refined, editorial look
- Real-time client-side field validation (green ✓ / red border + message)
- Two-column responsive grid layout for compact, professional feel
- Animated card entrance and success checkmark pop

### Features
- **Department** dropdown (Engineering, Marketing, HR, Finance, Operations, Sales)
- **Phone number** field (optional)
- **Email regex validation** on both client and server side
- **Print / Save** button on success page
- Improved success page showing all registered details in a styled card grid

## File Structure

```
Assignment_5/
├── src/com/example/
│   ├── Employee.java          # Model: id, name, email, salary, department, phone
│   ├── DBUtil.java            # JDBC connection helper
│   └── EmployeeServlet.java   # Servlet — validation + DB insert
├── WebContent/
│   ├── index.jsp              # Registration form (enhanced UI)
│   ├── success.jsp            # Success summary page (enhanced UI)
│   └── WEB-INF/web.xml
├── create_employee_db.sql     # DB setup (now includes department + phone columns)
└── assign_05.java
```

## Setup

### 1. Database
```sql
CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

CREATE TABLE IF NOT EXISTS employees (
    id         INT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL,
    salary     DOUBLE       NOT NULL,
    department VARCHAR(100) NOT NULL,
    phone      VARCHAR(30)  DEFAULT NULL
);
```

### 2. JDBC credentials
Edit `DBUtil.java` and set `DB_USER` / `DB_PASS` for your MySQL installation.

### 3. JDBC driver
Place `mysql-connector-j-*.jar` in `apache-tomcat/lib` (or project classpath).

### 4. Run
Deploy to Tomcat and open `http://localhost:8080/<your-app-name>/`.

## Validation Rules
| Field       | Rule                              |
|-------------|-----------------------------------|
| Employee ID | Integer between 1 and 20          |
| Department  | Must select from dropdown         |
| Name        | Non-empty string                  |
| Email       | Must match `x@y.z` pattern        |
| Salary      | Positive decimal number           |
| Phone       | Optional — stored as-is           |
