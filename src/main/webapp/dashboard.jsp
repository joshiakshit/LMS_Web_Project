<%@ page import="model.User" %>
<%@ page import="dao.CourseDAO" %>
<%@ page import="model.Course" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 1. Security Check
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // 2. Search Logic
    CourseDAO courseDAO = new CourseDAO();
    List<Course> courseList;

    String searchQuery = request.getParameter("q");
    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
        courseList = courseDAO.searchCourses(searchQuery);
    } else {
        courseList = courseDAO.getAllCourses();
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>LMS Dashboard</title>
    <style>
        body { font-family: 'Segoe UI', sans-serif; padding: 30px; background-color: #f4f6f9; }
        h1 { color: #333; }
        table { width: 100%; border-collapse: collapse; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #0d6efd; color: white; }
        tr:hover { background-color: #f1f1f1; }
        .btn { padding: 10px 15px; text-decoration: none; border-radius: 5px; color: white; display: inline-block; }
        .btn-green { background-color: #28a745; }
        .btn-red { background-color: #dc3545; }
    </style>
</head>
<body>
<div style="display: flex; justify-content: space-between; align-items: center;">
    <h1>Welcome, <%= user.getEmail() %>!</h1>
    <a href="logout.jsp" class="btn btn-red">Logout</a>
</div>

<p>Role: <strong><%= user.getRole() %></strong></p>

<hr>

<% if (!"Student".equalsIgnoreCase(user.getRole())) { %>
<div style="margin-bottom: 20px;">
    <a href="addCourse.jsp" class="btn btn-green">+ Add New Course</a>
</div>
<% } %>

<h3>Available Courses</h3>

<div style="margin-bottom: 15px;">
    <form action="dashboard.jsp" method="get">
        <input type="text" name="q" placeholder="Search by title..."
               value="<%= (searchQuery != null) ? searchQuery : "" %>"
               style="padding: 8px; width: 250px;">
        <button type="submit" style="padding: 8px;">Search</button>
        <% if (searchQuery != null && !searchQuery.isEmpty()) { %>
        <a href="dashboard.jsp" style="margin-left: 10px;">Clear</a>
        <% } %>
    </form>
</div>

<table>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Instructor</th>
    </tr>
    <% if (courseList.isEmpty()) { %>
    <tr>
        <td colspan="4" style="text-align: center; color: #777;">No courses found matching "<%= searchQuery %>"</td>
    </tr>
    <% } else { %>
    <% for (Course c : courseList) { %>
    <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getDescription() %></td>
        <td><%= c.getInstructorName() %></td>
    </tr>
    <% } %>
    <% } %>
</table>

</body>
</html>