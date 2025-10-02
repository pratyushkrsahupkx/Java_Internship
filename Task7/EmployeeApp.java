package Task7;

import java.util.List;
import java.util.Scanner;

public class EmployeeApp {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Employee Database Management System ===");
        System.out.println("Welcome to the Employee Management App!");
        createTableIfNotExists();

        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addNewEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeById();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    searchByDepartment();
                    break;
                case 7:
                    System.out.println("Thank you for using Employee Management System!");
                    DatabaseConnection.closeConnection();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== EMPLOYEE MANAGEMENT MENU ===");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Find Employee by ID");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Search by Department");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addNewEmployee() {
        System.out.println("\n=== ADD NEW EMPLOYEE ===");

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee email: ");
        String email = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format!");
            return;
        }

        Employee employee = new Employee(name, email, department, salary);

        if (employeeDAO.addEmployee(employee)) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private static void viewAllEmployees() {
        System.out.println("\n=== ALL EMPLOYEES ===");
        List<Employee> employees = employeeDAO.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.printf("%-5s %-20s %-25s %-15s %-10s%n", "ID", "Name", "Email", "Department", "Salary");
        System.out.println("-".repeat(80));

        for (Employee emp : employees) {
            System.out.printf("%-5d %-20s %-25s %-15s %-10.2f%n",
                emp.getId(), emp.getName(), emp.getEmail(), emp.getDepartment(), emp.getSalary());
        }
    }

    private static void viewEmployeeById() {
        System.out.println("\n=== FIND EMPLOYEE BY ID ===");
        System.out.print("Enter employee ID: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            Employee employee = employeeDAO.getEmployeeById(id);

            if (employee != null) {
                System.out.println("Employee found:");
                System.out.println(employee);
            } else {
                System.out.println("Employee not found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void updateEmployee() {
        System.out.println("\n=== UPDATE EMPLOYEE ===");
        System.out.print("Enter employee ID to update: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            Employee existingEmployee = employeeDAO.getEmployeeById(id);

            if (existingEmployee == null) {
                System.out.println("Employee not found with ID: " + id);
                return;
            }

            System.out.println("Current employee details:");
            System.out.println(existingEmployee);
            System.out.println("Enter new details (press Enter to keep current value):");

            System.out.print("Name (" + existingEmployee.getName() + "): ");
            String name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                existingEmployee.setName(name);
            }

            System.out.print("Email (" + existingEmployee.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                existingEmployee.setEmail(email);
            }

            System.out.print("Department (" + existingEmployee.getDepartment() + "): ");
            String department = scanner.nextLine();
            if (!department.trim().isEmpty()) {
                existingEmployee.setDepartment(department);
            }

            System.out.print("Salary (" + existingEmployee.getSalary() + "): ");
            String salaryStr = scanner.nextLine();
            if (!salaryStr.trim().isEmpty()) {
                try {
                    double salary = Double.parseDouble(salaryStr);
                    existingEmployee.setSalary(salary);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary format, keeping current value.");
                }
            }

            if (employeeDAO.updateEmployee(existingEmployee)) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Failed to update employee.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void deleteEmployee() {
        System.out.println("\n=== DELETE EMPLOYEE ===");
        System.out.print("Enter employee ID to delete: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            Employee employee = employeeDAO.getEmployeeById(id);

            if (employee == null) {
                System.out.println("Employee not found with ID: " + id);
                return;
            }

            System.out.println("Employee to delete:");
            System.out.println(employee);
            System.out.print("Are you sure you want to delete this employee? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")) {
                if (employeeDAO.deleteEmployee(id)) {
                    System.out.println("Employee deleted successfully!");
                } else {
                    System.out.println("Failed to delete employee.");
                }
            } else {
                System.out.println("Delete operation cancelled.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format!");
        }
    }

    private static void searchByDepartment() {
        System.out.println("\n=== SEARCH BY DEPARTMENT ===");
        System.out.print("Enter department name: ");
        String department = scanner.nextLine();

        List<Employee> employees = employeeDAO.getEmployeesByDepartment(department);

        if (employees.isEmpty()) {
            System.out.println("No employees found in department: " + department);
            return;
        }

        System.out.println("Employees in " + department + " department:");
        System.out.printf("%-5s %-20s %-25s %-15s %-10s%n", "ID", "Name", "Email", "Department", "Salary");
        System.out.println("-".repeat(80));

        for (Employee emp : employees) {
            System.out.printf("%-5d %-20s %-25s %-15s %-10.2f%n",
                emp.getId(), emp.getName(), emp.getEmail(), emp.getDepartment(), emp.getSalary());
        }
    }

    private static void createTableIfNotExists() {
        
        
       
        System.out.println("Make sure the 'employees' table exists in your database!");
    }
}