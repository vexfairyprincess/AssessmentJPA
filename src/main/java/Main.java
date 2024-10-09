import dao.BookDao;
import models.Book;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static BookDao bookDao = new BookDao();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    createBook();
                    break;
                case 2:
                    readBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Book Management System ---");
        System.out.println("1. Add a new book");
        System.out.println("2. View book details");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("5. List all books");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book price: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter book stock: ");
        double available = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the year of publication: ");
        String releaseDate = scanner.nextLine();

        Book book = new Book(title, author, price, available, releaseDate);
        bookDao.save(book);
        System.out.println("Book added successfully!");
    }

    private static void readBook() {
        System.out.print("Enter book ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Book book = bookDao.getBook(id);  // Fetch book using the DAO
        if (book != null) {
            System.out.println("Book Details:");
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Price: $" + book.getPrice());
            System.out.println("Stock: " + book.getAvailable());
            System.out.println("Release Date: " + book.getReleaseDate());
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter book ID: ");
        Long id = Long.parseLong(scanner.nextLine());

        Book existingBook = bookDao.getBook(id);
        if (existingBook != null) {
            System.out.print("Enter new title (leave empty to keep current): ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new author (leave empty to keep current): ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter new price (leave empty to keep current): ");
            String priceInput = scanner.nextLine();
            System.out.print("Enter new stock (leave empty to keep current): ");
            boolean availableInput = scanner.nextLine();
            System.out.print("Enter new release date (leave empty to keep current): ");
            String newReleaseDate = scanner.nextLine();

            if (!newTitle.isEmpty()) {
                existingBook.setTitle(newTitle);
            }
            if (!newAuthor.isEmpty()) {
                existingBook.setAuthor(newAuthor);
            }
            if (!priceInput.isEmpty()) {
                Double newPrice = Double.parseDouble(priceInput);
                existingBook.setPrice(newPrice);
            }
            if (!availableInput.isEmpty()) {
                int newStock = Integer.parseInt(availableInput);
                existingBook.setStock(newStock);
            }
            if (!newReleaseDate.isEmpty()) {
                existingBook.setReleaseDate(newReleaseDate);
            }

            bookDao.update(existingBook);
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());

        Book book = bookDao.getBook(id);
        if (book != null) {
            bookDao.delete(book);
            System.out.println("Book deleted successfully!");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void listAllBooks() {
        List<Book> books = bookDao.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n--- List of Books ---");
            for (Book book : books) {
                System.out.println("ID: " + book.getId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Price: $" + book.getPrice());
                System.out.println("Stock: " + book.getAvailable());
                System.out.println("Release Date: " + book.getReleaseDate());
                System.out.println("---------------------------");
            }
        }
    }
}
