package com.harllan.loanfirm.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
	
	private String loanCode;

	@NotNull(message = "Necessário informar o valor do empréstimo")
	private Double loanValue;

	@NotNull(message = "Necessário informar a data para a primeira parcela")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime fisrtDateToPayment;

	@NotNull(message = "Necessário informar a quantidade de parcelas")
	@Max(value = 60, message = "A quantidade máxima de parcelas é de 60.")
	private Integer quantityOfPayment;
	
	private Long clientId;
	
	private Boolean canceledLoan;
	
	private LocalDateTime dateCanceledLoan;

}
