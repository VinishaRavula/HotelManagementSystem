package com.usermanagement.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.service.models.Users;
import com.usermanagement.service.repo.UserRepo;


 

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        Users user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        
        logger.info("User Details :- {}", user);

       
        logger.info("User Details Impl:- {}", UserDetailsImpl.build(user));
        return UserDetailsImpl.build(user);
 
    }
}

