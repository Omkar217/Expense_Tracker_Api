package com.pairlearning.Tracker_api.services;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;

public interface TransactionService {
	
	void saveCategoryInTrans(Category category, String description, double cateExpense, Transaction transaction);
	
	void saveUser(User authenticatedUser);
	
	Transaction getTransactions(String name);
	
	void saveSingleCategoryInTrans(Category category);

	void saveCate(Transaction transaction);

}
