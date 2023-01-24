package se.lexicon.workshop_datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.AppUserDao;
import se.lexicon.workshop_datajpa.dao.BookDao;
import se.lexicon.workshop_datajpa.dao.BookLoanDao;
import se.lexicon.workshop_datajpa.dao.DetailsDao;
import se.lexicon.workshop_datajpa.entity.AppUser;
import se.lexicon.workshop_datajpa.entity.Book;
import se.lexicon.workshop_datajpa.entity.BookLoan;
import se.lexicon.workshop_datajpa.entity.Details;

import java.time.LocalDate;


@Component
public class AppCommandLineRunner implements CommandLineRunner {
    
    @Autowired
    AppUserDao appUserDao;
    @Autowired
    DetailsDao detailsDao;
    
    @Autowired
    BookDao bookDao;
    
    @Autowired
    BookLoanDao bookLoanDao;
    
    @Override
    @Transactional
    public void run( String... args ) throws Exception {
    
        System.out.println("###############");
        AppUser createdAppUser1 = appUserDao.persist(
                new AppUser(
                        "admin",
                        "*****"
                )
        );
        System.out.println("############### : " + createdAppUser1.getAppUserId());
    
        System.out.println();
        Details createdDetails1 = detailsDao.persist(
                new Details(
                        "Mikael",
                        "Svensson",
                        "ms@email.com"
                )
        );
        System.out.println("############### : " + createdDetails1.getDetailsId());
        createdAppUser1.setDetails(createdDetails1);
    
        Book createdBook1 = bookDao.create(new Book("123","The Book"));
        BookLoan bookLoan = bookLoanDao.create(new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-25"), createdAppUser1, createdBook1));
        bookLoan.setBook(createdBook1);
        bookLoan.setBorrower(createdAppUser1);
        System.out.println(bookLoan);
        
        
    }
}
