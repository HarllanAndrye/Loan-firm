package com.harllan.loanfirm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.harllan.loanfirm.controller.doc.ClientControllerDoc;
import com.harllan.loanfirm.dto.ClientDto;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.exception.BusinessRuleException;
import com.harllan.loanfirm.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController implements ClientControllerDoc {
	
	private final ClientService clientService;
	
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping
	public List<ClientDto> listClients() {
		return clientService.listClients();
	}
	
	@GetMapping("/{email}")
	public ClientDto findClient(@PathVariable("email") String email) {
		return clientService.findClientByEmail(email);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDto registerClient(@Valid @RequestBody ClientDto client) {
		return clientService.registerClient(client);
	}
	
	@PutMapping
	public MessageResponseDto updateClient(@Valid @RequestBody ClientDto clientToUpdate) throws BusinessRuleException {
		return clientService.updateClient(clientToUpdate);
	}

	@DeleteMapping("/{email}")
	public MessageResponseDto deleteClient(@PathVariable String email) throws BusinessRuleException {
		return clientService.deleteClient(email);
	}
	
}
