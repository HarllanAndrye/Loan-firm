package com.harllan.loanfirm.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Long id;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Necessário informar o nome")
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Necessário informar o CPF")
	private String cpf;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Necessário informar a renda")
	private Double income;

	@NotNull(message = "Necessário informar o e-mail")
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "Necessário informar a senha")
	private String password;

}
