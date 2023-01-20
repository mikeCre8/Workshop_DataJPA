package se.lexicon.workshop_datajpa.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.impl.DetailsDaoImpl;
import se.lexicon.workshop_datajpa.entity.Details;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class DetailsDaoImplTest {
    
    @Autowired
    TestEntityManager em;
    
    @Autowired
    DetailsDaoImpl testObject;
    
    int createdDetailsId1;
    int createdDetailsId2;
    
    @BeforeEach
    public void setup(){
        Details detailsData1 = new Details("John", "Deer", "jd@email.com");
        Details detailsData2 = new Details("Mary", "Poppins", "mp@email.com");
        
        Details createdDetails1 = em.persist(detailsData1);
        Details createdDetails2 = em.persist(detailsData2);
        
        createdDetailsId1 = createdDetails1.getDetailsId();
        createdDetailsId2 = createdDetails2.getDetailsId();
    }
    
    @Test
    public void persist(){
        Details detailsData = new Details("Harry", "Potter", "hp@email.com");
        Details createdDetails = testObject.persist(detailsData);
        
        assertNotNull(createdDetails);
        assertNotNull(createdDetails.getDetailsId());
    }

}
