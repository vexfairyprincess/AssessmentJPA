package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="books")
@Getter @Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "books_id_seq", allocationSize = 1)
    private Long id;

    private String title;
    private String author;
    private String releaseDate;
    private Double price;
    private boolean available;
}