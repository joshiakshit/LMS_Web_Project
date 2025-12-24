package dao;

import model.Course;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM courses";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("instructor_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
    // Method to add a new course
    public boolean addCourse(Course course) {
        boolean rowInserted = false;
        try (Connection con = DBConnection.getConnection()) {
            String sql = "INSERT INTO courses (title, description, instructor_name) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, course.getTitle());
            ps.setString(2, course.getDescription());
            ps.setString(3, course.getInstructorName());

            rowInserted = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
    // Innovation: Search Functionality
    public List<Course> searchCourses(String query) {
        List<Course> courses = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM courses WHERE title LIKE ? OR description LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            String searchPattern = "%" + query + "%"; // The % symbols are SQL wildcards
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("instructor_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
}