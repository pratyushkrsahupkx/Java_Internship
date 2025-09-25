package Task3;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private List<String> issuedBooks;
    private static final int MAX_BOOKS = 3; 

    
    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.issuedBooks = new ArrayList<>();
    }

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getIssuedBooks() {
        return new ArrayList<>(issuedBooks);
    }

    public int getIssuedBooksCount() {
        return issuedBooks.size();
    }

  
    public boolean addIssuedBook(String bookId) {
        if (issuedBooks.size() < MAX_BOOKS && !issuedBooks.contains(bookId)) {
            issuedBooks.add(bookId);
            return true;
        }
        return false;
    }

    
    public boolean removeIssuedBook(String bookId) {
        return issuedBooks.remove(bookId);
    }

    
    public boolean canIssueBook() {
        return issuedBooks.size() < MAX_BOOKS;
    }

  
    public boolean hasBook(String bookId) {
        return issuedBooks.contains(bookId);
    }

    @Override
    public String toString() {
    return "User{" +
        "userId='" + userId + '\'' +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", issuedBooks=" + issuedBooks +
        ", booksCount=" + issuedBooks.size() + "/" + MAX_BOOKS +
        '}';
    }
}