package com.harllan.loanfirm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

	private String street;
	private Integer number;
	private String neighborhood;
	private String city;
	private String zipcode;
	private String country;

}
