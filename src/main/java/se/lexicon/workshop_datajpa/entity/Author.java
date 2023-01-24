package se.lexicon.workshop_datajpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    @Column(nullable = false, length = 80)
    private String firstName;
    @Column(nullable = false, length = 80)
    private String lastName;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
    @JoinTable(name = "books_authors"
            , joinColumns = @JoinColumn(name = "author_id")
            , inverseJoinColumns = @JoinColumn(name = "book_id"))
    Set<Book> writtenBooks;
    
    public Author() {
    }
    
    public Author( String firstName, String lastName, Book book ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Author( String firstName, String lastName ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public Author( int authorId, String firstName, String lastName, Book book ) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public int getAuthorId() {
        return authorId;
    }
    
    public void setAuthorId( int authorId ) {
        this.authorId = authorId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    
    public Set<Book> getWrittenBooks() {
        if(writtenBooks == null) writtenBooks = new HashSet<>();
        return writtenBooks;
    }
    
    public void setWrittenBooks( Set<Book> writtenBooks ) {
        this.writtenBooks = writtenBooks;
    }
    
    public void addWrittenBook(Book book){
        if(book == null) throw new IllegalArgumentException("book is null");
        if(writtenBooks == null) writtenBooks = new HashSet<>();
        writtenBooks.add(book);
    }
    
    public void removeWrittenBook(Book book){
        if(book == null) throw new IllegalArgumentException("book is null");
        if(writtenBooks == null) writtenBooks = new HashSet<>();
        
        writtenBooks.remove(book);
    }
    
    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(writtenBooks, author.writtenBooks);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName, writtenBooks);
    }
    
    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writtenBooks=" + writtenBooks +
                '}';
    }
}
