package com.pairlearning.Tracker_api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.dtos.LoginUserDto;
import com.pairlearning.Tracker_api.dtos.RegisterUserDto;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.UserRepository;

@Service
public class AuthenticationService 
{
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public AuthenticationService(UserRepository userRepository, AuthenticationManager authenticationManager,PasswordEncoder passwordEncoder) 
    {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) 
    {
    	User user = new User();
    	user.setUser_id(input.getUser_id());
    	user.setFirst_name(input.getFirst_name());
    	user.setLast_name(input.getLast_name());
    	user.setEmail(input.getEmail());
    	user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}