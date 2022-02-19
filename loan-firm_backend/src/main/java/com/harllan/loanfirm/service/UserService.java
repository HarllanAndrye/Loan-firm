package com.harllan.loanfirm.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.harllan.loanfirm.dto.RegisterDto;
import com.harllan.loanfirm.dto.UserDto;
import com.harllan.loanfirm.entity.User;
import com.harllan.loanfirm.mapper.ClientMapper;
import com.harllan.loanfirm.mapper.UserMapper;
import com.harllan.loanfirm.repository.ClientRepository;
import com.harllan.loanfirm.repository.UserRepository;
import com.harllan.loanfirm.util.JwtUtil;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final ClientRepository clientRepository;
	private final UserMapper userMapper;
	private final ClientMapper clientMapper;
	private final PasswordEncoder encoder;
	private final JwtUtil jwtUtil;

	public UserService(UserRepository userRepository, ClientRepository clientRepository, UserMapper userMapper,
			ClientMapper clientMapper, PasswordEncoder encoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.clientRepository = clientRepository;
		this.userMapper = userMapper;
		this.clientMapper = clientMapper;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
	}

	public UserDto getUser(String email) {
		Optional<User> userOpt = userRepository.findByEmail(email);

		if (userOpt.isPresent()) {
			return userMapper.toDto(userOpt.get());
		}

		return null;
	}

	@Transactional
	public RegisterDto saveUser(UserDto userDto) {
		userDto.setPassword(encoder.encode(userDto.getPassword()));

		User user = userRepository.save(userMapper.toEntity(userDto));
		clientRepository.save(clientMapper.toEntity(userDto));

		RegisterDto registerDto = new RegisterDto();
		registerDto.setId(user.getId());
		registerDto.setEmail(user.getEmail());
		registerDto.setToken(jwtUtil.generateToken(user.getEmail()));

		return registerDto;
	}

}
