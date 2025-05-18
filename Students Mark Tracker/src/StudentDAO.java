import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void addStudent(String name) throws Exception {
        String sql = "INSERT INTO students (name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public void addGrade(int studentId, String course, double grade) throws Exception {
        String sql = "INSERT INTO grades (student_id, course, grade) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setString(2, course);
            stmt.setDouble(3, grade);
            stmt.executeUpdate();
        }
    }

    public List<String> getGradesByStudent(int studentId) throws Exception {
        List<String> grades = new ArrayList<>();
        String sql = "SELECT course, grade FROM grades WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                grades.add(rs.getString("course") + ": " + rs.getDouble("grade"));
            }
        }
        return grades;
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
