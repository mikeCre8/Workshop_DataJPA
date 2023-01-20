package se.lexicon.workshop_datajpa.dao;

import se.lexicon.workshop_datajpa.entity.Details;
import se.lexicon.workshop_datajpa.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface DetailsDao {
    
    Details persist(Details details);
    Optional<Details> findById(int id);
    Optional<Details> findByEmail(String email);
    Collection<Details> findByNameContains(String name);
    Collection<Details> findAll();
    Details update(Details details);
    void remove(int id) throws DataNotFoundException;

}
