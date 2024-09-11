package com.pairlearning.Tracker_api.services;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;

public interface TransactionService {
	
	Transaction getTransactions(String email);
	
}
