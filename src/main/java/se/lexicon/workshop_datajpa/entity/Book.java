package se.lexicon.workshop_datajpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(name = "books_authors"
            , joinColumns = @JoinColumn(name = "book_id")
            , inverseJoinColumns = @JoinColumn(name = "author_id"))
    Set<Author> authors;
    
    public Book() {
    }
    
    public Book( String isbn, String title) {
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
    
    public void addAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("author is null");
        if(authors == null) authors = new HashSet<>();
        if(!author.getWrittenBooks().contains(this)) {
            authors.add(author);
        }
    }
    
    public void removeAuthor(Author author){
        if(author == null) throw new IllegalArgumentException("author is null");
        if(authors == null) authors = new HashSet<>();
        
        author.getWrittenBooks().remove(this);
        authors.remove(author);
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
