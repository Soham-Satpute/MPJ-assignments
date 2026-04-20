package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idText      = request.getParameter("id");
        String name        = request.getParameter("name");
        String email       = request.getParameter("email");
        String salaryText  = request.getParameter("salary");
        String department  = request.getParameter("department");
        String phone       = request.getParameter("phone");   // optional

        String message = null;
        String error   = null;

        try {
            int id       = Integer.parseInt(idText);
            double salary = Double.parseDouble(salaryText);

            if (id < 1 || id > 20)
                throw new IllegalArgumentException("Employee ID must be between 1 and 20.");
            if (name == null || name.trim().isEmpty())
                throw new IllegalArgumentException("Name cannot be empty.");
            if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
                throw new IllegalArgumentException("Please enter a valid email address.");
            if (department == null || department.trim().isEmpty())
                throw new IllegalArgumentException("Please select a department.");
            if (salary <= 0)
                throw new IllegalArgumentException("Salary must be a positive value.");

            // Sanitise optional phone
            if (phone != null && phone.trim().isEmpty()) phone = null;

            Employee employee = new Employee(id, name.trim(), email.trim(), salary, department.trim(), phone);
            saveEmployee(employee);
            message = "Employee registered successfully.";
            request.setAttribute("employee", employee);

        } catch (NumberFormatException e) {
            error = "Please enter valid numeric values for ID and salary.";
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        } catch (SQLException e) {
            error = "Database error: " + e.getMessage();
        }

        if (error != null) {
            request.setAttribute("error", error);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("success.jsp");
            rd.forward(request, response);
        }
    }

    private void saveEmployee(Employee emp) throws SQLException {
        String sql = "INSERT INTO employees (id, name, email, salary, department, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getEmail());
            ps.setDouble(4, emp.getSalary());
            ps.setString(5, emp.getDepartment());
            ps.setString(6, emp.getPhone());
            ps.executeUpdate();
        }
    }
}
