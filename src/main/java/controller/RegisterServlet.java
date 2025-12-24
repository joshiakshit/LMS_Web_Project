package controller;

import dao.UserDAO;
import model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPassword");
        String role = request.getParameter("role"); // Student or Instructor

        // Validation 1: Passwords match
        if (!password.equals(confirmPass)) {
            response.sendRedirect("register.jsp?error=Passwords do not match");
            return;
        }

        UserDAO dao = new UserDAO();

        // Validation 2: Email already taken?
        if (dao.isEmailRegistered(email)) {
            response.sendRedirect("register.jsp?error=Email already registered");
            return;
        }

        // Create User (ID is 0 for auto-increment)
        User newUser = new User(0, email, password, role);

        if (dao.registerUser(newUser)) {
            // Success: Redirect to login
            response.sendRedirect("login.jsp?msg=Registration Successful! Please Login.");
        } else {
            response.sendRedirect("register.jsp?error=Database Error");
        }
    }
}