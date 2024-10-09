package interfaces;

import models.Book;
import java.util.List;

public interface IsBook {
    public void save(Book book);
    public Book getBook(Long id);
    public void update(Book book);
    public void delete(Book book);
    public List<Book> getBooks();
}