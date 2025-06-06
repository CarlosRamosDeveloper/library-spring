package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import com.crd.library_spring.entities.Loan;

public interface ILoanService {
    List<Loan> findAll();
    Optional<Loan> findById(Long id);
    Loan save(Loan loan);
    Optional<Loan> update(Long id, Loan loan);
    Optional<Loan> delete(Long id);
}
