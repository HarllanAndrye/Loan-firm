package com.harllan.loanfirm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessRuleException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessRuleException(String message, Object... params) {
		super(String.format(message, params));
	}

	public BusinessRuleException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessRuleException(String message) {
		super(message);
	}

	public BusinessRuleException(Throwable cause) {
		super(cause);
	}

}
