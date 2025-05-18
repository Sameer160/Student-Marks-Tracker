import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Grades Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Add Grade");
            System.out.println("4. View Grades for Student");
            System.out.println("5. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        dao.addStudent(name);
                        System.out.println("Student added.");
                        break;
                    case 2:
                        List<Student> students = dao.getAllStudents();
                        for (Student s : students) {
                            System.out.println(s.getId() + " - " + s.getName());
                        }
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        int sid = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter course name: ");
                        String course = scanner.nextLine();
                        System.out.print("Enter Marks: ");
                        double grade = scanner.nextDouble();
                        dao.addGrade(sid, course, grade);
                        System.out.println("Marks added.");
                        break;
                    case 4:
                        System.out.print("Enter student ID: ");
                        int id = scanner.nextInt();
                        List<String> grades = dao.getGradesByStudent(id);
                        if (grades.isEmpty()) {
                            System.out.println("No Marks found.");
                        } else {
                            for (String g : grades) {
                                System.out.println(g);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
