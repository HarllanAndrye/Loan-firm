package com.harllan.loanfirm.mapper;

import org.mapstruct.Mapper;

import com.harllan.loanfirm.dto.ClientDto;
import com.harllan.loanfirm.dto.UserDto;
import com.harllan.loanfirm.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {

	Client toEntity(ClientDto clientDto);
	
	Client toEntity(UserDto userDto);
	
	ClientDto toDto(Client client);
	
}
