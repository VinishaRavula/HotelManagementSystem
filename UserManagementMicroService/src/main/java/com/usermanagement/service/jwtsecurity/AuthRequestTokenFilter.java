package com.usermanagement.service.jwtsecurity;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.usermanagement.service.services.UserDetailsServiceImpl;



@Order
public class AuthRequestTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthRequestTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
 
			logger.info("Request: {}", request);
			String jwt = parseJwt(request);
			logger.info("Extracted JWT token: {}", jwt);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				
				String username = jwtUtils.getUserNameFromJwtToken(jwt);
				logger.info("Username: {}", username);

			
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);

				
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				logger.info("UserName Passwor Validation token : {}", authentication);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);

			} else {
				logger.debug("======= invalid token =======");
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Not Getting");
			return;
		}

		
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		
		logger.info("Request Header: {}", request.getHeader("Authorization"));
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			logger.debug("Header Name - " + headerName + ", Value - " + request.getHeader(headerName));
		}

		String headerAuth = request.getHeader("Authorization");

		if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
			
			return headerAuth.substring(7);
		}
		logger.info("Authorization header value: {}", headerAuth);
		return null;
	}

}


