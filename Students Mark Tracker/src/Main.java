import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Marks Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Marks");
            System.out.println("3. View Student Marks");
            System.out.println("4. Calculate Average");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();
                        dao.addStudent(name);
                        System.out.println("Student added.");
                        break;
                    case 2:
                        System.out.print("Student ID: ");
                        int sid = sc.nextInt(); sc.nextLine();
                        System.out.print("Subject: ");
                        String subject = sc.nextLine();
                        System.out.print("Marks: ");
                        int marks = sc.nextInt();
                        dao.addMarks(sid, subject, marks);
                        System.out.println("Marks added.");
                        break;
                    case 3:
                        System.out.print("Student ID: ");
                        int id = sc.nextInt();
                        List<String> marksList = dao.getMarksByStudent(id);
                        if (marksList.isEmpty()) {
                            System.out.println("No records found.");
                        } else {
                            for (String s : marksList) System.out.println(s);
                        }
                        break;
                    case 4:
                        System.out.print("Student ID: ");
                        int sidAvg = sc.nextInt();
                        double avg = dao.calculateAverage(sidAvg);
                        System.out.println("Average Marks: " + avg);
                        break;
                    case 5:
                        List<Student> students = dao.getAllStudents();
                        for (Student s : students)
                            System.out.println(s.getId() + " - " + s.getName());
                        break;
                    case 6:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

