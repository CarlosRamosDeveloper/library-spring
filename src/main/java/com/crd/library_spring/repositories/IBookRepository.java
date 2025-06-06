package com.crd.library_spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crd.library_spring.entities.Book;

public interface IBookRepository extends CrudRepository<Book, Long>{
    
}
