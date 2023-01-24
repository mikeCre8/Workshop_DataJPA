package se.lexicon.workshop_datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.workshop_datajpa.dao.*;
import se.lexicon.workshop_datajpa.entity.*;

import java.time.LocalDate;


@Component
public class AppCommandLineRunner implements CommandLineRunner {
    
    private AppUserDao appUserDao;
    private DetailsDao detailsDao;
    private BookDao bookDao;
    private BookLoanDao bookLoanDao;
    private AuthorDao authorDao;
    
    @Autowired
    public AppCommandLineRunner( AppUserDao appUserDao, DetailsDao detailsDao, BookDao bookDao, BookLoanDao bookLoanDao, AuthorDao authorDao ) {
        this.appUserDao = appUserDao;
        this.detailsDao = detailsDao;
        this.bookDao = bookDao;
        this.bookLoanDao = bookLoanDao;
        this.authorDao = authorDao;
    }
    
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
    
        System.out.println("###############");
        
        Book createdBook1 = bookDao.create(new Book("123","The Book"));
        BookLoan bookLoan = bookLoanDao.create(new BookLoan(LocalDate.now(), LocalDate.parse("2023-02-25"), createdAppUser1, createdBook1));
        bookLoan.setBook(createdBook1);
        bookLoan.setBorrower(createdAppUser1);
        System.out.println(bookLoan);
        
        System.out.println("###############");
    
        Author createdAuthor1 = authorDao.create(new Author("Test", "Test", createdBook1));
        createdAuthor1.addWrittenBook(createdBook1);
        
        
    }
}
