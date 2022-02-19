package com.harllan.loanfirm.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.harllan.loanfirm.util.JwtUtil;

public class JwtValidateFilter extends BasicAuthenticationFilter {
	
	private final JwtUtil jwtUtil;

	public JwtValidateFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		String bearer = "Bearer ";
		
		if (header != null && header.startsWith(bearer)) {
			String token = header.replace(bearer, "");

			UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);

			if (authenticationToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		boolean validToken = jwtUtil.validateToken(token);
		
		if (validToken) {
			String userEmail = jwtUtil.getSubject(token);

			if (userEmail == null) {
				return null;
			}
			
			return new UsernamePasswordAuthenticationToken(userEmail, null, new ArrayList<>());
		}
		
		return null;
	}

}
