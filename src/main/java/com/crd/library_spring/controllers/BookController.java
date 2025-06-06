package com.crd.library_spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.library_spring.entities.Book;
import com.crd.library_spring.services.BookServiceImpl;
import com.crd.library_spring.services.IBookService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private IBookService service = new BookServiceImpl();
    
    @GetMapping("")    
    public List<Book> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")    
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Book> optionalBook = service.findById(id);

        if(optionalBook.isPresent()){
            return ResponseEntity.ok(optionalBook.orElseThrow());
        }
        
        return ResponseEntity.notFound().build();
    }
    

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Book book){        
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(book));
    }    

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> optionalBook = service.update(id, book);

        if (optionalBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalBook.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        Optional<Book> optionalBook = service.delete(id);

        if(optionalBook.isPresent()){
            return ResponseEntity.ok(optionalBook.orElseThrow());
        }
        
        return ResponseEntity.notFound().build();
    }
}
