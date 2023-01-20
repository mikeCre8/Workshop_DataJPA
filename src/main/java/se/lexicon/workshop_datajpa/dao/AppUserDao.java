package se.lexicon.workshop_datajpa.dao;

import se.lexicon.workshop_datajpa.entity.AppUser;
import se.lexicon.workshop_datajpa.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDao {
    
    AppUser persist( AppUser appUser );
    Optional<AppUser> findById(int id);
    Optional<AppUser> findByUsername(String username);
    Collection<AppUser> findAll();
    void remove(int id) throws DataNotFoundException;

}
