package com.harllan.loanfirm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harllan.loanfirm.controller.doc.LoanControllerDoc;
import com.harllan.loanfirm.dto.LoanDto;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.exception.BusinessRuleException;
import com.harllan.loanfirm.service.LoanService;

@RestController
@RequestMapping("/loan")
public class LoanController implements LoanControllerDoc {
	
	private final LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	@GetMapping("/client/{email}")
	public List<LoanDto> listLoans(@PathVariable("email") String clientEmail) {
		return loanService.listLoans(clientEmail);
	}

	@GetMapping("/{loanCode}")
	public LoanDto findLoan(@PathVariable String loanCode) {
		return loanService.findLoanByCode(loanCode);
	}

	@PostMapping("/{clientEmail}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDto registerLoan(@PathVariable String clientEmail, @RequestBody LoanDto loan) throws BusinessRuleException {
		return loanService.registerLoan(clientEmail, loan);
	}

	@PutMapping("/cancel/{clientEmail}/{loanCode}")
	public MessageResponseDto cancelLoan(@PathVariable String clientEmail, @PathVariable String loanCode) throws BusinessRuleException {
		MessageResponseDto message = loanService.cancelLoan(clientEmail, loanCode);
		return message;
	}

}
