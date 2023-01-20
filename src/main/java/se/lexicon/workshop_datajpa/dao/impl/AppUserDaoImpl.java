package se.lexicon.workshop_datajpa.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.AppUserDao;
import se.lexicon.workshop_datajpa.entity.AppUser;
import se.lexicon.workshop_datajpa.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AppUserDaoImpl implements AppUserDao {
    
    @PersistenceContext
    EntityManager entityManager;
    
    @Override
    @Transactional
    public AppUser persist( AppUser appUser ) {
        entityManager.persist(appUser);
        return appUser;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AppUser> findById( int id ) {
        return Optional.ofNullable(entityManager.find(AppUser.class, id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<AppUser> findByUsername( String username ) {
        return entityManager.createQuery("SELECT a FROM AppUser a WHERE UPPER(a.username) = UPPER(:username)", AppUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("SELECT a from AppUser a", AppUser.class)
                .getResultList();
    }
    
    @Override
    @Transactional
    public void remove( int id ) throws DataNotFoundException {
        AppUser appUser = entityManager.find(AppUser.class, id);
        if(appUser != null) entityManager.remove(appUser);
        else throw new DataNotFoundException("Data not found");
    }
}
