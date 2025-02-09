package librarySystem;
import java.util.*;

class Book {
    String title;
    String author;
    String category;
    boolean isBorrowed;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isBorrowed = false;
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }
}

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    Map<String, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User " + username + " registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Authentication successful.");
            return true;
        }
        System.out.println("Invalid credentials.");
        return false;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.title + " by " + book.author + " [" + book.category + "] - " + (book.isBorrowed ? "Borrowed" : "Available"));
        }
    }
}

public class ELibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        library.addBook(new Book("Java Programming", "James Gosling", "Technology"));
        library.addBook(new Book("Data Structures", "Robert Lafore", "Education"));

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        library.registerUser(username, password);
        
        System.out.print("Enter username to login: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password to login: ");
        String loginPassword = scanner.nextLine();
        
        if (library.authenticate(loginUsername, loginPassword)) {
            library.listBooks();
        }
        
        scanner.close();
    }
}
