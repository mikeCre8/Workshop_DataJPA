package se.lexicon.workshop_datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.workshop_datajpa.dao.AppUserDao;
import se.lexicon.workshop_datajpa.dao.DetailsDao;
import se.lexicon.workshop_datajpa.entity.AppUser;
import se.lexicon.workshop_datajpa.entity.Details;


@Component
public class AppCommandLineRunner implements CommandLineRunner {
    
    @Autowired
    AppUserDao appUserDao;
    @Autowired
    DetailsDao detailsDao;
    
    @Override
    public void run( String... args ) throws Exception {
    
        System.out.println("###############");
        AppUser createdAppUser1 = appUserDao.persist(
                new AppUser(
                        "test_username",
                        "test_password"
                )
        );
        System.out.println("############### : " + createdAppUser1.getAppUserId());
    
        System.out.println();
        Details createdDetails1 = detailsDao.persist(
                new Details(
                        "test_firstName",
                        "test_lastName",
                        "test@email.com"
                )
        );
        System.out.println("############### : " + createdDetails1.getDetailsId());
        
    }
}
