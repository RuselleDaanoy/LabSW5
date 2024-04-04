import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class BookDA {
    private HashMap<String, Book> books;
    private AuthorDA authorDA;

    public BookDA() {
        books = new HashMap<>();
        authorDA = new AuthorDA();
        loadBooks();
    }

    private void loadBooks() {
        try (Scanner scanner = new Scanner(new FileReader("Book.csv"))) {
            while (scanner.hasNextLine()) {
                String bookLine = scanner.nextLine();
                String[] bookParts = bookLine.split(",");
                if (bookParts.length >= 3) {
                    String isbn = bookParts[0].trim();
                    String title = bookParts[1].trim();
                    String authorName = bookParts[2].trim();
                    Author author = authorDA.getAuthorByName(authorName);
                    books.put(isbn, new Book(isbn, title, author));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printBooks() {
        for (Book book : books.values()) {
            System.out.println(book.getIsbn() + " " + book.getTitle());
            System.out.println("\t" + book.getAuthor().getName() + " - " + book.getAuthor().getBio());
            System.out.println();
        }
    }
}
