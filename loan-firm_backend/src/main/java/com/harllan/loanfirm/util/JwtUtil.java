package com.harllan.loanfirm.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JwtUtil {
	
	Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	@Value("${jwt.expiration}")
	private Long TOKEN_EXPIRATION;
	
	@Value("${jwt.secret}")
	private String TOKEN_PASSWORD;

	public String generateToken(String email) {
		String token = JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		
		return token;
	}
	
	public boolean validateToken(String token) {
		try {
			JWT.require(Algorithm.HMAC512(TOKEN_PASSWORD))
				.build()
				.verify(token);
		} catch (JWTVerificationException | IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		
		return true;
	}
	
	public String getSubject(String token) {
		String subject = JWT.require(Algorithm.HMAC512(TOKEN_PASSWORD))
				.build()
				.verify(token)
				.getSubject();
		
		return subject;
	}
	
}
