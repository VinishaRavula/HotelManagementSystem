package com.usermanagement.service.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import com.usermanagement.service.jwtsecurity.JwtUtils;
import com.usermanagement.service.models.EnumRole;
import com.usermanagement.service.models.Roles;
import com.usermanagement.service.models.Users;
import com.usermanagement.service.payload.request.Login;
import com.usermanagement.service.payload.request.Signup;
import com.usermanagement.service.payload.response.JwtResponse;
import com.usermanagement.service.payload.response.MessageResponse;
import com.usermanagement.service.repo.RoleRepo;
import com.usermanagement.service.repo.UserRepo;
import com.usermanagement.service.services.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepo userRepository;

	@Autowired
	RoleRepo roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {
	
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	
		SecurityContextHolder.getContext().setAuthentication(authentication);

		
		String jwt = jwtUtils.generateJwtToken(authentication);
		logger.info(" JWT token: {}", jwt);


		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

	
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

	
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}


	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Signup signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

	
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

	
		Users user = new Users(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Roles> roles = new HashSet<>();

		
		if (strRoles == null || strRoles.isEmpty()) {
			
			Roles receptionistRole = roleRepository.findByName(EnumRole.RECEPTIONIST)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(receptionistRole);
		} else {
		
			for (String role : strRoles) {
				EnumRole enumRole = EnumRole.valueOf(role.toUpperCase());
				Roles userRole = roleRepository.findByName(enumRole)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			}
		}


		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}