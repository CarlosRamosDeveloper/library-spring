package com.crd.library_spring.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
    @ManyToOne
    private User user;    
    @ManyToOne
    private Book book;
    @Column(name = "rental_date")
    private LocalDateTime rentalDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    
    public Loan() {
        Date currentDate = new Date();
        rentalDate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        returnDate = rentalDate.plusDays(5);
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

    
}
