package com.crd.library_spring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.crd.library_spring.entities.Loan;

public interface ILoanRepository extends CrudRepository<Loan, Long>{
    
}
