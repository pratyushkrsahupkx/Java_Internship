package Task3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String libraryName;
    private Map<String, Book> books;
    private Map<String, User> users;

   
    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.users = new HashMap<>();
    }

    
    public boolean addBook(Book book) {
        if (book != null && !books.containsKey(book.getBookId())) {
            books.put(book.getBookId(), book);
            System.out.println("Book added successfully: " + book.getTitle());
            return true;
        }
        System.out.println("Book already exists or invalid book data!");
        return false;
    }

    
    public boolean removeBook(String bookId) {
        Book book = books.remove(bookId);
        if (book != null) {
            System.out.println("Book removed successfully: " + book.getTitle());
            return true;
        }
        System.out.println("Book not found!");
        return false;
    }

   
    public boolean registerUser(User user) {
        if (user != null && !users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
            System.out.println("User registered successfully: " + user.getName());
            return true;
        }
        System.out.println("User already exists or invalid user data!");
        return false;
    }

   
    public boolean removeUser(String userId) {
        User user = users.get(userId);
        if (user != null) {
            if (user.getIssuedBooksCount() > 0) {
                System.out.println("Cannot remove user. User has issued books!");
                return false;
            }
            users.remove(userId);
            System.out.println("User removed successfully: " + user.getName());
            return true;
        }
        System.out.println("User not found!");
        return false;
    }

    
    public boolean issueBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }

        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        if (book.isIssued()) {
            System.out.println("Book is already issued to another user!");
            return false;
        }

        if (!user.canIssueBook()) {
            System.out.println("User has reached maximum book limit!");
            return false;
        }

        // Issue the book
        if (book.issueBook(userId) && user.addIssuedBook(bookId)) {
            System.out.println("Book '" + book.getTitle() + "' issued successfully to " + user.getName());
            return true;
        }

        System.out.println("Failed to issue book!");
        return false;
    }

    // Method to return book
    public boolean returnBook(String bookId, String userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }

        if (user == null) {
            System.out.println("User not found!");
            return false;
        }

        if (!book.isIssued() || !book.getIssuedTo().equals(userId)) {
            System.out.println("Book is not issued to this user!");
            return false;
        }

        // Return the book
        if (book.returnBook() && user.removeIssuedBook(bookId)) {
            System.out.println("Book '" + book.getTitle() + "' returned successfully by " + user.getName());
            return true;
        }

        System.out.println("Failed to return book!");
        return false;
    }

    // Method to display all books
    public void displayAllBooks() {
        System.out.println("\n All Books in " + libraryName);
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    
    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        boolean hasAvailable = false;

        for (Book book : books.values()) {
            if (!book.isIssued()) {
                System.out.println(book);
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("No books available for issue.");
        }
    }

    
    public void displayAllUsers() {
        System.out.println("\n=== All Users ===");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    
    public List<Book> searchBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

   
    public List<Book> searchBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                foundBooks.add(book);
            }
        }

        return foundBooks;
    }

    
    public void displayUserBooks(String userId) {
        User user = users.get(userId);
        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        System.out.println("\n=== Books issued to " + user.getName() + " ===");
        List<String> userBooks = user.getIssuedBooks();

        if (userBooks.isEmpty()) {
            System.out.println("No books issued to this user.");
            return;
        }

        for (String bookId : userBooks) {
            Book book = books.get(bookId);
            if (book != null) {
                System.out.println(book);
            }
        }
    }

    
    public String getLibraryName() {
        return libraryName;
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getTotalUsers() {
        return users.size();
    }

    public int getAvailableBooks() {
        int count = 0;
        for (Book book : books.values()) {
            if (!book.isIssued()) {
                count++;
            }
        }
        return count;
    }

    public int getIssuedBooks() {
        return getTotalBooks() - getAvailableBooks();
    }
}