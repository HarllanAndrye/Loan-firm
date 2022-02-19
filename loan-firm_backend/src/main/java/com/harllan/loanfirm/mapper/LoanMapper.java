package com.harllan.loanfirm.mapper;

import org.mapstruct.Mapper;

import com.harllan.loanfirm.dto.LoanDto;
import com.harllan.loanfirm.entity.Loan;

@Mapper(componentModel = "spring")
public interface LoanMapper {

	Loan toEntity(LoanDto loanDto);

	LoanDto toDto(Loan loan);

}
