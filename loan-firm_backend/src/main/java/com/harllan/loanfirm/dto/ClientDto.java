package com.harllan.loanfirm.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

	@NotNull(message = "Necessário informar o nome")
	private String name;
	
	@NotNull(message = "Necessário informar o e-mail")
	private String email;
	
	@NotNull(message = "Necessário informar o CPF")
	private String cpf;
	
	private Integer rg;
	
	private AddressDto address;
	
	@NotNull(message = "Necessário informar a renda")
	private Double income;

}
