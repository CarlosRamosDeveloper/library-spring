package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crd.library_spring.entities.User;
import com.crd.library_spring.repositories.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {
    
    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {            
        return userRepository.findById(id);
    }

    @Transactional
    public User save(User user){
        return userRepository.save(user);
    }
    
    @Transactional
    public Optional<User> update(Long id, User user){
        Optional<User> userDb = userRepository.findById(id);

        if (userDb.isPresent()){
            User u = userDb.get();
            u.setUsername(user.getUsername());            
        }

        return userDb;
    }

    @Transactional
    public Optional<User> delete(Long id){
        Optional<User> userDb = userRepository.findById(id);

        userDb.ifPresent(u -> userRepository.delete(u));

        return userDb;
    }
}
