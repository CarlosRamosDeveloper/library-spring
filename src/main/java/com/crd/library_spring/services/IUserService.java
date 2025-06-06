package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import com.crd.library_spring.entities.User;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    Optional<User> update(Long id, User user);
    Optional<User> delete(Long id);

}
