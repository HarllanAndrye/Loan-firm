package com.harllan.loanfirm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harllan.loanfirm.dto.ClientDto;
import com.harllan.loanfirm.dto.response.MessageResponseDto;
import com.harllan.loanfirm.entity.Client;
import com.harllan.loanfirm.exception.BusinessRuleException;
import com.harllan.loanfirm.mapper.ClientMapper;
import com.harllan.loanfirm.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	private final ClientMapper clientMapper;

	public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
		this.clientRepository = clientRepository;
		this.clientMapper = clientMapper;
	}

	public MessageResponseDto registerClient(ClientDto clientDto) {
		Client client = clientMapper.toEntity(clientDto);
		Client savedClient = clientRepository.save(client);
		return createMessageResponse("Cliente cadastrado com sucesso :: id: ", savedClient.getId());
	}

	public List<ClientDto> listClients() {
		List<Client> client = clientRepository.findAll();
		
		if (client.isEmpty()) {
			return new ArrayList<>();
		}
		
		return client.stream().map(clientMapper::toDto).collect(Collectors.toList());
	}

	public ClientDto findClientByEmail(String email) {
		Client client = clientRepository.findByEmail(email);
		
		if (client == null) {
			return new ClientDto();
		}
		
		ClientDto clientDto = clientMapper.toDto(client);
		return clientDto;
	}

	private MessageResponseDto createMessageResponse(String message, Long id) {
		return MessageResponseDto.builder().message(message + id).build();
	}

	public MessageResponseDto updateClient(ClientDto clientToUpdate) throws BusinessRuleException {
		Client client = clientRepository.findByEmail(clientToUpdate.getEmail());
		
		if (client != null) {
			Client clientUpdate = clientMapper.toEntity(clientToUpdate);
			clientUpdate.setId(client.getId());
			
			Client savedClient = clientRepository.save(clientUpdate);
			
			return createMessageResponse("Cliente atualizado com sucesso :: id: ", savedClient.getId());
		}

		throw new BusinessRuleException("Cliente não encontrado com o email %s.", clientToUpdate.getEmail());
	}

	public MessageResponseDto deleteClient(String email) throws BusinessRuleException {
		Client client = clientRepository.findByEmail(email);
		
		if (client != null) {
			clientRepository.deleteById(client.getId());
			
			return createMessageResponse("Cliente removido com sucesso :: id: ", client.getId());
		}
		
		throw new BusinessRuleException("Cliente não encontrado com o email %s.", email);
	}

}
