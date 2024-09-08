package com.pairlearning.Tracker_api.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.pairlearning.Tracker_api.entity.Category;
import com.pairlearning.Tracker_api.entity.Transaction;
import com.pairlearning.Tracker_api.entity.User;
import com.pairlearning.Tracker_api.repo.CategoryRepo;
import com.pairlearning.Tracker_api.repo.TransactionRepo;
import com.pairlearning.Tracker_api.repo.UserRepository;
import com.pairlearning.Tracker_api.resources.AuthenticationController;

@Service
public class CategoryService implements CategorySerInt {
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	Transaction transaction = new Transaction();

	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TransactionRepo transRep;
		
	@Autowired
	private UserRepository userRep;
	
	@Autowired
	private TransactionService trans;
	
//	public CategoryService(TransactionService transSer) {
//		this.transSer = transSer;
//	}
	
	public Category saveTheCategory(String title, String description, Double catExpense, String email)
	{	
			User  userObj =  (User)userDetailsService.loadUserByUsername(email); 
				
			Category category = new Category();
									
			Double total = userObj.getTotalexpense();
						
			if(total == null) {
				userObj.setTotalexpense(catExpense);
			}else{
				total += catExpense;	
				userObj.setTotalexpense(total);
			}
							
			category.setUser(userObj);
		
			category.setTitle(title);
		
			category.setDescription(description);
			
			category.setCateExpense(catExpense);
				
			catRepo.save(category);

			userRep.save(userObj);
			
			logger.info("saved transactions");
			
			transaction.setTime(LocalDateTime.now());
			
			transaction.setAmount(catExpense);
			
			transaction.setNote(description);
			
			trans.saveCate(transaction);
																			
			logger.info("Saved the transactions...");
									
			return category;
	}
	
	public List<Category> getCatByUserService(String email)
	{
		User user1 =  (User) userDetailsService.loadUserByUsername(email); 
		
		Integer userId = user1.getUser_id();
		
		List<Category> catList = catRepo.findCategoriesByUserId(userId);
				
		return catList;
	}
	
	
	public Category getCatByUserServiceById(String email,int catId)
	{
		User user1 =  (User)userDetailsService.loadUserByUsername(email); 
		
		Integer userId = user1.getUser_id();
		
	    Category category = catRepo.findSpecificCategoryByUserId(userId, catId);
	    
	    return category;
	}

}
