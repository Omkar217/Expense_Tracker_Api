package com.pairlearning.Tracker_api.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.TransactionRepo;

@Service
public class TransactionServiceImpl implements  TransactionService{
	
	private TransactionRepo repo;
	
	private Transaction transaction = new Transaction();


	@Override
	public void saveCategoryInTrans(Category category) 
	{
		   if (transaction.getSetOfCategories().isEmpty()) {
		        transaction.setSetOfCategories(new HashSet<Category>());
		    }
		    transaction.getSetOfCategories().add(category);
		    
		    repo.save(transaction);
	}

	@Override
	public void saveUser(User authenticatedUser) 
	{	
		transaction.setUser(authenticatedUser);
		
		repo.save(transaction);		
	}

	

}
