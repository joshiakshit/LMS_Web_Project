<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Security Check: Kick out if not logged in
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("login.jsp"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Course</title>
    <style>
        body { font-family: sans-serif; padding: 20px; }
        form { max-width: 400px; margin: auto; }
        input, textarea { width: 100%; padding: 10px; margin: 10px 0; }
        button { background-color: #28a745; color: white; border: none; padding: 10px 20px; cursor: pointer; }
    </style>
</head>
<body>
<h2>Add New Course</h2>
<form action="AddCourseServlet" method="post">
    <label>Course Title:</label>
    <input type="text" name="title" required>

    <label>Description:</label>
    <textarea name="description" rows="4" required></textarea>

    <input type="hidden" name="instructor" value="<%= user.getEmail() %>">

    <button type="submit">Publish Course</button>
</form>
<br>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>