package se.lexicon.workshop_datajpa.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Details {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int detailsId;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    
    
    public Details() {
    }
    
    public Details( String firstName, String lastName, String email ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public Details( int detailsId, String firstName, String lastName, String email) {
        this.detailsId = detailsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public int getDetailsId() {
        return detailsId;
    }
    
    public void setDetailsId( int detailsId ) {
        this.detailsId = detailsId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail( String email ) {
        this.email = email;
    }
    
  
    
    
    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Details details = (Details) o;
        return detailsId == details.detailsId &&
                Objects.equals(firstName, details.firstName) &&
                Objects.equals(lastName, details.lastName) &&
                Objects.equals(email, details.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(detailsId, firstName, lastName, email);
    }
    
    @Override
    public String toString() {
        return "Details{" +
                "detailsId=" + detailsId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
