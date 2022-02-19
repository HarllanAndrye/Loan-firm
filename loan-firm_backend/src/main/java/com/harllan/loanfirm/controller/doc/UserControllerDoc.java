package com.harllan.loanfirm.controller.doc;

import org.springframework.http.ResponseEntity;

import com.harllan.loanfirm.config.SwaggerConfig;
import com.harllan.loanfirm.dto.RegisterDto;
import com.harllan.loanfirm.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Usuários", tags = { SwaggerConfig.TAG_USER })
public interface UserControllerDoc {
	
	@ApiOperation(value = "Retorna o usuário de acordo com o email informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuário encontrado."),
    })
	public ResponseEntity<UserDto> getUser(String email);
	
	@ApiOperation(value = "Retorna 'true' se token válido e 'false caso contrário'.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Token válido."),
    })
	public ResponseEntity<Boolean> tokenValid();
	
	@ApiOperation(value = "Cadastrar usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Usuário cadastrado."),
    })
	public ResponseEntity<RegisterDto> saveUser(UserDto user);

}
