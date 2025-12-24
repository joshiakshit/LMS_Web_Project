<!DOCTYPE html>
<html>
<head>
    <title>LMS Registration</title>
    <style>
        body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f8f9fa; }
        .card { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); width: 350px; }
        input, select { width: 100%; padding: 10px; margin: 8px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        button { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; cursor: pointer; }
        .error { color: red; font-size: 14px; text-align: center; }
        a { display: block; text-align: center; margin-top: 10px; text-decoration: none; color: #007bff; }
    </style>
</head>
<body>
<div class="card">
    <h2 style="text-align:center">Sign Up</h2>

    <% String error = request.getParameter("error"); %>
    <% if (error != null) { %> <p class="error"><%= error %></p> <% } %>

    <form action="RegisterServlet" method="post">
        <input type="email" name="email" placeholder="Email Address" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required>

        <label for="role">I am a:</label>
        <select name="role">
            <option value="Student">Student</option>
            <option value="Instructor">Instructor</option>
        </select>

        <button type="submit">Register</button>
    </form>
    <a href="login.jsp">Already have an account? Login</a>
</div>
</body>
</html>