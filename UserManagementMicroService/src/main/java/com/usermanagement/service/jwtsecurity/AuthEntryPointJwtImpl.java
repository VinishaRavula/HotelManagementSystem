package com.usermanagement.service.jwtsecurity;
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
public class AuthEntryPointJwtImpl implements AuthenticationEntryPoint {
	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwtImpl.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if (logger.isErrorEnabled()) {
		
			logger.error("Unauthorized error: {}", authException.getMessage());
		}
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}
}

