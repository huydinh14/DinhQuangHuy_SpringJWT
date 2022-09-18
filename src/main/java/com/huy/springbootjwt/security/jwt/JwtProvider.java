package com.huy.springbootjwt.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.huy.springbootjwt.security.userpincal.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	private String jwtSecret = "huydinh@gmail.com";
	private int jwtExpiration = 86400;
	public String createToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpiration*1000))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public Boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			// TODO: handle exception
			logger.error("Invalid JWT sinature => Message: {}", e);
		} catch (MalformedJwtException e) {
			// TODO: handle exception
			logger.error("The token invalid format => Message: {}", e);
		} catch (UnsupportedJwtException e) {
			// TODO: handle exception
			logger.error("Unsupported JWT token => Message: {}", e);
		} catch (ExpiredJwtException e) {
			// TODO: handle exception
			logger.error("Expired JWT token => Message: {}", e);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			logger.error("Jwt claims string is empty => Message: {}", e);
		}
		return true;
	}
	
	public String getUserNameFromToken(String token) {
		String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		return userName;
	}
}
