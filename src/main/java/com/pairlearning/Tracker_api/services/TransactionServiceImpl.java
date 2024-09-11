package com.pairlearning.Tracker_api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.TransactionRepo;
import com.pairlearning.Tracker_api.repo.UserRepository;

@Service
public class TransactionServiceImpl implements TransactionService
{	
	@Autowired
	private TransactionRepo transRepo;
	
	@Autowired
	private UserRepository UserRepo;
		
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	@Override
	public Transaction getTransactions(String email) {
		
		Optional<User> userObj  =  UserRepo.findByEmail(email);
		
		int userId   =	 userObj.get().getUser_id();
		
		logger.info("Got transactions....");
		
		return transRepo.getByTransId(userId);		
		
	}

}
