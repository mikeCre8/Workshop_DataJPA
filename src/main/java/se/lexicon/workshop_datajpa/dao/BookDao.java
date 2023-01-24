package se.lexicon.workshop_datajpa.dao;

import se.lexicon.workshop_datajpa.entity.Book;

import java.util.Collection;

public interface BookDao {
    
    Book findById(int id);
    
    Collection<Book> findAll();
    
    Book create(Book book);
    
    Book update(Book book);
    
    void delete(int id);
    
}
