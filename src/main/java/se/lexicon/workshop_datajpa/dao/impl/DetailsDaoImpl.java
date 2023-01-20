package se.lexicon.workshop_datajpa.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.DetailsDao;
import se.lexicon.workshop_datajpa.entity.Details;
import se.lexicon.workshop_datajpa.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class DetailsDaoImpl implements DetailsDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    @Transactional
    public Details persist( Details details ) {
        entityManager.persist(details);
        return details;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Details> findById( int id ) {
        return Optional.ofNullable(entityManager.find(Details.class, id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Details> findByEmail( String email ) {
        return entityManager.createQuery("SELECT d FROM Details d WHERE UPPER(d.email) = UPPER(:email)", Details.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findByNameContains( String name ) {
        return entityManager.createQuery("SELECT d FROM Details d " +
                "WHERE UPPER(d.firstName) LIKE UPPER(concat('%', :name, '%'))" +
                "OR UPPER(d.lastName) LIKE UPPER(concat('%', :name, '%'))", Details.class)
                .setParameter("name", name)
                .getResultList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<Details> findAll() {
        return entityManager.createQuery("SELECT d FROM Details d", Details.class)
                .getResultList();
    }
    
    @Override
    @Transactional
    public Details update( Details details ) {
        return entityManager.merge(details);
    }
    
    @Override
    @Transactional
    public void remove( int id ) throws DataNotFoundException {
        Details details = entityManager.find(Details.class, id);
        if(details != null) entityManager.remove(details);
        else throw new DataNotFoundException("Data not found");
    }
}
