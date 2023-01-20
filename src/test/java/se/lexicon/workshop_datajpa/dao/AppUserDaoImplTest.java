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
import se.lexicon.workshop_datajpa.dao.impl.AppUserDaoImpl;
import se.lexicon.workshop_datajpa.entity.AppUser;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Transactional
@DirtiesContext
public class AppUserDaoImplTest {
    
    @Autowired
    TestEntityManager em;
    
    @Autowired
    AppUserDaoImpl testObject;
    
    int createdAppUserId1;
    int createdAppUserId2;
    
    @BeforeEach
    public void setup(){
        AppUser appUserData1 = new AppUser("username1", "password1");
        AppUser appUserData2 = new AppUser("user", "pass");
        
        AppUser createdAppUser1 = em.persist(appUserData1);
        AppUser createdAppUser2 = em.persist(appUserData2);
        
        createdAppUserId1 = createdAppUser1.getAppUserId();
        createdAppUserId2 = createdAppUser2.getAppUserId();
    }
    
    @Test
    public void persist(){
        AppUser appUserData = new AppUser("username", "password");
        AppUser createdAppUser = testObject.persist(appUserData);
    
        assertNotNull(createdAppUser);
    }

}
