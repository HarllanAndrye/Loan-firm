package com.harllan.loanfirm.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harllan.loanfirm.dto.LoanDto;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.entity.Client;
import com.harllan.loanfirm.entity.Loan;
import com.harllan.loanfirm.exception.BusinessRuleException;
import com.harllan.loanfirm.mapper.LoanMapper;
import com.harllan.loanfirm.repository.ClientRepository;
import com.harllan.loanfirm.repository.LoanRepository;

@Service
public class LoanService {

	private final ClientRepository clientRepository;
	private final LoanRepository loanRepository;
	private final LoanMapper loanMapper;

	private LoanService(ClientRepository clientRepository, LoanRepository loanRepository, LoanMapper loanMapper) {
		this.clientRepository = clientRepository;
		this.loanRepository = loanRepository;
		this.loanMapper = loanMapper;
	}

	public List<LoanDto> listLoans(String clientEmail) {
		Client client = clientRepository.findByEmail(clientEmail);

		if (client == null) {
			return new ArrayList<>();
		}

		List<Loan> loans = loanRepository.findAllByClientId(client.getId());

		return loans.stream().map(loanMapper::toDto).collect(Collectors.toList());
	}

	public LoanDto findLoanByCode(String loanCode) {
		Loan loan = loanRepository.findByLoanCode(loanCode);
		return loanMapper.toDto(loan);
	}

	public MessageResponseDto registerLoan(String clientEmail, LoanDto loanDto) throws BusinessRuleException {
		Client client = clientRepository.findByEmail(clientEmail);

		if (client != null) {
			validateLoanFields(loanDto);

			loanDto.setClientId(client.getId());
			loanDto.setLoanCode(generateLoanCode());
			loanDto.setCanceledLoan(false);

			Loan savedLoan = loanRepository.save(loanMapper.toEntity(loanDto));

			return createMessageResponse("Empréstimo cadastrado com sucesso :: id ", savedLoan.getId());
		}

		throw new BusinessRuleException("Cliente não encontrado com o email %s.", clientEmail);
	}

	/**
	 * Código do emprestimo, ex.: anoMes-NumeroSequencialMes ... 202112-16 (16
	 * significa que houve 16 empréstimos no mês)
	 */
	private String generateLoanCode() {
		Calendar calendar = Calendar.getInstance();

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		String firstPartOfCode = year + "" + month;
		String secondPartOfCode = "01";

		Loan lastLoan = loanRepository.findTopByOrderByIdDesc();

		if (lastLoan != null) {
			String lastLoanCode = lastLoan.getLoanCode();

			String[] split = lastLoanCode.split("-");

			if (firstPartOfCode.equals(split[0])) {
				Integer sequence = Integer.valueOf(split[1]);
				sequence += 1;

				DecimalFormat df = new DecimalFormat("00");

				secondPartOfCode = df.format(sequence);
			}
		}

		return firstPartOfCode + "-" + secondPartOfCode;
	}

	public MessageResponseDto cancelLoan(String clientEmail, String loanCode) throws BusinessRuleException {
		Client client = clientRepository.findByEmail(clientEmail);

		if (client != null) {
			Loan loan = loanRepository.findByLoanCode(loanCode);
			
			if (loan != null) {
				if (loan.getCanceledLoan()) {
					throw new BusinessRuleException("Empréstimo já se encontra cancelado.");
				}
				
				loan.setCanceledLoan(true);
				loan.setDateCanceledLoan(LocalDateTime.now());
				loanRepository.save(loan);

				return createMessageResponse("Empréstimo cancelado com sucesso :: id ", loan.getId());
			} else {
				throw new BusinessRuleException("Empréstimo não encontrado com o código %s.", loanCode);
			}
		}

		throw new BusinessRuleException("Cliente não encontrado com o email %s.", clientEmail);
	}

	private MessageResponseDto createMessageResponse(String message, Long id) {
		return MessageResponseDto.builder().message(message + id).build();
	}

	private void validateLoanFields(LoanDto loanDto) throws BusinessRuleException {
		if (loanDto.getQuantityOfPayment() <= 0 || loanDto.getQuantityOfPayment() > 60) {
			throw new BusinessRuleException("Quantidade de parcelas deve ser entre 1 e 60.");
		}

		LocalDateTime threeMonthsAfterNow = LocalDateTime.now().plusMonths(3);

		if (loanDto.getFisrtDateToPayment().isAfter(threeMonthsAfterNow)) {
			throw new BusinessRuleException("Data da 1º parcela deve ser no máximo 3 meses após o dia atual.");
		}
	}

}
