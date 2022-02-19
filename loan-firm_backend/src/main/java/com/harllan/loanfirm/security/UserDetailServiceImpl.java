package com.harllan.loanfirm.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.harllan.loanfirm.entity.User;
import com.harllan.loanfirm.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository repository;

	public UserDetailServiceImpl(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOpt = repository.findByEmail(email);

		if (userOpt.isEmpty()) {
			throw new UsernameNotFoundException(String.format("Usuário [%s] não encontrado!", email));
		}

		return new UserDetailImpl(userOpt);
	}

}
