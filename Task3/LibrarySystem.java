package Task3;

import java.util.Scanner;

public class LibrarySystem {
    private static Library library = new Library("Library System");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        initializeData();

        System.out.println("Welcome to Library Management System");

        while (true) {
            displayMenu();

            int choice = getChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    library.displayAllBooks();
                    break;
                case 6:
                    library.displayAvailableBooks();
                    break;
                case 7:
                    library.displayAllUsers();
                    break;
                case 8:
                    searchBooks();
                    break;
                case 9:
                    viewUserBooks();
                    break;
                case 10:
                    displayStatistics();
                    break;
                case 0:
                    System.out.println("Thank you for using Library System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void initializeData() {
       
        library.addBook(new Book("B001", "Task3 Question Solutions", "Pkx", "Programming"));
        library.addBook(new Book("B002", "Spring Boot", "Oracle", "Framework"));
        library.addBook(new Book("B003", "Gaming A permanent Job", "Pika", "Programming"));

       
        library.registerUser(new User("001", "Pkx Sahu", "pkxsahu@gmail.com", "1234567890"));
        library.registerUser(new User("002", "Pika Sahu", "pikasahu@gmail.com", "0987654321"));

        System.out.println("Sample data initialized successfully!\n");
    }

    private static void displayMenu() {
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM MENU ===");
        System.out.println("1. Add Book");
        System.out.println("2. Register User");
        System.out.println("3. Issue Book");
        System.out.println("4. Return Book");
        System.out.println("5. Display All Books");
        System.out.println("6. Display Available Books");
        System.out.println("7. Display All Users");
        System.out.println("8. Search Books");
        System.out.println("9. View User's Books");
        System.out.println("10. Library Statistics");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addBook() {
        System.out.println("\nAdd New Book");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        Book book = new Book(bookId, title, author, genre);
        library.addBook(book);
    }

    private static void registerUser() {
        System.out.println("\nRegister New User");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        User user = new User(userId, name, email, phone);
        library.registerUser(user);
    }

    private static void issueBook() {
        System.out.println("\nIssue Book");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        library.issueBook(bookId, userId);
    }

    private static void returnBook() {
        System.out.println("\nReturn Book");
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        library.returnBook(bookId, userId);
    }

    private static void searchBooks() {
        System.out.println("\nSearch Books");
        System.out.println("1. Search by Title");
        System.out.println("2. Search by Author");
        System.out.print("Enter choice: ");

        int choice = getChoice();

        switch (choice) {
            case 1:
                System.out.print("Enter title to search: ");
                String title = scanner.nextLine();
                var titleBooks = library.searchBooksByTitle(title);
                if (titleBooks.isEmpty()) {
                    System.out.println("No books found with that title.");
                } else {
                    System.out.println("Books found:");
                    for (Book book : titleBooks) {
                        System.out.println(book);
                    }
                }
                break;
            case 2:
                System.out.print("Enter author to search: ");
                String author = scanner.nextLine();
                var authorBooks = library.searchBooksByAuthor(author);
                if (authorBooks.isEmpty()) {
                    System.out.println("No books found by that author.");
                } else {
                    System.out.println("Books found:");
                    for (Book book : authorBooks) {
                        System.out.println(book);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void viewUserBooks() {
        System.out.println("\nView User's Books");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        library.displayUserBooks(userId);
    }

    private static void displayStatistics() {
        System.out.println("\nLibrary Statistics");
        System.out.println("Library Name: " + library.getLibraryName());
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Total Users: " + library.getTotalUsers());
        System.out.println("Available Books: " + library.getAvailableBooks());
        System.out.println("Issued Books: " + library.getIssuedBooks());
    }
}