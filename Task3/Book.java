package Task3;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private boolean isIssued;
    private String issuedTo;

 
    public Book(String bookId, String title, String author, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isIssued = false;
        this.issuedTo = null;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public boolean issueBook(String userId) {
        if (!isIssued) {
            this.isIssued = true;
            this.issuedTo = userId;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (isIssued) {
            this.isIssued = false;
            this.issuedTo = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
    return "Book{" +
        "bookId='" + bookId + '\'' +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", genre='" + genre + '\'' +
        ", isIssued=" + isIssued +
        ", issuedTo='" + issuedTo + '\'' +
        '}';
    }
}