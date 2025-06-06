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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crd.library_spring.entities.User;
import com.crd.library_spring.services.IUserService;
import com.crd.library_spring.services.UserServiceImpl;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService service = new UserServiceImpl();

    @GetMapping("")
    public List<User> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        Optional<User> optionalUser = service.findById(id);
        
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User user){
        Optional<User> optionalUser = service.update(id, user);
        
        if(optionalUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> optionalUser = service.delete(id);

        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}
