package com.pairlearning.Tracker_api.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService
{	
	private TransactionRepo transRepo;
	
	private UserDetailsService userDetailsService;
	
	private CategoryService catSer;
	
	public User user = new User();
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
	
	public  Category category = new Category();
	
	public Transaction transaction = new Transaction();
	
	@Override
	public void saveCategoryInTrans(Category category, String description, double catExpense, Transaction transaction) 
	{
//		transaction.setTime(LocalDateTime.now());
		transaction.setAmount(catExpense);
		transaction.setNote(description);
//		if(transaction.getSetOfCategories().isEmpty()) {
//		    transaction.getSetOfCategories().add(category);
//		}
//		else{
//		   transaction.getSetOfCategories().add(category);
//		}
	//	 transaction.setCategory(category);
	//	transRepo.save(transaction);
	}
	
	public void saveSingleCategoryInTrans(Category category)
	{
		String email = category.getUser().getEmail();
		User userObj  =  (User) userDetailsService.loadUserByUsername(email);
		transaction.setUser(userObj);
		transaction.setTime(LocalDateTime.now());
		transaction.setAmount(category.getCateExpense());
		transaction.setNote(category.getDescription());
		if(transaction.getSetOfCategories().isEmpty()) {
		    transaction.getSetOfCategories().add(category);
		}
		else{
		   transaction.getSetOfCategories().add(category);
		}
	//	 transaction.setCategory(category);
	//	transRepo.save(transaction);
	}

	@Override
	public void saveUser(User authenticatedUser) 
	{			
//		transaction.setUser(authenticatedUser);
			
	//	transRepo.save(transaction);		
	}

	@Override
	public Transaction getTransactions(String name) {
		
		User userObj  =  (User) userDetailsService.loadUserByUsername(name);
		
		int userId   =	 userObj.getUser_id();
		
		return transRepo.getByTransName(userId);		
	}

	@Override
	public void saveCate(Transaction transaction) {
		 
//		transRepo.save(transaction);
		
	}


}
