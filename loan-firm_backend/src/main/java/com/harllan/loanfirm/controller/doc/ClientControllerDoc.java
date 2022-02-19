package com.harllan.loanfirm.controller.doc;

import java.util.List;

import com.harllan.loanfirm.config.SwaggerConfig;
import com.harllan.loanfirm.dto.ClientDto;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.exception.BusinessRuleException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Clientes", tags = { SwaggerConfig.TAG_CLIENT })
public interface ClientControllerDoc {
	
	@ApiOperation(value = "Retorna uma lista de clientes.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de clientes."),
    })
	public List<ClientDto> listClients();
	
	@ApiOperation(value = "Retorna um cliente de acordo com o e-mail informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente encontrado."),
    })
	public ClientDto findClient(String email);
	
	@ApiOperation(value = "Insere o registro de um cliente no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Cliente criado com sucesso."),
    })
	public MessageResponseDto registerClient(ClientDto client);
	
	@ApiOperation(value = "Atualiza o registro de um cliente no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente atualizado com sucesso."),
    })
	public MessageResponseDto updateClient(ClientDto clientToUpdate) throws BusinessRuleException;
	
	@ApiOperation(value = "Exclui o registro de um cliente do banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente removido com sucesso."),
    })
	public MessageResponseDto deleteClient(String email) throws BusinessRuleException;

}
