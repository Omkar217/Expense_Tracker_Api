package com.pairlearning.Tracker_api.services;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.User;

public interface TransactionService {
	
	void saveCategoryInTrans(Category category);
	
	void saveUser(User authenticatedUser);

}
