import java.io.*;
import java.util.Scanner;

public class NoteTakingApp {
    private static final String NOTES_FILE = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Note Taking App");

        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    createNote();
                    break;
                case 2:
                    viewAllNotes();
                    break;
                case 3:
                    appendToNotes();
                    break;
                case 4:
                    clearAllNotes();
                    break;
                case 5:
                    System.out.println("The Application has been closed.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

   
    private static void displayMenu() {
        System.out.println("\nNotes Manager Menu");
        System.out.println("1. Create a new note ");
        System.out.println("2. View all notes");
        System.out.println("3. Add to existing notes");
        System.out.println("4. Clear all notes");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    
    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }

    private static void createNote() {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

       
        try (FileWriter writer = new FileWriter(NOTES_FILE)) {
            writer.write(note);
            writer.write(System.lineSeparator()); 
            System.out.println("Note created successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            logException(e);
        }
    }


    private static void viewAllNotes() {
        File file = new File(NOTES_FILE);

        if (!file.exists()) {
            System.out.println("No notes found. Create a note first!");
            return;
        }

        System.out.println("\nYour Notes");

        
        try (BufferedReader reader = new BufferedReader(new FileReader(NOTES_FILE))) {
            String line;
            int lineNumber = 1;
            boolean hasContent = false;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(lineNumber + ". " + line);
                    lineNumber++;
                    hasContent = true;
                }
            }

            if (!hasContent) {
                System.out.println("No notes found in the file.");
            }

        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            logException(e);
        }
    }


    private static void appendToNotes() {
        System.out.print("Enter note to add: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(NOTES_FILE, true)) {
            writer.write(note);
            writer.write(System.lineSeparator()); 
            System.out.println("Note added successfully!");
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
            logException(e);
        }
    }

   
    private static void clearAllNotes() {
        System.out.print("Are you sure you want to delete all notes? (y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            try (FileWriter writer = new FileWriter(NOTES_FILE)) {
                writer.write("");
                System.out.println("All notes cleared successfully!");
            } catch (IOException e) {
                System.err.println("Error clearing file: " + e.getMessage());
                logException(e);
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private static void logException(Exception e) {
        System.err.println("\nException Details");
        System.err.println("Exception Type: " + e.getClass().getSimpleName());
        System.err.println("Message: " + e.getMessage());
        System.err.println("Stack Trace:");
        e.printStackTrace();
        System.err.println("End Exception Details\n");
    }
}