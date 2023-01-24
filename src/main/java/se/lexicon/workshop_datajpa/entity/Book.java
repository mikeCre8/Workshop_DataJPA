package se.lexicon.workshop_datajpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    
    @Column(length = 80)
    private String isbn;
    @Column(nullable = false, length = 80)
    private String title;
    
    @Column
    private int maxLoanDays;
    
    public Book() {
    }
    
    public Book( String isbn, String title ) {
        this.isbn = "ISBN-"+isbn;
        this.title = title;
    }
    
    public Book( int bookId, String isbn, String title ) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
    }
    
    public int getId() {
        return bookId;
    }
    
    public void setId( int bookId ) {
        this.bookId = bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle( String title ) {
        this.title = title;
    }
    
    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && Objects.equals(title, book.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(bookId, title);
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId +
                ", title='" + title + '\'' +
                '}';
    }
}
