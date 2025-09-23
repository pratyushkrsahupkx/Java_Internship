
import java.util.Scanner;

public class ConsoleCalculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Error: Division by zero is not allowed!");
        }
        return a / b;
    }


    public static void displayMenu() {
        System.out.println("\n Java Console Calculator");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exit");
        System.out.print("Enter your choice : ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculating = true;

        System.out.println("Calculator is ready to use!");

        while (continueCalculating) {
            try {
                displayMenu();
                int choice = scanner.nextInt();

                if (choice == 5) {
                    System.out.println("Run the application again to use the calculator.");
                    continueCalculating = false;
                    continue;
                }

                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice! Please enter a number between 1-5.");
                    continue;
                }

                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                double result = 0;
                String operation = "";

                switch (choice) {
                    case 1:
                        result = add(num1, num2);
                        operation = "+";
                        break;
                    case 2:
                        result = subtract(num1, num2);
                        operation = "-";
                        break;
                    case 3:
                        result = multiply(num1, num2);
                        operation = "*";
                        break;
                    case 4:
                        result = divide(num1, num2);
                        operation = "/";
                        break;
                }

                System.out.printf("Result: %.2f %s %.2f = %.2f%n", num1, operation, num2, result);

            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter valid numbers.");
                scanner.nextLine(); 
            }

            System.out.println("\n" + "=".repeat(20));
        }

        scanner.close();
    }
}
