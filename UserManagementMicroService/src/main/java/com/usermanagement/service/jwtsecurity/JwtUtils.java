package com.usermanagement.service.jwtsecurity;

import java.util.Date;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.usermanagement.service.services.UserDetailsImpl;

import io.jsonwebtoken.*;


@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${vinisha.app.jwtSecret}")
	private String jwtSecret;

	@Value("${vinisha.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	
	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Date now = new Date();
	
		Date expiryDate = new Date(now.getTime() + jwtExpirationMs);		
		

		logger.info("UserPrincipal: {}", userPrincipal);

		String jwt = Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

		logger.info("Jwt at Generate : {}", jwt);

		return jwt;
	}


	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}


	public boolean validateJwtToken(String authToken) {
		try {
			Jws<Claims> jwt = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			if (logger.isInfoEnabled()) {
				logger.info("Jwt at Validate: {}", jwt);
			}
			return true;
		} catch (SignatureException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Invalid JWT signature: {}", e.getMessage());
			}
		} catch (MalformedJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Invalid JWT token: {}", e.getMessage());
			}
		} catch (ExpiredJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT token is expired: {}", e.getMessage());
			}
		} catch (UnsupportedJwtException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT token is unsupported: {}", e.getMessage());
			}
		} catch (IllegalArgumentException e) {
			if (logger.isErrorEnabled()) {
				logger.error("JWT claims string is empty: {}", e.getMessage());
			}
		}

		return false;
	}

}