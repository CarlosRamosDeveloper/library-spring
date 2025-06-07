package com.crd.library_spring.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;    
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    @Column(name = "rental_date")        
    private LocalDateTime rentalDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @Column(name = "active_rent")
    private Boolean activeRent;    
    
    public Loan() {
        Date currentDate = new Date();
        rentalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        returnDate = rentalDate.plusDays(5);
        activeRent = true;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public Loan setUser(User user) {
        this.user = user;
        return this;
    }
    public Book getBook() {
        return book;
    }
    public Loan setBook(Book book) {
        this.book = book;
        return this;
    }
    public LocalDateTime getRentalDate() {
        return rentalDate;
    }
    public Loan setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
        return this;
    }
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    public Loan setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public Boolean getActiveRent() {
        return activeRent;
    }

    public void setActiveRent(Boolean activeRent) {
        this.activeRent = activeRent;
    }

    public void rentABook(){     
        if(book.getCurrentQuantity()>0){
            book.decreaseCurrentQuantity();  
            setActiveRent(true);  
        }
        //TODO tirar un error "No hay suficientes libros"
    }

    public void returnABook(){
        book.increaseCurrentQuantity();
        setActiveRent(false);  
    }
        
    
}
