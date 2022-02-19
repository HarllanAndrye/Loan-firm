package com.harllan.loanfirm.controller.doc;

import java.util.List;

import com.harllan.loanfirm.config.SwaggerConfig;
import com.harllan.loanfirm.dto.LoanDto;
import com.harllan.loanfirm.dto.response.ErrorResponse;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.exception.BusinessRuleException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Empréstimos", tags = { SwaggerConfig.TAG_LOAN })
public interface LoanControllerDoc {
	
	@ApiOperation(value = "Retorna uma lista de empréstimos solicitados pelo cliente.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de empréstimos."),
    })
	public List<LoanDto> listLoans(String clientEmail);
	
	@ApiOperation(value = "Retorna uma solicitação de empréstimo de um cliente de acordo com o código do empréstimo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Empréstimo encontrado."),
    })
	public LoanDto findLoan(String loanCode);
	
	@ApiOperation(value = "Insere uma solicitação de empréstimo para o cliente informado.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Solicitação de empréstimo criada com sucesso."),
            @ApiResponse(code = 400, message = "Não foi possível solicitar empréstimo", response = ErrorResponse.class)
    })
	public MessageResponseDto registerLoan(String clientEmail, LoanDto loan) throws BusinessRuleException;
	
	@ApiOperation(value = "Cancelar solicitação de empréstimo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Solicitação cancelada com sucesso."),
    })
	public MessageResponseDto cancelLoan(String clientEmail, String loanCode) throws BusinessRuleException;

}
