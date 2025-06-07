package com.crd.library_spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String name;  
    @Column(name = "current_quantity") 
    private int currentQuantity;
    @Column(name = "total_quantity")
    private int totalQuantity;

    public Book() {   
    }

    public Book(String isbn, String name, int quantity) {
        this.isbn = isbn;
        this.name = name;
        this.currentQuantity = quantity;        
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    } 

    public void increaseCurrentQuantity(){
        currentQuantity++;
    }

    public void decreaseCurrentQuantity(){
        currentQuantity--;
    }       
}
