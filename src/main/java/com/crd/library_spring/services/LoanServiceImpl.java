package com.crd.library_spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crd.library_spring.entities.Loan;
import com.crd.library_spring.repositories.ILoanRepository;

@Service
public class LoanServiceImpl implements ILoanService{

    @Autowired
    private ILoanRepository loanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        return (List<Loan>) loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Loan> findById(Long id) {
        return loanRepository.findById(id);
    }

    @Override
    @Transactional()
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    @Transactional()
    public Optional<Loan> update(Long id, Loan loan) {
        Optional<Loan> loanDb = loanRepository.findById(id);
        if (loanDb.isPresent()){
            Loan l = loanDb.get();
            l.setUser(loan.getUser())
                .setBook(loan.getBook())
                .setRentalDate(loan.getRentalDate())
                .setReturnDate(loan.getReturnDate());
        }

        return loanDb;
    }

    @Override
    @Transactional()
    public Optional<Loan> delete(Long id) {
        Optional<Loan> loanDb = loanRepository.findById(id);

        loanDb.ifPresent(l -> loanRepository.delete(l));

        return loanDb;
    }    
}
