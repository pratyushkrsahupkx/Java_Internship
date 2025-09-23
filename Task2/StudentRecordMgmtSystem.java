package Task2;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class StudentRecordMgmtSystem {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Student Record Management System");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    sortStudents();
                    break;
                case 7:
                    System.out.println("Application Closed Succesfully");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--Menu--");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("6. Sort Students by Marks");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

       
        if (findStudentById(id) != null) {
            System.out.println("Student with ID " + id + " already exists!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();

        if (marks < 0 || marks > 100) {
            System.out.println("Marks should be between 0 and 100!");
            return;
        }

        Student student = new Student(id, name, marks);
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() {
        if (students.isEmpty()) {

            System.out.println("No students found!");
            return;
        }

        System.out.println("\n--All Students--");
        System.out.println("ID\tName\t\tMarks\tGrade");
        System.out.println("------------------------------------");

        for (Student student : students) {
            String grade = calculateGrade(student.getMarks());
            System.out.printf("%d\t%-10s\t%.2f\t%s\n", 
                student.getId(), student.getName(), student.getMarks(), grade);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        System.out.println("Current details: " + student);
        System.out.println("Which information would you like to update?");
        System.out.println("1. Name");
        System.out.println("2. Marks");
        System.out.println("3. Both");
        System.out.print("Enter choice: ");

        int updateChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (updateChoice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                student.setName(newName);
                break;
            case 2:
                System.out.print("Enter new marks: ");
                double newMarks = scanner.nextDouble();
                if (newMarks >= 0 && newMarks <= 100) {
                    student.setMarks(newMarks);
                } else {
                    System.out.println("Marks should be between 0 and 100!");
                    return;
                }
                break;
            case 3:
                System.out.print("Enter new name: ");
                String name = scanner.nextLine();
                System.out.print("Enter new marks: ");
                double marks = scanner.nextDouble();
                if (marks >= 0 && marks <= 100) {
                    student.setName(name);
                    student.setMarks(marks);
                } else {
                    System.out.println("Marks should be between 0 and 100!");
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("Student information updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
            return;
        }

        System.out.println("Student to delete: " + student);
        System.out.print("Are you sure? (y/n): ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("y")) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();

        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
        } else {
            System.out.println("Student found: " + student);
            String grade = calculateGrade(student.getMarks());
            System.out.println("Grade: " + grade);
        }
    }

    private static void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to sort!");
            return;
        }

        System.out.println("Sort by:");
        System.out.println("1. Marks (Ascending)");
        System.out.println("2. Marks (Descending)");
        System.out.println("3. Name (Alphabetical)");
        System.out.print("Enter choice: ");

        int sortChoice = scanner.nextInt();

        switch (sortChoice) {
            case 1:
                Collections.sort(students, Comparator.comparingDouble(Student::getMarks));
                break;
            case 2:
                Collections.sort(students, Comparator.comparingDouble(Student::getMarks).reversed());
                break;
            case 3:
                Collections.sort(students, Comparator.comparing(Student::getName));
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        System.out.println("Students sorted successfully!");
        viewAllStudents();
    }

    private static Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private static String calculateGrade(double marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B+";
        else if (marks >= 60) return "B";
        else if (marks >= 50) return "C+";
        else if (marks >= 40) return "C";
        else return "F";
    }
}