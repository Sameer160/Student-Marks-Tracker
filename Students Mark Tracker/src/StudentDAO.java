import java.sql.*;
import java.util.*;

public class StudentDAO {

    public void addStudent(String name) throws Exception {
        String sql = "INSERT INTO students (name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public void addMarks(int studentId, String subject, int marks) throws Exception {
        String sql = "INSERT INTO marks (student_id, subject, marks) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, subject);
            stmt.setInt(3, marks);
            stmt.executeUpdate();
        }
    }

    public List<String> getMarksByStudent(int studentId) throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "SELECT subject, marks FROM marks WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("subject") + ": " + rs.getInt("marks"));
            }
        }
        return list;
    }

    public double calculateAverage(int studentId) throws Exception {
        String sql = "SELECT AVG(marks) AS average FROM marks WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("average");
            }
        }
        return 0.0;
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name")));
            }
        }
        return students;
    }
}
