package se.lexicon.workshop_datajpa.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.AuthorDao;
import se.lexicon.workshop_datajpa.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorDaoImpl implements AuthorDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    @Transactional(readOnly = true)
    public Author findById( int id ) {
        return entityManager.find(Author.class, id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a")
                .getResultList();
    }
    
    @Override
    @Transactional
    public Author create( Author author ) {
        entityManager.persist(author);
        return author;
    }
    
    @Override
    @Transactional
    public Author update( Author author ) {
        return entityManager.merge(author);
    }
    
    @Override
    @Transactional
    public void remove( int id ) {
        entityManager.remove(entityManager.find(Author.class, id));
    }
}
