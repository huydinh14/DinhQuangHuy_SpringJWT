package com.huy.springbootjwt.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint{
	private static final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.error("Unauthorized error Message {}", authException);
		response.sendError(HttpServletResponse.SC_ACCEPTED,"Error -> Unauthorized");
	}
	
}
