package com.crd.library_spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crd.library_spring.entities.User;

public interface IUserRepository extends CrudRepository<User, Long> {
    
}
