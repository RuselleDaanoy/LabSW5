import java.util.*;
import java.io.FileReader;
import java.io.IOException;

public class AuthorDA {
    private HashMap<String, Author> authors;

    public AuthorDA() {
        authors = new HashMap<>();
        loadAuthors();
    }

    private void loadAuthors() {
        try (Scanner scanner = new Scanner(new FileReader("Author.csv"))) {
            while (scanner.hasNextLine()) {
                String authorLine = scanner.nextLine();
                String[] authorParts = authorLine.split(",");
                if (authorParts.length >= 2) {
                    String name = authorParts[0].trim();
                    String bio = authorParts[1].trim();
                    authors.put(name, new Author(name, bio));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorByName(String name) {
        return authors.get(name);
    }
}
