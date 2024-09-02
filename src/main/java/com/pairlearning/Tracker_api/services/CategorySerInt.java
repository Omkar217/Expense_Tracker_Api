package com.pairlearning.Tracker_api.services;

import java.util.List;

import com.pairlearning.Tracker_api.entity.Category;

public interface CategorySerInt {
	
	public Category saveCategory1(String title, String description, Double cateExpense ,String email);
	
	List<Category> getCatByUserService(String email);
	
	Category getCatByUserServiceById(String email,int catId);
	
}
