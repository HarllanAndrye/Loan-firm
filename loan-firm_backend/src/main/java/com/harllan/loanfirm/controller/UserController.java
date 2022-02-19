package com.harllan.loanfirm.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harllan.loanfirm.controller.doc.UserControllerDoc;
import com.harllan.loanfirm.dto.RegisterDto;
import com.harllan.loanfirm.dto.UserDto;
import com.harllan.loanfirm.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerDoc {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/{email}")
	public ResponseEntity<UserDto> getUser(@PathVariable String email) {
		UserDto userDto = userService.getUser(email);
		
		return ResponseEntity.ok(userDto);
	}
	
	@GetMapping("/authorized")
	public ResponseEntity<Boolean> tokenValid() {
		boolean validCredentials = true;
		return ResponseEntity.status(HttpStatus.OK).body(validCredentials);
	}
	
	@PostMapping(value = "/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<RegisterDto> saveUser(@RequestBody UserDto userDto) {
		RegisterDto registerDto = userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerDto);
	}
	
}
