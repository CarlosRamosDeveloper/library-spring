package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crd.library_spring.entities.Book;
import com.crd.library_spring.repositories.IBookRepository;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return (List<Book>) bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional()
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional()
    @Override
    public Optional<Book> update(Long id, Book book) {
        Optional<Book> bookDb = bookRepository.findById(id);
        if(bookDb.isPresent()){
            Book b = bookDb.get();
            b.setName(book.getName());
            b.setQuantity(book.getQuantity());
            b.setIsbn(book.getIsbn());
        }

        return bookDb;
    }

    @Transactional()
    @Override
    public Optional<Book> delete(Long id) {
        Optional<Book> bookDb = bookRepository.findById(id);

        bookDb.ifPresent(b -> bookRepository.delete(b));

        return bookDb;
    }
    
}
