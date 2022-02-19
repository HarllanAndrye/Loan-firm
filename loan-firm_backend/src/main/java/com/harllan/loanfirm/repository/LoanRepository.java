package com.harllan.loanfirm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harllan.loanfirm.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

	Loan findTopByOrderByIdDesc();
	
	List<Loan> findAllByClientId(Long clientId);
	
	Loan findByLoanCode(String loanCode);

}
