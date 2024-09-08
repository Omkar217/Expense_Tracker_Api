package com.pairlearning.Tracker_api.services;

import java.util.List;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;

public interface CategorySerInt {
	
     Category saveTheCategory(String title, String description, Double cateExpense ,String email);
	
	List<Category> getCatByUserService(String email);
	
	Category getCatByUserServiceById(String email,int catId);
	
}
