package se.lexicon.workshop_datajpa.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int appUserId;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    private LocalDateTime registrationDate;
    
    public AppUser() {
        this.registrationDate = LocalDateTime.now();
    }
    
    @OneToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private Details details;
    
    @OneToMany
    private List<BookLoan> loans;
    
    public AppUser( String username, String password ) {
        this();
        this.username = username;
        this.password = password;
    }
    
    public int getAppUserId() {
        return appUserId;
    }
    
    public void setAppUserId( int appUserId ) {
        this.appUserId = appUserId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername( String username ) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword( String password ) {
        this.password = password;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate( LocalDateTime registrationDate ) {
        this.registrationDate = registrationDate;
    }
    
    public Details getDetails() {
        if(details == null) details = new Details();
        return details;
    }
    
    public void setDetails( Details details ) {
        this.details = details;
    }
    
    public List<BookLoan> getLoans() {
        if(loans == null) loans = new ArrayList<>();
        return loans;
    }
    
    public void setLoans( List<BookLoan> loans ) {
        this.loans = loans;
    }
    
    public void addLoans(){
        if(loans == null) loans = new ArrayList<>();
    }
    
    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(password, appUser.password) &&
                Objects.equals(registrationDate, appUser.registrationDate);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(appUserId, username, password, registrationDate);
    }
    
    @Override
    public String toString() {
        return "AppUser{" +
                "appUserId=" + appUserId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
