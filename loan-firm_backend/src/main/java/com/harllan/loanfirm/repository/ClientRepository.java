package com.harllan.loanfirm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.harllan.loanfirm.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByEmail(String email);
	
}
