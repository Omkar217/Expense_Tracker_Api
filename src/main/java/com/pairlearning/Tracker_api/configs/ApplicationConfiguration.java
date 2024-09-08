package com.pairlearning.Tracker_api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.repo.UserRepository;
import com.pairlearning.Tracker_api.services.CategoryService;
import com.pairlearning.Tracker_api.services.TransactionService;

@Configuration
public class ApplicationConfiguration {
	
    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) 
    {
        this.userRepository = userRepository;
    }

    @Bean
    UserDetailsService userDetailsService() 
    {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean// Here Beans are used because hey specify they reflect that they are ready to use from spring application context....
    BCryptPasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception 
    {
        return config.getAuthenticationManager();
    }
    
//    @Bean
//    public CategoryService getCategoryService(TransactionService tranSer) {
//      return new CategoryService(tranSer);
//    }

    @Bean
    AuthenticationProvider authenticationProvider() 
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}