package se.lexicon.workshop_datajpa.dao;

import se.lexicon.workshop_datajpa.entity.Author;

import java.util.Collection;

public interface AuthorDao {
    
    Author findById(int id);
    
    Collection<Author> findAll();
    
    Author create(Author author);
    
    Author update(Author author);
    
    void remove(int id);

}
