package com.harllan.loanfirm.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String loanCode;

	@Column(nullable = false)
	private Double loanValue;

	@Column(nullable = false)
	private LocalDateTime fisrtDateToPayment;

	@Column(nullable = false)
	private Integer quantityOfPayment;
	
	@Column(nullable = false)
	private Long clientId;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	private Boolean canceledLoan = false;
	
	private LocalDateTime dateCanceledLoan;

}
