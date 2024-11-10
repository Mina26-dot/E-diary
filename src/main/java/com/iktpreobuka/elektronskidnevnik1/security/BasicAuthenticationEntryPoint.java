package com.iktpreobuka.elektronskidnevnik1.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BasicAuthenticationEntryPoint {

	public void setRealm(String string) {
		// TODO Auto-generated method stub
		
	}

	//@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}
