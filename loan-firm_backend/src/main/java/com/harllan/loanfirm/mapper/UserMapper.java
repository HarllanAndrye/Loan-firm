package com.harllan.loanfirm.mapper;

import org.mapstruct.Mapper;

import com.harllan.loanfirm.dto.UserDto;
import com.harllan.loanfirm.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User toEntity(UserDto userDto);
	
	UserDto toDto(User user);

}
