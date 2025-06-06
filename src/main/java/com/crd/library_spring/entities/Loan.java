package com.crd.library_spring.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;    
    private Book book;
    @Column(name = "rental_date")
    private Date rentalDate;
    @Column(name = "return_date")
    private Date returnDate;
    
    public Loan() {
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
    public Date getRentalDate() {
        return rentalDate;
    }
    public Loan setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
        return this;
    }
    public Date getReturnDate() {
        return returnDate;
    }
    public Loan setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    
}
