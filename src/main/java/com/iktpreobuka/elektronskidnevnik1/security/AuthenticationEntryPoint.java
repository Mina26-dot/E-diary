package com.iktpreobuka.elektronskidnevnik1.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.AuthenticationException;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void afterPropertiesSet() {
		setRealmName("DeveloperStack");
		super.afterPropertiesSet();
	}

	//@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException {
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}
	

//    private String realm;
//
//    public void setRealm(String realm) {
//        this.realm = realm;
//    }
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response,
//                         AuthenticationException authException) throws IOException, ServletException {
//        response.addHeader("WWW-Authenticate", "Basic realm=" + realm);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().println("HTTP Status 401 - Unauthorized: " + authException.getMessage());
//    }

}
