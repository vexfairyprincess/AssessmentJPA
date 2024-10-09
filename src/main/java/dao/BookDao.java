package dao;

import interfaces.IsBook;
import jakarta.persistence.EntityManager;
import models.Book;

import java.util.List;

public class BookDao implements IsBook {
    @Override
    public void save(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    @Override
    public Book getBook(Long id) {
        EntityManager em = EntityManagerAdmin.getInstance();
        return em.find(Book.class, id);
    }

    @Override
    public void delete(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        Book bookToDelete = em.find(Book.class, book.getId());
        if (bookToDelete != null) {
            em.remove(bookToDelete);
        }
    }

    @Override
    public void update(Book book) {
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(book);
        em.getTransaction().commit();
    }

    @Override
    public List<Book> getBooks() {
        return null;
    }
}