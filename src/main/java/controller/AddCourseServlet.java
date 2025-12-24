package controller;

import dao.CourseDAO;
import model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCourseServlet", urlPatterns = {"/AddCourseServlet"})
public class AddCourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Retrieve data from the form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String instructorName = request.getParameter("instructor"); // We will send this from the form hidden field

        // 2. Create Course Object (ID is 0 because DB auto-increments it)
        Course newCourse = new Course(0, title, description, instructorName);

        // 3. Save to DB
        CourseDAO dao = new CourseDAO();
        boolean success = dao.addCourse(newCourse);

        // 4. Redirect back to dashboard
        if(success) {
            response.sendRedirect("dashboard.jsp?msg=Course Added Successfully");
        } else {
            response.sendRedirect("addCourse.jsp?error=Failed to Add Course");
        }
    }
}