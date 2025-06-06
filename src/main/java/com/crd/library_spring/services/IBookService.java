package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import com.crd.library_spring.entities.Book;

public interface IBookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    Optional<Book> update(Long id, Book book);
    Optional<Book> delete(Long id);
}
