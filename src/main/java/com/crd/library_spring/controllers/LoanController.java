package com.crd.library_spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.library_spring.entities.Book;
import com.crd.library_spring.entities.Loan;
import com.crd.library_spring.entities.User;
import com.crd.library_spring.services.BookServiceImpl;
import com.crd.library_spring.services.IBookService;
import com.crd.library_spring.services.ILoanService;
import com.crd.library_spring.services.IUserService;
import com.crd.library_spring.services.LoanServiceImpl;
import com.crd.library_spring.services.UserServiceImpl;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    
    @Autowired
    private ILoanService service = new LoanServiceImpl();        
    @Autowired
    private IUserService userService = new UserServiceImpl();
    @Autowired
    private IBookService bookService = new BookServiceImpl();

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Loan loan, @RequestHeader int userId, @RequestHeader int bookId){             
        Optional<User> user = userService.findById(Long.valueOf(userId));        
        Optional<Book> book = bookService.findById(Long.valueOf(bookId));
        
        if (book.get().getCurrentQuantity()>0 && user.isPresent() && book.isPresent()){
            saveIntoDb(loan, user.get(), book.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(loan));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    public List<Loan> find(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<Loan> loanDb = service.findById(id);

        if (loanDb.isPresent()) {
            return ResponseEntity.ok(loanDb);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Loan loan){
        Optional<Loan> loanDb = service.update(id, loan);
        
        if(loanDb.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(loanDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<?> returnABook(@PathVariable Long id){
        Optional<Loan> loanDb = service.findById(id);
        
        if(loanDb.isPresent() && loanDb.get().getActiveRent()){
            loanDb.get().returnABook();
            service.update(id, loanDb.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(loanDb.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Loan> optionalUser = service.delete(id);

        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/seed")
        public void populateDB(){                  
        saveIntoDb(new Loan(), userService.findById(1L).get(), bookService.findById(1L).get());
        saveIntoDb(new Loan(), userService.findById(2L).get(), bookService.findById(2L).get());
        saveIntoDb(new Loan(), userService.findById(3L).get(), bookService.findById(3L).get());
        saveIntoDb(new Loan(), userService.findById(4L).get(), bookService.findById(4L).get());
        saveIntoDb(new Loan(), userService.findById(2L).get(), bookService.findById(1L).get());
    }

    public void saveIntoDb(Loan loan, User user, Book book){
        loan.setUser(user);
        loan.setBook(book);
        loan.rentABook();
        service.save(loan);
    }
}